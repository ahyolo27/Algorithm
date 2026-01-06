import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        Stack<Character> stack = new Stack<>();
        Stack<Integer> length = new Stack<>();

        // input
        String str = br.readLine();
        for (int i = 0; i < str.length(); i++)
            stack.push(str.charAt(i));

        // solution
        while (!stack.isEmpty()) {
            char c = stack.pop();

            if ('0' <= c && c <= '9') { // 숫자
                length.push(1);
            } else if (c == ')') { // 괄호 시작
                length.push(-1);
            } else if (c == '(') { // 괄호 내부 계산 후 값 누적
                int innerLen = 0;
                while (length.peek() != -1)
                    innerLen += length.pop();
                length.pop();
                
                int k = stack.pop() - '0';
                length.push(innerLen * k);
            }
        }

        // output
        int answer = 0;
        while (!length.isEmpty())
            answer += length.pop();
        System.out.println(answer);
    }
}