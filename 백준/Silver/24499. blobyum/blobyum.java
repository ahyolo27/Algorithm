import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		int n = scan.nextInt(); // 애플파이의 개수
		int k = scan.nextInt(); // 먹으려는 애플파이의 개수

		int taste[] = new int[n];
		for (int i = 0; i < n; i++)
			taste[i] = scan.nextInt();

		int all_sum[] = new int[n]; // 누적합 계산
		int sum = 0;
		for (int i = 0; i < n; i++) {
			sum += taste[i];
			all_sum[i] = sum;
		}

		int max = 0; // 맛의 최댓값
		if (n == 1)
			max = taste[0];
		else if (k == n)
			max = all_sum[n - 1];
		else {
			for (int i = 0; i < n; i++) {

				int blob = 0; // 블롭이 먹은 파이 맛의 합

				if (i == 0)
					blob = all_sum[k - 1];
				else if (i + k <= n)
					blob = all_sum[i + k - 1] - all_sum[i - 1];
				else
					blob = all_sum[n - 1] - all_sum[i - 1] + all_sum[i + k - 1 - n];

				// 최댓값 갱신
				max = max > blob ? max : blob;
			}
		}

		System.out.println(max);

		scan.close();
	}
}