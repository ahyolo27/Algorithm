import java.io.*;
import java.util.*;

public class Main {
    static int R, C, maxTime;
    static char map[][];
    static int dr[] = {-1, 0, 1, 0}, dc[] = {0, 1, 0, -1};
    static Queue<Pos> q = new LinkedList<>();
    static boolean visited[][];

    static class Pos {
        int r, c, time;

        Pos(int r, int c, int time) {
            this.r = r;
            this.c = c;
            this.time = time;
        }
    }

    public static void main(String[] args) throws IOException {

        input(); // 입력

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++)
                if (map[i][j] == 'L')
                    simulate(i, j);
        }

        System.out.println(maxTime);
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new char[R][C];
        for (int i = 0; i < R; i++) {
            String str = br.readLine();
            for (int j = 0; j < C; j++)
                map[i][j] = str.charAt(j);
        }

        visited = new boolean[R][C];
    }

    static void simulate(int r, int c) {
        int time = 0;

        q.add(new Pos(r, c, 0));
        visited[r][c] = true;

        while (!q.isEmpty()) {
            Pos p = q.poll();

            maxTime = Math.max(p.time, maxTime);

            for (int i = 0; i < 4; i++) {
                int nr = p.r + dr[i];
                int nc = p.c + dc[i];

                if (!isValid(nr, nc) || visited[nr][nc] || map[nr][nc] == 'W') continue;

                visited[nr][nc] = true;
                q.add(new Pos(nr, nc, p.time + 1));
            }
        }

        for (int i = 0; i < R; i++)
            Arrays.fill(visited[i], false); // 초기화
    }

    static boolean isValid(int r, int c) {
        return 0 <= r && r < R && 0 <= c && c < C;
    }
}