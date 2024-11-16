import java.util.*;
import java.io.*;

class Solution
{
	public static void main(String args[]) throws Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int ans[] = new int[10];

		for (int t = 0; t < 10; t++) {
			int count = Integer.parseInt(br.readLine());
			StringTokenizer st = new StringTokenizer(br.readLine());

			int box[] = new int[100];
			for (int i = 0; i < 100; i++)
				box[i] = Integer.parseInt(st.nextToken());

			while (count > 0) {
				int max = Arrays.stream(box).max().getAsInt();
				int min = Arrays.stream(box).min().getAsInt();

				if (max - min <= 1) // 평탄화
					break;

				for (int i = 0; i < 100; i++) { // 제일 높은 상자--
					if (box[i] == max) {
						box[i]--;
						break;
					}
				}

				for (int i = 0; i < 100; i++) { // 제일 낮은 상자++
					if (box[i] == min) {
						box[i]++;
						break;
					}
				}

				count--;
			}

			int max = Arrays.stream(box).max().getAsInt();
			int min = Arrays.stream(box).min().getAsInt();
			ans[t] = max - min;

		}
		for (int i = 0; i < 10; i++)
			System.out.println("#" + (i + 1) + " " + ans[i]);
	}
}