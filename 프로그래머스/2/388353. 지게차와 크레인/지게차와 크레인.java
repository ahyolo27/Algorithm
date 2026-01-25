import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

class Solution {
    public int solution(String[] storage, String[] requests) {
        // init
        int N = storage.length;
        int M = storage[0].length();
        char map[][] = new char[N + 2][M + 2];
        for (char[] chars : map)
            Arrays.fill(chars, '-');
        for (int i = 1; i < map.length - 1; i++) {
            for (int j = 1; j < map[i].length - 1; j++)
                map[i][j] = storage[i - 1].charAt(j - 1);
        }
        int total = N * M;

        // solution
        for (String req : requests) {
            char target = req.charAt(0);
            if (req.length() == 1) // 지게차
                total -= forklift(target, map);
            else // 크레인
                total -= crane(target, map);
        }

        return total;
    }

    int forklift(char target, char map[][]) { // bfs
        Queue<Pos> q = new LinkedList<>();
        boolean visited[][] = new boolean[map.length][map[0].length];

        q.add(new Pos(0, 0));
        visited[0][0] = true;

        int dr[] = {-1, 0, 1, 0}, dc[] = {0, 1, 0, -1};
        int cnt = 0;

        while (!q.isEmpty()) {
            Pos p = q.poll();

            for (int i = 0; i < 4; i++) {
                int nr = p.r + dr[i];
                int nc = p.c + dc[i];
                if (!(0 <= nr && nr < map.length && 0 <= nc && nc < map[0].length)) continue; // 범위 밖

                if (!visited[nr][nc] && map[nr][nc] == '-') { // 빈 공간
                    visited[nr][nc] = true;
                    q.add(new Pos(nr, nc));
                } else if (!visited[nr][nc] && map[nr][nc] == target) {
                    visited[nr][nc] = true;
                    map[nr][nc] = '-';
                    cnt++;
                }
            }
        }

        return cnt;
    }


    int crane(char target, char map[][]) {
        int cnt = 0;

        for (int i = 1; i < map.length - 1; i++)
            for (int j = 1; j < map[i].length - 1; j++)
                if (map[i][j] == target) {
                    map[i][j] = '-';
                    cnt++;
                }

        return cnt;
    }

    class Pos {
        int r, c;

        Pos(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
}