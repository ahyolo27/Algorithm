import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // input
        String s = br.readLine();

        // 컵 쌓기
        int sum = 10;
        char before = s.charAt(0);

        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == before)
                sum += 5;
            else
                sum += 10;
            before = s.charAt(i); // 갱신
        }

        System.out.println(sum);
    }
}