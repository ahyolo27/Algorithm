import java.util.*;
import java.io.*;

public class Solution {
    static int TC, N, L, T[], K[], max;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        TC = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= TC; tc++) {

            input(); // 입력

            for (int i = 1; i <= N; i++)
                makeHamburger(0, 0, 0, i);

            System.out.println("#" + tc + " " + max);
        }
    }

    static void input() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        T = new int[N];
        K = new int[N];
        max = 0;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            T[i] = Integer.parseInt(st.nextToken());
            K[i] = Integer.parseInt(st.nextToken());
        }
    }

    static void makeHamburger(int idx, int sumT, int sumK, int r) {
        if (r == 0) { // 다 뽑은 경우
            if (sumK <= L)
                max = Math.max(max, sumT);
            return;
        }

        for (int i = idx; i < N; i++)
            makeHamburger(i + 1, sumT + T[i], sumK + K[i], r - 1);
    }
}