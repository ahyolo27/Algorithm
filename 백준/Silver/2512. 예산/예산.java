import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, money[], max, total, M, ans;

    public static void main(String[] args) throws IOException {

        input();
        calc();

        System.out.println(ans);
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        money = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            money[i] = Integer.parseInt(st.nextToken());
            total += money[i];
            max = Math.max(money[i], max);
        }

        M = Integer.parseInt(br.readLine());
    }

    static void calc() {
        int left = 0;
        int right = max;

        while (left <= right) {
            int mid = (left + right) / 2;

            if (isPossible(mid)) {
                ans = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
    }

    static boolean isPossible(int mid) {
        long sum = 0;
        for (int m : money)
            sum += Math.min(mid, m);
        return sum <= M;
    }
}