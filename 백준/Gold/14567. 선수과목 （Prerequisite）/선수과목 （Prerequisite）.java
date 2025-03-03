import java.io.*;
import java.util.*;

public class Main {
    static int N, M, dp[];
    static List<Integer>[] list;
    static boolean visited[];
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {

        input(); // 입력
        calc(); // 계산

        System.out.println(sb);
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        list = new ArrayList[N + 1]; // 인접 리스트 초기화
        for (int i = 1; i < N + 1; i++)
            list[i] = new ArrayList<>();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            list[B].add(A); // B의 선수과목 리스트에 A를 추가
        }
    }

    static void calc() {
        dp = new int[N + 1]; // i번째 과목을 들을 수 있는 학기
        visited = new boolean[N + 1];

        for (int i = 1; i < N + 1; i++)
            if (!visited[i])
                dfs(i);

        for (int i = 1; i < N + 1; i++)
            sb.append(dp[i]).append(" ");
    }

    static int dfs(int n) {
        if (visited[n]) return dp[n]; // 이미 방문한 노드

        visited[n] = true;
        dp[n] = 1; // 디폴트 값

        for (int i = 0; i < list[n].size(); i++) // 선행과목 확인
            dp[n] = Math.max(dp[n], dfs(list[n].get(i)) + 1); // 먼저 들어야 하는 과목들의 필요 학기 + 1

        return dp[n];
    }
}