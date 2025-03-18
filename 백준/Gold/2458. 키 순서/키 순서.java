import java.io.*;
import java.util.*;

public class Main {
    static int N, M, cnt, ans;
    static boolean visited[];
    static List<Integer> graph[];
    static List<Integer> reverseGraph[];

    public static void main(String[] args) throws IOException {

        input(); // 입력
        find();

        System.out.println(ans);
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        graph = new ArrayList[N + 1];
        reverseGraph = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
            reverseGraph[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph[a].add(b);
            reverseGraph[b].add(a); // 역방향 그래프
        }
    }

    static void find() {
        for (int i = 1; i <= N; i++) {
            int sum = 0;

            visited = new boolean[N + 1];
            cnt = 0;
            dfs(i, graph);
            sum += cnt;

            visited = new boolean[N + 1];
            cnt = 0;
            dfs(i, reverseGraph);
            sum += cnt;

            if (sum == N - 1) // 자신 제외 모든 학생과 비교 가능한 경우 count
                ans++;
        }
    }

    static void dfs(int start, List<Integer> g[]) {
        visited[start] = true;
        for (int node : g[start]) {
            if (!visited[node]) {
                cnt++;
                dfs(node, g);
            }
        }
    }
}