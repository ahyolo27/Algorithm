import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        BigInteger N = new BigInteger(br.readLine());
        BigInteger M = new BigInteger(br.readLine());

        System.out.println(N.add(M));
        System.out.println(N.subtract(M));
        System.out.println(N.multiply(M));
    }
}