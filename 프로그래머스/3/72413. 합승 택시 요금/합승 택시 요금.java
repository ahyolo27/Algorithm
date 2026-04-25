import java.util.*;

class Solution {
    public int solution(int n, int s, int a, int b, int[][] fares) {
        final int INF = Integer.MAX_VALUE;
        int answer = INF;

        // 간선 만들기
        ArrayList<Node> graph[] = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++)
            graph[i] = new ArrayList<>();
        for (int fare[] : fares) {
            graph[fare[0]].add(new Node(fare[1], fare[2]));
            graph[fare[1]].add(new Node(fare[0], fare[2]));
        }

        // 다익스트라
        int distS[] = dijkstra(s, n, graph);
        int distA[] = dijkstra(a, n, graph);
        int distB[] = dijkstra(b, n, graph);

        for (int k = 1; k <= n; k++) {
            if (distS[k] == INF || distA[k] == INF || distB[k] == INF) continue;
            answer = Math.min(answer, distS[k] + distA[k] + distB[k]); // s->k, k->a, k->b
        }

        return answer;
    }

    int[] dijkstra(int start, int nodeCnt, ArrayList<Node> graph[]) {
        int weight[] = new int[nodeCnt + 1];
        Arrays.fill(weight, Integer.MAX_VALUE);

        Queue<Node> pq = new PriorityQueue<>((o1, o2) -> o1.w - o2.w);
        pq.add(new Node(start, 0));
        weight[start] = 0;

        while (!pq.isEmpty()) {
            Node now = pq.poll();

            if (now.w > weight[now.idx]) continue; // 갱신 필요 X

            for (Node next : graph[now.idx]) {
                if (weight[next.idx] > weight[now.idx] + next.w) {
                    weight[next.idx] = weight[now.idx] + next.w;
                    pq.add(new Node(next.idx, weight[next.idx]));
                }
            }
        }
        return weight;
    }

    class Node {
        int idx, w;

        Node(int idx, int w) {
            this.idx = idx;
            this.w = w;
        }
    }
}