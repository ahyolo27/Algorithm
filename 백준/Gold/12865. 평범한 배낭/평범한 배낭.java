import java.io.*;
import java.util.*;

public class Main {
    static final int INF = Integer.MAX_VALUE;
    static int N, K, w[], v[], ans;

    public static void main(String[] args) throws IOException {

        input(); // 입력
        knapsack(); // 가방

        System.out.println(ans);
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        w = new int[N];
        v = new int[N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            w[i] = Integer.parseInt(st.nextToken());
            v[i] = Integer.parseInt(st.nextToken());
        }

        ans = 0; // 초기화
    }

    static void knapsack() {
        int dp[] = new int[K + 1];
        Arrays.fill(dp, -INF);
        dp[0] = 0;

        for (int i = 0; i < N; i++)
            for (int j = K; j >= w[i]; j--)
                dp[j] = Math.max(dp[j], dp[j - w[i]] + v[i]);

        for (int val : dp) {
            ans = Math.max(val, ans);
        }
    }
}