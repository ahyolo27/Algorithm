import java.util.*;
import java.io.*;

public class Main {
    static int N;
    static int T[], P[], dp[];

    public static void main(String[] args) throws IOException {

        input(); // 입력
        findMax(); // 최대 수익 구하기

        System.out.println(dp[N]);
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        T = new int[N];
        P = new int[N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            T[i] = Integer.parseInt(st.nextToken());
            P[i] = Integer.parseInt(st.nextToken());
        }
    }

    static void findMax() {
        dp = new int[N + 1];
        for (int i = 0; i < N; i++) {
            if (i + T[i] <= N)  // 회사에 있는 날에 한해 계산
                dp[i + T[i]] = Math.max(dp[i + T[i]], dp[i] + P[i]);
            dp[i + 1] = Math.max(dp[i + 1], dp[i]);
        }
    }
}