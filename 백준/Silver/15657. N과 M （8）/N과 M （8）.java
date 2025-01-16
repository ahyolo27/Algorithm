import java.util.*;
import java.io.*;

public class Main {
	static int n;
	static int m;
	static int list[];
	static int ans[];

	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken()); // 자연수 개수
		m = Integer.parseInt(st.nextToken()); // 수열의 길이
		list = new int[n]; // n개의 자연수가 들어있는 배열
		ans = new int[m]; // 정답 담는 배열

		// list에 주어진 자연수를 모두 넣은 뒤 오름차순으로 정렬
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++)
			list[i] = Integer.parseInt(st.nextToken());
		Arrays.sort(list);

		// 탐색 진행
		dfs(0, 0);

	}

	public static void dfs(int idx, int depth) {
		if (depth == m) { // 깊이가 원하는 값(m)일 때 프린트
			for (int i = 0; i < m; i++)
				System.out.print(ans[i] + " ");
			System.out.println();
			return;
		}
		for (int i = idx; i < n; i++) { // 탐색 진행
			ans[depth] = list[i];
			dfs(i, depth + 1);
		}
	}
}