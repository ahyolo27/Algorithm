import java.util.*;
import java.io.*;

public class Solution {
    static int T, N, M, cnt;
    static ArrayList<int[]> noList = new ArrayList<>();
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {

            input(); // 입력
            count(0, new ArrayList<>()); // 부분집합 개수 세기

            System.out.println("#" + t + " " + cnt);
        }
    }

    static void input() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        noList.clear();
        cnt = 1; // 초기화

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            if (!noList.contains(new int[]{a, b}) && !noList.contains(new int[]{b, a}))
                noList.add(new int[]{a, b});
        }
    }

    static void count(int depth, ArrayList<Integer> list) {
        if (depth == N) {
            if (check(list) && !list.isEmpty())
                cnt++;
            return;
        }

        list.add(depth + 1);
        count(depth + 1, list);
        list.remove((Integer) (depth + 1));
        count(depth + 1, list);
    }

    static boolean check(ArrayList<Integer> list) {
        for (int i = 0; i < noList.size(); i++) {
            int a = noList.get(i)[0];
            int b = noList.get(i)[1];
            if (list.contains(a) && list.contains(b))
                return false;
        }
        return true;
    }
}