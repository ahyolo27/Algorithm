import java.io.*;
import java.util.*;

public class Main {
    static int N, max;
    static String maxTitle;
    static HashMap<String, Integer> books = new HashMap<>();

    public static void main(String[] args) throws IOException {

        input(); // 입력
        find(); // 책 찾기

        System.out.println(maxTitle);
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        maxTitle = null; // 초기값 설정

        for (int i = 0; i < N; i++) {
            String name = br.readLine();
            if (!books.containsKey(name))
                books.put(name, 1); // 책이 없는 경우 새로 삽입
            else
                books.replace(name, books.get(name) + 1); // 책이 있는 경우 카운트 증가
            max = Math.max(books.get(name), max); // 최고 빈도수 갱신
        }
    }

    static void find() {
        for (Map.Entry<String, Integer> book : books.entrySet()) {
            if (book.getValue() == max && (maxTitle == null || maxTitle.compareTo(book.getKey()) > 0))
                maxTitle = book.getKey(); // 가장 높은 빈도의 사전 순으로 앞선 제목 갱신
        }
    }
}