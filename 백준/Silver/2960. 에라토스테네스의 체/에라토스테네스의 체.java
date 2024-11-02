import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer str = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(str.nextToken());
        int k = Integer.parseInt(str.nextToken());

        int cnt = 0;

        // 1. 2부터 n까지 모든 정수를 적는다.
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 2; i <= n; i++)
            list.add(i);

        while (!list.isEmpty()) {

            // 2. 아직 지우지 않은 수 중 가장 작은 수를 찾는다. 이것을 P라고 하고, 이 수는 소수이다.
            int idx = 0;
            while (true) {
                if (!isPrime(list.get(idx)))
                    idx++;
                else
                    break;
            }
            int p = list.get(idx);

            // 3. P를 지우고 아직 지우지 않은 P의 배수를 크기 순서대로 지운다.
            list.remove(idx);
            cnt++;
            if (cnt == k) {
                System.out.println(p);
                return;
            }

            for (int i = 2; i * p <= list.get(list.size() - 1); i++) {
                int j = 0;
                if (list.contains(i * p)) {
                    j = list.indexOf(i * p);
                    list.remove(j);
                    cnt++;
                }
                if (cnt == k) {
                    System.out.println(i * p);
                    return;
                }
            }
        }
    }

    public static boolean isPrime(int n) {
        for (int i = n - 1; i >= 2; i--) {
            if (n % i == 0)
                return false;
        }
        return true;
    }
}