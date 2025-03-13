import java.io.*;
import java.util.*;

public class Main {
    static int R, C, ans;
    static char map[][];
    static boolean visited[][], isArrived;
    static int dr[] = {-1, 0, 1, 0}, dc[] = {0, 1, 0, -1};
    static Queue<Pos> hedgehog = new LinkedList<>();
    static Queue<Pos> water = new LinkedList<>();

    static class Pos {
        int r, c, cnt;

        Pos(int r, int c, int cnt) {
            this.r = r;
            this.c = c;
            this.cnt = cnt;
        }
    }

    public static void main(String[] args) throws IOException {

        input(); // 입력
        move(); // 이동 시작

        if (ans < 0) // ans = -1 이면 도착하지 못하고 갇힌 경우
            System.out.println("KAKTUS");
        else
            System.out.println(ans);
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new char[R][C];
        visited = new boolean[R][C];
        ans = 0;

        for (int i = 0; i < R; i++) {
            String str = br.readLine();
            for (int j = 0; j < C; j++) {
                map[i][j] = str.charAt(j);
                if (map[i][j] == 'S') {  // 고슴도치 시작점
                    hedgehog.offer(new Pos(i, j, 0));
                    map[i][j] = '.';
                    visited[i][j] = true;
                } else if (map[i][j] == '*') // 물 시작점
                    water.offer(new Pos(i, j, 0));
            }
        }
    }

    static void move() {
        // 고슴도치 이동
        while (true) {
            setWater(); // 홍수 일으키기

            int len = hedgehog.size();

            while (len > 0) {
                Pos p = hedgehog.poll();

                for (int i = 0; i < 4; i++) {
                    int nr = p.r + dr[i];
                    int nc = p.c + dc[i];

                    if (check(nr, nc) && !visited[nr][nc] && map[nr][nc] == '.') { // 이동할 수 있으면 이동
                        visited[nr][nc] = true;
                        hedgehog.offer(new Pos(nr, nc, p.cnt + 1));
                    } else if (check(nr, nc) && !visited[nr][nc] && map[nr][nc] == 'D') { // 도착하면 종료
                        ans = p.cnt + 1;
                        return;
                    }
                }
                if (hedgehog.isEmpty())  // 사방이 막혀 움직일 수 없는 경우
                    break;
                len--;
            }

            if (hedgehog.isEmpty()) // 더이상 움직일 수 없는 경우
                break;
        }
        ans = -1;
    }

    static void setWater() {
        int len = water.size();

        while (len > 0) {
            Pos p = water.poll();

            for (int i = 0; i < 4; i++) {
                int nr = p.r + dr[i];
                int nc = p.c + dc[i];

                if (check(nr, nc) && map[nr][nc] == '.') {
                    water.offer(new Pos(nr, nc, 0));
                    map[nr][nc] = '*';
                }
            }
            len--;
        }
    }

    static boolean check(int r, int c) {
        return 0 <= r && r < R && 0 <= c && c < C;
    }
}