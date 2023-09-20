import java.util.Scanner;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        ArrayList<String> r1 = new ArrayList<String>();
        ArrayList<String> r2 = new ArrayList<String>();
        ArrayList<String> r3 = new ArrayList<String>();
        ArrayList<String> r4 = new ArrayList<String>();
        ArrayList<String> r5 = new ArrayList<String>();

        String s1 = scan.nextLine();
        for (int i = 0; i < 15; i++) {
            if (i >= s1.length())
                r1.add("");
            else
                r1.add(String.valueOf(s1.charAt(i)));

        }

        String s2 = scan.nextLine();
        for (int i = 0; i < 15; i++) {
            if (i >= s2.length())
                r2.add("");
            else
                r2.add(String.valueOf(s2.charAt(i)));
        }

        String s3 = scan.nextLine();
        for (int i = 0; i < 15; i++) {
            if (i >= s3.length())
                r3.add("");
            else
                r3.add(String.valueOf(s3.charAt(i)));
        }

        String s4 = scan.nextLine();
        for (int i = 0; i < 15; i++) {
            if (i >= s4.length())
                r4.add("");
            else
                r4.add(String.valueOf(s4.charAt(i)));
        }

        String s5 = scan.nextLine();
        for (int i = 0; i < 15; i++) {
            if (i >= s5.length())
                r5.add("");
            else
                r5.add(String.valueOf(s5.charAt(i)));
        }

        // 세로 읽기
        for (int i = 0; i < 15; i++) {
            System.out.print(r1.get(i) + r2.get(i) + r3.get(i) + r4.get(i) + r5.get(i));
        }

        scan.close();
    }

}
