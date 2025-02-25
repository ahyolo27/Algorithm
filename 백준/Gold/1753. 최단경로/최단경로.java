import java.io.*;
import java.util.*;

public class Main {
    static int V, E, K, weight[];
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
            return this.w - node.w; // 우선순위 큐 정렬
        }
    }

    public static void main(String[] args) throws IOException {

        input(); // 입력
        dijkstra(K);

        for (int i = 1; i < weight.length; i++) {
            if (weight[i] == INF)
                sb.append("INF").append("\n");
            else
                sb.append(weight[i]).append("\n");
        }

        System.out.println(sb);
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(br.readLine());

        graph = new List[V + 1];
        weight = new int[V + 1];
        for (int i = 1; i < V + 1; i++) {
            graph[i] = new ArrayList<>();
            weight[i] = INF;
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            graph[u].add(new Node(v, w));
        }
    }

    static void dijkstra(int start) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(start, 0));
        weight[start] = 0;

        while (!pq.isEmpty()) {
            Node node = pq.poll();
            int u = node.v; // 다음 노드

            for (int i = 0; i < graph[u].size(); i++) {
                Node next = graph[u].get(i);
                if (weight[next.v] > weight[u] + next.w) { // 갱신할 가중치가 더 작은 경우 갱신
                    weight[next.v] = weight[u] + next.w;
                    pq.add(new Node(next.v, weight[next.v])); // 다음 노드 추가
                }
            }
        }
    }
}