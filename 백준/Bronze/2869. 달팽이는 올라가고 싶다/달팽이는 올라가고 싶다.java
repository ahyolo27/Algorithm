import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int a = scan.nextInt(); // 올라간 길이
        int b = scan.nextInt(); // 미끄러진 길이
        int v = scan.nextInt(); // 나무 막대의 높이

        int day = 0; // 걸리는 날 수

        day += (v - b) / (a - b);
        if ((v - b) % (a - b) != 0)
            day++;

        System.out.println(day);

        scan.close();
    }

}
