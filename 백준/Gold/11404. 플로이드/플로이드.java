import java.util.*;
import java.io.*;

public class Main {
    static int N, M;
    static long graph[][];
    static final int INF = Integer.MAX_VALUE;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        input(); // 입력
        floyd(graph, N);
        System.out.println(sb);
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        graph = new long[N + 1][N + 1];
        for (int i = 1; i < N + 1; i++) {
            Arrays.fill(graph[i], INF);
            graph[i][i] = 0; // 시작 도시와 도착 도시가 같은 경우는 없음
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            graph[a][b] = Math.min(graph[a][b], c);
        }
    }

    static void floyd(long graph[][], int n) {
        for (int k = 1; k <= n; k++)
            for (int i = 1; i <= n; i++)
                for (int j = 1; j <= n; j++)
                    graph[i][j] = Math.min(graph[i][j], graph[i][k] + graph[k][j]);

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (graph[i][j] == INF)
                    sb.append(0).append(" ");
                else
                    sb.append(graph[i][j]).append(" ");
            }
            sb.append("\n");
        }
    }
}