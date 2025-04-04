import java.io.*;
import java.util.*;

public class Solution {
    static final int INF = 1000000;
    static int T, N, M, graph[][], cnt;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();

        T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {

            input(); // 입력
            floyd(); // 플로이드 워셜
            count(); // 키 순서를 알 수 있는 학생 수 세기

            sb.append("#").append(t).append(" ").append(cnt).append("\n");
        }
        System.out.println(sb);
    }

    static void input() throws IOException {
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        graph = new int[N + 1][N + 1];
        for (int i = 0; i < N + 1; i++) {
            Arrays.fill(graph[i], INF);
            graph[i][i] = 0;
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph[a][b] = 1;
        }
    }

    static void floyd() {
        for (int k = 1; k <= N; k++) {
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    graph[i][j] = Math.min(graph[i][j], graph[i][k] + graph[k][j]);
                }
            }
        }
    }

    static void count() {
        cnt = 0; // 초기화

        for (int i = 1; i <= N; i++) {
            int known = 0;

            for (int j = 1; j <= N; j++) {
                if (i == j) continue; // 자기 자신은 continue

                if (graph[i][j] != INF || graph[j][i] != INF) // 관계가 있으면
                    known++;
            }

            if (known == N - 1) // 자기 자신 빼고 다 관계를 아는 경우
                cnt++;
        }
    }
}