import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, num[][], answer;
    static char command[][];

    public static void main(String[] args) throws IOException {
        input(); // 입력
        play();

        System.out.println(answer <= 0 ? "ddong game" : answer);
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        num = new int[N][2];
        command = new char[N][2];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 2; j++) {
                String s = st.nextToken();
                command[i][j] = s.charAt(0);
                num[i][j] = s.charAt(1) - '0';
            }
        }
    }

    static void play() {
        int dp[][] = new int[N + 1][2];
        dp[0][0] = 1; // 초기값

        for (int i = 1; i <= N; i++) {
            // skip 한 경우
            if (dp[i - 1][0] > 0 && dp[i - 1][1] > 0)
                dp[i][1] = Math.max(dp[i - 1][0], Math.max(calc(dp[i - 1][1], num[i - 1][0], command[i - 1][0]), calc(dp[i - 1][1], num[i - 1][1], command[i - 1][1])));
            else if (dp[i - 1][0] > 0)
                dp[i][1] = dp[i - 1][0];
            else if (dp[i - 1][1] > 0)
                dp[i][1] = Math.max(calc(dp[i - 1][1], num[i - 1][0], command[i - 1][0]), calc(dp[i - 1][1], num[i - 1][1], command[i - 1][1]));

            // skip 안한 경우
            if (dp[i - 1][0] > 0)
                dp[i][0] = Math.max(calc(dp[i - 1][0], num[i - 1][0], command[i - 1][0]), calc(dp[i - 1][0], num[i - 1][1], command[i - 1][1]));

            // game-over이 되는 경우
            if (dp[i][0] <= 0 && dp[i][1] <= 0) {
                answer = -1;
                return;
            }
        }

        answer = Math.max(dp[N][0], dp[N][1]);
    }

    static int calc(int a, int b, char c) {
        switch (c) {
            case '+':
                return a + b;
            case '-':
                return a - b;
            case '*':
                return a * b;
            case '/':
                return a / b;
        }
        return -1;
    }
}