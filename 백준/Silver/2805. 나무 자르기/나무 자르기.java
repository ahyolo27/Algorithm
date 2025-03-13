import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static long M, len[], left, right, maxH;

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
            right = Math.max(len[i], right);
        }
    }

    static void cut() {
        while (left <= right) {
            long mid = (left + right) / 2;

            long sum = 0;
            for (int i = 0; i < len.length; i++)
                sum += Math.max((len[i] - mid), 0);

            if (sum >= M) {
                left = mid + 1;
                maxH = Math.max(mid, maxH);
            } else
                right = mid - 1;
        }
    }
}