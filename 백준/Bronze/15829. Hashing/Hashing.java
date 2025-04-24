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
        long hash = 0;
        long pow = 1;
        for (int i = 0; i < L; i++) {
            hash = (hash + (c[i] - 'a' + 1) * pow) % M;
            pow = (pow * R) % M;
        }
        System.out.println(hash);
    }
}