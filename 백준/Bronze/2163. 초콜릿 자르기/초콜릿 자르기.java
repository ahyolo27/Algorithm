import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		int n = scan.nextInt();
		int m = scan.nextInt();

		int ans = (n - 1) + n * (m - 1);

		System.out.println(ans);

		scan.close();
	}

}
