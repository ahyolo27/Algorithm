import java.util.*;

class Solution {
    public String solution(int n, int m, int x, int y, int r, int c, int k) {

        // 좌표 보정
        x--;
        y--;
        r--;
        c--;

        int dr[] = {1, 0, 0, -1}, dc[] = {0, -1, 1, 0};
        char dir[] = {'d', 'l', 'r', 'u'};

        int pr = x;
        int pc = y;
        int cnt = 0;
        StringBuilder path = new StringBuilder();

        int dist = Math.abs(x - r) + Math.abs(y - c);
        if (dist > k || (k - dist) % 2 != 0) return "impossible"; // 애초에 도달 불가능한 경우

        while (cnt < k) {
            for (int i = 0; i < 4; i++) {
                int nr = pr + dr[i];
                int nc = pc + dc[i];

                if (nr < 0 || nr >= n || nc < 0 || nc >= m) continue; // 격자 밖인 경우

                int distance = Math.abs(r - nr) + Math.abs(c - nc);
                int remains = k - (cnt + 1);

                if (distance > remains || (remains - distance) % 2 != 0) continue; // 도달 불가 경우

                // 값 갱신
                pr = nr;
                pc = nc;
                cnt++;
                path.append(dir[i]);

                break;
            }
        }

        if (pr == r && pc == c)
            return path.toString();
        else
            return "impossible";
    }
}