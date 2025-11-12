import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // input
        int N = Integer.parseInt(br.readLine());
        long energy[] = new long[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++)
            energy[i] = Integer.parseInt(st.nextToken());

        // dp
        long dp[][] = new long[2][N];

        final int YES = 0, NO = 1;
        dp[YES][0] = energy[0];
        dp[NO][0] = 0;

        for (int i = 0; i < N - 1; i++) {
            dp[NO][i + 1] = dp[YES][i];
            dp[YES][i + 1] = Math.min(dp[YES][i], dp[NO][i]) + energy[i + 1];
        }

        // output
        System.out.println(Math.min(dp[YES][N - 1], dp[NO][N - 1]));
    }
}