import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        if (k == 1) {
            System.out.println(1);
            return;
        }

        // 약수 구하기
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 1; i <= n / 2; i++) {
            if (n % i == 0)
                list.add(i);
        }
        list.add(n);

        // k번째 약수 구하기
        if (list.size() < k)
            System.out.println(0);
        else System.out.println(list.get(k - 1));
    }
}