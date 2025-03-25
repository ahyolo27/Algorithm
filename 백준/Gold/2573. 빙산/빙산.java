import java.io.*;
import java.util.*;

public class Main {
    static int N, M, map[][], minus[][], year;
    static boolean visited[][];
    static int dr[] = {-1, 0, 1, 0}, dc[] = {0, 1, 0, -1};

    static class Pos {
        int r, c;

        Pos(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    public static void main(String[] args) throws IOException {

        input(); // 입력
        start(); // 시작

        System.out.println(year);
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        minus = new int[N][M];
        visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++)
                map[i][j] = Integer.parseInt(st.nextToken());
        }
    }

    static void initMinus() {
        for (int i = 0; i < N; i++)
            Arrays.fill(minus[i], 0);
    }

    static void initVisited() {
        for (int i = 0; i < N; i++)
            Arrays.fill(visited[i], false);
    }

    static void start() {
        while (true) {
            boolean isMelted = false;
            initMinus(); // 배열 초기화
            initVisited();
            year++;

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++)
                    if (!visited[i][j] && map[i][j] > 0) { // 방문하지 않은 빙산인 경우
                        bfs(i, j);
                        isMelted = true;
                    }
            }

            melt();
            if (count()) // 여러 덩이리인 경우 종료
                break; 
            else if (!isMelted) { // 녹을게 없는 경우 답을 0으로 세팅하고 종료
                year = 0;
                break;
            }
        }
    }

    static void bfs(int i, int j) {
        Queue<Pos> q = new LinkedList<>();
        q.offer(new Pos(i, j));
        visited[i][j] = true;

        while (!q.isEmpty()) {
            Pos p = q.poll();

            int cnt = 0; // 바다와 접한 면의 수

            for (int t = 0; t < 4; t++) {
                int nr = p.r + dr[t];
                int nc = p.c + dc[t];
                if (map[nr][nc] == 0) { // 바다인 경우
                    cnt++;
                } else if (!visited[nr][nc] && map[nr][nc] > 0) { // 빙산인 경우
                    q.offer(new Pos(nr, nc));
                    visited[nr][nc] = true;
                }
            }

            minus[p.r][p.c] = cnt; // 녹아야 하는 양 저장
        }
    }

    static void melt() {
        for (int i = 0; i < N; i++)
            for (int j = 0; j < M; j++)
                if (minus[i][j] > 0)
                    map[i][j] = Math.max(0, map[i][j] - minus[i][j]); // 녹기 (다 녹은 경우 0 처리)
    }

    static boolean count() { // 여러 덩이리인 경우 true
        int cnt = 0;
        initVisited(); // 배열 초기화

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++)
                if (!visited[i][j] && map[i][j] > 0) { // 방문하지 않은 빙산인 경우
                    bfs(i, j);
                    cnt++;
                }
        }
        return cnt > 1;
    }
}