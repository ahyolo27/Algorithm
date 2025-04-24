import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // Input
        int L = Integer.parseInt(br.readLine());
        char c[] = new char[L];
        String str = br.readLine();
        for (int i = 0; i < L; i++)
            c[i] = str.charAt(i);
        final int R = 31;
        final int M = 1234567891;

        // Calc
        int hash = 0;
        for (int i = 0; i < L; i++)
            hash += (c[i] - 96) * (int) Math.pow(R, i);
        hash %= M;
        System.out.println(hash);
    }
}