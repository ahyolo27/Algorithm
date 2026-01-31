import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N, M, graph[][];
    static final int INF = 10_000_000;
    static StringBuilder sb = new StringBuilder();


    public static void main(String[] args) throws IOException {

        input();
        floyd();
        count();

        System.out.println(sb);
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        graph = new int[N + 1][N + 1];
        for (int i = 0; i < graph.length; i++) {
            Arrays.fill(graph[i], INF);
            graph[i][i] = 0;
        }

        M = Integer.parseInt(br.readLine());
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph[a][b] = 1; // 단방향
        }

    }

    static void floyd() {
        for (int k = 0; k < N + 1; k++) {
            for (int i = 0; i < N + 1; i++) {
                for (int j = 0; j < N + 1; j++) {
                    if (graph[i][k] != INF && graph[k][j] != INF)
                        graph[i][j] = Math.min(graph[i][j], graph[i][k] + graph[k][j]);
                }
            }
        }
    }

    static void count() {
        int cnt[] = new int[N + 1];

        for (int i = 1; i < N + 1; i++) {
            for (int j = 1; j < N + 1; j++) {
                if (i != j && graph[i][j] == INF && graph[j][i] == INF)
                    cnt[i]++;
            }
        }

        for (int i = 1; i < N + 1; i++)
            sb.append(cnt[i]).append("\n");
    }
}