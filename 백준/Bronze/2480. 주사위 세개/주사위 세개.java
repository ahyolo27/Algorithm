import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		int a = scan.nextInt();
		int b = scan.nextInt();
		int c = scan.nextInt();
		
		int money;
		
		if (a==b && b==c)
			money = 10000 + a*1000;
		else if (a==b)
			money = 1000 + a*100;
		else if (b==c)
			money = 1000 + b*100;
		else if (c==a)
			money = 1000 + c*100;
		else {
			a = a>b?a:b;
			int max = a>c?a:c;
			money = max*100;
		}
		
		System.out.print(money);
		
		scan.close();
	}

}