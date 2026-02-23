import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String str = br.readLine();

        String ans = "";

        for (int i = 1; i < str.length() - 1; i++) {
            for (int j = i + 1; j < str.length(); j++) {

                String a = new StringBuilder(str.substring(0, i)).reverse().toString();
                String b = new StringBuilder(str.substring(i, j)).reverse().toString();
                String c = new StringBuilder(str.substring(j)).reverse().toString();

                String candidate = a + b + c;

                if (ans.isEmpty() || ans.compareTo(candidate) > 0)
                    ans = candidate;
            }
        }

        System.out.println(ans);
    }
}