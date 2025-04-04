import java.io.*;
import java.util.*;

public class Main {
    static int T, N, M, dp[][];
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();

        T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            dp = new int[M + 1][N + 1];

            sb.append(combination(M, N)).append("\n");
        }
        System.out.println(sb);
    }

    static int combination(int m, int n) {
        if (dp[m][n] > 0) return dp[m][n];
        if (m == n || n == 0) return dp[m][n] = 1;

        return dp[m][n] = combination(m - 1, n - 1) + combination(m - 1, n);
    }
}