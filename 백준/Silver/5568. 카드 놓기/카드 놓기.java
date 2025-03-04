import java.util.*;
import java.io.*;

public class Main {
    static int N, K, cards[];
    static boolean isSelected[];
    static Set<String> ans = new HashSet<>();

    public static void main(String[] args) throws IOException {

        input(); // 입력
        makeNumber(0, ""); // 카드 뽑기

        System.out.println(ans.size());
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        K = Integer.parseInt(br.readLine());
        cards = new int[N];
        isSelected = new boolean[N];

        for (int i = 0; i < N; i++)
            cards[i] = Integer.parseInt(br.readLine());
    }

    static void makeNumber(int depth, String str) {
        if (depth == K) {
            ans.add(str);
            return;
        }

        for (int i = 0; i < cards.length; i++) {
            if (!isSelected[i]) {
                isSelected[i] = true;
                makeNumber(depth + 1, str + cards[i]);
                isSelected[i] = false;
            }
        }
    }
}