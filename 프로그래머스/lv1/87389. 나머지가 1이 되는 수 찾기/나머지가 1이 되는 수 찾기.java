import java.util.*;

public class Solution {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int n = scan.nextInt();

        System.out.println(solution(n));

        scan.close();
    }

    static int solution(int n) {
        int min = n;

        for (int i = n; i > 0; i--) {
            if (n % i == 1)
                min = min > i ? i : min;
        }

        return min;
    }
}