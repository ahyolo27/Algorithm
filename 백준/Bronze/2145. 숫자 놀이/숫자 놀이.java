import java.util.*;

public class Main {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		ArrayList<Integer> ans = new ArrayList<>();

		while (true) {
			int n = scan.nextInt();
			if (n == 0)
				break;

			while (true) {
				if (n < 10) {
					ans.add(n);
					break;
				}

				ArrayList<Integer> arr = new ArrayList<>();
				while (true) {
					if (n == 0)
						break;
					arr.add(n % 10);
					n /= 10;
				}

				for (int i = 0; i < arr.size(); i++) {
					n += arr.get(i);
				}
			}
		}

		for (int i = 0; i < ans.size(); i++)
			System.out.println(ans.get(i));

		scan.close();
	}
}