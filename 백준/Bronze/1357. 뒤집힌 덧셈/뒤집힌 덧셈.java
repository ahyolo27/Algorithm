import java.util.*;
import java.lang.Math;

public class Main {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		int x = scan.nextInt();
		int y = scan.nextInt();

		x = Rev(x);
		y = Rev(y);

		int ans = Rev(x + y);

		System.out.println(ans);

		scan.close();
	}

	static int Rev(int x) { // 숫자를 뒤집는 함수
		int ans = 0;
		int cnt = -1;
		ArrayList<Integer> a = new ArrayList<>(); // 분리한 숫자를 담을 리스트

		while (true) { // 숫자를 자릿수대로 분리
			a.add(x % 10);
			x /= 10;
			cnt++;

			if (x == 0) // 더이상 나눌 수 없는 경우
				break;
		}

		// 숫자 뒤집기
		for (int i = 0; i < a.size(); i++, cnt--)
			ans += a.get(i) * Math.pow(10, cnt);

		return ans;
	}
}