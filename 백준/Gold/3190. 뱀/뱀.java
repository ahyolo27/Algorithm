import java.io.*;
import java.util.*;

public class Main {
    static int N, K, L, time;
    static boolean path[][];
    static boolean apples[][];
    static int dr[] = {-1, 0, 1, 0}, dc[] = {0, 1, 0, -1};
    static Queue<Command> commands = new LinkedList<>();
    static ArrayDeque<Pos> snake = new ArrayDeque<>();

    static class Pos {
        int r, c;

        Pos(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    static class Command {
        int x;
        char dir;

        Command(int x, char dir) {
            this.x = x;
            this.dir = dir;
        }
    }

    public static void main(String[] args) throws IOException {

        input(); // 입력
        simulate(); // 시뮬레이션 시작

        System.out.println(time);
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        K = Integer.parseInt(br.readLine());
        path = new boolean[N + 1][N + 1];
        apples = new boolean[N + 1][N + 1];

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            apples[r][c] = true; // 사과
        }

        L = Integer.parseInt(br.readLine());
        for (int i = 0; i < L; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            char dir = st.nextToken().charAt(0);
            commands.add(new Command(x, dir)); // 명령어 추가
        }

        path[1][1] = true; // 뱀의 시작 위치
        snake.addFirst(new Pos(1, 1));
    }

    static void simulate() {
        int dir = 1; // 이동 방향: 오른쪽
        int r = 1; // 머리 위치: r
        int c = 1; // 머리 위치: c

        Command com = commands.poll(); // 명령어
        int x = com.x; // 이동 시간
        int rotate = com.dir == 'L' ? -1 : 1; // 회전 방향

        while (true) {
            time++; // 시간 증가

            // 뱀 이동
            int nr = r + dr[dir];
            int nc = c + dc[dir];

            if (!isValid(nr, nc) || path[nr][nc]) break; // 벽이나 자신과 부딪히는 경우 종료

            if (apples[nr][nc]) { // 사과가 있으면
                apples[nr][nc] = false;
            } else { // 사과가 없으면
                Pos tail = snake.pollLast();
                path[tail.r][tail.c] = false; // 뱀 꼬리 줄이기
            }
            path[nr][nc] = true;
            snake.addFirst(new Pos(nr, nc));  // 뱀 머리 늘리기

            // 머리 좌표 업데이트
            r = nr;
            c = nc;

            if (time == x) { // 명령어에서 요구한 시간 도달
                dir = (dir + rotate + 4) % 4; // 회전
                if (!commands.isEmpty()) { // 다음 명령어 가져오기
                    com = commands.poll();
                    x = com.x;
                    rotate = com.dir == 'L' ? -1 : 1;
                }
            }
        }
    }

    static boolean isValid(int r, int c) { // 범위 내 값이면 true
        return 1 <= r && r < N + 1 && 1 <= c && c < N + 1;
    }
}