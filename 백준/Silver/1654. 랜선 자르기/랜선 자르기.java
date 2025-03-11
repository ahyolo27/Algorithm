import java.io.*;
import java.util.*;

public class Main {
    static int K, N, lines[];
    static long left, right, max;

    public static void main(String[] args) throws IOException {

        input(); // 입력
        cut(); // 막대 자르기

        System.out.println(max);
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        K = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        left = 1;

        lines = new int[K];
        for (int i = 0; i < K; i++) {
            lines[i] = Integer.parseInt(br.readLine());
            right = Math.max(lines[i], right);
        }
    }

    static void cut() { // 막대 자르기
        while (left <= right) {
            long mid = (left + right) / 2;

            long cnt = 0;
            for (int i = 0; i < K; i++)
                cnt += lines[i] / mid;

            if (cnt >= N) { // 개수가 충분한 경우 길이 늘리기
                max = Math.max(mid, max);
                left = mid + 1;
            } else // 개수가 부족한 경우 길이 줄이기
                right = mid - 1;
        }
    }
}