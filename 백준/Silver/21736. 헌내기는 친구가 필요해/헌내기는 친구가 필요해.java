import java.io.*;
import java.util.*;

public class Main {
    static int N, M, ans;
    static char map[][];
    static boolean visited[][];
    static int dr[] = {-1, 0, 1, 0}, dc[] = {0, 1, 0, -1};
    static Queue<Pos> q = new LinkedList<>();

    static class Pos {
        int r, c;

        Pos(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    public static void main(String[] args) throws IOException {

        input(); // 입력
        bfs(); // 탐색

        System.out.println(ans == 0 ? "TT" : ans);
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new char[N][M];
        visited = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = str.charAt(j);
                if (map[i][j] == 'I') { // 시작점
                    visited[i][j] = true;
                    q.add(new Pos(i, j));
                }
            }
        }
    }

    static void bfs() {
        int cnt = 0;
        while (!q.isEmpty()) {
            Pos p = q.poll();

            for (int i = 0; i < 4; i++) {
                int nr = p.r + dr[i];
                int nc = p.c + dc[i];

                if (!isValid(nr, nc) || visited[nr][nc] || map[nr][nc] == 'X') continue; // 해당 칸으로 이동할 수 없는 경우 continue

                visited[nr][nc] = true;
                if (map[nr][nc] == 'P') cnt++; // 사람이면 count
                q.offer(new Pos(nr, nc));
            }
        }
        ans = cnt;
    }

    static boolean isValid(int r, int c) {
        return 0 <= r && r < N && 0 <= c && c < M;
    }
}