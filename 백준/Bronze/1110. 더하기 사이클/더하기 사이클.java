import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int n = scan.nextInt(); // 사이클을 계산할 정수

        int a = n / 10;
        int b = n % 10;

        System.out.println(getCycle(n, a, b));

        scan.close();
    }

    static int sum = 0; // 사이클 횟수

    static int getCycle(int n, int a, int b) {

        sum++;

        int c = (a + b) % 10;
        int ans = 10 * b + c;

        if (ans == n)
            return sum;
        else
            return getCycle(n, b, c);
    }
}
