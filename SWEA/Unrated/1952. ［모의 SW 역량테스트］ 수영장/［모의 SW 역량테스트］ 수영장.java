import java.io.*;
import java.util.*;

public class Solution {
    static int T, list[], day, month, months, year, ans;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();

        T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {

            input(); // 입력
            calcCost(); // 비용 계산하기

            sb.append("#").append(t).append(" ").append(ans).append("\n");
        }
        System.out.println(sb);
    }

    static void input() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());

        day = Integer.parseInt(st.nextToken());
        month = Integer.parseInt(st.nextToken());
        months = Integer.parseInt(st.nextToken());
        year = Integer.parseInt(st.nextToken());

        ans = 0; // 초기화
        list = new int[13];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= 12; i++)
            list[i] = Integer.parseInt(st.nextToken());
    }

    static void calcCost() {
        int dp[] = new int[13];
        dp[0] = 0;

        for (int i = 1; i <= 12; i++) {
            dp[i] = Math.min(dp[i - 1] + day * list[i], dp[i - 1] + month);
            if (i >= 3)
                dp[i] = Math.min(dp[i], dp[i - 3] + months);
        }

        dp[12] = Math.min(dp[12], year);
        ans = dp[12];
    }
}