import java.util.*;
import java.io.*;

public class Main {
    static ArrayList<Integer> S = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int M = Integer.parseInt(br.readLine());

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            String s = st.nextToken();

            switch (s) {
                case "add": {
                    int x = Integer.parseInt(st.nextToken());
                    if (!S.contains(x))
                        S.add(x);
                    break;
                }
                case "remove": {
                    int x = Integer.parseInt(st.nextToken());
                    if (S.contains(x))
                        S.remove((Integer) x);
                    break;
                }
                case "check": {
                    int x = Integer.parseInt(st.nextToken());
                    if (S.contains(x))
                        sb.append("1").append("\n");
                    else
                        sb.append("0").append("\n");
                    break;
                }
                case "toggle": {
                    int x = Integer.parseInt(st.nextToken());
                    if (S.contains(x))
                        S.remove((Integer) x);
                    else
                        S.add(x);
                    break;
                }
                case "all": {
                    S.clear();
                    for (int j = 1; j <= 20; j++)
                        S.add(j);
                    break;
                }
                default: // empty
                    S.clear();
            }
        }

        System.out.println(sb);
    }
}