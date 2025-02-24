import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int M = Integer.parseInt(br.readLine());
        int N = Integer.parseInt(br.readLine());
        int sum = 0;
        int min = N + 1;

        for (int i = M; i < N + 1; i++) {
            if (isPrime(i)) {
                min = Math.min(i, min);
                sum += i;
            }
        }

        if (sum == 0)
            System.out.println(-1);
        else
            System.out.println(sum + "\n" + min);
    }

    static boolean isPrime(int n) {
        if (n == 1)
            return false;
        if (n < 4)
            return true;

        for (int i = 2; i < Math.sqrt(n + 1); i++)
            if (n % i == 0)
                return false;
        return true;
    }
}
