import java.io.*;
import java.util.*;

class Solution
{
	public static void main(String args[]) throws Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int ans[] = new int[10];

		for (int tc = 0; tc < 10; tc++) {

			int n = Integer.parseInt(br.readLine());
			String s = br.readLine();
			StringTokenizer st = new StringTokenizer(s);

			int building[] = new int[n];
			for (int i = 0; i < n; i++)
				building[i] = Integer.parseInt(st.nextToken());

			for (int i = 2; i < n - 2; i++) {
				int cnt = 256;
				// left
				cnt = (building[i] - building[i - 2]) < cnt ? (building[i] - building[i - 2]) : cnt;
				cnt = (building[i] - building[i - 1]) < cnt ? (building[i] - building[i - 1]) : cnt;
				// right
				cnt = (building[i] - building[i + 1]) < cnt ? (building[i] - building[i + 1]) : cnt;
				cnt = (building[i] - building[i + 2]) < cnt ? (building[i] - building[i + 2]) : cnt;

				if (cnt > 0)
					ans[tc] += cnt;
			}
		}

		for (int i = 0; i < 10; i++) {
			System.out.println("#" + (i + 1) + " " + ans[i]);
		}
	}
}