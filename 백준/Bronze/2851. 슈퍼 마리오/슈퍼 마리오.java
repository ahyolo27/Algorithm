import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int mr[] = new int[10]; // 버섯 점수 저장 배열

        // 배열에 버섯 점수를 각각 저장
        for (int i = 0; i < 10; i++) {
            int a = scan.nextInt();
            mr[i] = a;
        }

        int sum = 0; // 누적 점수
        int cnt = 0; // 버섯 인덱스
        int ans = 0; // 정답

        while (true) {
            sum += mr[cnt];
            if (sum == 100) {
                ans = 100;
                break;
            } else if (sum > 100) {
                if ((sum - 100) == (100 - (sum - mr[cnt])))
                    ans = sum;
                else
                    ans = (sum - 100) < (100 - (sum - mr[cnt])) ? (sum) : (sum - mr[cnt]);
                break;
            }

            if (cnt == mr.length - 1) {
                ans = sum;
                break;
            } else
                cnt++;
        }

        System.out.println(ans);

        scan.close();
    }

}
