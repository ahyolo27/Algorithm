import java.io.*;
import java.util.*;

public class Solution {
    static int T, N, map[][], startR, startC, max;
    static int dr[] = {1, 1, -1, -1}, dc[] = {1, -1, -1, 1};
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();

        T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {

            input(); // 입력
            move(); // 이동 - 최대 사각형 찾기

            sb.append("#").append(t).append(" ").append(max).append("\n");
        }

        System.out.println(sb);
    }

    static void input() throws IOException {
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        max = -1;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++)
                map[i][j] = Integer.parseInt(st.nextToken());
        }
    }

    static void move() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                startR = i;
                startC = j;
                boolean visited[] = new boolean[101];
                visited[map[i][j]] = true;
                dfs(i, j, 0, 1, visited);
            }
        }
    }

    static void dfs(int r, int c, int dir, int cnt, boolean visited[]) {
        for (int i = dir; i < 4; i++) { // 시계방향 오른쪽 아래부터 시작
            int nr = r + dr[i];
            int nc = c + dc[i];

            if (nr == startR && nc == startC && cnt >= 4) { // 다시 돌아왔을 때에 cnt 갱신 후 종료
                max = Math.max(cnt, max);
                return;
            }

            if (check(nr, nc) && !visited[map[nr][nc]]) { // 중복 아니고 첫방문하는 곳이면 dfs()
                visited[map[nr][nc]] = true;
                dfs(nr, nc, i, cnt + 1, visited);
                visited[map[nr][nc]] = false;
            }
        }
    }

    static boolean check(int r, int c) {
        return 0 <= r && r < N && 0 <= c && c < N;
    }
}