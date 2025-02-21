import java.util.*;
import java.io.*;

public class Solution {
    static int T, N, M, map[][], max;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            input(); // 입력
            hit(); // 파리 잡기
            System.out.println("#" + t + " " + max);
        }
    }

    static void input() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        max = 0;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++)
                map[i][j] = Integer.parseInt(st.nextToken());
        }
    }

    static void hit() {
        for (int i = 0; i < N - M + 1; i++) {
            for (int j = 0; j < N - M + 1; j++) {
                int sum = 0;
                for (int r = i; r < i + M; r++) {
                    for (int c = j; c < j + M; c++) {
                        sum += map[r][c];
                    }
                }
                max = Math.max(max, sum);
            }
        }
    }
}