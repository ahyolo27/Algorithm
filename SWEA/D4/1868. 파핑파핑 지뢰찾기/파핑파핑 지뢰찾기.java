import java.io.*;
import java.util.*;

public class Solution {
    static int T, N, count[][], cnt;
    static String map[][];
    static int dr[] = {-1, -1, 0, 1, 1, 1, 0, -1}, dc[] = {0, 1, 1, 1, 0, -1, -1, -1};
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
            setMap(); // 지뢰 개수 세기
            click(); // 클릭
            sb.append("#").append(t).append(" ").append(cnt).append("\n");
        }

        System.out.println(sb);
    }

    static void input() throws IOException {
        N = Integer.parseInt(br.readLine());

        map = new String[N][N];
        count = new int[N][N];
        cnt = 0;

        for (int i = 0; i < N; i++)
            map[i] = br.readLine().split("");
    }

    static void setMap() {
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {

                if (map[r][c].equals("*")) { // 현재 칸이 지뢰인 경우
                    count[r][c] = -1;
                    for (int i = 0; i < 8; i++) { // 주변 지뢰 아닌 칸들에 +1
                        int nr = r + dr[i];
                        int nc = c + dc[i];
                        if (check(nr, nc) && !map[nr][nc].equals("*"))
                            count[nr][nc]++;
                    }
                }
            }
        }
    }

    static void click() {
        boolean visited[][] = new boolean[N][N];
        Queue<Pos> q = new LinkedList<>();

        for (int r = 0; r < N; r++) { // 0의 위치 찾아 큐에 넣기
            for (int c = 0; c < N; c++) {

                if (count[r][c] == 0 && !visited[r][c]) { // 방문하지 않은 0의 경우 클릭 후 방문 처리
                    cnt++;
                    visited[r][c] = true;
                    q.add(new Pos(r, c));

                    while (!q.isEmpty()) { // 주변 탐색
                        Pos p = q.poll();

                        for (int i = 0; i < 8; i++) {
                            int nr = p.r + dr[i];
                            int nc = p.c + dc[i];
                            if (check(nr, nc) && !visited[nr][nc] && count[nr][nc] == 0) { // 0인 경우 탐색을 위해 큐에 삽입
                                visited[nr][nc] = true;
                                q.add(new Pos(nr, nc));
                            } else if (check(nr, nc) && !visited[nr][nc]) // 0은 아닌 경우 방문처리만
                                visited[nr][nc] = true;
                        }
                    }
                }
            }
        }

        for (int r = 0; r < N; r++) { // 0에 인접하지 않아 일일이 클릭해야 하는 경우
            for (int c = 0; c < N; c++) {
                if (!visited[r][c] && count[r][c] > 0) {
                    cnt++;
                    visited[r][c] = true;
                }
            }
        }
    }

    static boolean check(int r, int c) {
        return 0 <= r && r < N && 0 <= c && c < N;
    }
}