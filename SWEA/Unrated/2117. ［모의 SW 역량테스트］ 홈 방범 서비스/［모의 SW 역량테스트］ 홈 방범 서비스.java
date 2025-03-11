import java.util.*;
import java.io.*;

public class Solution {
    static int T, N, M, map[][], home, max;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();

        T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {

            input(); // 입력
            setService(); // 서비스 설치

            sb.append("#").append(t).append(" ").append(max).append("\n");
        }

        System.out.println(sb);
    }

    static void input() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        max = 0; // 초기화
        home = 0;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 1)
                    home++;
            }
        }
    }

    static void setService() {
        int k = 1;
        int cost = 1;

        while (true) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    int cnt = countHome(i, j, k);

                    int benefit = cnt * M - cost;
                    if (benefit >= 0)  // 손해가 아니면
                        max = Math.max(cnt, max); // 집의 개수 갱신

                    if (home == cnt) // 모든 집을 다 본 경우
                        return;
                }
            }

            k++; // 값 갱신
            cost = k * k + (k - 1) * (k - 1);
        }
    }

    static int countHome(int r, int c, int k) {
        int cnt = 0;

        for (int i = r - k + 1; i <= r + k - 1; i++)
            for (int j = c - k + 1; j <= c + k - 1; j++)
                if (check(i, j) && map[i][j] == 1 && Math.abs(r - i) + Math.abs(c - j) < k)  // 서비스 범위 안에 있으면
                    cnt++;

        return cnt;
    }

    static boolean check(int r, int c) {
        return 0 <= r && r < N && 0 <= c && c < N;
    }
}