import java.io.*;
import java.util.*;

public class Main {
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String s = br.readLine();
		StringTokenizer st = new StringTokenizer(s);

		int h = Integer.parseInt(st.nextToken());
		int w = Integer.parseInt(st.nextToken());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		int cnt = 0;

		for (int i = 0; i < h; i += (n+1)) {
			if(i>=h) break;
			
			for (int j = 0; j < w; j += (m+1)) {
				if(j>=w) break;
				cnt++;
			}
		}
		
		System.out.println(cnt);
		
	}

}