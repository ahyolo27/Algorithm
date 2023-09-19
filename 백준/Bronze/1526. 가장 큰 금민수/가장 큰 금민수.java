import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		int n = scan.nextInt();

		if (checkNum(n))
			System.out.println(n);
		else {
			while (!checkNum(n)) {
				n--;
			}
			System.out.println(n);
		}

		scan.close();
	}

	static boolean checkNum(int n) { // 금민수인지 아닌지 확인하는 함수
		int tmp = 0;

		while (true) {
			if (n % 10 != 4 && n % 10 != 7)
				tmp++;
			n /= 10;

			if (n == 0)
				break;
		}

		if (tmp == 0)
			return true;
		else
			return false;

	}

}