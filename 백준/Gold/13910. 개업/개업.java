import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // input
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int woks[] = new int[M];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++)
            woks[i] = Integer.parseInt(st.nextToken());

        // solution
        // 1. 사용할 수 있는 용량 아이템 만들기
        Set<Integer> list = new HashSet<>(); // 중복 방지

        for (int i = 0; i < M; i++) {
            list.add(woks[i]); // 한손
            for (int j = i + 1; j < M; j++)
                list.add(woks[i] + woks[j]); // 양손
        }

        // 2. 중복 가능한 배낭 문제
        int dp[] = new int[N + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;

        for (int wok : list) {
            for (int i = 0; i <= N; i++) {
                if (i + wok <= N && dp[i] != Integer.MAX_VALUE)
                    dp[i + wok] = Math.min(dp[i + wok], dp[i] + 1);
            }
        }

        // output
        System.out.println(dp[N] == Integer.MAX_VALUE ? -1 : dp[N]);
    }
}