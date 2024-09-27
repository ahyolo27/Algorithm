import java.io.*;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int sum = 0;

        for (int i = 0; i < n; i++) {
            String s = br.readLine();

            ArrayList<Character> list = new ArrayList<>();
            int check = 0;

            if (s.length() == 1)
                sum++;
            else {
                list.add(s.charAt(0));
                for (int j = 1; j < s.length(); j++) {

                    if (s.charAt(j) != s.charAt(j - 1)) {
                        if (!list.contains(s.charAt(j)))
                            list.add(s.charAt(j));
                        else
                            check++;
                    } else continue;

                }
                if (check == 0)
                    sum++;
            }
        }

        System.out.println(sum);
    }
}