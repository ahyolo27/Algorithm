import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		int x = scan.nextInt(); // 총 금액
		int n = scan.nextInt(); // 구매한 물건의 종류

		int add = 0; // 계산된 금액 (누적)

		for (int i = 0; i < n; i++) {
			int a = scan.nextInt(); // 각 물건의 가격
			int b = scan.nextInt(); // 각 물건의 개수

			add += a * b;
		}
		
		if (x == add)
			System.out.print("Yes");
		else
			System.out.print("No");

		scan.close();
	}

}
