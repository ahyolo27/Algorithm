import java.util.*;
import java.io.*;

public class Main {
    static int n, m, ans;
    static int list[][];
    static boolean visited[][];
    static int dx[] = {0, 0, -1, 1};
    static int dy[] = {-1, 1, 0, 0};

    static class Pos {
        int x, y, score;

        Pos(int x, int y, int score) {
            this.x = x;
            this.y = y;
            this.score = score;
        }
    }

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        list = new int[n][m];
        visited = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            String s = br.readLine();
            for (int j = 0; j < m; j++)
                list[i][j] = s.charAt(j) - '0';
        }

        bfs(0, 0);

        System.out.println(ans);
    }

    static void bfs(int x, int y) {
        Queue<Pos> q = new LinkedList<>();
        q.add(new Pos(x, y, 1)); // 시작점 세팅

        while (!q.isEmpty()) {
            Pos p = q.poll();

            if (p.x == m - 1 && p.y == n - 1) {
                ans = p.score;
                return;
            }

            for (int i = 0; i < 4; i++) { // 이동
                int nx = p.x + dx[i];
                int ny = p.y + dy[i];

                if (valid(nx, ny) && list[ny][nx] == 1 && !visited[ny][nx]) {
                    q.add(new Pos(nx, ny, p.score + 1));
                    visited[ny][nx] = true;
                }
            }
        }
    }

    static boolean valid(int x, int y) {
        return 0 <= x && x < m && 0 <= y && y < n;
    }
}