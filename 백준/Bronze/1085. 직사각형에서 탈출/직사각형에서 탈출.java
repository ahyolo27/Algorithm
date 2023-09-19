import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int x = scan.nextInt();
        int y = scan.nextInt();
        int w = scan.nextInt();
        int h = scan.nextInt();

        int min = x;
        if (min > y)
            min = y;
        if (min > (h - y))
            min = h - y;
        if (min > (w - x))
            min = w - x;
        
        System.out.println(min);

        scan.close();
    }
}