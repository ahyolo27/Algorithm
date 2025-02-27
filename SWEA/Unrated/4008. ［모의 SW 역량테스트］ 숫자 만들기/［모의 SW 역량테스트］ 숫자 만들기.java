import java.util.*;
import java.io.*;

public class Solution {
    static int T, N, op[], num[], max, min;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            input(); // 입력
            dfs(1, num[0]);
            System.out.println("#" + t + " " + (max - min));
        }
    }

    static void input() throws IOException {
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        num = new int[N];
        op = new int[4];
        max = Integer.MIN_VALUE; // 초기화
        min = Integer.MAX_VALUE;

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 4; i++)
            op[i] = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++)
            num[i] = Integer.parseInt(st.nextToken());
    }

    static void dfs(int depth, int result) {
        if (depth == N) {
            max = Math.max(max, result);
            min = Math.min(min, result);
            return;
        }

        for (int i = 0; i < 4; i++) {
            if (op[i] > 0) { // 사용할 수 있으면
                op[i]--; // 사용
                dfs(depth + 1, calc(result, num[depth], i));
                op[i]++; // 복원
            }
        }
    }

    static int calc(int a, int b, int op) {
        switch (op) {
            case 0:
                return a + b;
            case 1:
                return a - b;
            case 2:
                return a * b;
            case 3:
                return a / b;
            default:
                return 0;
        }
    }
}