import java.io.*;
import java.util.*;

public class Solution {
    static int T, N, map[][], DAY, max;
    static int dr[] = {-1, 0, 1, 0}, dc[] = {0, 1, 0, -1};
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static class Pos {
        int r, c;

        Pos(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();

        T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {

            input(); // 입력
            eat(); // 치즈 먹기

            sb.append("#").append(t).append(" ").append(max).append("\n");
        }

        System.out.println(sb);
    }

    static void input() throws IOException {
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        DAY = -1;
        max = -1;


        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                DAY = Math.max(DAY, map[i][j]);
            }
        }
    }

    static void eat() {
        for (int day = 0; day <= DAY; day++)
            bfs(day);
    }

    static void bfs(int day) {
        boolean visited[][] = new boolean[N][N];
        Queue<Pos> q = new LinkedList<>();
        int cnt = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {

                if (!visited[i][j] && map[i][j] > day) {
                    q.add(new Pos(i, j));
                    visited[i][j] = true;

                    while (!q.isEmpty()) {
                        Pos p = q.poll();

                        for (int t = 0; t < 4; t++) {
                            int nr = p.r + dr[t];
                            int nc = p.c + dc[t];
                            if (check(nr, nc) && !visited[nr][nc] && map[nr][nc] > day) {
                                visited[nr][nc] = true;
                                q.add(new Pos(nr, nc));
                            }
                        }
                    }

                    cnt++;
                }
            }
        }

        max = Math.max(cnt, max);
    }

    static boolean check(int r, int c) {
        return 0 <= r && r < N && 0 <= c && c < N;
    }
}