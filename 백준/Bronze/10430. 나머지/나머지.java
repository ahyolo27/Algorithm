import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		int a = scan.nextInt();
		int b = scan.nextInt();
		int c = scan.nextInt();

		int ans1 = (a + b) % c;
		int ans2 = ((a % c) + (b % c)) % c;
		int ans3 = (a * b) % c;
		int ans4 = ((a % c) * (b % c)) % c;

		System.out.print(ans1 + "\n" + ans2 + "\n" + ans3 + "\n" + ans4);

		scan.close();
	}

}
