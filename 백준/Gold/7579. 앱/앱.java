import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N, M, a[], c[], limit;
    static long ans;

    public static void main(String[] args) throws IOException {

        input(); // 입력
        deactivate(); // 앱 비활성화

        System.out.println(ans);
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        a = new int[N + 1];
        c = new int[N + 1];

        limit = 0; // 초기화
        ans = Integer.MAX_VALUE;

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            a[i] = Integer.parseInt(st.nextToken());
            limit += a[i];
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++)
            c[i] = Integer.parseInt(st.nextToken());
    }

    static void deactivate() {
        long dp[] = new long[limit + 1];
        Arrays.fill(dp, 10000001);
        dp[0] = 0;

        for (int i = 1; i <= N; i++)
            for (int j = limit; j >= a[i]; j--)
                dp[j] = Math.min(dp[j], dp[j - a[i]] + c[i]);

        for (int i = M; i <= limit; i++)
            ans = Math.min(dp[i], ans);
    }
}