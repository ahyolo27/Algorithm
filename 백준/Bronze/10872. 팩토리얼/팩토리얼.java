import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        if (n == 0)
            System.out.println(1);
        else
            System.out.println(factorial(n));
    }

    public static int factorial(int n) {
        int ans = 1;
        for (int i=1;i<=n;i++)
            ans *= i;
        return ans;
    }
}