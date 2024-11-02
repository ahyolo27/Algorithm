import java.io.*;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        if (n == 1) {
            System.out.println(2);
            return;
        }

        while (true) {
            if (isPalindrome(n) && isPrime(n)) {
                break;
            } else
                n++;
        }

        System.out.println(n);
    }

    public static boolean isPrime(int n) { //소수인가
        for (int i = n - 1; i >= 2; i--) {
            if (n % i == 0)
                return false;
        }
        return true;
    }

    public static boolean isPalindrome(int n) { //팰린드롬인가
        ArrayList<Integer> list = new ArrayList<>();

        while (true) {
            if (n == 0)
                break;

            list.add(n % 10);
            n /= 10;
        }

        int s = list.size();

        for (int idx = 0; idx <= s / 2; idx++) {
            if (list.get(idx) != list.get(s - idx - 1))
                return false;
        }
        return true;
    }
}