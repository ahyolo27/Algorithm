import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // Input
        int N = Integer.parseInt(br.readLine());

        // Calc
        int ans = 0;
        for (int i = 0; i < N; i++) {
            int num = i;
            int sum = num;
            while (num != 0) {
                sum += num % 10;
                num /= 10;
            }
            if (sum == N) {
                ans = i;
                break;
            }
        }
        System.out.println(ans);
    }
}