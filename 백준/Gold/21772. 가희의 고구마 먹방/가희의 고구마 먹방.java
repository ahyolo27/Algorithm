import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int R, C, T, startR, startC, max;
    static char map[][];
    static boolean visited[][][];
    static int dr[] = {-1, 0, 1, 0}, dc[] = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {

        input();
        dfs(startR, startC, 0, 0);

        System.out.println(max);
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        map = new char[R][C];
        visited = new boolean[R][C][R * C]; // r * c * 고구마 개수

        for (int i = 0; i < R; i++) {
            String str = br.readLine();
            for (int j = 0; j < C; j++) {
                char c = str.charAt(j);

                if (c == 'G') { // 시작 지점
                    map[i][j] = '.';
                    visited[i][j][0] = true;
                    startR = i;
                    startC = j;
                } else
                    map[i][j] = c;
            }
        }
    }

    static void dfs(int r, int c, int time, int cnt) {

        if (time == T) {
            max = Math.max(max, cnt);
            return;
        }

        for (int i = 0; i < 4; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];

            if (!isValid(nr, nc)) continue;

            if (!visited[nr][nc][cnt] && map[nr][nc] == '.') {
                visited[nr][nc][cnt] = true;
                dfs(nr, nc, time + 1, cnt);
                visited[nr][nc][cnt] = false;
            } else if (!visited[nr][nc][cnt + 1] && map[nr][nc] == 'S') {
                visited[nr][nc][cnt + 1] = true;
                map[nr][nc] = '.';
                dfs(nr, nc, time + 1, cnt + 1);
                map[nr][nc] = 'S';
                visited[nr][nc][cnt + 1] = false;
            }
        }
        dfs(r, c, time + 1, cnt);
    }

    static boolean isValid(int r, int c) {
        return 0 <= r && r < R && 0 <= c && c < C;
    }
}