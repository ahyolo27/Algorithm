import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int n = scan.nextInt();

        int i = 1;
        int sum = 0;
        while (n != 0) {
            n -= i;
            i++;
            sum++;
            if (n < i)
                i = 1;
        }

        System.out.println(sum);

        scan.close();
    }

}
