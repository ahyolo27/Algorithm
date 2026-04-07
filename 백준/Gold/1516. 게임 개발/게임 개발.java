import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, degree[], weight[];
    static List<Integer> graph[];
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        input(); // 입력

        sort(); // 위상정렬

        System.out.println(sb);
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        graph = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++)
            graph[i] = new ArrayList<>();

        degree = new int[N + 1]; // 진입차수
        weight = new int[N + 1]; // 건물 짓는데 필요한 시간

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            weight[i] = Integer.parseInt(st.nextToken());

            while (true) {
                int prev = Integer.parseInt(st.nextToken());
                if (prev == -1) break;

                graph[prev].add(i);
                degree[i]++;
            }
        }
    }

    static void sort() {
        Queue<Integer> q = new LinkedList<>();

        // 0. 가중치 복사
        int dp[] = weight.clone();

        // 1. 진입차수 0인 노드 -> 큐 삽입
        for (int i = 1; i <= N; i++)
            if (degree[i] == 0) q.add(i);

        // 2. 큐의 노드로부터 이어진 노드 탐색 + 진입차수/DP 갱신
        while (!q.isEmpty()) {
            int now = q.poll();

            for (int next : graph[now]) {
                degree[next]--;
                dp[next] = Math.max(weight[next] + dp[now], dp[next]);

                if (degree[next] == 0) q.add(next);
            }
        }

        // 3. 건물 별 시간 출력
        sb = new StringBuilder();
        for (int i = 1; i <= N; i++)
            sb.append(dp[i]).append("\n");
    }
}