import java.io.*;
import java.util.*;

public class Solution {
    static int T, N, M, C, map[][], max, ans;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();

        T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {

            input(); // 입력
            selectPlace(0, 0, 0, 0); // 벌꿀 채집 시작

            sb.append("#").append(t).append(" ").append(ans).append("\n");
        }
        System.out.println(sb);
    }

    static void input() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++)
                map[i][j] = Integer.parseInt(st.nextToken());
        }

        ans = 0; // 초기화
    }

    static void selectPlace(int depth, int r, int c, int total) {
        if (depth == 2) {
            ans = Math.max(total, ans);
            return;
        }

        max = 0; // 초기화

        if (check(r, c)) {  // 현재 칸을 선택할 수 있는 경우
            int list[] = new int[M];

            for (int m = 0; m < M; m++)  // 현재 칸을 선택하는 경우
                list[m] = map[r][c + m];
            selectHoney(0, 0, 0, list); // set max
            selectPlace(depth + 1, r, c + M, total + max);

            selectPlace(depth, r, c + 1, total); // 현재 칸을 선택하지 않는 경우

        } else { // 현재 칸을 선택할 수 없는 경우
            if (r == N - 1)  // 끝까지 탐색 완료
                return;

            selectPlace(depth, r + 1, 0, total);
        }
    }

    static void selectHoney(int depth, int sum, int profit, int list[]) {
        if (depth == M) {
            max = Math.max(profit, max);
            return;
        }
        if (sum + list[depth] <= C) // 현재 벌통을 선택하는 경우
            selectHoney(depth + 1, sum + list[depth], profit + (int) Math.pow(list[depth], 2), list);
        selectHoney(depth + 1, sum, profit, list); // 현재 벌통을 선택하지 않는 경우
    }

    static boolean check(int r, int c) {
        return 0 <= r && r < N && 0 <= c && c <= N - M;
    }
}