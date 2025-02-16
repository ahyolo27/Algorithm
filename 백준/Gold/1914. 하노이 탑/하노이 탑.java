import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class Main {
    static int N;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {

        input(); // 입력

        System.out.println(BigInteger.TWO.pow(N).subtract(BigInteger.ONE)); // 이동 횟수

        if (N <= 20) { // N이 20 이하인 경우 과정 출력
            hanoi(N, 1, 2, 3);
            System.out.println(sb);
        }
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
    }

    static void hanoi(int n, int from, int tmp, int to) {
        if (n == 0) // 다 옮긴 경우
            return;

        hanoi(n - 1, from, to, tmp);
        sb.append(from).append(" ").append(to).append('\n');
        hanoi(n - 1, tmp, from, to);
    }
}