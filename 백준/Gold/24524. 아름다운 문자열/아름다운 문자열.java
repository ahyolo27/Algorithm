import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static String S, T;
    static int answer;

    public static void main(String[] args) throws IOException {

        input(); // 입력
        calc(); // 계산

        System.out.println(answer);
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        S = br.readLine();
        T = br.readLine();
    }

    static void calc() {
        int pos[] = new int[26];
        Arrays.fill(pos, -1);
        for (int i = 0; i < T.length(); i++)
            pos[T.charAt(i) - 'a'] = i;

        int dp[] = new int[T.length()];

        for (int i = 0; i < S.length(); i++) {
            int idx = pos[S.charAt(i) - 'a'];

            if (idx == -1) continue; // T에 해당하는 문자가 아닌 경우

            if (idx == 0) { // 가장 첫번째 원소
                dp[idx]++;
            } else { // 그 외 원소
                if (dp[idx - 1] >= dp[idx] + 1)
                    dp[idx]++;
            }
        }

        answer = dp[T.length() - 1];
    }
}