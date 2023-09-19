import java.util.*;

public class Main {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		int n = scan.nextInt(); // 반의 학생 수

		int student[][] = new int[n][5]; // 학생들의 표

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < 5; j++) {
				student[i][j] = scan.nextInt();
			}
		}

		int max = 0; // 같은 반이었던 학생수의 최댓값
		int index = 0; // max일 때의 학생 번호

		for (int i = 0; i < n; i++) {
			ArrayList<Integer> check = new ArrayList<Integer>(); // 같은 반이 된 적 있는 친구 번호 리스트

			for (int j = 0; j < 5; j++) {
				for (int k = 0; k < n; k++) {
					// 같은 반이 된 적 있고 리스트에 중복되지 않는 경우 add
					if (student[i][j] == student[k][j] && !check.contains(k))
						check.add(k);
				}
			}

			// 자신 제거
			check.remove(Integer.valueOf(i));

			if (check.size() > max) {
				max = check.size();
				index = i;
			} else if (check.size() == max)
				index = index < i ? index : i;
		}

		System.out.println(index + 1); // index는 0부터가 아니라 1부터의 값이므로 +1

		scan.close();
	}
}