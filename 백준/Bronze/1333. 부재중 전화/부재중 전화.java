import java.io.*;
import java.util.*;

public class Main {
    static int N, L, D, ans;

    public static void main(String[] args) throws IOException {

        input(); // 입력
        call(); // 부재중 전화

        System.out.println(ans);
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());
    }

    static void call() {
        int d = D;
        int l = L;
        int nextl = (L + 5);

        for (int n = 1; n <= N; n++) {
            if (l > d)
                while (l > d) {
                    d += D;
                }

            // 전화가 노래 사이에 오거나 끝나고 오는 경우
            if ((l <= d && d < nextl) || (n == N && l <= d)) {
                ans = d;
                return;
            }

            l = nextl + L;
            nextl = l + 5;
        }
    }
}