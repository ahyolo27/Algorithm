import java.io.*;
import java.util.*;

class Solution {
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int t = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= t; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a[] = new int[Integer.parseInt(st.nextToken())];
			int b[] = new int[Integer.parseInt(st.nextToken())];

			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < a.length; i++)
				a[i] = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < b.length; i++)
				b[i] = Integer.parseInt(st.nextToken());

			int max = a.length > b.length ? getMax(a, b) : getMax(b, a);

			System.out.println("#" + tc + " " + max);
		}
	}

	static int getMax(int a[], int b[]) {
		int max = 0;
		for (int i = 0; i < a.length - b.length + 1; i++) {
			int tmp = 0;
			for (int j = 0; j < b.length; j++)
				tmp += a[j + i] * b[j];
			max = tmp > max ? tmp : max;
		}
		return max;
	}
}