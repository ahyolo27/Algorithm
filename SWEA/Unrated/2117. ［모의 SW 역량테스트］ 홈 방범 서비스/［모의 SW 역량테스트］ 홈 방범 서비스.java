import java.io.*;
import java.util.*;

public class Solution {
    static int T, N, M, homeMax;
    static List<Home> homes = new ArrayList<>();
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static class Home {
        int r, c;

        Home(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            input(); // 입력

            findMax(); // 시뮬레이션 시작

            sb.append("#").append(t).append(" ").append(homeMax).append("\n");
        }
        System.out.println(sb);
    }

    static void input() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        homes.clear();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++)
                if (Integer.parseInt(st.nextToken()) == 1)
                    homes.add(new Home(i, j));
        }
    }

    static void findMax() {
        homeMax = 0; // 초기화
        int k = 1;
        int cost = 1;

        while (true) {
            cost = k * k + (k - 1) * (k - 1);

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    int cnt = countHome(i, j, k);
                    int profit = cnt * M - cost; // 이익 = 수익 - 운영비용

                    if (profit >= 0) // 손해가 아닌 경우
                        homeMax = Math.max(cnt, homeMax);

                    if (homes.size() == cnt) // 모든 집을 다 본 경우 종료
                        return;
                }
            }
            k++; // 갱신
        }
    }

    static int countHome(int r, int c, int k) {
        int cnt = 0;

        for (Home home : homes) {
            if (r - k < home.r && home.r < r + k && c - k < home.c && home.c < c + k)
                if (Math.abs(r - home.r) + Math.abs(c - home.c) < k) cnt++;
        }

        return cnt;
    }
}