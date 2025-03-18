import java.util.*;
import java.io.*;

public class Main {
    static int R, C, ansR, ansC;
    static char map[][], ans;
    static boolean visited[][];
    static int dr[] = {-1, 0, 1, 0}, dc[] = {0, 1, 0, -1};
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
        move(); // 이동
        repairPipe(); // 고장난 파이프 고치기'

        System.out.println(++ansR + " " + ++ansC + " " + ans);
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new char[R][C];
        visited = new boolean[R][C];

        for (int i = 0; i < R; i++) {
            String str = br.readLine();
            for (int j = 0; j < C; j++) {
                map[i][j] = str.charAt(j);
                if (map[i][j] == 'M') {
                    q.add(new Pos(i, j)); // 시작점 넣기
                    visited[i][j] = true;
                }
            }
        }
    }

    static void move() {
        boolean direction[] = new boolean[4];

        while (!q.isEmpty()) {
            Pos p = q.poll();

            if (map[p.r][p.c] == 'M') {
                for (int i = 0; i < 4; i++) {
                    int nr = p.r + dr[i];
                    int nc = p.c + dc[i];
                    if (check(nr, nc) && findPipe(map[nr][nc]))
                        direction[i] = true;
                }
            } else
                setDirection(map[p.r][p.c], direction);

            for (int i = 0; i < 4; i++) {
                if (direction[i]) { // 4방 중 이동할 수 있는 방향에 따라 이동
                    int nr = p.r + dr[i];
                    int nc = p.c + dc[i];

                    if (check(nr, nc) && !visited[nr][nc] && findPipe(map[nr][nc])) { // 파이프 따라가기
                        visited[nr][nc] = true;
                        q.offer(new Pos(nr, nc));
                    } else if (check(nr, nc) && !visited[nr][nc] && map[nr][nc] == '.') { // 파이프에서 끊긴 경우
                        visited[nr][nc] = true;
                        ansR = nr;
                        ansC = nc;
                        return;
                    }
                }
            }
        }
    }

    static void setDirection(char m, boolean direction[]) {
        Arrays.fill(direction, false); // 초기화

        if (m == '|')
            direction[0] = direction[2] = true;
        else if (m == '-')
            direction[1] = direction[3] = true;
        else if (m == '1')
            direction[1] = direction[2] = true;
        else if (m == '2')
            direction[0] = direction[1] = true;
        else if (m == '3')
            direction[0] = direction[3] = true;
        else if (m == '4')
            direction[2] = direction[3] = true;
        else if (m == '+')
            Arrays.fill(direction, true);
    }

    static boolean findPipe(char m) { // pipe인 경우 true
        return m == '|' || m == '-' || m == '+' || m == '1' || m == '2' || m == '3' || m == '4';
    }

    static boolean check(int r, int c) { // 범위 안에 있는 경우 true
        return 0 <= r && r < R && 0 <= c && c < C;
    }

    static void repairPipe() {
        boolean otherDirection[][] = new boolean[4][4];
        boolean direction[] = new boolean[4];

        for (int i = 0; i < 4; i++) {
            int nr = ansR + dr[i];
            int nc = ansC + dc[i];
            if (check(nr, nc)) {
                setDirection(map[nr][nc], otherDirection[i]);
                for (int j = 0; j < 4; j++)
                    if (Math.abs(i - j) == 2 && otherDirection[i][j]) // 반대편 칸이 true이면 해당 면으로 이어져있으므로 방향 추가
                        direction[i] = true;
            }
        }

        if (direction[0] && direction[2] && !direction[1] && !direction[3])
            ans = '|';
        else if (direction[1] && direction[3] && !direction[0] && !direction[2])
            ans = '-';
        else if (direction[1] && direction[2] && !direction[3] && !direction[0])
            ans = '1';
        else if (direction[0] && direction[1] && !direction[2] && !direction[3])
            ans = '2';
        else if (direction[0] && direction[3] && !direction[1] && !direction[2])
            ans = '3';
        else if (direction[2] && direction[3] && !direction[0] && !direction[1])
            ans = '4';
        else // +
            ans = '+';
    }
}