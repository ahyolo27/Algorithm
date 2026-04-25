import java.util.*;

class Solution {
    public int solution(int n, int s, int a, int b, int[][] fares) {
        int answer = Integer.MAX_VALUE;

        // 간선 만들기
        int map[][] = new int[n + 1][n + 1];
        for (int m[] : map)
            Arrays.fill(m, Integer.MAX_VALUE);
        for (int fare[] : fares) {
            map[fare[0]][fare[1]] = fare[2];
            map[fare[1]][fare[0]] = fare[2];
        }

        // 다익스트라
        int distS[] = dijkstra(s, n, map);
        int distA[] = dijkstra(a, n, map);
        int distB[] = dijkstra(b, n, map);

        for (int k = 1; k <= n; k++)
            answer = Math.min(answer, distS[k] + distA[k] + distB[k]); // s->k, k->a, k->b

        return answer;
    }

    int[] dijkstra(int start, int nodeCnt, int map[][]) {
        int weight[] = new int[nodeCnt + 1];
        Arrays.fill(weight, Integer.MAX_VALUE);

        Queue<Node> pq = new PriorityQueue<>((o1, o2) -> o1.w - o2.w);
        pq.add(new Node(start, 0));
        weight[start] = 0;

        while (!pq.isEmpty()) {
            Node now = pq.poll();

            if (now.w > weight[now.idx]) continue; // 갱신 필요 X

            for (int next = 1; next <= nodeCnt; next++) {
                if (map[now.idx][next] == Integer.MAX_VALUE) continue;

                if (weight[next] > weight[now.idx] + map[now.idx][next]) {
                    weight[next] = weight[now.idx] + map[now.idx][next];
                    pq.add(new Node(next, weight[next]));
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