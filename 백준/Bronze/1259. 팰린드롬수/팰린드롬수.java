import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        while (true) {
            String str = br.readLine();

            if (str.equals("0")) break; // 종료

            int midIdx = str.length() / 2;

            String sub = str.substring(0, midIdx);
            int subIdx = 0;
            boolean isRight = true;

            if (str.length() % 2 == 0) {
                for (int i = str.length() - 1; i >= midIdx; i--) {
                    if (sub.charAt(subIdx++) != str.charAt(i)) {
                        isRight = false;
                        break;
                    }
                }
            } else {
                for (int i = str.length() - 1; i >= midIdx + 1; i--) {
                    if (sub.charAt(subIdx++) != str.charAt(i)) {
                        isRight = false;
                        break;
                    }
                }
            }

            if (isRight)
                sb.append("yes\n");
            else
                sb.append("no\n");
        }

        System.out.println(sb);
    }
}