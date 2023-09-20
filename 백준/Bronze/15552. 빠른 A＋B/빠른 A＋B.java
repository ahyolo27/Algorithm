import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		// 테스트 케이스의 개수
		int t = Integer.parseInt(br.readLine());

		int addArray[] = new int[t];

		for (int i = 0; i < t; i++) {
			// 테스트 케이스
			StringTokenizer ab = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(ab.nextToken());
			int b = Integer.parseInt(ab.nextToken());
			addArray[i] = a+b;
		}
		
		for (int i = 0; i < t; i++) 
			bw.write(String.valueOf(addArray[i])+"\n");

		bw.flush();
		bw.close();

	}

}