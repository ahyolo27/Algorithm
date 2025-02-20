import java.util.*;
import java.io.*;

public class Solution {
    static int TC, N, L, T[], K[], max;
    static boolean isSelected[];
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        TC = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= TC; tc++) {

            input(); // 입력
            makeHamburger(0, 0, 0);

            System.out.println("#" + tc + " " + max);
        }
    }

    static void input() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        T = new int[N];
        K = new int[N];
        isSelected = new boolean[N];
        max = 0;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            T[i] = Integer.parseInt(st.nextToken());
            K[i] = Integer.parseInt(st.nextToken());
        }
    }

    static void makeHamburger(int depth, int sumT, int sumK) {
        if (depth == N) {
            if (sumK <= L)
                max = Math.max(max, sumT);
            return;
        }

        if (sumK + K[depth] <= L)
            makeHamburger(depth + 1, sumT + T[depth], sumK + K[depth]);
        makeHamburger(depth + 1, sumT, sumK);
    }
}