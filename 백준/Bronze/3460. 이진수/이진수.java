import java.io.*;

public class Main {
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++)
            calc(Integer.parseInt(br.readLine()));

        System.out.println(sb);
    }

    static void calc(int N) {
        int idx = 0;

        while (N > 0) {
            if (N % 2 == 1)
                sb.append(idx).append(" ");
            N /= 2;
            idx++;
        }
        sb.append("\n");
    }
}