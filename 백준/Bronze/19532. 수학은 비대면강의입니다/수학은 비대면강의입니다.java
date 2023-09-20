
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		// 상수 입력
		double a = scan.nextInt();
		double b = scan.nextInt();
		double c = scan.nextInt();
		double d = scan.nextInt();
		double e = scan.nextInt();
		double f = scan.nextInt();
		// 변수 선언
		int x, y;

		if (a == 0) {
			y = (int) (c / b);
			x = (int) ((f - e * y) / d);
		}
		else if (b == 0) {
			x = (int)(c/a);
			y = (int)((f-d*x)/e);
		}
		else if (d == 0) {
			y = (int)(f/e);
			x = (int)((c-b*y)/a);
		}
		else if (e == 0) {
			x = (int)(f/d);
			y = (int)((c-a*x)/b);
		}
		else {
			// a, b, c, d가 0이 아닌 경우
			x = (int) ((b * f - e * c) / (b * d - e * a));
			y = (int) (c / b - (a / b) * x);
		}

		

		System.out.println(x + " " + y);
		scan.close();
	}

}
