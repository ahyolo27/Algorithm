import java.io.*;
import java.util.*;

public class Solution {
    static int T, N, L, ans;
    static List<Ingredient> list = new ArrayList<>();
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static class Ingredient {
        int calorie, taste;

        Ingredient(int calorie, int taste) {
            this.calorie = calorie;
            this.taste = taste;
        }
    }

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();

        T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {

            input(); // 입력
            getMaxScore(); // 가장 높은 햄버거 점수 찾기

            sb.append("#").append(t).append(" ").append(ans).append("\n");
        }
        System.out.println(sb);
    }

    static void input() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken()); // 제한 칼로리

        ans = 0; // 초기화
        list.clear();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int t = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            list.add(new Ingredient(c, t));
        }
    }

    static void getMaxScore() {
        int dp[] = new int[L + 1];
        Arrays.fill(dp, -1);

        dp[0] = 0;
        dp[list.get(0).calorie] = list.get(0).taste;

        for (int i = 1; i < N; i++) {
            int nowCal = list.get(i).calorie;
            int nowTaste = list.get(i).taste;

            for (int j = L; j >= nowCal; j--)
                if (dp[j - nowCal] != -1)
                    dp[j] = Math.max(dp[j], dp[j - nowCal] + nowTaste);
        }

        for (int taste : dp)
            if (taste != -1)
                ans = Math.max(taste, ans);
    }
}