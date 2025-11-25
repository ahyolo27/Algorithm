import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int R, C, answer;
    static int dr[] = {-1, 0, 1, 0}, dc[] = {0, 1, 0, -1};
    static char map[][];
    static boolean visited[][];
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

        System.out.println(answer);
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new char[R][C];
        visited = new boolean[R][C];

        for (int i = 0; i < R; i++) {
            String s = br.readLine();
            for (int j = 0; j < C; j++) {
                map[i][j] = s.charAt(j);
                if (q.isEmpty() && map[i][j] == '.') {
                    q.add(new Pos(i, j)); // 임의의 시작점
                    visited[i][j] = true;
                }
            }
        }

        answer = 0; // 초기화
    }

    static void bfs() {
        // 막다른 길 정의 ----> 세 방향으로 이동 불가한 경우

        while (!q.isEmpty()) {
            Pos p = q.poll();

            int unreachableCnt = 0;

            for (int i = 0; i < 4; i++) {
                int nr = p.r + dr[i];
                int nc = p.c + dc[i];

                if (!isValid(nr, nc) || map[nr][nc] == 'X') // 이동할 수 없는 경우
                    unreachableCnt++;

                if (isValid(nr, nc) && map[nr][nc] == '.' && !visited[nr][nc]) {
                    visited[nr][nc] = true;
                    q.add(new Pos(nr, nc));
                }
            }

            if (unreachableCnt == 3) {
                answer = 1;
                return;
            }
        }
    }

    static boolean isValid(int r, int c) {
        return 0 <= r && r < R && 0 <= c && c < C;
    }
}