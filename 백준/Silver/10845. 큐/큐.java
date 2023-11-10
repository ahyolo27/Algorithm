import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	static int que[] = new int[10000];
	static int start = 0;
	static int end = 0;

	static void push(int x) {
		que[end] = x;
		end++;
	}

	static void pop() {
		if (end == start)
			System.out.println(-1);
		else {
			System.out.println(que[start]);
			que[start] = 0;
			start++;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine()); // 명령의 수

		String s[][] = new String[n][2];

		for (int i = 0; i < n; i++) {
			String command = br.readLine();
			s[i] = command.split(" ");
		}

		for (int i = 0; i < n; i++) {
			if (s[i][0].equals("push"))
				push(Integer.valueOf(s[i][1]));
			else if (s[i][0].equals("pop"))
				pop();
			else if (s[i][0].equals("size"))
				System.out.println(end - start);
			else if (s[i][0].equals("empty"))
				if (end == start)
					System.out.println(1);
				else
					System.out.println(0);
			else if (s[i][0].equals("front"))
				if (end == start)
					System.out.println(-1);
				else
					System.out.println(que[start]);
			else if (s[i][0].equals("back"))
				if (end == start)
					System.out.println(-1);
				else
					System.out.println(que[end - 1]);
			;
		}
	}
}