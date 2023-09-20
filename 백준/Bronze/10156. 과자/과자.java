import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int k = scan.nextInt(); // 과자 한 개의 가격
        int n = scan.nextInt(); // 사려고 하는 과자의 개수
        int m = scan.nextInt(); // 현재 동수가 가진 돈

        int ans = k * n - m;

        if (ans <= 0)
            System.out.println(0);
        else
            System.out.println(ans);

        scan.close();
    }
}
