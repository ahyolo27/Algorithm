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

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++)
            numbers[i] = Integer.parseInt(st.nextToken());

        ans = new int[N];
        Arrays.fill(ans, -1);
    }

    static void getNGE() {
        Deque<Integer> stack = new ArrayDeque<>();

        for (int i = numbers.length - 1; i >= 0; i--) {
            while (!stack.isEmpty() && numbers[i] >= stack.peek()) {
                stack.pop();
            }
            if (!stack.isEmpty())
                ans[i] = stack.peek();
            stack.push(numbers[i]);
        }
    }
}