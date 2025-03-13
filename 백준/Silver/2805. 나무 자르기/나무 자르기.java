import java.io.*;
import java.util.*;

public class Main {
    static int N, left, right, maxH;
    static long M, len[];

    public static void main(String[] args) throws IOException {

        input(); // 입력
        cut(); // 나무 자르기

        System.out.println(maxH);
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        left = 1; // 초기화
        maxH = 0;

        len = new long[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            len[i] = Integer.parseInt(st.nextToken());
            right = (int) Math.max(len[i], right);
        }
    }

    static void cut() {
        while (left <= right) {
            long mid = (left + right) / 2;

            long sum = 0;
            for (int i = 0; i < len.length; i++) {
                if (len[i] > mid) { // 양수인 경우에만 sum
                    sum += len[i] - mid;
                    if (sum >= M) // 최소 조건 만족하면 loop 종료
                        break;
                }
            }

            if (sum >= M) {
                left = (int) mid + 1;
                maxH = (int) Math.max(mid, maxH);
            } else
                right = (int) mid - 1;
        }
    }
}