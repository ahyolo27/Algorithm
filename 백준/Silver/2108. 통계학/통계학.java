import java.io.*;
import java.util.*;

public class Main {
    static int N, nums[];
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {

        input(); // 입력
        calc(); // 계산

        System.out.println(sb);
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        nums = new int[N];
        for (int i = 0; i < N; i++)
            nums[i] = Integer.parseInt(br.readLine());
        Arrays.sort(nums);
    }

    static void calc() {
        int sum = 0;
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        for (int i = 0; i < N; i++) {
            if (hashMap.containsKey(nums[i]))
                hashMap.put(nums[i], hashMap.get(nums[i]) + 1);
            else
                hashMap.put(nums[i], 1);
            sum += nums[i];
        }

        List<Integer> modeCandidates = new ArrayList<>();
        int cntMax = 0;
        for (Map.Entry<Integer, Integer> entry : hashMap.entrySet()) {
            int num = entry.getKey(), freq = entry.getValue();
            if (freq > cntMax) {
                cntMax = freq;
                modeCandidates.clear();
                modeCandidates.add(num);
            } else if (freq == cntMax) {
                modeCandidates.add(num);
            }
        }
        Collections.sort(modeCandidates);

        sb.append((int) Math.round(sum / (double) N)).append("\n");
        sb.append(nums[N / 2]).append("\n");
        sb.append(modeCandidates.size() >= 2 ? modeCandidates.get(1) : modeCandidates.get(0)).append("\n");
        sb.append(nums[N - 1] - nums[0]).append("\n");
    }
}