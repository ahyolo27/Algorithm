import java.util.Scanner;
import java.lang.Math;

public class Main {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		int t = scan.nextInt(); // 테스트 케이스의 개수

		int x_1[] = new int[t];
		int y_1[] = new int[t];
		int r_1[] = new int[t];

		int x_2[] = new int[t];
		int y_2[] = new int[t];
		int r_2[] = new int[t];

		for (int i = 0; i < t; i++) {

			int x1 = scan.nextInt(); // 조규현의 좌표 x
			int y1 = scan.nextInt(); // 조규현의 좌표 y
			int r1 = scan.nextInt(); // 조규현이 계산한 류재명과의 거리 r

			int x2 = scan.nextInt(); // 백승환의 좌표 x
			int y2 = scan.nextInt(); // 백승환의 좌표 y
			int r2 = scan.nextInt(); // 백승환이 계산한 류재명과의 거리

			x_1[i] = x1;
			y_1[i] = y1;
			r_1[i] = r1;

			x_2[i] = x2;
			y_2[i] = y2;
			r_2[i] = r2;

		}

		for (int i = 0; i < t; i++) {

			double d = Math.sqrt(Math.pow((x_1[i] - x_2[i]), 2) + Math.pow((y_1[i] - y_2[i]), 2));
			int rMax = r_1[i] >= r_2[i] ? r_1[i] : r_2[i];
			int rMin = r_1[i] >= r_2[i] ? r_2[i] : r_1[i];

			if (x_1[i] == x_2[i] && y_1[i] == y_2[i] && rMax == rMin)
				System.out.println("-1");
			else if ((rMax + rMin) < d || (rMax - rMin) > d)
				System.out.println("0");
			else if ((rMax + rMin) == d || (rMax - rMin) == d)
				System.out.println("1");
			else
				System.out.println("2");
			
		}

		scan.close();
	}

}