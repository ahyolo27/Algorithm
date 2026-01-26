import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M;
    static long weight[];
    static final int INF = 100_000_000;
    static ArrayList<Edge> edges;

    static class Edge {
        int a, b, w;

        Edge(int a, int b, int w) {
            this.a = a;
            this.b = b;
            this.w = w;
        }
    }

    public static void main(String[] args) throws IOException {
        input(); // 입력

        bellmanFord();
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        edges = new ArrayList<>();
        weight = new long[N + 1];
        Arrays.fill(weight, INF);

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            edges.add(new Edge(a, b, c));
        }
    }

    static void bellmanFord() {
        int start = 1;
        weight[start] = 0;

        for (int i = 1; i < N + 1; i++) {
            for (int j = 0; j < M; j++) {
                Edge edge = edges.get(j);

                if (weight[edge.a] != INF)
                    weight[edge.b] = Math.min(weight[edge.b], weight[edge.a] + edge.w);
            }
        }

        // 사이클 확인
        for (Edge edge : edges) {
            if (weight[edge.a] != INF && weight[edge.b] > weight[edge.a] + edge.w) {
                System.out.println(-1);
                return;
            }
        }
        
        StringBuilder sb = new StringBuilder();
        for (int i = 2; i < N + 1; i++)
            sb.append(weight[i] == INF ? -1 : weight[i]).append("\n");
        System.out.println(sb);
    }
}