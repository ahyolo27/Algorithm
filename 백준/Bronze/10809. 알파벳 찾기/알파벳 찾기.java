import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String str = br.readLine();
        int cnt[] = new int[26];
        Arrays.fill(cnt, Integer.MAX_VALUE);

        for (int i = 0; i < str.length(); i++) {
            int idx = str.charAt(i) - 'a';
            cnt[idx] = Math.min(i, cnt[idx]);
        }

        StringBuilder sb = new StringBuilder();
        for (int c : cnt)
            sb.append(c == Integer.MAX_VALUE ? -1 : c).append(" ");
        System.out.println(sb);
    }
}