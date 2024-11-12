import java.io.*;

public class Main {
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		long n = Integer.parseInt(br.readLine());
		long sum = 1;
		int idx = 1;
		
		if (n == 1) {
			System.out.println(1);
			return;
		}

		while (sum<n) {
			sum += 6*idx;
			idx++;
		}
		
		System.out.println(idx);

	}
}