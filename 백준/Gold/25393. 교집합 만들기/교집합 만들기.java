import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        // input
        int N = Integer.parseInt(br.readLine());

        Map<Integer, TreeSet<Integer>> start = new HashMap<>();
        Map<Integer, TreeSet<Integer>> end = new HashMap<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int l = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());

            start.computeIfAbsent(l, s -> new TreeSet<>()).add(r);
            end.computeIfAbsent(r, s -> new TreeSet<>()).add(l);
        }

        int Q = Integer.parseInt(br.readLine());
        for (int i = 0; i < Q; i++) {
            st = new StringTokenizer(br.readLine());
            int l = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());

            // solution

            TreeSet<Integer> endList = start.get(l);

            if (endList == null)
                sb.append(-1).append("\n");
            else {
                Integer endR = endList.ceiling(r);

                if (endR == null)
                    sb.append(-1).append("\n");
                else if (endR == r) // 타겟과 동일한 구간이 있는 경우
                    sb.append(1).append("\n");
                else {
                    TreeSet<Integer> startList = end.get(r);
                    if (startList == null || startList.floor(l) == null)
                        sb.append(-1).append("\n");
                    else
                        sb.append(2).append("\n"); // 타겟을 포함하는 구간이 있는 경우
                }
            }
        }

        // output
        System.out.println(sb);
    }
}