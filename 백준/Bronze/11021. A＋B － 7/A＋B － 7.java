import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		// 테스트 케이스 개수
		int t = scan.nextInt();

		// 테스트 값 보관할 배열
		int addArray[] = new int[t];

		for (int i = 0; i < t; i++) {
			// 테스트 케이스
			int a = scan.nextInt();
			int b = scan.nextInt();

			addArray[i] = a + b;
		}

		for (int i = 0; i < t; i++)
			System.out.println("Case #" + (i + 1) + ": " + addArray[i]);
		
		scan.close();
	}

}