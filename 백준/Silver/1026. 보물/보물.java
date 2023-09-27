import java.util.*;

public class Main {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		int n = scan.nextInt();

		int a[] = new int[n];
		ArrayList<Integer> na = new ArrayList<>();
		int b[] = new int[n];
		ArrayList<int[]> nb = new ArrayList<>();

		int s = 0; // 정답

		for (int i = 0; i < n; i++)
			na.add(scan.nextInt());

		for (int i = 0; i < n; i++) {
			b[i] = scan.nextInt();
			nb.add(new int[] { i, b[i] });
		}

		// b를 비교해가며 a를 재정렬하기

		for (int j = 0; j < n; j++) {

			// b의 최대값과 그 인덱스 찾기
			int max = 0;
			int maxIndex = 0;
			int index = 0;
			for (int i = 0; i < nb.size(); i++) {
				if (max <= nb.get(i)[1]) {
					max = nb.get(i)[1];
					maxIndex = nb.get(i)[0];
					index = i;
				}
			}

			// a의 최소값 찾기
			int min = Collections.min(na);

			// a의 위치 변경
			a[maxIndex] = min;

			// na, nb 갱신
			na.remove(Integer.valueOf(min));
			nb.remove(index);
		}

		for (int i = 0; i < n; i++)
			s += a[i] * b[i];

		System.out.println(s);

		scan.close();
	}
}