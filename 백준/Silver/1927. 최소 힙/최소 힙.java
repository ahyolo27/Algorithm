import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        ArrayList<Integer> ans = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            int x = Integer.parseInt(br.readLine());

            if (x > 0)
                minHeap.add(x);
            else {
                if (!minHeap.isEmpty()) {
                    ans.add(minHeap.peek());
                    minHeap.remove();
                } else
                    ans.add(0);
            }
        }

        for (int i = 0; i < ans.size(); i++)
            System.out.println(ans.get(i));

    }
}