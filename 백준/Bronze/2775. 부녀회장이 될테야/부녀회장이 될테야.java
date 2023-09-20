import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int t = scan.nextInt(); // 테스트 케이스의 수
        int k[] = new int[t]; // 층
        int n[] = new int[t]; // 호

        // 테스트 케이스 별 k와 n 입력받고 저장
        for (int i = 0; i < t; i++) {
            int kk = scan.nextInt();
            int nn = scan.nextInt();
            k[i] = kk;
            n[i] = nn;
        }

        for (int i = 0; i < t; i++) {
            System.out.println(getNumber(k[i], n[i]));
        }

        scan.close();
    }

    static int tmp[][] = new int[15][15];

    static int getNumber(int k, int n) {
        int sum = 0;
        if (k == 0) { // 0층인 경우
            sum += n;
        } else if (k == 1) {
            for (int i = 1; i <= n; i++)
                sum += i;
        } else if (tmp[k][n] != 0) { // 1층 이상이고 값을 계산한 적이 있는 경우
            sum += tmp[k][n];
        } else { // 1층 이상이고 값을 처음 계산하는 경우
            for (int i = 1; i <= n; i++) {
                sum += getNumber(k - 1, i);
                tmp[k - 1][i] = getNumber(k - 1, i);
            }
        }
        return sum;
    }

}
