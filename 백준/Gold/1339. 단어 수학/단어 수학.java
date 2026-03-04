import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int sum;
    static List<String> words = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        input();
        
        calc();
        
        System.out.println(sum);
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++)
            words.add(br.readLine());
    }

    static void calc() {
        Map<Character, Integer> map = new HashMap<>();

        // map에 가중치를 포함해 넣기
        for (String word : words) {
            int a = 0;
            for (char c : word.toCharArray()) {
                int weight = (int) Math.pow(10, word.length() - a - 1);
                map.compute(c, (k, v) -> v == null ? weight : v + weight);
                a++;
            }
        }

        // 가중치 기준 정렬하기
        List<Character> keySet = new ArrayList<>(map.keySet());
        keySet.sort((k1, k2) -> map.get(k2) - map.get(k1));

        // 문자에 숫자 대입하기
        int number = 9;
        for (char c : keySet) {
            sum += map.get(c) * number;
            number--;
        }
    }
}