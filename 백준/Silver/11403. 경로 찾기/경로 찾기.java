import java.util.*;
import java.io.*;

public class Main {
    static int N, len[], graph[][];
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
        len = new int[N + 1];
        graph = new int[N + 1][N + 1];

        for (int i = 1; i < N + 1; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j < N + 1; j++)
                graph[i][j] = Integer.parseInt(st.nextToken());
        }
    }

    static void floyd(int graph[][], int n) {
        for (int k = 1; k <= n; k++)
            for (int i = 1; i <= n; i++)
                for (int j = 1; j <= n; j++)
                    if (graph[i][k] == 1 && graph[k][j] == 1) // 중간 지점을 통해 이어져 있는 경우 연결되었다고 표시
                        graph[i][j] = 1;

        for (int i = 1; i < graph.length; i++) {
            for (int j = 1; j < graph.length; j++)
                sb.append(graph[i][j]).append(" ");
            sb.append("\n");
        }
    }
}