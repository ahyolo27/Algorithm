import java.util.*;

public class Main {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		int n = scan.nextInt();
		int score[] = new int[n];

		for (int i = 0; i < n; i++)
			score[i] = scan.nextInt();

		int sum = 0; // 점수의 합
		int weight = 0; // 가중치

		for (int i = 0; i < n; i++) {
			if (score[i] == 0) {
				weight = 0;
			}
			if (score[i] == 1) {
				sum = sum + 1 + weight;
				weight++;
			}
		}

		System.out.println(sum);

		scan.close();
	}
}