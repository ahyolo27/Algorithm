import java.util.*;
import java.io.*;

public class Main {
    static int N, M, cnt;
    static char map[][];
    static int dr[] = {-1, 0, 1, 0}, dc[] = {0, 1, 0, -1}; // 홀수: 가로 | 짝수: 세로
    static Queue<Pos> q = new LinkedList<>();
    static boolean visited[][];

    static class Pos {
        int r, c;

        Pos(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    public static void main(String[] args) throws IOException {
        input(); // 입력
        move(); // 이동+탐색

        System.out.println(cnt);
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new char[N][M];
        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < M; j++)
                map[i][j] = str.charAt(j);
        }

        cnt = 0; // 초기화
        visited = new boolean[N][M];
    }

    static void move() {
        for (int i = 0; i < N; i++)
            for (int j = 0; j < M; j++)
                if (!visited[i][j])
                    bfs(i, j);
    }

    static void bfs(int r, int c) {
        q.clear();

        if (map[r][c] == '-') {
            q.add(new Pos(r, c));
            visited[r][c] = true;

            while (!q.isEmpty()) {
                Pos p = q.poll();

                for (int i = 1; i < 4; i += 2) {
                    int nr = p.r + dr[i];
                    int nc = p.c + dc[i];

                    if (!isValid(nr, nc) || visited[nr][nc]) continue; // 범위 밖 값

                    if (map[nr][nc] == '-') { // 같은 판자만 확인
                        visited[nr][nc] = true;
                        q.add(new Pos(nr, nc));
                    }
                }
            }
        } else if (map[r][c] == '|') {
            q.add(new Pos(r, c));
            visited[r][c] = true;

            while (!q.isEmpty()) {
                Pos p = q.poll();

                for (int i = 0; i < 4; i += 2) {
                    int nr = p.r + dr[i];
                    int nc = p.c + dc[i];

                    if (!isValid(nr, nc) || visited[nr][nc]) continue; // 범위 밖 값

                    if (map[nr][nc] == '|') { // 같은 판자만 확인
                        visited[nr][nc] = true;
                        q.add(new Pos(nr, nc));
                    }
                }
            }
        }

        cnt++;
    }

    static boolean isValid(int r, int c) {
        return 0 <= r && r < N && 0 <= c && c < M;
    }
}