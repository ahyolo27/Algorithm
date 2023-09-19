import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int n = scan.nextInt(); // 주어지는 수의 개수
        int nlist[] = new int[n]; // 주어지는 수 배열
        int sum = 0; // 소수 개수

        for (int i = 0; i < n; i++) {
            int t = scan.nextInt();
            nlist[i] = t;
        }

        for (int i = 0; i < n; i++) {
            if (nlist[i] == 1)
                sum += 0;
            else {
                int cnt = 0;
                for (int j = 2; j < nlist[i]; j++) {
                    if (nlist[i] % j == 0) {
                        cnt += 1;
                    }
                }
                if (cnt == 0)
                    sum += 1;
            }
        }

        System.out.println(sum);

        scan.close();
    }
}