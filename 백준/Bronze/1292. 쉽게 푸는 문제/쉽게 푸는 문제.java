import java.util.Scanner;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int a = scan.nextInt(); // 구간의 시작
        int b = scan.nextInt(); // 구간의 끝

        ArrayList<Integer> al = new ArrayList<Integer>();

        int i = 1;
        int cnt = 0; // al의 크기
        while (true) {
            boolean cont = false;
            for (int j = 0; j < i; j++) {
                al.add(i);
                cnt++;
                if (cnt == b + 1) {
                    cont = true;
                    break;
                }
            }
            if (cont)
                break;
            i++;
        }

        int sum = 0; // 합
        for (int j = a - 1; j < b; j++) {
            sum += al.get(j);
        }

        System.out.println(sum);

        scan.close();
    }

}
