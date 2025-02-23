import java.util.*;
import java.io.*;

public class Main {
    static int N;
    static int PRIME[] = {2, 3, 5, 7};
    static int ODD[] = {1, 3, 7, 9};
    static StringBuilder ans = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        for (int i = 0; i < 4; i++) {
            StringBuilder sb = new StringBuilder();
            sb.append(PRIME[i]);
            makeNumber(sb);
        }

        System.out.println(ans);
    }

    static void makeNumber(StringBuilder sb) {
        if (N == sb.toString().length()) {
            ans.append(sb).append("\n");
            return;
        }

        for (int i = 0; i < 4; i++) {
            int n = Integer.parseInt(sb.toString() + ODD[i]);
            if (isPrime(n)) {
                sb.append(ODD[i]);
                makeNumber(sb);
                sb.deleteCharAt(sb.length() - 1);
            }
        }
    }

    static boolean isPrime(int n) {
        if (n == 1)
            return false;
        if (n == 2 || n == 3)
            return true;

        for (int i = 2; i <= Math.sqrt(n); i++)
            if (n % i == 0) // 소수가 아닌 경우
                return false;
        return true;
    }
}