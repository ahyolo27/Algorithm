import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int num = scan.nextInt(); // 진짜 약수의 개수

        int min = 1000001;
        int max = 1;

        for (int i = 0; i < num; i++) {
            int a = scan.nextInt();
            min = a < min ? a : min;
            max = a > max ? a : max;
        }

        System.out.println(min * max);

        scan.close();
    }

}
