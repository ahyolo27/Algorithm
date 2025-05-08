import java.io.*;
import java.util.*;

public class Main {
    static final int INF = 500000;
    static int N, M, weight[][], cnt[];

    public static void main(String[] args) throws IOException {
        input(); // 입력
        floyd(); // 플로이드 워셜 탐색
        count(); // 케빈 베이컨의 수 구하기
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        weight = new int[N + 1][N + 1];
        cnt = new int[N + 1];
        for (int i = 1; i <= N; i++)
            Arrays.fill(weight[i], INF);

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            weight[a][b] = weight[b][a] = 1;
        }
    }

    static void floyd() {
        for (int k = 1; k <= N; k++)
            for (int i = 1; i <= N; i++)
                for (int j = 1; j <= N; j++)
                    weight[i][j] = Math.min(weight[i][j], weight[i][k] + weight[k][j]);
    }

    static void count() {
        int min = INF;
        for (int i = 1; i <= N; i++) {
            int sum = 0;
            for (int j = 1; j <= N; j++) {
                if (i == j) continue;
                sum += weight[i][j];
            }
            cnt[i] = sum;
            min = Math.min(sum, min);
        }

        for (int i = 1; i <= N; i++) {
            if (cnt[i] == min) {
                System.out.println(i);
                return;
            }
        }
    }
}