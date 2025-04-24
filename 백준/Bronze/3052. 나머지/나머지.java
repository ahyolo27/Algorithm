import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // Input
        int nums[] = new int[10];
        for (int i = 0; i < 10; i++)
            nums[i] = Integer.parseInt(br.readLine());

        // Calc
        boolean isExist[] = new boolean[42];
        for (int n : nums)
            isExist[n % 42] = true;

        // Ans
        int cnt = 0;
        for (boolean b : isExist)
            if (b) cnt++;
        System.out.println(cnt);
    }
}