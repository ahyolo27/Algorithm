import java.io.*;
import java.util.*;

public class Solution {
    static int N, H, trees[], max, ans;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        for (int t = 0; t < N; t++) {

            input(); // 입력
            water(); // 나무에 물 주기

            System.out.println("#" + (t + 1) + " " + ans);
        }
    }

    static void input() throws IOException {
        StringTokenizer st;

        H = Integer.parseInt(br.readLine());
        trees = new int[H];
        max = 0;

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < H; i++) {
            trees[i] = Integer.parseInt(st.nextToken());
            max = Math.max(max, trees[i]); // 최대값 갱신
        }
    }

    static void water() {
        int cnt1 = 0;
        int cnt2 = 0;

        for (int i = 0; i < H; i++) {
            int remain = max - trees[i];
            cnt1 += remain % 2;
            cnt2 += remain / 2;
        }

        while (true) {
            if (cnt2 - cnt1 > 1) {
                cnt2 -= 1;
                cnt1 += 2;
            } else if (cnt1 - cnt2 > 1) {
                cnt2 = cnt1 - 1; // 물 안 주는 날
            } else break;
        }

        if (cnt2 > cnt1) {
            ans = cnt1 + cnt2 + 1;
        } else
            ans = cnt1 + cnt2;
    }
}