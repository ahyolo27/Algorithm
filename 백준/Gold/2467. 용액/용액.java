import java.io.*;
import java.util.*;

public class Main {
    static int N, num[], ans[];
    static long min;

    public static void main(String[] args) throws IOException {

        input(); // 입력
        search(0, num.length - 1);

        System.out.println(ans[0] + " " + ans[1]);
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        num = new int[N];
        ans = new int[2];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++)
            num[i] = Integer.parseInt(st.nextToken());

        min = Long.MAX_VALUE;
    }

    static void search(int left, int right) {
        if (left == right)
            return;

        long sum = num[left] + num[right];

        if (min > Math.abs(sum)) {
            min = Math.abs(sum);
            ans[0] = num[left];
            ans[1] = num[right];
        }

        if (sum > 0)
            search(left, right - 1);
        else if (sum < 0)
            search(left + 1, right);
    }
}