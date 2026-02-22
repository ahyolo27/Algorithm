import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int quotient = N / 5;
        int remains = N % 5;

        if (remains % 2 != 0) {
            quotient -= 1;
            remains += 5;
        }

        if (quotient < 0) // 2, 5로 나누어 떨어지지 않는 경우
            System.out.println(-1);
        else
            System.out.println(quotient + remains / 2);
    }
}