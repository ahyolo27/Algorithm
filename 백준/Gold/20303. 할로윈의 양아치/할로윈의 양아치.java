import java.util.*;
import java.io.*;

public class Main {
    static int N, M, K, candy[], ans;
    static boolean visited[];
    static List<Integer> graph[];
    static List<Knapsack> knapsacks = new ArrayList<>();

    static class Knapsack {
        int people, candies;

        public Knapsack(int people, int candies) {
            this.people = people;
            this.candies = candies;
        }
    }

    public static void main(String[] args) throws IOException {

        input(); // 입력
        getCandies(); // 사탕 빼앗기

        System.out.println(ans);
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        candy = new int[N + 1];
        visited = new boolean[N + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++)
            candy[i] = Integer.parseInt(st.nextToken());

        graph = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++)
            graph[i] = new ArrayList<>();

        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph[a].add(b); // 양방향
            graph[b].add(a);
        }
    }

    static void getCandies() {
        for (int i = 1; i <= N; i++) {
            if (!visited[i])
                bfs(i); // 배낭 만들기
        }

        calc(); // DP
    }

    static void bfs(int start) {
        Queue<Integer> q = new LinkedList<>();
        q.add(start);
        visited[start] = true;

        int peopleCnt = 1;
        int candiesCnt = candy[start];

        while (!q.isEmpty()) {
            Integer now = q.poll();
            for (int next : graph[now]) {
                if (!visited[next]) {
                    visited[next] = true;
                    peopleCnt++;
                    candiesCnt += candy[next];
                    q.add(next);
                }
            }
        }

        knapsacks.add(new Knapsack(peopleCnt, candiesCnt));
    }

    static void calc() {
        int dp[] = new int[K];
        final int INF = -Integer.MAX_VALUE;
        Arrays.fill(dp, INF);
        dp[0] = 0;

        for (Knapsack knapsack : knapsacks) {
            for (int k = K - 1; k >= knapsack.people; k--) {
                if (dp[k - knapsack.people] != INF)
                    dp[k] = Math.max(knapsack.candies + dp[k - knapsack.people], dp[k]);
            }
        }

        for (int value : dp)
            ans = Math.max(ans, value);
    }
}