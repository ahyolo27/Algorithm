import java.io.*;
import java.util.*;

public class Solution {
    static int T, H, W, N, r, c, dir;
    static String words[], map[][];
    static int dr[] = {-1, 0, 1, 0}, dc[] = {0, 1, 0, -1};
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {
            input(); // 입력
            move(); // 이동

            sb.append("#").append(t).append(" ");
            output(); // 출력
        }
        System.out.println(sb);
    }

    static void input() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        map = new String[H][W];
        for (int i = 0; i < H; i++) {
            String str = br.readLine();
            map[i] = str.split("");
            if (str.contains("^")) { // 탱크 위치 잡기
                r = i;
                c = str.indexOf("^");
                dir = 0;
            } else if (str.contains(">")) {
                r = i;
                c = str.indexOf(">");
                dir = 1;
            } else if (str.contains("v")) {
                r = i;
                c = str.indexOf("v");
                dir = 2;
            } else if (str.contains("<")) {
                r = i;
                c = str.indexOf("<");
                dir = 3;
            }
        }
        N = Integer.parseInt(br.readLine());
        words = br.readLine().split("");
    }

    static void move() {
        for (int idx = 0; idx < N; idx++) {
            String s = words[idx];
            setBehavior(s);
        }
    }

    static void setBehavior(String w) {
        switch (w) {
            case "U":
                up();
                break;
            case "D":
                down();
                break;
            case "L":
                left();
                break;
            case "R":
                right();
                break;
            case "S":
                shoot();
                break;
        }
    }

    static void up() {
        dir = 0; // 방향 전환
        if (check(r - 1, c) && map[r - 1][c].equals(".")) { // 평지이면
            map[r][c] = ".";
            r--;
            map[r][c] = "^";
        } else map[r][c] = "^";
    }

    static void down() {
        dir = 2;
        if (check(r + 1, c) && map[r + 1][c].equals(".")) {
            map[r][c] = ".";
            r++;
            map[r][c] = "v";
        } else map[r][c] = "v";
    }

    static void left() {
        dir = 3;
        if (check(r, c - 1) && map[r][c - 1].equals(".")) {
            map[r][c] = ".";
            c--;
            map[r][c] = "<";
        } else map[r][c] = "<";
    }

    static void right() {
        dir = 1;
        if (check(r, c + 1) && map[r][c + 1].equals(".")) {
            map[r][c] = ".";
            c++;
            map[r][c] = ">";
        } else map[r][c] = ">";
    }

    static void shoot() {
        int pr = r; // 포탄의 위치
        int pc = c;

        while (true) {
            int nr = pr + dr[dir];
            int nc = pc + dc[dir];

            if (!check(nr, nc)) // map 밖으로 나가는 경우
                return;
            else if (map[nr][nc].equals("*")) { // 벽돌벽에 충돌하는 경우
                map[nr][nc] = ".";
                return;
            } else if (map[nr][nc].equals("#")) // 강철벽에 충돌하는 경우
                return;
            else { // 이동
                pr = nr;
                pc = nc;
            }
        }
    }

    static boolean check(int r, int c) {
        return 0 <= r && r < H && 0 <= c && c < W;
    }

    static void output() {
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++)
                sb.append(map[i][j]);
            sb.append("\n");
        }
    }
}