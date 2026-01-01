import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Problem {
        int i, s;

        Problem(int i, int s) {
            this.i = i;
            this.s = s;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        int sum = 0;

        PriorityQueue<Problem> problems = new PriorityQueue<>((o1, o2) -> o2.s - o1.s);
        Queue<Integer> ac = new LinkedList<>();

        for (int i = 1; i <= N; i++) {
            int s = Integer.parseInt(br.readLine());

            sum += s;

            if (s == 0)
                ac.add(i);
            else
                problems.add(new Problem(i, s));
        }

        if (sum != N - 1) { // 답을 만들수가 없는 경우
            System.out.println(-1);
            return;
        }

        for (int i = 0; i < N; i++) {
            if (ac.isEmpty()) { // 더이상 맞을게 없는 경우
                System.out.println(-1);
                return;
            }

            int p = ac.poll();
            sb.append(p).append("\n");

            if (i == N - 1) break;// 종료

            if (problems.isEmpty()) { // 더이상 틀릴게 없는 경우
                System.out.println(-1);
                return;
            }

            Problem problem = problems.poll();
            sb.append(problem.i).append("\n");
            problem.s--;

            if (problem.s == 0)
                ac.add(problem.i);
            else
                problems.add(problem);
        }

        System.out.println(sb);
    }
}