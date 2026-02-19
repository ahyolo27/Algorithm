import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Main {
    static String maxPrefix;
    static List<Word> words;

    static class Word {
        String val;
        int idx;

        Word(String val, int idx) {
            this.val = val;
            this.idx = idx;
        }
    }

    public static void main(String[] args) throws IOException {
        input(); // 입력

        solve(); // LCP 계산

        print(); // 출력
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        words = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            String word = br.readLine();
            words.add(new Word(word, i));
        }
    }

    static void solve() {
        words.sort(Comparator.comparing(o -> o.val)); // 단어 기준 사전순

        int maxLen = -1;
        int maxIdx = words.size();
        maxPrefix = "";

        for (int i = 0; i < words.size() - 1; i++) {
            String prefix = lcp(words.get(i).val, words.get(i + 1).val);

            if (prefix.isEmpty()) continue; // 공통 접두사가 없는 경우

            if (prefix.length() > maxLen || (prefix.length() == maxLen && (Math.min(words.get(i).idx, words.get(i + 1).idx) < maxIdx))) {
                maxLen = prefix.length();
                maxIdx = Math.min(words.get(i).idx, words.get(i + 1).idx);
                maxPrefix = prefix;
            }
        }
    }

    static String lcp(String a, String b) {
        int len = Math.min(a.length(), b.length());

        int i = 0;
        while (i < len && a.charAt(i) == b.charAt(i)) {
            i++;
        }

        return a.substring(0, i);
    }

    static void print() {
        StringBuilder sb = new StringBuilder();
        words.sort(Comparator.comparing(o -> o.idx)); // 입력순

        int cnt = 0;
        for (Word w : words) {
            if (w.val.startsWith(maxPrefix)) {
                cnt++;
                sb.append(w.val).append("\n");
            }
            if (cnt == 2) break;
        }

        System.out.println(sb);
    }
}