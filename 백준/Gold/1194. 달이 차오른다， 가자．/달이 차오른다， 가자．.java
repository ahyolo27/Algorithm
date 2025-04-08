import java.io.*;
import java.util.*;

public class Main {
    static int N, M, ans;
    static char map[][];
    static boolean visited[][][];
    static int dr[] = {-1, 0, 1, 0}, dc[] = {0, 1, 0, -1};

    static Queue<Pos> q = new LinkedList<>();

    static class Pos {
        int r, c, cnt;
        boolean keys[];

        Pos(int r, int c, int cnt, boolean keys[]) {
            this.r = r;
            this.c = c;
            this.cnt = cnt;
            this.keys = keys;
        }
    }

    public static void main(String[] args) throws IOException {

        input(); // 입력
        move(); // 미로 탈출

        System.out.println(ans);
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        visited = new boolean[N][M][64]; // keyState 저장
        map = new char[N][M];
        ans = 0; // 초기화

        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = str.charAt(j);
                if (map[i][j] == '0') { // 민식이의 현재 위치
                    q.offer(new Pos(i, j, 0, new boolean[6]));
                    visited[i][j][0] = true;
                    map[i][j] = '.';
                }
            }
        }
    }

    static void move() {
        while (!q.isEmpty()) {
            Pos p = q.poll();

            for (int i = 0; i < 4; i++) {
                int nr = p.r + dr[i];
                int nc = p.c + dc[i];

                if (isValid(nr, nc) && map[nr][nc] != '#') { // 갈 수 있는 곳 중에

                    if (map[nr][nc] == '1') { // 도착한 경우
                        ans = p.cnt + 1;
                        return;
                    }

                    int keyState = getKeyState(p.keys);
                    boolean tmpKeys[] = p.keys.clone();

                    if (isKey(nr, nc)) { // 열쇠인 경우
                        getKey(nr, nc, tmpKeys);
                        keyState = getKeyState(tmpKeys); // keyState 갱신
                    }

                    if (map[nr][nc] == '.' || (isDoor(nr, nc) && canOpenDoor(nr, nc, tmpKeys)) || isKey(nr, nc)) { // 이동할 수 있는 경우
                        if (!visited[nr][nc][keyState]) {
                            visited[nr][nc][keyState] = true;
                            q.offer(new Pos(nr, nc, p.cnt + 1, tmpKeys));
                        }
                    }
                }
            }
        }

        ans = -1; // 탈출하지 못한 경우
    }

    static boolean isKey(int r, int c) {
        return 97 <= map[r][c] && map[r][c] <= 102; // ASCII Code ---> a: 97, f: 102
    }

    static void getKey(int r, int c, boolean keys[]) {
        switch (map[r][c]) {
            case 'a':
                keys[0] = true;
                break;
            case 'b':
                keys[1] = true;
                break;
            case 'c':
                keys[2] = true;
                break;
            case 'd':
                keys[3] = true;
                break;
            case 'e':
                keys[4] = true;
                break;
            case 'f':
                keys[5] = true;
                break;
        }
    }

    static int getKeyState(boolean keys[]) {
        int state = 0;
        state = state * 2 + (keys[0] ? 1 : 0);
        state = state * 2 + (keys[1] ? 1 : 0);
        state = state * 2 + (keys[2] ? 1 : 0);
        state = state * 2 + (keys[3] ? 1 : 0);
        state = state * 2 + (keys[4] ? 1 : 0);
        state = state * 2 + (keys[5] ? 1 : 0);

        return state;
    }

    static boolean isDoor(int r, int c) {
        return 65 <= map[r][c] && map[r][c] <= 70; // ASCII Code ---> A: 65, F: 70
    }

    static boolean canOpenDoor(int r, int c, boolean keys[]) {
        int keyIdx = -1;

        switch (map[r][c]) {
            case 'A':
                keyIdx = 0;
                break;
            case 'B':
                keyIdx = 1;
                break;
            case 'C':
                keyIdx = 2;
                break;
            case 'D':
                keyIdx = 3;
                break;
            case 'E':
                keyIdx = 4;
                break;
            case 'F':
                keyIdx = 5;
                break;
        }

        return keys[keyIdx];
    }

    static boolean isValid(int r, int c) {
        return 0 <= r && r < N && 0 <= c && c < M;
    }
}