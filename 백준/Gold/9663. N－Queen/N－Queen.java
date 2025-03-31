import java.io.*;
import java.util.*;

public class Main {
    static int N, cnt;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        dfs(0, new ArrayList<>());

        System.out.println(cnt);
    }

    public static void dfs(int currentRow, ArrayList<Integer> list) {
        if (currentRow == N) { // 보드판 끝까지 모두 순회한 경우
            cnt++;
            return;
        }

        for (int col = 0; col < N; col++) {
            if (isPossible(list, col)) {
                list.add(col);
                dfs(currentRow + 1, list);
                list.remove(list.size() - 1);
            }
        }
    }

    public static boolean isPossible(ArrayList<Integer> list, int currentCol) {
        int currentRow = list.size(); // 현재 행에서 어느 열에 놓을지 결정

        for (int i = 0; i < currentRow; i++)
            if (currentCol == list.get(i) || Math.abs(list.get(i) - currentCol) == Math.abs(currentRow - i)) // 수직(같은 열)이거나 대각선인 경우
                return false;

        return true;
    }
}