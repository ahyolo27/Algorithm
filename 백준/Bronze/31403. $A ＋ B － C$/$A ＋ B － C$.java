import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // Input
        int A = Integer.parseInt(br.readLine());
        int B = Integer.parseInt(br.readLine());
        int C = Integer.parseInt(br.readLine());

        // Calc
        StringBuilder sb = new StringBuilder();
        String A_ = String.valueOf(A);
        String B_ = String.valueOf(B);

        // Ans
        sb.append(A + B - C).append("\n");
        sb.append(Integer.parseInt(A_ + B_) - C).append("\n");
        System.out.println(sb);
    }
}