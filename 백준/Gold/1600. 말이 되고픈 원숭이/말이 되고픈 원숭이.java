import java.io.*;
import java.util.*;

public class Main {
    static int K, W, H, map[][];
    static boolean visited[][][];
    static int dr[] = {-1, 0, 1, 0};
    static int dc[] = {0, 1, 0, -1};
    static int kr[] = {-2, -1, 1, 2, 2, 1, -1, -2};
    static int kc[] = {1, 2, 2, 1, -1, -2, -2, -1};
    static Queue<Pos> q = new LinkedList<>();

    static class Pos {
        int r, c, cnt, k;

        Pos(int r, int c, int cnt, int k) {
            this.r = r;
            this.c = c;
            this.cnt = cnt;
            this.k = k;
        }
    }

    public static void main(String[] args) throws IOException {

        input(); // 입력

        System.out.println(move());
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        K = Integer.parseInt(br.readLine()); // 말처럼 움직일 수 있는 횟수

        st = new StringTokenizer(br.readLine());
        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        map = new int[H][W];
        visited = new boolean[H][W][K + 1];

        for (int i = 0; i < H; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < W; j++)
                map[i][j] = Integer.parseInt(st.nextToken());
        }
    }

    static int move() {
        visited[0][0][0] = true; // 초기값 설정
        q.offer(new Pos(0, 0, 0, 0));

        while (!q.isEmpty()) {
            Pos p = q.poll();

            if (p.r == H - 1 && p.c == W - 1) // 도착한 경우 종료
                return p.cnt;

            for (int i = 0; i < 4; i++) {
                int nr = p.r + dr[i]; // 원숭이
                int nc = p.c + dc[i];

                if (check(nr, nc) && !visited[nr][nc][p.k] && map[nr][nc] == 0) {
                    visited[nr][nc][p.k] = true;
                    q.add(new Pos(nr, nc, p.cnt + 1, p.k));
                }
            }
            if (p.k < K) { // 말 이동 횟수가 남은 경우
                for (int i = 0; i < 8; i++) {
                    int nr = p.r + kr[i]; // 말
                    int nc = p.c + kc[i];

                    if (check(nr, nc) && !visited[nr][nc][p.k + 1] && map[nr][nc] == 0) {
                        visited[nr][nc][p.k + 1] = true;
                        q.add(new Pos(nr, nc, p.cnt + 1, p.k + 1));
                    }
                }
            }
        }
        return -1;
    }

    static boolean check(int r, int c) {
        return 0 <= r && r < H && 0 <= c && c < W;
    }
}