import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        ArrayList<Integer> list = new ArrayList<>();

        int k = scan.nextInt();
        int pnt = 0;
        int sum = 0;

        for (int i = 0; i < k; i++) {
            int n = scan.nextInt();

            if (n == 0) {
                pnt--;
                list.remove(pnt);
            } else {
                list.add(n);
                pnt++;
            }
        }

        for (int i = 0; i < list.size(); i++) {
            sum += list.get(i);
        }

        System.out.println(sum);

        scan.close();
    }
}