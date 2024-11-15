import java.util.*;
import java.io.*;

class Solution
{
	public static void main(String args[]) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int t = Integer.parseInt(br.readLine());

		int ans[] = new int[t];

		for (int i = 0; i < t; i++) {
			String s = br.readLine();
			StringTokenizer st = new StringTokenizer(s);
			int sum = 0;

			for (int cnt = 0; cnt < 10; cnt++) {
				int a = Integer.parseInt(st.nextToken());

				if (a % 2 == 1)
					sum += a;
			}
			ans[i] = sum;
		}

		for (int i = 0; i < t; i++)
			System.out.println("#" + (i + 1) + " " + ans[i]);
	}
}