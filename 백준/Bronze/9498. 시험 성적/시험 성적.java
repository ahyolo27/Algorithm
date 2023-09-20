import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		int score = scan.nextInt();

		if (90 <= score && score <= 100)
			System.out.print("A");
		else if (80 <= score && score < 90)
			System.out.print("B");
		else if (70<=score && score<80)
			System.out.print("C");
		else if (60<=score && score<70)
			System.out.print("D");
		else
			System.out.print("F");

			scan.close();
	}

}
