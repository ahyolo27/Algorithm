import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		// 테스트 케이스 개수
		int t = scan.nextInt();

		int aArray[] = new int [t];
		int bArray[] = new int [t];

		for (int i = 0; i < t; i++) {
			// 테스트 케이스
			int a = scan.nextInt();
			int b = scan.nextInt();

			aArray[i] = a;
			bArray[i] = b;
		}

		for (int i = 0; i < t; i++)
			System.out.println("Case #" + (i + 1) + ": " +aArray[i]+" + "+bArray[i]+" = "+ (aArray[i]+bArray[i]));
		
		scan.close();
	}

}