import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        Set<String> set = new HashSet<>();

        String s = br.readLine();

        int tmp = 1;
        while (tmp <= s.length()) {
            String sub = "";
            for (int i = 0; i < s.length(); i++) {
                if (i + tmp <= s.length())
                    sub = s.substring(i, i + tmp);
                set.add(sub);
            }
            tmp++;
        }

        bw.write(set.size()+"\n");

        bw.flush();
        bw.close();
    }
}