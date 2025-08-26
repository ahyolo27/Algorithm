import java.util.*;
import java.io.*;

public class Main {
    static int N, M, start, end, weight[];
    static List<Edge> graph[];

    static class Edge {
        int node, weight;

        Edge(int node, int weight) {
            this.node = node;
            this.weight = weight;
        }
    }

    public static void main(String[] args) throws IOException {
        input(); // 입력
        dijkstra();
        System.out.println(weight[end]);
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        graph = new ArrayList[N + 1];
        for (int i = 0; i <= N; i++)
            graph[i] = new ArrayList<>();

        weight = new int[N + 1];
        Arrays.fill(weight, Integer.MAX_VALUE);

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            graph[a].add(new Edge(b, w)); // 단방향
        }

        st = new StringTokenizer(br.readLine());
        start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());
    }

    static void dijkstra() {
        PriorityQueue<Edge> pq = new PriorityQueue<>((o1, o2) -> o1.weight - o2.weight);
        pq.add(new Edge(start, 0));
        weight[start] = 0;

        while (!pq.isEmpty()) {
            Edge n = pq.poll();

            if (weight[n.node] < n.weight) continue;

            for (Edge next: graph[n.node]) {
                if (weight[next.node] > weight[n.node] + next.weight) {
                    weight[next.node] = weight[n.node] + next.weight;
                    pq.add(new Edge(next.node, weight[next.node]));
                }
            }
        }
    }
}