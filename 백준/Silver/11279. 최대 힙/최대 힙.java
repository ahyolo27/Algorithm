import java.util.*;
import java.io.*;

class Main {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return -Integer.compare(o1, o2);
            }
        });

        int n = Integer.parseInt(br.readLine());
        for (int cs = 0; cs < n; cs++) {
            int x = Integer.parseInt(br.readLine());

            if(x == 0) {
                if(maxHeap.isEmpty())
                    System.out.println(0);
                else
                    System.out.println(maxHeap.poll());
            }
            else
                maxHeap.add(x);
        }
    }
}