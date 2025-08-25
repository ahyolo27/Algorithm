import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        String vps[] = new String[N];
        for (int i = 0; i < N; i++)
            vps[i] = br.readLine();

        Stack<Character> stack = new Stack<>();

        for (String str : vps) {
            stack.clear(); // 스택 초기화

            for (int j = 0; j < str.length(); j++) {
                char now = str.charAt(j);
                if (stack.isEmpty()) {
                    stack.push(now);
                } else {
                    if (stack.peek() == '(' && now == ')')
                        stack.pop();
                    else
                        stack.push(now);
                }
            }
            if (stack.isEmpty())
                sb.append("YES").append("\n");
            else
                sb.append("NO").append("\n");
        }

        System.out.println(sb);
    }
}