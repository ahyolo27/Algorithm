import java.io.*;
import java.util.*;

class Solution {
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());

		StringTokenizer st = new StringTokenizer(br.readLine());
		ArrayList<Integer> list = new ArrayList<>();

		for (int i = 0; i < n; i++)
			list.add(Integer.parseInt(st.nextToken()));

		Collections.sort(list);

		System.out.println(list.get(n / 2));
	}
}