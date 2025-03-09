import java.io.*;
import java.util.*;

public class Main {
    static int N, noCnt, yesCnt;
    static String map[][];
    static int dr[] = {-1, 0, 1, 0}, dc[] = {0, 1, 0, -1};

    static class Pos {
        int r, c;

        Pos(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    public static void main(String[] args) throws IOException {
        input(); // 입력

        noBfs(); // noCnt
        yesBfs(); // yesCnt

        System.out.println(noCnt + " " + yesCnt);
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        map = new String[N][N];

        for (int i = 0; i < N; i++)
            map[i] = br.readLine().split("");
    }

    static void noBfs() {
        Queue<Pos> q = new LinkedList<>();
        boolean visited[][] = new boolean[N][N];
        String now = "";

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!visited[i][j]) {
                    now = map[i][j];
                    q.offer(new Pos(i, j));
                    visited[i][j] = true;
                    noCnt++;

                    while (!q.isEmpty()) {
                        Pos p = q.poll();

                        for (int k = 0; k < 4; k++) {
                            int nr = p.r + dr[k];
                            int nc = p.c + dc[k];
                            if (check(nr, nc) && !visited[nr][nc] && map[nr][nc].equals(now)) {
                                visited[nr][nc] = true;
                                q.offer(new Pos(nr, nc));
                            }
                        }
                    }
                }
            }
        }
    }

    static void yesBfs() {
        Queue<Pos> q = new LinkedList<>();
        boolean visited[][] = new boolean[N][N];
        int type = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                type = setType(map[i][j]);
                if (!visited[i][j]) {
                    q.offer(new Pos(i, j));
                    visited[i][j] = true;
                    yesCnt++;

                    while (!q.isEmpty()) {
                        Pos p = q.poll();

                        for (int k = 0; k < 4; k++) {
                            int nr = p.r + dr[k];
                            int nc = p.c + dc[k];
                            if (check(nr, nc) && !visited[nr][nc] && type == setType(map[nr][nc])) {
                                visited[nr][nc] = true;
                                q.offer(new Pos(nr, nc));
                            }
                        }
                    }
                }
            }
        }
    }

    static int setType(String s) {
        if (s.equals("B"))
            return 2;
        else return 1;
    }

    static boolean check(int r, int c) {
        return 0 <= r && r < N && 0 <= c && c < N;
    }
}