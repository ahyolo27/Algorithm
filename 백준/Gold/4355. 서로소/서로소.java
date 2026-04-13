import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        while (true) {
            long N = Long.parseLong(br.readLine());

            if (N == 0) break; // 종료

            if (N == 1) {
                sb.append(0).append("\n");
                continue;
            }

            long cnt = N; // N 이하에서 N과 서로소인 수의 개수

            for (long i = 2; i * i <= N; i++) {
                if (N % i == 0) {
                    while (N % i == 0)
                        N /= i;
                    cnt -= cnt / i; // i의 배수 제거
                }
            }

            if (N > 1)
                cnt -= cnt / N; // 마지막 남은 소인수도 계산

            sb.append(cnt).append("\n");
        }

        System.out.println(sb);
    }
}