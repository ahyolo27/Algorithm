import java.util.*;
import java.io.*;

public class Main {
	static int N, M, cards[], max;
	static boolean isSelected[];

	public static void main(String[] args) throws IOException {

		input(); // 입력
		choose(0, 0); // 카드 고르기

		System.out.println(max);
	}

	static void input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		cards = new int[N];
		isSelected = new boolean[N];
		max = -1;

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++)
			cards[i] = Integer.parseInt(st.nextToken());
	}

	static void choose(int cnt, int sum) {
		if (cnt == 3) {
			if (sum <= M)
				max = Math.max(sum, max);
			return;
		}

		for (int i = cnt; i < cards.length; i++) {
			if (!isSelected[i]) {
				isSelected[i] = true;
				choose(cnt + 1, sum + cards[i]);
				isSelected[i] = false;
			}
		}
	}
}