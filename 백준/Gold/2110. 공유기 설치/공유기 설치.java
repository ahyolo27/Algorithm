import java.io.*;
import java.util.*;

public class Main {
    static int N, C, left, right, home[], ans;

    public static void main(String[] args) throws IOException {

        input(); // 입력
        set();

        System.out.println(ans);
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        home = new int[N];

        for (int i = 0; i < N; i++)
            home[i] = Integer.parseInt(br.readLine());

        Arrays.sort(home);
        left = 1;
        right = home[N - 1] - home[0];
        ans = left;
    }

    static void set() {
        while (left <= right) {
            int cnt = 1; // 초기화
            int prev = home[0];

            int mid = (left + right) / 2;

            for (int i = 1; i < home.length; i++) {
                if (home[i] - prev < mid)
                    continue;
                cnt++;
                prev = home[i];
            }

            if (cnt >= C) {
                left = mid + 1;
                ans = Math.max(mid, ans);
            } else {
                right = mid - 1;
            }
        }
    }
}