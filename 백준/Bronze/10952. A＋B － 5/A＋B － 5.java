import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        ArrayList<Integer> ans = new ArrayList<>();

        while (true) {
            int a = scan.nextInt();
            int b = scan.nextInt();

            if (a == 0 && b == 0)
                break;
            else {
                ans.add(a + b);
            }
        }

        for (int i = 0; i < ans.size(); i++)
            System.out.println(ans.get(i));

        scan.close();
    }
}