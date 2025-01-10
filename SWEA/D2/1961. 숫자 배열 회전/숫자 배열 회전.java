import java.io.*;
import java.util.*;

class Solution {
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int t = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= t; tc++) {
			int n = Integer.parseInt(br.readLine());
			int list[][] = new int[n][n];
			int ans90[][] = new int[n][n];
			int ans180[][] = new int[n][n];
			int ans270[][] = new int[n][n];

			for (int i = 0; i < n; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < n; j++)
					list[i][j] = Integer.parseInt(st.nextToken());
			}

			// 90도
			for (int i = 0; i < n; i++)
				for (int j = 0; j < n; j++)
					ans90[i][j] = list[n - j - 1][i];

			// 180도
			for (int i = 0; i < n; i++)
				for (int j = 0; j < n; j++)
					ans180[i][j] = ans90[n - j - 1][i];

			// 270도
			for (int i = 0; i < n; i++)
				for (int j = 0; j < n; j++)
					ans270[i][j] = ans180[n - j - 1][i];

			// answer
			System.out.println("#" + tc);
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++)
					System.out.print(ans90[i][j]);
				System.out.print(" ");
				for (int j = 0; j < n; j++)
					System.out.print(ans180[i][j]);
				System.out.print(" ");
				for (int j = 0; j < n; j++)
					System.out.print(ans270[i][j]);
				System.out.println();
			}

		}
	}
}