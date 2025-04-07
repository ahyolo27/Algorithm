import java.io.*;
import java.util.*;

public class Solution {
    static final int INF = Integer.MAX_VALUE;
    static int T, N, K, v[], c[], ans;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();

        T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            input(); // 입력
            knapsack();
            sb.append("#").append(t).append(" ").append(ans).append("\n");
        }

        System.out.println(sb);
    }

    static void input() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        v = new int[N + 1];
        c = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            v[i] = Integer.parseInt(st.nextToken());
            c[i] = Integer.parseInt(st.nextToken());
        }

        ans = 0; // 초기화
    }

    static void knapsack() {
        int dp[] = new int[K + 1];
        Arrays.fill(dp, -INF);
        dp[0] = 0;

        for (int i = 1; i <= N; i++) {
            for (int j = K; j >= v[i]; j--) {
                dp[j] = Math.max(dp[j], dp[j - v[i]] + c[i]);
            }
        }

        for (int val : dp)
            ans = Math.max(val, ans);
    }
}