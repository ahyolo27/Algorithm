import java.util.*;
import java.io.*;

public class Solution {
    static String s;
    static Stack<Character> stack = new Stack<>();
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        for (int t = 1; t <= 10; t++) {

            input(); // 입력

            if (check())
                System.out.println("#" + t + " " + 1);
            else
                System.out.println("#" + t + " " + 0);
        }
    }

    static void input() throws IOException {
        br.readLine();
        s = br.readLine();
        stack.clear();
    }

    static boolean check() {
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (isOpen(c)) // 여는 괄호이면 push
                stack.push(c);
            else if (!isOpen(c) && stack.isEmpty()) // 닫는 괄호이고 스택이 비어있으면 종료
                return false;
            else if (match(c) == stack.peek()) // 닫는 괄호이고 짝이 맞으면 pop
                stack.pop();
            else // 닫는 괄호이고 짝이 맞지 않으면
                return false;
        }
        return stack.isEmpty(); // stack이 비어있으면 유효한 괄호이므로 true 반환
    }

    static char match(char a) {
        char b = '-';
        switch (a) {
            case '>':
                return '<';
            case '}':
                return '{';
            case ')':
                return '(';
            case ']':
                return '[';
            default:
                return b;
        }
    }

    static boolean isOpen(char a) {
        return a == '<' || a == '{' || a == '(' || a == '[';
    }
}