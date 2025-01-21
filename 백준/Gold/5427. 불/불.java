import java.util.*;
import java.io.*;

public class Main {
    static int w, h, ans;
    static int map[][]; // 이동 가능 여부를 나타내는 맵
    static boolean visited[][];
    static Queue<Pos> fire = new LinkedList<>();
    static Queue<Pos> person = new LinkedList<>();
    static int dr[] = {-1, 1, 0, 0};
    static int dc[] = {0, 0, -1, 1};

    static class Pos {
        int r, c, cnt;

        Pos(int r, int c, int cnt) {
            this.r = r;
            this.c = c;
            this.cnt = cnt;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int t = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < t; tc++) {
            st = new StringTokenizer(br.readLine());
            w = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());
            map = new int[h][w];
            visited = new boolean[h][w];

            // 맵 세팅
            for (int i = 0; i < h; i++) {
                String s = br.readLine();
                for (int j = 0; j < w; j++) {
                    if (s.charAt(j) == '#') // 벽
                        map[i][j] = -1;
                    else if (s.charAt(j) == '*') { // 불
                        map[i][j] = 1;
                        fire.add(new Pos(i, j, 0));
                    } else if (s.charAt(j) == '@') { // 상근
                        map[i][j] = 0;
                        visited[i][j] = true;
                        person.add(new Pos(i, j, 0)); // 큐에 시작 위치 삽입
                    } else // 빈 공간
                        map[i][j] = 0;
                }
            }

            // 이동
            move();
            fire.clear();
            person.clear();

            if (ans == 0)
                System.out.println("IMPOSSIBLE");
            else
                System.out.println(ans);
        }
    }

    static void move() {
        ans = 0;

        while (true) {
            int len = person.size();
            if (len == 0) {
                break;
            }

            setFire();

            while (len != 0) {
                Pos p = person.poll();

                if (p.r == 0 || p.r == h - 1 || p.c == 0 || p.c == w - 1) { // 현재 위치가 가장자리이면 탈출 성공
                    ans = p.cnt + 1;
                    return;
                }

                for (int i = 0; i < 4; i++) {
                    int nr = p.r + dr[i];
                    int nc = p.c + dc[i];

                    if (check(nr, nc) && map[nr][nc] == 0 && !visited[nr][nc]) { // 이동
                        visited[nr][nc] = true;
                        person.add(new Pos(nr, nc, p.cnt + 1));
                    }
                }
                len--;
            }
        }
    }

    static void setFire() {
        int len = fire.size(); // 한번 이동하는 동안만 계산

        while (len != 0) {
            Pos p = fire.poll();

            // 상하좌우 탐색 - 범위 내 값이고 빈 공간인 경우 불 붙이기
            for (int i = 0; i < 4; i++) {
                int nr = p.r + dr[i];
                int nc = p.c + dc[i];

                if (check(nr, nc) && map[nr][nc] == 0) {
                    map[nr][nc] = 1;
                    fire.add(new Pos(nr, nc, 0));
                }
            }
            len--;
        }
    }


    static boolean check(int r, int c) {
        return 0 <= r && r < h && 0 <= c && c < w;
    }
}