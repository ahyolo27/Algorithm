import java.util.*;
import java.io.*;

public class Solution {
    static int T, N, map[][], max, minIdx;
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
        T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            input(); // 입력

            for (int i = 0; i < N; i++)
                for (int j = 0; j < N; j++)
                    move(i, j); // 이동

            System.out.println("#" + t + " " + minIdx + " " + max);
        }
    }

    static void input() throws IOException {
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        max = -1; // 초기화
        minIdx = -1;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++)
                map[i][j] = Integer.parseInt(st.nextToken());
        }
    }

    static void move(int startR, int startC) {
        int cnt = 1;
        Queue<Pos> q = new LinkedList<>();
        q.offer(new Pos(startR, startC));

        while (!q.isEmpty()) {
            Pos p = q.poll();

            for (int i = 0; i < 4; i++) {
                int nr = p.r + dr[i];
                int nc = p.c + dc[i];
                if (check(nr, nc) && map[p.r][p.c] + 1 == map[nr][nc]) {
                    cnt++;
                    q.offer(new Pos(nr, nc));
                    break;
                }
            }
        }

        if (max <= cnt) {
            if (max == cnt)
                minIdx = Math.min(minIdx, map[startR][startC]);
            else
                minIdx = map[startR][startC];
            max = cnt;
        }
    }

    static boolean check(int r, int c) {
        return 0 <= r && r < N && 0 <= c && c < N;
    }
}