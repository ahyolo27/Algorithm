import java.util.*;
import java.io.*;

class Solution
{
	public static void main(String args[]) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int t = Integer.parseInt(br.readLine());

		for (int i = 0; i < t; i++) {
			int idx = Integer.parseInt(br.readLine());
			int score[] = new int[101];
			int ans = 0;

			String s = br.readLine();
			StringTokenizer st = new StringTokenizer(s);

			for (int j = 0; j < 1000; j++) {
				int n = Integer.parseInt(st.nextToken());
				score[n]++;
			}

			int max = score[0];

			for (int j = 1; j < 101; j++) {
				if (max < score[j]) {
					max = score[j];
					ans = j;
				} else if (max == score[j]) {
					if (ans < j)
						ans = j;
				}
			}
            
			System.out.println("#" + idx + " " + ans);
		}
	}
}