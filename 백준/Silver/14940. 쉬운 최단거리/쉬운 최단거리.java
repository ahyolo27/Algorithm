import java.io.*;
import java.util.*;

public class Main {
    static int N, M, map[][], weight[][];
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
        StringBuilder sb = new StringBuilder();

        input(); // 입력
        bfs(); // 최단거리 탐색

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++)
                sb.append(weight[i][j]).append(" ");
            sb.append("\n");
        }
        System.out.println(sb);
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        weight = new int[N][M];
        visited = new boolean[N][M];

        for (int i = 0; i < N; i++)
            Arrays.fill(weight[i], -1);

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 2) { // 출발점인 경우
                    visited[i][j] = true;
                    weight[i][j] = 0;
                    q.offer(new Pos(i, j));
                } else if (map[i][j] == 0) { // 갈 수 없는 곳인 경우
                    weight[i][j] = 0;
                }
            }
        }
    }

    static void bfs() {
        while (!q.isEmpty()) {
            Pos p = q.poll();

            for (int i = 0; i < 4; i++) {
                int nr = p.r + dr[i];
                int nc = p.c + dc[i];

                // map 밖이거나, 방문한 칸이거나, 갈 수 없는 칸인 경우 continue
                if (!isValid(nr, nc) || visited[nr][nc] || map[nr][nc] == 0) continue;

                visited[nr][nc] = true;
                weight[nr][nc] = weight[p.r][p.c] + 1; // 최단 거리 갱신
                q.offer(new Pos(nr, nc));
            }
        }
    }

    static boolean isValid(int r, int c) {
        return 0 <= r && r < N && 0 <= c && c < M;
    }
}