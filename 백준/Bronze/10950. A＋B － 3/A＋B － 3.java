import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		// 테스트 케이스의 개수
		int t = scan.nextInt();

		// 테스트 답들을 보관한 배열
		int ansArray[] = new int[t];

		for (int i = 0; i < t; i++) {
			// 각각의 테스트 케이스
			int a = scan.nextInt();
			int b = scan.nextInt();

			int ans = a + b;

			ansArray[i] = ans;
		}

		for (int i = 0; i < t; i++)
			System.out.println(ansArray[i]);

		scan.close();
	}

}
