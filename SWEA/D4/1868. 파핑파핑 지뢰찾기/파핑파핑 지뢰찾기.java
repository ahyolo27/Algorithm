import java.io.*;
import java.util.*;

import java.io.*;
import java.util.*;

public class Solution {
    static int n, click;
    static int map[][];
    static boolean visited[][];
    static Queue<Pos> q = new LinkedList<>();
    static int dr[] = {0, -1, 0, 1, -1, -1, 1, 1};
    static int dc[] = {1, 0, -1, 0, 1, -1, 1, -1};

    static class Pos {
        int r, c;

        Pos(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());

        for (int tc = 0; tc < t; tc++) {
            n = Integer.parseInt(br.readLine());
            map = new int[n][n];
            visited = new boolean[n][n];

            for (int i = 0; i < n; i++) {
                String s = br.readLine();
                for (int j = 0; j < n; j++) {
                    if (s.charAt(j) == '.')
                        map[i][j] = 0;
                    else map[i][j] = -1; // 지뢰라면 -1
                }
            }

            setMap();
            gameStart();

            System.out.println("#" + (tc + 1) + " " + click);
        }
    }

    static void setMap() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j] == -1) { // 지뢰라면
                    for (int t = 0; t < 8; t++) {
                        int nr = i + dr[t];
                        int nc = j + dc[t];
                        if (check(nr, nc) && map[nr][nc] != -1)
                            map[nr][nc]++;
                    }
                }
            }
        }
    }

    static void gameStart() { // (0,0)부터 시작
        click = 0; // 초기화

        // 0 탐색 후 클릭
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j] == 0 && !visited[i][j]) { // 값이 0이고 아직 클릭되지 않은 칸
                    click++;
                    visited[i][j] = true;
                    q.add(new Pos(i, j));

                    while (!q.isEmpty()) {
                        Pos p = q.poll();

                        for (int t = 0; t < 8; t++) {
                            int nr = p.r + dr[t];
                            int nc = p.c + dc[t];
                            if (check(nr, nc) && !visited[nr][nc] && map[nr][nc] == 0) {
                                q.add(new Pos(nr, nc));
                                visited[nr][nc] = true;
                            } else if (check(nr, nc) && !visited[nr][nc])
                                visited[nr][nc] = true;
                        }
                    }
                }
            }
        }

        // 0 아닌거 모두 클릭
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (!visited[i][j] && map[i][j] != -1) { // 지뢰가 아닌 방문하지 않은 칸
                    click++;
                    visited[i][j] = true;
                }
            }
        }
    }


    static boolean check(int r, int c) {
        return 0 <= r && r < n && 0 <= c && c < n;
    }
}