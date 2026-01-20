import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // input
        int N = Integer.parseInt(br.readLine()); // 인식할 수 있는 알파벳의 종류의 최대 개수
        String str = br.readLine();

        char c[] = new char[str.length()];
        for (int i = 0; i < str.length(); i++)
            c[i] = str.charAt(i);

        int alphabet[] = new int[26];
        int ans = 0;

        // solution
        int start = 0;
        int cnt = 0;

        for (int i = 0; i < c.length; i++) {
            char now = c[i];

            if (alphabet[now - 'a'] == 0)
                cnt++;
            alphabet[now - 'a']++;

            while (cnt > N) {
                alphabet[c[start] - 'a']--;
                if (alphabet[c[start] - 'a'] == 0)
                    cnt--;
                start++;
            }

            ans = Math.max(ans, i - start + 1);
        }

        // output
        System.out.println(ans);
    }
}