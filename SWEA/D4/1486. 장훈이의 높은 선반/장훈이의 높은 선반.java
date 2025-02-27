import java.util.*;
import java.io.*;

public class Solution {
    static int T, N, B, H[], min;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            input(); // 입력
            makeTower(0, 0);
            System.out.println("#" + t + " " + (min - B));
        }
    }

    static void input() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        min = Integer.MAX_VALUE;

        H = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++)
            H[i] = Integer.parseInt(st.nextToken());
    }

    static void makeTower(int depth, int sum) {
        if (sum >= B) { // 조건 충족한 경우
            min = Math.min(min, sum);
            return;
        } else if (depth == N) // 조건 충족하지 못한 경우
            return;

        makeTower(depth + 1, sum + H[depth]); // 선택하는 경우
        makeTower(depth + 1, sum); // 선택하지 않는 경우
    }
}