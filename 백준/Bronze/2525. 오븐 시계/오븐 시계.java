import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		// 현재 시각
		int h = scan.nextInt();
		int m = scan.nextInt();

		// 필요한 시간
		int c = scan.nextInt();

		if (m + c < 60)
			m += c;
		else {
			m = m + c - 60;
			h += 1;
			while (m>=60) {
				m -= 60;
				h+=1;
			}
		}
	
		if (h >= 24)
			h -= 24;

		System.out.print(h + " " + m);	
		scan.close();
	}

}
