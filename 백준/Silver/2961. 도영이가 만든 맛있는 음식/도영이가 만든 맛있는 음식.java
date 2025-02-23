import java.util.*;
import java.io.*;

public class Main {
    static int N, S[], B[], min;

    public static void main(String[] args) throws IOException {

        input(); // 입력
        find(0, 1, 0); // 신맛과 쓴맛의 최소값 찾기

        System.out.println(min);
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        S = new int[N];
        B = new int[N];
        min = Integer.MAX_VALUE;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            S[i] = Integer.parseInt(st.nextToken());
            B[i] = Integer.parseInt(st.nextToken());
        }
    }

    static void find(int depth, int sumS, int sumB) {
        if (depth == N) {
            if (sumS != 1 && sumB != 0) // 공집합이 아닌 경우
                min = Math.min(Math.abs(sumS - sumB), min);
            return;
        }

        find(depth + 1, sumS * S[depth], sumB + B[depth]); // 재료를 선택하는 경우
        find(depth + 1, sumS, sumB); // 재료를 선택하지 않는 경우
    }
}