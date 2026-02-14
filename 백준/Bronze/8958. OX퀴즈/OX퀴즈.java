import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());

        String s;
        for (int i = 0; i < N; i++) {
            int ans = 0;

            s = br.readLine();

            int cnt = 1;
            char before = 'X';

            for (int j = 0; j < s.length(); j++) {
                char now = s.charAt(j);

                if (before != now) {
                    cnt = 1; // init
                    if (now == 'O') {
                        ans += cnt;
                        cnt++;
                    }
                } else if (now == 'O') {
                    ans+=cnt;
                    cnt++;
                }

                before = now;
            }

            sb.append(ans).append("\n");
        }

        System.out.println(sb);
    }
}