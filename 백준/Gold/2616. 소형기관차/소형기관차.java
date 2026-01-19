import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M, trains[];

    public static void main(String[] args) throws IOException {
        input(); // 입력

        solution(); // 구간합 + DP
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        trains = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++)
            trains[i] = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(br.readLine());
    }

    static void solution() {
        // 1. 누적합 만들기
        int sum[] = new int[N + 1];
        for (int i = 1; i <= N; i++)
            sum[i] = sum[i - 1] + trains[i - 1];

        // 2. 누적합으로 구간합 만들기
        int w[] = new int[N + 1];
        for (int i = M; i <= N; i++)
            w[i] = sum[i] - sum[i - M]; // i에서 끝나는 M칸 구간합

        // 3. DP
        int dp[][] = new int[N + 1][4]; // i번째 객차까지 기관차 j대를 이용해 얻을 수 있는 최대 승객 수
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= 3; j++) {
                dp[i][j] = dp[i - 1][j]; // 기관차 안 쓰는 경우
                if (i >= M)
                    dp[i][j] = Math.max(dp[i][j], dp[i - M][j - 1] + w[i]); // vs 기관차 쓰는 경우
            }
        }
        System.out.println(dp[N][3]);
    }
}