import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int characters[] = new int[26];

        String str = br.readLine().toLowerCase();

        int max = 0;
        for (int i = 0; i < str.length(); i++) {
            int idx = str.charAt(i) - 97;
            characters[idx]++;
            max = Math.max(max, characters[idx]);
        }

        int cnt = 0;
        int idx = 0;
        for (int i = 0; i < characters.length; i++) {
            if (characters[i] == max) {
                cnt++;
                if (cnt > 1)
                    break;
                idx = i;
            }
        }

        if (cnt == 1)
            System.out.println(Character.toChars(idx + 65));
        else
            System.out.println("?");
    }
}