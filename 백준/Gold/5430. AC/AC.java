import java.io.*;
import java.util.*;

public class Main {
    static int T, len;
    static String command;
    static Deque<Integer> deque = new ArrayDeque<>();
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {

        T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            input(); // 입력
            calc(); // 연산
        }
        System.out.println(sb);
    }

    static void input() throws IOException {
        command = br.readLine();
        len = Integer.parseInt(br.readLine());

        String arrays = br.readLine();
        String newArrays = arrays.substring(1, arrays.length() - 1);

        deque.clear();

        if (!newArrays.isEmpty()) {
            String nums[] = newArrays.split(",");
            for (String s : nums)
                deque.add(Integer.parseInt(s));
        }
    }

    static void calc() {
        boolean reverse = false; // flag

        for (int i = 0; i < command.length(); i++) {
            char com = command.charAt(i);

            switch (com) {
                case 'R': // 순서 뒤집기
                    reverse = !reverse;
                    break;
                case 'D': // 첫번째 원소 삭제
                    if (deque.isEmpty()) {
                        sb.append("error").append("\n");
                        return;
                    } else {
                        if (!reverse) deque.pollFirst();
                        else deque.pollLast();
                    }
            }
        }
        
        sb.append("[");
        if (!deque.isEmpty()) {
            if (!reverse) {
                sb.append(deque.pollFirst());
                while(!deque.isEmpty())
                    sb.append(",").append(deque.pollFirst());
            } else {
                sb.append(deque.pollLast());
                while(!deque.isEmpty())
                    sb.append(",").append(deque.pollLast());
            }
        }
        sb.append("]").append("\n");
    }
}