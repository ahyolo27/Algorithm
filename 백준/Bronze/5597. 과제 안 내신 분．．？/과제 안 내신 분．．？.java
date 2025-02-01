import java.util.*;
import java.io.*;

public class Main {
    static ArrayList<Integer> list = new ArrayList<>();
    static boolean visited[] = new boolean[31];

    public static void main(String[] args) throws IOException {

        input(); // 입력
        findStudent(); // 안 낸 학생 찾기

        for (int i = 1; i < 31; i++) {
            if (!visited[i])
                System.out.println(i);
        }
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 0; i < 28; i++)
            list.add(Integer.parseInt(br.readLine()));
    }

    static void findStudent() {
        Collections.sort(list);
        for (int i = 0; i < 28; i++) {
            for (int j = 1; j <= 30; j++)
                if (list.get(i) == j)
                    visited[j] = true;
        }
    }
}