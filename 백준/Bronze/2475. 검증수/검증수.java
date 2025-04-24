import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // Input
        int nums[] = new int[5];
        for (int i = 0; i < 5; i++)
            nums[i] = Integer.parseInt(st.nextToken());

        // Calc
        int sum = 0;
        for (int n : nums)
            sum += n * n;

        System.out.println(sum % 10);
    }
}