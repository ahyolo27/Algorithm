import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, person[], max;

    public static void main(String[] args) throws IOException {

        input(); // 입력

        calc(); // 계산

        System.out.println(N - max);
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        person = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++)
            person[i] = Integer.parseInt(st.nextToken());
    }

    static void calc() {
        int dp[] = new int[N];
        dp[0] = 1;

        for (int i = 1; i < N; i++) {
            dp[i] = 1; // 자신
            for (int j = 0; j < i; j++) {
                if (person[i] < person[j])
                    dp[i] = Math.max(dp[i], dp[j] + 1);
            }
        }

        for (int i = 0; i < N; i++)
            max = Math.max(dp[i], max);
    }
}