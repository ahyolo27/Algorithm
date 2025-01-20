import java.util.*;
import java.io.*;

public class Main {
    static int m, n, h, ans;
    static int tomatoes[][][];
    static boolean visited[][][];
    static Queue<Pos> q = new LinkedList<>();
    static int dx[] = {0, 0, -1, 1, 0, 0};
    static int dy[] = {-1, 1, 0, 0, 0, 0};
    static int dz[] = {0, 0, 0, 0, -1, 1};

    static class Pos {
        int x, y, z, day;

        Pos(int x, int y, int z, int day) {
            this.x = x;
            this.y = y;
            this.z = z;
            this.day = day;
        }
    }

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());
        tomatoes = new int[h][n][m];
        visited = new boolean[h][n][m];

        for (int i = 0; i < h; i++) {
            for (int j = 0; j < n; j++) {
                st = new StringTokenizer(br.readLine());
                for (int k = 0; k < m; k++) {
                    tomatoes[i][j][k] = Integer.parseInt(st.nextToken());
                    if (tomatoes[i][j][k] == 1) {
                        q.add(new Pos(k, j, i, 0)); // 처음부터 익어있는 토마토 (시작점)
                        visited[i][j][k] = true;
                    }
                }
            }
        }

        bfs();

        for (int i = 0; i < h; i++)
            for (int j = 0; j < n; j++)
                for (int k = 0; k < m; k++)
                    if (tomatoes[i][j][k] == 0) { // 탐색 후에도 안 익은 토마토가 있다면
                        System.out.println(-1);
                        return;
                    }
        System.out.println(ans);
    }

    static void bfs() {
        while (!q.isEmpty()) {
            Pos p = q.poll();

            for (int i = 0; i < 6; i++) { // 이동
                int nx = p.x + dx[i];
                int ny = p.y + dy[i];
                int nz = p.z + dz[i];

                if (valid(nx, ny, nz) && tomatoes[nz][ny][nx] == 0 && !visited[nz][ny][nx]) {
                    q.add(new Pos(nx, ny, nz, p.day + 1));
                    visited[nz][ny][nx] = true;
                    tomatoes[nz][ny][nx] = 1;
                }
            }
            ans = p.day;
        }
    }

    static boolean valid(int x, int y, int z) {
        return 0 <= x && x < m && 0 <= y && y < n && 0 <= z && z < h;
    }
}