import java.util.*;
import java.io.*;

public class Main {
    static int n, k, max;
    static int tmperatures[];

    public static void main(String[] args) throws IOException {

        input(); // 입력

        findMax(); // 온도의 합이 가장 큰 경우 찾기

        System.out.println(max);
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        tmperatures = new int[n];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++)
            tmperatures[i] = Integer.parseInt(st.nextToken());
    }

    static void findMax() {
        max = Integer.MIN_VALUE;

        for (int i = 0; i <= n - k; i++) {
            int sum = 0;
            for (int j = 0; j < k; j++)
                sum += tmperatures[i + j];
            max = Math.max(max, sum);
        }
    }
}