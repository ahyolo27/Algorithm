import java.io.*;
import java.util.*;

public class Main {
    static int N, M, ans;
    static char map[][];
    static boolean visited[][][][];
    static int dr[] = {-1, 0, 1, 0}, dc[] = {0, 1, 0, -1};
    static Queue<Pos> q1 = new LinkedList<>();
    static Queue<Pos> q2 = new LinkedList<>();

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

        System.out.println(ans);
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new char[N][M];
        visited = new boolean[N][M][N][M];

        int r1 = 0;
        int r2 = 0;
        int c1 = 0;
        int c2 = 0;
        int idx = 0;

        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = str.charAt(j);
                if (map[i][j] == 'o') {
                    if (idx++ == 0) {
                        q1.offer(new Pos(i, j));
                        r1 = i;
                        c1 = j;
                    } else {
                        q2.offer(new Pos(i, j));
                        r2 = i;
                        c2 = j;
                    }
                    map[i][j] = '.';
                }
            }
        }
        visited[r1][c1][r2][c2] = true;

        ans = -1; // 초기화
    }

    static void bfs() {
        int time = 1;

        while (time <= 10) {
            int size = q1.size();

            for (int i = 0; i < size; i++) {
                Pos p1 = q1.poll();
                Pos p2 = q2.poll();

                for (int t = 0; t < 4; t++) {
                    int nr1 = p1.r + dr[t];
                    int nc1 = p1.c + dc[t];
                    int nr2 = p2.r + dr[t];
                    int nc2 = p2.c + dc[t];

                    if (isValid(nr1, nc1) && isValid(nr2, nc2)) { // 하나도 안 떨어지는 경우

                        if (visited[nr1][nc1][nr2][nc2]) // 이미 방문한 경우 continue
                            continue;

                        if (map[nr1][nc1] == '.' && map[nr2][nc2] == '.') { // 둘다 이동할 수 있는 경우
                            visited[nr1][nc1][nr2][nc2] = true;
                            q1.offer(new Pos(nr1, nc1));
                            q2.offer(new Pos(nr2, nc2));
                        } else if (map[nr1][nc1] == '.' && map[nr2][nc2] == '#') { // 1번 동전만 이동할 수 있는 경우
                            visited[nr1][nc1][p2.r][p2.c] = true;
                            q1.offer(new Pos(nr1, nc1));
                            q2.offer(new Pos(p2.r, p2.c));
                        } else if (map[nr1][nc1] == '#' && map[nr2][nc2] == '.') { // 2번 동전만 이동할 수 있는 경우
                            visited[p1.r][p1.c][nr2][nc2] = true;
                            q1.offer(new Pos(p1.r, p1.c));
                            q2.offer(new Pos(nr2, nc2));
                        }

                    } else if ((!isValid(nr1, nc1) && isValid(nr2, nc2)) || (isValid(nr1, nc1) && !isValid(nr2, nc2))) { // 하나만 떨어지는 경우
                        ans = time;
                        return;
                    }
                }
            }

            time++;
        }
    }

    static boolean isValid(int r, int c) {
        return 0 <= r && r < N && 0 <= c && c < M;
    }
}