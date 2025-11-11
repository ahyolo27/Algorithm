import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.TreeMap;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        TreeMap<String, Integer> map = new TreeMap<>();
        int total = 0;

        while (true) {
            String tree = br.readLine();
            if (tree == null) break;

            if (map.containsKey(tree)) {
                map.replace(tree, map.get(tree) + 1);
            } else {
                map.put(tree, 1);
            }
            total++;
        }

        for (String tree : map.keySet()) {
            double rate = (double) map.get(tree) / total * 100.0;
            sb.append(tree).append(" ").append(String.format("%.4f", rate)).append("\n");
        }
        System.out.print(sb);
    }
}