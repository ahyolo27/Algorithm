import java.util.*;
import java.io.*;

class Solution
{
	public static void main(String args[]) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int t = Integer.parseInt(br.readLine());
		ArrayList<int[][]> ans = new ArrayList<>();

		for (int i = 0; i < t; i++) {
			int n = Integer.parseInt(br.readLine());
			int snail[][] = new int[n][n];

			int dx[] = { 0, 1, 0, -1 };
			int dy[] = { 1, 0, -1, 0 };
			int x = 0;
			int y = 0;
			int dir = 0;

			for (int j = 1; j <= n * n; j++) {
				snail[x][y] = j;

				int nextx = x + dx[dir];
				int nexty = y + dy[dir];

				if (nextx < 0 || nexty < 0 || nextx >= n || nexty >= n || snail[nextx][nexty] != 0)
					dir = (dir + 1) % 4;

				x = x + dx[dir];
				y = y + dy[dir];
			}

			ans.add(snail);
		}

		for (int i = 0; i < t; i++) {
			System.out.print("#" + (i + 1) + "\n");
			printArray(ans.get(i));
		}

	}

	public static void printArray(int a[][]) {
		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < a[i].length; j++) {
				System.out.print(a[i][j] + " ");
			}
			System.out.println();
		}

	}
}