import java.io.*;

public class Solution {
    static int T;
    static long N, cnt;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();

        T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            N = Long.parseLong(br.readLine());

            if (N == 2) cnt = 0;
            else calc();

            sb.append("#").append(t).append(" ").append(cnt).append("\n");
        }
        System.out.println(sb);
    }

    static void calc() {
        cnt = 0; // 초기화

        while (true) {
            if (N == 2)
                return;
            else if (Math.sqrt(N) == (long) Math.sqrt(N)) { // 제곱근을 취했을 때에 정수가 되는 경우
                N = (long) Math.sqrt(N);
                cnt++;
            } else {
                long dis = (long) Math.pow((long) Math.sqrt(N) + 1, 2) - N;
                N = (long) Math.sqrt(N + dis);
                cnt += dis + 1;
            }
        }
    }
}