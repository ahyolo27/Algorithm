import java.io.*;
import java.util.*;

public class Main {
    static int T, K;
    static TreeMap<Integer, Integer> q = new TreeMap<>();
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {

        T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++)
            setQueue();

        System.out.println(sb);
    }

    static void setQueue() throws IOException {
        q.clear();
        StringTokenizer st;

        K = Integer.parseInt(br.readLine());
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            String op = st.nextToken();
            int n = Integer.parseInt(st.nextToken());

            if (op.equals("I")) {
                if (q.containsKey(n))
                    q.replace(n, q.get(n) + 1);
                else
                    q.put(n, 1);
            } else { // "D"
                if (q.isEmpty())
                    continue;

                if (n == 1) {
                    if (q.get(q.lastKey()) > 1)
                        q.replace(q.lastKey(), q.get(q.lastKey()) - 1);
                    else
                        q.remove(q.lastKey());
                } else {
                    if (q.get(q.firstKey()) > 1)
                        q.replace(q.firstKey(), q.get(q.firstKey()) - 1);
                    else
                        q.remove(q.firstKey());
                }
            }
        }

        if (q.isEmpty())
            sb.append("EMPTY").append("\n");
        else
            sb.append(q.lastKey()).append(" ").append(q.firstKey()).append("\n");
    }
}