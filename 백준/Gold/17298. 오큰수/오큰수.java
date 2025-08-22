import java.util.*;
import java.io.*;

public class Main {
    static int N, numbers[], ans[];

    public static void main(String[] args) throws IOException {
        input(); // 입력
        getNGE();

        StringBuilder sb = new StringBuilder();
        for (int a : ans)
            sb.append(a).append(" ");
        System.out.println(sb);
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        numbers = new int[N];
        ans = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++)
            numbers[i] = Integer.parseInt(st.nextToken());
    }

    static void getNGE() {
        Stack<Integer> stack = new Stack<>();

        for (int i = numbers.length - 1; i >= 0; i--) {
            while (!stack.isEmpty()) {
                int n = stack.peek();
                if (numbers[i] >= n)
                    stack.pop();
                else {
                    ans[i] = n;
                    stack.push(numbers[i]);
                    break;
                }
            }
            if (stack.isEmpty()) {
                ans[i] = -1;
                stack.push(numbers[i]);
            }
        }
    }
}