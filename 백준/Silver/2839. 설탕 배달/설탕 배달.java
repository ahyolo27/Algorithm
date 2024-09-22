import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException { // 예외처리
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        if (n < 5) {
            if (n == 3)
                System.out.println(1);
            else
                System.out.println(-1);
            return;
        }

        int dp[] = new int[n + 1];
        Arrays.fill(dp, 5000);

        dp[0] = 0;
        dp[3] = 1;
        dp[5] = 1;

        for (int i = 6; i <= n; i++)
            dp[i] = Math.min(dp[i - 3], dp[i - 5]) + 1;

        if (dp[n] > 5000)
            System.out.println(-1);
        else
            System.out.println(dp[n]);

    }
}