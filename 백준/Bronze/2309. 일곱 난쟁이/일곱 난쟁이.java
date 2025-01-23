import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        ArrayList<Integer> list = new ArrayList<>();
        int sum = 0;

        // 세팅
        for (int i = 0; i < 9; i++) {
            int h = Integer.parseInt(br.readLine());
            list.add(h);
            sum += h;
        }

        // 전체 값에서 두명을 뺐을 때 100이 되는 경우 찾기
        for (int i = 0; i < list.size() - 1; i++) {
            for (int j = i + 1; j < list.size(); j++) {
                if (sum - list.get(i) - list.get(j) == 100) {
                    list.remove(j);
                    list.remove(i);

                    // 남은 난쟁이의 키를 오름차순으로 정렬 후 출력
                    Collections.sort(list);
                    for (int k = 0; k < 7; k++)
                        System.out.println(list.get(k));
                    return;
                }
            }
        }
    }
}