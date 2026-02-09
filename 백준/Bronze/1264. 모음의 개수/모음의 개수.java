import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        String s;
        while (true) {
            s = br.readLine();
            if (s.equals("#")) break;

            int cnt = 0;
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                
                if ('A' <= c && c <= 'Z') c += 32; // 대문자 -> 소문자

                if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u')
                    cnt++;
            }

            sb.append(cnt).append("\n");
        }

        System.out.println(sb);
    }
}