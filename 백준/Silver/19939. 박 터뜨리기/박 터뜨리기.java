import java.io.*;
import java.util.*;

public class Main {
    static int N, K, ans;
    static int basket[];

    public static void main(String[] args) throws IOException {
        input(); // 입력
        play();
        System.out.println(ans);
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        basket = new int[K];
    }

    static void play() {
        if (K == 2) { // 바구니가 2개인 경우
            if (N == K)
                ans = -1;
            else if (N % 2 == 1) // 공이 홀수
                ans = 1;
            else // 공이 짝수
                ans = 2;
            return;
        } else if (N < K) {
            ans = -1;
            return;
        }

        // 바구니가 3개 이상인 경우
        int cnt = 0;
        int startIdx = 0;
        boolean check = true;

        while (check) {
            for (int i = startIdx; i < K; i++) {
                basket[i]++;
                cnt++;
                if (cnt == N || startIdx == K - 1) {
                    check = false;
                    break;
                }
            }
            startIdx++;
        }

        if (cnt == N) { // 공이 남지 않은 경우
            if (checkDuplicated())
                ans = -1;
        } else { // 공이 남은 경우
            N = (N - cnt) % K;
            cnt = 0;
            for (int i = K - 1; i >= 0; i--) {
                basket[i]++;
                cnt++;
                if (cnt == N)
                    break;
            }
        }

        // 모든 공 배치 완료
        if (checkDuplicated())
            ans = -1;
        else
            ans = basket[K - 1] - basket[0];
    }

    static boolean checkDuplicated() { // 중복인 경우 true
        for (int i = 0; i < K - 1; i++)
            for (int j = i + 1; j < K; j++)
                if (basket[i] == basket[j]) { // 중복 개수가 들어있는 경우
                    return true;
                }
        return false;
    }
}