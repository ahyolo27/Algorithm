import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N[] = new int[9];
        int sum = 0;

        for (int i = 0; i < 9; i++) {
            N[i] = Integer.parseInt(br.readLine());
            sum += N[i];
        }

        for (int i = 0; i < 8; i++) {
            for (int j = i + 1; j < 9; j++)
                if (sum - N[i] - N[j] == 100) { // 전체의 합에서 2명을 빼서 100이 되는 경우 찾기
                    N[i] = 0;
                    N[j] = 0;
                    break;
                }
            if (N[i] == 0)
                break;
        }

        for (int i = 0; i < 9; i++)
            if (N[i] != 0)
                System.out.println(N[i]);
    }
}