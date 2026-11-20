import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String me = br.readLine();
        String target = br.readLine();

        if (target.length() <= me.length()) System.out.println("go");
        else System.out.println("no");
    }
}