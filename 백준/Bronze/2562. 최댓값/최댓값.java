import java.util.*;
import java.io.*;

public class Main {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < 9; i++)
            list.add(Integer.parseInt(br.readLine()));

        System.out.println(Collections.max(list));
        System.out.println(list.indexOf(Collections.max(list)) + 1);
    }
}