import java.io.*;
import java.util.*;

public class Main {
    static int N, M, map[][], time;
    static int dr[] = {-1, 0, 1, 0}, dc[] = {0, 1, 0, -1};
    static Queue<Pos> meltCheese = new LinkedList<>(); // 녹일 치즈를 담는 큐

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

        System.out.println(time);
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++)
                map[i][j] = Integer.parseInt(st.nextToken());
        }
    }

    static void simulate() {
        while (true) {
            bfs(); //녹일 치즈 탐색

            if (meltCheese.isEmpty()) break; // 더이상 녹일 치즈가 없는 경우 종료

            while (!meltCheese.isEmpty()) { // 치즈 녹이기
                Pos p = meltCheese.poll();
                map[p.r][p.c] = 0;
            }
            time++;
        }
    }

    static void bfs() {
        int cnt[][] = new int[N][M]; // 외부 공기와 접촉 횟수

        boolean visited[][] = new boolean[N][M];
        Queue<Pos> q = new LinkedList<>();
        q.add(new Pos(0, 0));
        visited[0][0] = true;

        while (!q.isEmpty()) {
            Pos p = q.poll();

            for (int i = 0; i < 4; i++) {
                int nr = p.r + dr[i];
                int nc = p.c + dc[i];

                if (!isValid(nr, nc)) continue; // 범위 밖 탐색 X

                if (map[nr][nc] == 0 && !visited[nr][nc]) { // 빈 공간인 경우: 큐에 추가
                    visited[nr][nc] = true; // 방문 처리
                    q.add(new Pos(nr, nc));
                } else if (map[nr][nc] > 0)  // 치즈인 경우: 외부 공기와 접촉 횟수 증가
                    cnt[nr][nc]++;
            }
        }

        // 녹여야 하는 치즈 저장
        for (int i = 0; i < N; i++)
            for (int j = 0; j < M; j++)
                if (cnt[i][j] >= 2)
                    meltCheese.add(new Pos(i, j));
    }

    static boolean isValid(int r, int c) { // 범위 내 값이면 true
        return 0 <= r && r < N && 0 <= c && c < M;
    }
}