import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		int n = scan.nextInt();
		int m = scan.nextInt();

		int nArr[] = new int[n];
		
		for (int t =0; t<n; t++) {
			nArr[t] = 0;
		}

		for (int t = 0; t < m; t++) {

			int i = scan.nextInt();
			int j = scan.nextInt();
			int k = scan.nextInt();
			
			for (int z = i; z<=j;z++) {
				nArr[z-1] = k;
			}
		}
		
		for (int t=0;t<n;t++)
			System.out.print(nArr[t]+" ");
		
		scan.close();
	}

}