import java.io.*;
import java.util.*;

public class Solution {
    static int T, N, M, startR, startC, L, map[][], ans;
    static boolean visited[][];
    static int dr[] = {-1, 0, 1, 0}, dc[] = {0, 1, 0, -1};
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static class Pos {
        int r, c;

        Pos(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();

        T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {

            input(); // 입력
            findMan(); // 도둑 잡기

            sb.append("#").append(t).append(" ").append(ans).append("\n");
        }
        System.out.println(sb);
    }

    static void input() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 가로 N, 세로 M
        M = Integer.parseInt(st.nextToken());
        startR = Integer.parseInt(st.nextToken()); // 맨홀 위치 (R, C)
        startC = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken()); // 소요 시간

        map = new int[N][M];
        visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++)
                map[i][j] = Integer.parseInt(st.nextToken());
        }
    }

    static void findMan() {
        Queue<Pos> q = new LinkedList<>();
        q.offer(new Pos(startR, startC));
        visited[startR][startC] = true;

        ans = 0; // 초기화
        int time = 1;
        boolean dir[] = new boolean[4];
        boolean nextDir[] = new boolean[4];

        while (!q.isEmpty()) {
            int size = q.size();

            for (int s = 0; s < size; s++) {
                Pos p = q.poll();
                dir = setDirection(dir, map[p.r][p.c]);

                for (int i = 0; i < 4; i++) {
                    int nr = p.r + dr[i];
                    int nc = p.c + dc[i];

                    if (check(nr, nc)) {
                        nextDir = setDirection(nextDir, map[nr][nc]);

                        if (dir[i] && nextDir[(i + 2) % 4] && !visited[nr][nc] && map[nr][nc] > 0) {
                            visited[nr][nc] = true;
                            q.offer(new Pos(nr, nc));
                        }
                    }
                }
            }
            ans += size;
            if (time == L) return;
            time++;
        }
    }

    static boolean[] setDirection(boolean dir[], int pipe) {
        Arrays.fill(dir, false);
        switch (pipe) {
            case 1:
                dir[0] = true;
                dir[1] = true;
                dir[2] = true;
                dir[3] = true;
                break;
            case 2:
                dir[0] = true;
                dir[2] = true;
                break;
            case 3:
                dir[1] = true;
                dir[3] = true;
                break;
            case 4:
                dir[0] = true;
                dir[1] = true;
                break;
            case 5:
                dir[1] = true;
                dir[2] = true;
                break;
            case 6:
                dir[2] = true;
                dir[3] = true;
                break;
            case 7:
                dir[3] = true;
                dir[0] = true;
                break;
        }
        return dir;
    }

    static boolean check(int r, int c) {
        return 0 <= r && r < N && 0 <= c && c < M;
    }
}