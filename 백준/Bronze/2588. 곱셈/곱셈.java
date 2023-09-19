import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		String a = scan.next();
		String b = scan.next();

		int bList[] = new int[3];

		bList[0] = Character.getNumericValue(b.charAt(0));
		bList[1] = Character.getNumericValue(b.charAt(1));
		bList[2] = Character.getNumericValue(b.charAt(2));
		

		int num1 = Integer.parseInt(a);
		int num2 = Integer.parseInt(b);
		int num3 = num1 * bList[2];
		int num4 = num1 * bList[1];
		int num5 = num1 * bList[0];
		int num6 = num1 * num2;

		System.out.print(num3+"\n"+num4+"\n"+num5+"\n"+num6);
		
		scan.close();
	}

}