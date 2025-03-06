import java.util.*;
import java.io.*;

public class Main {
    static int H, W, map[][], time, cheese;
    static boolean visited[][];
    static int dr[] = {-1, 0, 1, 0}, dc[] = {0, 1, 0, -1};
    static Queue<Pos> q = new LinkedList<>(); // BFS
    static Queue<Pos> cheeseList = new LinkedList<>(); // 지울 치즈 리스트

    static class Pos {
        int r, c;

        Pos(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    public static void main(String[] args) throws IOException {

        input(); // 입력
        start(); // 치즈 녹는 중

        System.out.println(time + "\n" + cheese);
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        map = new int[H][W];
        visited = new boolean[H][W];

        for (int i = 0; i < H; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < W; j++)
                map[i][j] = Integer.parseInt(st.nextToken());
        }
    }

    static void start() {
        while (true) {
            calcCheese(); // 녹기 이전 치즈 면적
            find(); // 치즈 가장자리 찾기
            melt(); // 치즈 녹이기
            time++;

            if (isCheese())  // 치즈가 다 녹았다면
                return;
        }
    }

    static void find() { // 치즈 가장자리 찾기
        q.add(new Pos(0, 0));
        visited[0][0] = true;

        while (!q.isEmpty()) {
            Pos p = q.poll();

            for (int t = 0; t < 4; t++) {
                int nr = p.r + dr[t];
                int nc = p.c + dc[t];
                if (check(nr, nc) && !visited[nr][nc] && map[nr][nc] == 1) { // 치즈의 가장자리인 경우
                    visited[nr][nc] = true;
                    cheeseList.add(new Pos(nr, nc)); // 지울 치즈 리스트에 넣기
                } else if (check(nr, nc) && !visited[nr][nc] && map[nr][nc] == 0) {
                    visited[nr][nc] = true;
                    q.add(new Pos(nr, nc)); // 탐색 큐에 넣기
                }
            }
        }

        for (int i = 0; i < H; i++) // 방문 배열 초기화
            Arrays.fill(visited[i], false);
    }

    static void melt() { // 치즈 녹이기
        while (!cheeseList.isEmpty()) {
            Pos p = cheeseList.poll();

            map[p.r][p.c] = 0;
        }
    }

    static boolean isCheese() { // 치즈가 남아있다면 false
        for (int i = 0; i < H; i++)
            for (int j = 0; j < W; j++)
                if (map[i][j] > 0)
                    return false;
        return true;
    }

    static void calcCheese() { // 치즈 면적 세기
        cheese = 0; // 초기화
        for (int i = 0; i < H; i++)
            for (int j = 0; j < W; j++)
                if (map[i][j] > 0)
                    cheese++;
    }

    static boolean check(int r, int c) { // map 범위 안에 있으면 true
        return 0 <= r && r < H && 0 <= c && c < W;
    }
}