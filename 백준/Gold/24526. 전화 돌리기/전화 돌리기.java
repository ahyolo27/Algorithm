import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M, isPossible[], ans;
    static List<Integer> graph[];
    static final int PENDING = 0, IN_DFS = 1, SAFE = 2, CYCLE = 3;

    public static void main(String[] args) throws IOException {
        input(); // 입력

        for (int i = 1; i <= N; i++) {
            if (isPossible[i] != PENDING) continue;

            dfs(i); // 사이클 찾기
        }
        for (int i = 1; i <= N; i++) {
            if (isPossible[i] == SAFE)
                ans++;
        }

        System.out.println(ans);
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        graph = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++)
            graph[i] = new ArrayList<>();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            graph[a].add(b);
        }

        // init
        isPossible = new int[N + 1]; // 불가능한 시작점
        Arrays.fill(isPossible, PENDING);
    }

    static boolean dfs(int now) {
        if (isPossible[now] == SAFE) return true;
        else if (isPossible[now] == CYCLE) return false;
        else if (isPossible[now] == IN_DFS) {
            isPossible[now] = CYCLE;
            return false;
        }

        if (graph[now].isEmpty()) {
            isPossible[now] = SAFE;
            return true;
        }

        isPossible[now] = IN_DFS;

        boolean flag = true;
        for (int next : graph[now]) {
            if (!dfs(next)) {
                flag = false;
                break;
            }
        }
        isPossible[now] = flag ? SAFE : CYCLE;
        return flag;
    }
}