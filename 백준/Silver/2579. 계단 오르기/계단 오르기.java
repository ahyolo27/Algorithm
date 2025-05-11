import java.util.*;
import java.io.*;

public class Main {
    static int N, steps[], ans;

    public static void main(String[] args) throws IOException {

        input(); // 입력
        calc(); // dp
        
        System.out.println(ans);
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        steps = new int[N + 1];
        for (int i = 1; i <= N; i++)
            steps[i] = Integer.parseInt(br.readLine());
    }

    static void calc() {

        /** 이렇게 했을 때의 문제점 : 연속 3칸인 경우를 배제할 수 없음 */
//        int dp[] = new int[N + 1];
//        dp[0] = 0;
//        dp[1] = steps[1];
//
//        for (int i = 2; i <= N; i++)
//            dp[i] = Math.max(dp[i - 1], dp[i - 2]) + steps[i];

        int dp[][] = new int[N + 1][3]; /** 해결 방안 : 상태를 나타낼 수 있도록 2차원 배열 사용 */
        dp[0][0] = dp[0][1] = 0;
        dp[1][1] = steps[1];

        for (int i = 2; i <= N; i++) {
            dp[i][1] = Math.max(dp[i - 2][1], dp[i - 2][2]) + steps[i]; // 전전칸에서 2칸 오기
            dp[i][2] = dp[i - 1][1] + steps[i]; // 전칸에서 1칸 오기
        }

        ans = Math.max(dp[N][1], dp[N][2]);
    }
}