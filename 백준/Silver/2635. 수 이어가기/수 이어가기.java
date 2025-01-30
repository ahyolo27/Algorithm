import java.util.*;
import java.io.*;

public class Main {
    static int n, n1, n2, max;
    static ArrayList<Integer> ans = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        calc(); // 계산

        System.out.println(ans.size());
        for (int i = 0; i < ans.size(); i++)
            System.out.print(ans.get(i) + " ");
    }

    static void calc() {
        if (n == 1) {
            ans.add(1);
            ans.add(1);
            ans.add(0);
            ans.add(1);
            return;
        }

        for (int i = 1; i < n; i++) {
            ArrayList<Integer> list = new ArrayList<>();
            n1 = n;
            n2 = i;

            list.add(n1);
            list.add(n2);

            while (true) {
                int tmp = n1 - n2;

                if (tmp < 0) // 음수이면 중단
                    break;
                else
                    list.add(tmp);

                n1 = n2;
                n2 = tmp;
            }
            if (list.size() > max) {
                max = list.size();
                ans = list;
            }
        }
    }
}