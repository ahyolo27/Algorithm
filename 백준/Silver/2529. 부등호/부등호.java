import java.io.*;

public class Main {
    static int K;
    static String min, max, arr[];
    static boolean isSelected[];

    public static void main(String[] args) throws IOException {

        input(); // 입력

        for (int i = 0; i < 10; i++) {
            isSelected = new boolean[10];
            isSelected[i] = true;
            find(0, i, String.valueOf(i)); // 조건을 만족하는 수열 찾기
        }

        System.out.println(max + "\n" + min);
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        K = Integer.parseInt(br.readLine());
        arr = br.readLine().split(" ");
        min = String.valueOf(Long.MAX_VALUE);
        max = String.valueOf(Long.MIN_VALUE);
    }

    static void find(int depth, int before, String s) { // 깊이, 직전 수, 최종 수열
        if (depth == K) {
            if (Long.parseLong(min) > Long.parseLong(s))
                min = s;
            else if (Long.parseLong(max) < Long.parseLong(s))
                max = s;
            return;
        }

        for (int i = 0; i < 10; i++) {
            if (!isSelected[i]) {
                isSelected[i] = true;

                if (arr[depth].equals("<") && before < i)
                    find(depth + 1, i, s + String.valueOf(i));
                else if (arr[depth].equals(">") && before > i)
                    find(depth + 1, i, s + String.valueOf(i));

                isSelected[i] = false;
            }
        }
    }
}