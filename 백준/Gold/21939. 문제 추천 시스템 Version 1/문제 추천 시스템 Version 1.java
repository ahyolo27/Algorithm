import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static class Problem {
        int number, level;

        Problem(int number, int level) {
            this.number = number;
            this.level = level;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        HashMap<Integer, Problem> map = new HashMap<>();
        TreeSet<Problem> problems = new TreeSet<>((o1, o2) -> {
            if (o1.level == o2.level)
                return o2.number - o1.number;
            return o2.level - o1.level;
        });

        int N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            int number = Integer.parseInt(st.nextToken());
            int level = Integer.parseInt(st.nextToken());

            map.put(number, new Problem(number, level));
            problems.add(map.get(number));
        }

        int M = Integer.parseInt(br.readLine());
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            String command = st.nextToken();
            
            if (command.equals("add")) {
                int number = Integer.parseInt(st.nextToken());
                int level = Integer.parseInt(st.nextToken());

                map.put(number, new Problem(number, level));
                problems.add(map.get(number));

            } else if (command.equals("recommend")) {
                int n = Integer.parseInt(st.nextToken());

                if (n == 1)  // 가장 어려운 문제
                    sb.append(problems.first().number).append("\n");
                else  // -1 -> 가장 쉬운 문제
                    sb.append(problems.last().number).append("\n");

            } else { // solved
                int number = Integer.parseInt(st.nextToken());

                problems.remove(map.get(number));
            }
        }

        System.out.println(sb);
    }
}