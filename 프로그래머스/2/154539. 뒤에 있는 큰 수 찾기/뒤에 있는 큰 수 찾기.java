import java.util.*;

class Solution {
    public int[] solution(int[] numbers) {
        int[] answer = new int[numbers.length];
        Arrays.fill(answer, -1);

        Stack<Integer> stack = new Stack<>();

        for (int i = numbers.length - 1; i >= 0; i--) {
            while (!stack.isEmpty()) {
                if (stack.peek() <= numbers[i])
                    stack.pop();
                else {
                    answer[i] = stack.peek();
                    stack.push(numbers[i]);
                    break;
                }
            }
            if (stack.isEmpty())
                stack.push(numbers[i]);
        }

        return answer;
    }
}