import java.util.*;

class Solution {
    boolean solution(String s) {
        if (s.charAt(0) == ')') return false; // 시작부터 틀린 경우
        
        Stack<Character> stack = new Stack<>();
        
        for(char c: s.toCharArray()) {
            if (stack.isEmpty() && c == ')') return false; // 시작부터 틀린 경우 22
            
            else if (stack.isEmpty())
                stack.push(c);
            else {
                if (stack.peek() == '(' && c==')')
                    stack.pop();
                else
                    stack.push(c);
            }
        }

        return stack.isEmpty();
    }
}