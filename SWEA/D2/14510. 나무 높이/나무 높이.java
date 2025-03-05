import java.io.*;
import java.util.*;

public class Solution {
    static int T, N, h[], MAX, days;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();

        T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            input(); // 입력
            water(); // 나무에 물 주기

            sb.append("#").append(t).append(" ").append(days).append("\n");
        }
        System.out.println(sb);
    }

    static void input() throws IOException {
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        h = new int[N];
        days = 0;
        MAX = 0;

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            h[i] = Integer.parseInt(st.nextToken());
            MAX = Math.max(MAX, h[i]); // 최대값 갱신
        }
    }

    static void water() {
        int cnt1 = 0;
        int cnt2 = 0;

        for (int i = 0; i < N; i++) {
            int remain = MAX - h[i];
            cnt1 += remain % 2;
            cnt2 += remain / 2;
        }

        if (cnt1 > cnt2)
            days = cnt1 * 2 - 1;
        else if (cnt1 == cnt2)
            days = cnt1 * 2;
        else { // cnt1 < cnt2
            days += cnt1 * 2;
            cnt2 -= cnt1;
            days += ((cnt2 * 2) / 3) * 2;
            if ((cnt2 * 2) % 3 == 1)
                days += 1;
            else if ((cnt2 * 2) % 3 == 2)
                days += 2;
        }
    }
}