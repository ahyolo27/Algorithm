import java.io.*;
import java.util.*;

public class Main {
    static int R, C, ans;
    static char map[][];
    static boolean visited[][];
    static int dr[] = {-1, 0, 1, 0}, dc[] = {0, 1, 0, -1};
    static Queue<Pos> hedgehog = new LinkedList<>();
    static Queue<Pos> water = new LinkedList<>();

    static class Pos {
        int r, c;

        Pos(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    public static void main(String[] args) throws IOException {

        input(); // 입력
        simulate(); // 시뮬레이션 시작

        System.out.println(ans > 0 ? ans : "KAKTUS");
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new char[R][C];
        visited = new boolean[R][C];
        ans = 0; // 초기화

        for (int i = 0; i < R; i++) {
            String str = br.readLine();
            for (int j = 0; j < C; j++) {
                map[i][j] = str.charAt(j);
                if (map[i][j] == 'S') {// 시작 위치
                    hedgehog.offer(new Pos(i, j));
                    map[i][j] = '.';
                    visited[i][j] = true;
                } else if (map[i][j] == '*') // 물
                    water.offer(new Pos(i, j));
            }
        }
    }

    static void simulate() {
        int cnt = 0;

        while (!hedgehog.isEmpty()) { // 고슴도치가 더 이상 이동할 수 없는 경우 종료
            // 물 이동
            int qSize = water.size();
            for (int i = 0; i < qSize; i++) {
                Pos w = water.poll();

                for (int t = 0; t < 4; t++) {
                    int nr = w.r + dr[t];
                    int nc = w.c + dc[t];

                    if (isValid(nr, nc) && map[nr][nc] == '.') { // 물 번지기
                        map[nr][nc] = '*';
                        water.offer(new Pos(nr, nc));
                    }
                }
            }

            // 고슴도치 이동
            qSize = hedgehog.size();
            for (int i = 0; i < qSize; i++) {
                Pos h = hedgehog.poll();

                if (map[h.r][h.c] == 'D') { // 비버굴에 도착한 경우 종료
                    ans = cnt;
                    return;
                }

                for (int t = 0; t < 4; t++) {
                    int nr = h.r + dr[t];
                    int nc = h.c + dc[t];

                    if (isValid(nr, nc) && !visited[nr][nc] && (map[nr][nc] == '.' || map[nr][nc] == 'D')) {
                        visited[nr][nc] = true;
                        hedgehog.offer(new Pos(nr, nc));
                    }
                }
            }
            cnt++;
        }
    }

    static boolean isValid(int r, int c) { // 범위 내 값이면 true
        return 0 <= r && r < R && 0 <= c && c < C;
    }
}