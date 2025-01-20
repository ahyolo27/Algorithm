import java.util.*;
import java.io.*;

public class Solution {
    static int n, m;
    static int flies[][];
    static int dx[] = {0, 0, -1, 1};
    static int dy[] = {-1, 1, 0, 0};
    static int d1[] = {1, 1, -1, -1};
    static int d2[] = {1, -1, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());
        StringTokenizer st;

        for (int tc = 1; tc <= t; tc++) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            int max = 0;

            flies = new int[n][n];
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < n; j++)
                    flies[i][j] = Integer.parseInt(st.nextToken());
            }

            for (int i = 0; i < n; i++)
                for (int j = 0; j < n; j++)
                    max = Math.max(getMax(i, j), max);

            System.out.println("#" + tc + " " + max);
        }
    }

    static int getMax(int y, int x) {
        int tmp1 = flies[y][x];
        int tmp2 = flies[y][x];

        for (int j = 1; j < m; j++) { // + 영역
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i] * j;
                int ny = y + dy[i] * j;
                if (valid(nx, ny))
                    tmp1 += flies[ny][nx];
            }
        }

        for (int j = 1; j < m; j++) { // x 영역
            for (int i = 0; i < 4; i++) {
                int nx = x + d1[i] * j;
                int ny = y + d2[i] * j;
                if (valid(nx, ny))
                    tmp2 += flies[ny][nx];
            }
        }

        return Math.max(tmp1, tmp2);
    }

    static boolean valid(int x, int y) {
        return 0 <= x && x < n && 0 <= y && y < n;
    }
}