import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int t = scan.nextInt(); // 테스트 케이스
        String text[] = new String[t];

        for (int i = 0; i < t; i++) {
            String s = scan.next();
            text[i] = s;
        }

        for (int i = 0; i < t; i++) {
            if (text[i].length() != 1) {
                System.out.print(text[i].charAt(0));
                System.out.print(text[i].charAt(text[i].length() - 1) + "\n");
            } else {
                System.out.print(text[i].charAt(0));
                System.out.print(text[i].charAt(0) + "\n");
            }
        }

        scan.close();
    }

}
