import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {
            st = new StringTokenizer(br.readLine());

            char s1[] = st.nextToken().toCharArray();
            char s2[] = st.nextToken().toCharArray();
            char target[] = st.nextToken().toCharArray();

            boolean dp[][] = new boolean[s1.length + 1][s2.length + 1]; // i+j개를 가지고 target에서 i+j까지 만들 수 있는가
            dp[0][0] = true;

            for (int i = 0; i <= s1.length; i++) {
                for (int j = 0; j <= s2.length; j++) {
                    if (dp[i][j]) { // 확장 가능한 경우 -> 같은 문자일 때 확장
                        int idx = i + j;
                        if (i < s1.length && s1[i] == target[idx])
                            dp[i + 1][j] = true;
                        if (j < s2.length && s2[j] == target[idx])
                            dp[i][j + 1] = true;
                    }
                }
            }
            
            sb.append("Data set ").append(t).append(": ").append(dp[s1.length][s2.length] ? "yes" : "no").append("\n");
        }

        System.out.println(sb);
    }
}