import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	static int stack[] = new int[10000];
	static int sp = 0; // StackPointer

	static void push(int x) {
		stack[sp] = x;
		sp++;
	}

	static void pop() {
		if (sp == 0) // 스택이 비어있는 경우
			System.out.println(-1);
		else {
			System.out.println(stack[--sp]);
			stack[sp] = 0;
		}
	}

	public static void main(String[] args)throws IOException {
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
				System.out.println(sp);
			else if (s[i][0].equals("empty"))
				if (sp == 0) // 스택이 비어있는 경우
					System.out.println("1");
				else
					System.out.println("0");
			else if (s[i][0].equals("top")) {
				if (sp == 0) // 스택이 비어있는 경우
					System.out.println(-1);
				else
					System.out.println(stack[sp - 1]);
			}
		}
	}
}