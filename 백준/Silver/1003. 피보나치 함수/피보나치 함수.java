import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException { // 예외처리
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());
        String ans[] = new String[t];

        for (int i = 0; i < t; i++) {
            int n = Integer.parseInt(br.readLine());

            if (n == 0)
                ans[i] = 1 + " " + 0;
            else if (n == 1)
                ans[i] = 0 + " " + 1;
            else {
                int fibo[][] = new int[n + 1][2];

                fibo[0][0] = 1;
                fibo[1][1] = 1;

                for (int j = 2; j <= n; j++) {
                    fibo[j][0] = fibo[j - 1][0] + fibo[j - 2][0];
                    fibo[j][1] = fibo[j - 1][1] + fibo[j - 2][1];
                }

                ans[i] = fibo[n][0] + " " + fibo[n][1];
            }
        }

        for (int i = 0; i < ans.length; i++)
            System.out.println(ans[i]);
    }
}