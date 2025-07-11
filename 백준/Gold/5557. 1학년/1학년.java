import java.util.*;
import java.io.*;

public class Main {
    static int N, nums[];
    static long ans;

    public static void main(String[] args) throws IOException {

        input(); // 입력
        calc();

        System.out.println(ans);
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        nums = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++)
            nums[i] = Integer.parseInt(st.nextToken());
    }

    static void calc() {
        long dp[][] = new long[N][21];
        dp[0][nums[0]] = 1;

        for (int i = 1; i < N - 1; i++) {
            for (int j = 0; j <= 20; j++) {
                if (dp[i - 1][j] > 0) { // 가능한 경우
                    if (j + nums[i] <= 20)
                        dp[i][j + nums[i]] += dp[i - 1][j];
                    if (j - nums[i] >= 0)
                        dp[i][j - nums[i]] += dp[i - 1][j];
                }
            }
        }
        ans = dp[N - 2][nums[N - 1]];
    }
}