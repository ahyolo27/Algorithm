import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());

        String pattern = br.readLine();
        String parts[] = pattern.split("\\*");

        for (int i = 0; i < N; i++) {
            String target = br.readLine();

            if (target.length() >= parts[0].length() && parts[0].equals(target.substring(0, parts[0].length()))) {
                target = target.substring(parts[0].length());

                if (target.length() >= parts[1].length() && parts[1].equals(target.substring(target.length() - parts[1].length())))
                    sb.append("DA").append("\n");
                else
                    sb.append("NE").append("\n");
            } else
                sb.append("NE").append("\n");
        }
        System.out.println(sb);
    }
}