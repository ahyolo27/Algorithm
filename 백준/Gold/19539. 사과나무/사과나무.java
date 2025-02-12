import java.io.*;
import java.util.*;

public class Main {
    static int N, sum;
    static int h[];

    public static void main(String[] args) throws IOException {

        input(); // 입력
        if (water())  // 나무에 물 주기
            System.out.println("YES");
        else
            System.out.println("NO");
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine()); // 나무의 개수
        h = new int[N]; // 나무의 희망 높이

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            h[i] = Integer.parseInt(st.nextToken());
            sum += h[i];
        }
    }

    static boolean water() {
        if (sum == 0) // 모두 빈 땅인 경우
            return true;
        else if (sum % 3 != 0) // 총합의 3의 배수인 경우에만 아래 수행
            return false;

        int cnt1 = 0;
        int cnt2 = 0;

        for (int i = 0; i < N; i++) {
            cnt1 += h[i] % 2;
            cnt2 += h[i] / 2;
        }

        return cnt1 <= cnt2;
    }
}