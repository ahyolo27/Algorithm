import java.io.*;
import java.util.*;

public class Main {
    static int N, M, V, map[][];
    static boolean visited[];

    public static void main(String[] args) throws IOException {
        input(); // 입력

        dfs(V);
        System.out.println();
        visited = new boolean[N + 1]; // 초기화
        
        bfs();
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 정점 개수
        M = Integer.parseInt(st.nextToken()); // 간선 개수
        V = Integer.parseInt(st.nextToken()); // 탐색 시작 번호

        map = new int[N + 1][N + 1];
        visited = new boolean[N + 1];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            map[a][b] = map[b][a] = 1;
        }
    }

    static void dfs(int p) {
        visited[p] = true;
        System.out.print(p + " ");

        for (int n = 1; n <= N; n++) {
            if (map[p][n] == 1 && !visited[n])
                dfs(n);
        }
    }

    static void bfs() {
        Queue<Integer> q = new LinkedList<>();

        q.offer(V); // 시작점
        visited[V] = true;

        while (!q.isEmpty()) {
            Integer p = q.poll();
            System.out.print(p + " ");

            for (int n = 1; n <= N; n++)
                if (map[p][n] == 1 && !visited[n]) {
                    q.offer(n);
                    visited[n] = true;
                }
        }
    }
}
