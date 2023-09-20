import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		int n = scan.nextInt(); // 상금 금액
		int c1, c2; // 경우 1, 2의 경우 실제 수령액
		
		// case 1
		c1 = (int)(n * 0.78);
		
		// case 2
		c2 = (int)(n*0.2*0.78+n*0.8);
		
		System.out.println(c1+" "+c2);
		
		scan.close();
	}

}
