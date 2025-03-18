import java.io.*;
import java.util.*;

public class Main {
    static final int INF = 10000000;
    static int N, M, dist[][], ans;

    public static void main(String[] args) throws IOException {

        input(); // 입력
        floyd();

        System.out.println(ans);
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        dist = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (i == j) dist[i][j] = 0;
                else dist[i][j] = INF;
            }
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            dist[a][b] = 1;
        }
    }

    static void floyd() {
        for (int k = 1; k <= N; k++) {
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    if (dist[i][k] != INF && dist[k][j] != INF)
                        dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]); // 갱신
                }
            }
        }

        for (int i = 1; i <= N; i++) {
            int cnt = 0;
            for (int j = 1; j <= N; j++)
                if (dist[i][j] != INF || dist[j][i] != INF) // 둘 중 하나의 관계만 명확해도 둘 간의 관계를 알 수 있음
                    cnt++;
            if (cnt == N) ans++;
        }
    }
}