import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M, K, Q[], answer;
    static long D[], P[];
    static PriorityQueue<Integer> pq = new PriorityQueue<>(((o1, o2) -> o2 - o1));

    public static void main(String[] args) throws IOException {

        input();

        for (int i = 0; i < N; i++) // DP
            calc(i);

        for (int i = 0; i < M; i++)
            answer += pq.poll();

        System.out.println(answer);
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        D = new long[N];
        P = new long[K];
        Q = new int[K];

        for (int i = 0; i < N; i++) {
            D[i] = Long.parseLong(br.readLine());
        }

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            P[i] = Long.parseLong(st.nextToken());
            Q[i] = Integer.parseInt(st.nextToken());
        }
    }

    static void calc(int num) { // 캐릭터 번호: num
        long damage = D[num];

        int dp[] = new int[901]; // 15분 -> 초당 얻을 수 있는 최대 메소

        dp[0] = 0;

        for (int i = 0; i < K; i++) { // 각 보스 별 순회

            if (P[i] > damage * 60 * 15) continue; // 못잡는 보스

            int time = (int) (P[i] / damage); // 잡는데 걸리는 시간
            if (P[i] % damage != 0) time++;

            for (int j = dp.length - 1; j >= time; j--) {
                dp[j] = Math.max(dp[j], dp[j - time] + Q[i]); // 지금거 vs 전거 + 새거
            }
        }

        int max = 0;
        for (int money : dp)
            max = Math.max(max, money);

        pq.add(max);
    }
}