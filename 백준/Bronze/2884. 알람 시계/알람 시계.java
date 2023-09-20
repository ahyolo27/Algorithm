import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		int h = scan.nextInt();
		int m = scan.nextInt();
		
		if (m-45>=0)
			m -= 45;
		else {
			int minus = m-45;
			m = 60 + minus;
			h -= 1;
		}
		
		if (h<0)
			h = 24 + h;

		System.out.print(h+" "+m);	

		scan.close();

	}

}