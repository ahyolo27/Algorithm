import java.util.*;
import java.math.*;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        final int PC = 10;

        int t = scan.nextInt();
        int answers[] = new int[t];

        for (int i = 0; i < t; i++) {
            int a = scan.nextInt();
            int b = scan.nextInt();

            int data = 1;
            for (int j = 0; j<b;j++){
                data *= a;
                data %= PC;
            }
            if (data == 0)
                data = 10;

            answers[i] = data;
        }

        for (int i = 0; i < answers.length; i++) {
            System.out.println(answers[i]);
        }

        scan.close();
    }
}