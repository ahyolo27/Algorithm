import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        boolean factor = false;
        boolean multiple = false;

        while (true) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if (a == 0 && b == 0)
                break;

            factor = false; // 초기화
            multiple = false;

            if (b % a == 0)
                factor = true;
            if (a % b == 0)
                multiple = true;

            if (factor)
                sb.append("factor").append("\n");
            else if (multiple)
                sb.append("multiple").append("\n");
            else
                sb.append("neither").append("\n");
        }

        System.out.println(sb);
    }
}