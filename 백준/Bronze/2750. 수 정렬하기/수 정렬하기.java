import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        ArrayList<Integer> al = new ArrayList<Integer>();

        int n = scan.nextInt();

        for (int i = 0; i < n; i++) {
            int a = scan.nextInt();
            al.add(a);
        }

        Collections.sort(al);

        for (int i = 0; i < n; i++) {
            System.out.println(al.get(i));
        }

        scan.close();

    }
}