import java.util.*;

public class Main {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		int x = scan.nextInt(); // 구하고 싶은 분수의 번호

		// m/n
		int m = 0;
		int n = 0;

		int cnt = 1; // 분수 번호
		int index = 1; // 대각선 내 분수 개수

		int ans[][] = new int[1][2]; // 정답을 담을 배열

		while (true) {

			// 초기 설정
			if (index % 2 == 1) {
				m = index;
				n = 1;
			} else {
				m = 1;
				n = index;
			}

			boolean check = false; // cnt 확인할 때 사용

			// 분수 계산
			for (int i = 0; i < index; i++) {
				ans[0][0] = m;
				ans[0][1] = n;

				if (index % 2 == 1) {
					m--;
					n++;
				} else {
					m++;
					n--;
				}

				if (cnt == x) {
					check = true;
					break;
				} else {
					check = false;
					cnt++;
				}
			}

			if (check)
				break;
			else if (!check && cnt - 1 == x)
				break;
			else
				index++;
		}

		System.out.println(ans[0][0] + "/" + ans[0][1]);

		scan.close();
	}
}