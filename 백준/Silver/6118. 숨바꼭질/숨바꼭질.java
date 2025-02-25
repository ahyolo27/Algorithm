import java.util.*;
import java.io.*;

public class Main {
    static int N, M, weight[];
    static final int INF = Integer.MAX_VALUE;
    static List<Node> graph[];
    static StringBuilder sb = new StringBuilder();

    static class Node implements Comparable<Node> {
        int v, w;

        Node(int v, int w) {
            this.v = v;
            this.w = w;
        }

        @Override
        public int compareTo(Node node) {
            return this.w - node.w;
        }
    }

    public static void main(String[] args) throws IOException {

        input(); // 입력
        dijkstra();
        find();

        System.out.println(sb);
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        graph = new List[N + 1];
        weight = new int[N + 1];
        for (int i = 1; i < N + 1; i++) {
            graph[i] = new ArrayList<>();
            weight[i] = INF;
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph[a].add(new Node(b, 1)); // 양방향으로 연결
            graph[b].add(new Node(a, 1));
        }
    }

    static void dijkstra() {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(1, 0));
        weight[1] = 0;

        while (!pq.isEmpty()) {
            Node node = pq.poll();
            int u = node.v; // 다음 노드

            if (node.w > weight[u]) continue;

            for (int i = 0; i < graph[u].size(); i++) {
                Node next = graph[u].get(i);
                if (weight[next.v] > weight[u] + next.w) { // 가중치 갱신
                    weight[next.v] = weight[u] + next.w;
                    pq.add(new Node(next.v, weight[next.v]));
                }
            }
        }
    }

    static void find() {
        int max = 0;
        int idx = 0;
        int cnt = 0;
        for (int i = 1; i < weight.length; i++) {
            if (weight[i] != INF && weight[i] > max) { // INF 값이 아닌 것 중에 최댓값과 그 위치 찾기
                max = weight[i];
                idx = i;
            }
        }
        for (int i = 1; i < weight.length; i++) // 최댓값 개수 세기
            if (weight[i] == max)
                cnt++;

        sb.append(idx).append(" ").append(max).append(" ").append(cnt);
    }
}