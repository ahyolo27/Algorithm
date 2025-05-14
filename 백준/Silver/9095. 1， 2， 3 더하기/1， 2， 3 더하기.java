import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            int N = Integer.parseInt(br.readLine());
            int dp[] = new int[11];

            dp[0] = 0;
            dp[1] = 1;
            dp[2] = 2;
            dp[3] = 4;
            
            if (N <= 3)
                sb.append(dp[N]).append("\n");
            else {
                for (int i = 4; i <= N; i++)
                    dp[i] = dp[i - 3] + dp[i - 2] + dp[i - 1];
                sb.append(dp[N]).append("\n");
            }
        }

        System.out.println(sb);
    }
}