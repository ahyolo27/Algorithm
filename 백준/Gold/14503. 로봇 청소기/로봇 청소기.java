import java.util.*;
import java.io.*;

public class Main {
    static int N, M, r, c, dir, ans;
    static boolean map[][], visited[][];
    static int dr[] = {-1, 0, 1, 0}, dc[] = {0, 1, 0, -1};

    static class Pos {
        int r, c, dir;

        Pos(int r, int c, int dir) {
            this.r = r;
            this.c = c;
            this.dir = dir;
        }
    }

    public static void main(String[] args) throws IOException {
        input();
        simulate(r, c, dir);
        System.out.println(ans);
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new boolean[N][M];
        visited = new boolean[N][M];

        st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        dir = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++)
                map[i][j] = Integer.parseInt(st.nextToken()) == 0;
        }
    }

    static void simulate(int r, int c, int dir) {
        Queue<Pos> q = new LinkedList<>();
        q.add(new Pos(r, c, dir));
        visited[r][c] = true;
        ans = 1;

        while (!q.isEmpty()) {
            Pos p = q.poll();

            if (isRotate(p.r, p.c)) { // 청소되지 않은 빈칸이 있는 경우
                int nDir = (p.dir + 3) % 4; // 반시계 90도 회전
                int nr = p.r + dr[nDir];
                int nc = p.c + dc[nDir];
                if (isValid(nr, nc) && map[nr][nc] && !visited[nr][nc]) { // 앞쪽이 청소되지 않은 빈칸인 경우 전진
                    visited[nr][nc] = true;
                    ans++;
                    q.add(new Pos(nr, nc, nDir));
                } else { // 앞쪽이 청소된 칸인 경우
                    q.add(new Pos(p.r, p.c, nDir));
                }
            } else { // 청소되지 않은 빈칸이 없는 경우
                int nr = p.r - dr[p.dir];
                int nc = p.c - dc[p.dir];
                if (isValid(nr, nc) && map[nr][nc]) { // 후진할 수 있다면 후진
                    q.add(new Pos(nr, nc, p.dir));
                } else { // 벽인 경우 종료
                    return;
                }
            }
        }
    }

    static boolean isRotate(int r, int c) { // 주변에 청소할 수 있는 칸이 있는 경우 true
        for (int i = 0; i < 4; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];
            if (isValid(nr, nc) && map[nr][nc] && !visited[nr][nc]) {
                return true;
            }
        }
        return false;
    }

    static boolean isValid(int r, int c) {
        return 0 <= r && r < N && 0 <= c && c < M;
    }
}