import java.util.*;

public class Main {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		int t = scan.nextInt(); // 테스트 케이스의 수

		int ans[] = new int[t]; // 정답을 담을 배열

		for (int i = 0; i < t; i++) {
			int n = scan.nextInt(); // 문서의 수
			int m = scan.nextInt(); // 몇 번째로 인쇄되었는지 궁금한 문서가 현재 큐에서 몇 번째에 놓여있는지?
			int value[] = new int[n]; // 중요도를 담는 함수

			for (int j = 0; j < n; j++) {
				value[j] = scan.nextInt(); // 중요도
			}

			if (value.length > 1)
				ans[i] = search(m, value);
			else
				ans[i] = 1;
		}

		for (int i = 0; i < t; i++) {
			System.out.println(ans[i]);
		}

		scan.close();
	}

	static int search(int m, int value[]) {

		ArrayList<int[]> data = new ArrayList<>(); // 인덱스와 값을 저장할 리스트

		for (int i = 0; i < value.length; i++)
			data.add(new int[] { i, value[i] }); // {index, value(중요도)}

		int cnt = 0; // 출력 횟수
		boolean check = false; // while문 빠져나오기용

		while (true) {

			// 최대값 갱신
			int max = 0; // 중요도 중 최대값
			int maxIndex = 0; // 중요도 중 최대값의 인덱스
			for (int i = 0; i < data.size(); i++) {
				if (max < data.get(i)[1]) {
					max = data.get(i)[1];
					maxIndex = i;
				}
			}

			for (int i = 0; i <= maxIndex; i++) {
				if (data.get(0)[0] == m && data.get(0)[1] == max) {
					check = true;
					break;
				} else if (i == maxIndex) {
					data.remove(0);
					cnt++;
				} else {
					// 큐 가장 뒤로 보내기
					int tmp[] = data.get(0);
					data.remove(0);
					data.add(tmp);
				}

			}
			if (check)
				break;

		}
		return ++cnt;

	}
}