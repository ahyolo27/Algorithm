import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        String s1 = br.readLine();
        String s2 = br.readLine();

        int dp[][] = new int[s1.length() + 1][s2.length() + 1];

        for (int i = 1; i < s1.length() + 1; i++) {
            for (int j = 1; j < s2.length() + 1; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1))
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                else
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
            }
        }

        int i = s1.length();
        int j = s2.length();
        int now = dp[i][j];

        while (now != 0) {
            if (dp[i - 1][j] == now)
                i--;
            else if (dp[i][j - 1] == now)
                j--;
            else {
                sb.append(s1.charAt(i - 1));
                i--;
                j--;
            }
            now = dp[i][j];
        }

        System.out.println(sb.reverse());
    }
}