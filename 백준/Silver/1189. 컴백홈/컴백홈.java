import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int R, C, K, ans;
    static int dr[] = {-1, 0, 1, 0}, dc[] = {0, 1, 0, -1};
    static char map[][];

    public static void main(String[] args) throws IOException {
        input();

        boolean visited[][] = new boolean[R][C];
        visited[R - 1][0] = true;
        dfs(R - 1, 0, visited, 1);

        System.out.println(ans);
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new char[R][C];
        for (int i = 0; i < R; i++) {
            String s = br.readLine();
            for (int j = 0; j < C; j++)
                map[i][j] = s.charAt(j);
        }
    }

    static void dfs(int r, int c, boolean visited[][], int cnt) {
        if (r == 0 && c == C - 1 && cnt == K) {
            ans++;
            return;
        }

        for (int i = 0; i < 4; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];

            if (nr < 0 || R <= nr || nc < 0 || C <= nc) continue;
            if (visited[nr][nc] || map[nr][nc] == 'T') continue;

            visited[nr][nc] = true;
            dfs(nr, nc, visited, cnt + 1);
            visited[nr][nc] = false;
        }
    }
}