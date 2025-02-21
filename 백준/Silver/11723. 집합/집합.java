import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int M = Integer.parseInt(br.readLine());
        int bit = 0;

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            String s = st.nextToken();

            switch (s) {
                case "add": {
                    int x = Integer.parseInt(st.nextToken());
                    bit |= (1 << (x - 1));
                    break;
                }
                case "remove": {
                    int x = Integer.parseInt(st.nextToken());
                    bit = bit & ~(1 << (x - 1));
                    break;
                }
                case "check": {
                    int x = Integer.parseInt(st.nextToken());
                    sb.append((bit & (1 << (x - 1))) != 0 ? "1\n" : "0\n");
                    break;
                }
                case "toggle": {
                    int x = Integer.parseInt(st.nextToken());
                    bit ^= (1 << (x - 1));
                    break;
                }
                case "all": {
                    bit |= (~0);
                    break;
                }
                default: // empty
                    bit &= 0;
            }
        }

        System.out.println(sb);
    }
}