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
        int max = -4001;
        int min = 4001;
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        for (int i = 0; i < N; i++) {
            if (hashMap.containsKey(nums[i]))
                hashMap.put(nums[i], hashMap.get(nums[i]) + 1);
            else
                hashMap.put(nums[i], 1);
            sum += nums[i];
            max = Math.max(nums[i], max);
            min = Math.min(nums[i], min);
        }

        int cntMax = 0;
        TreeSet<Integer> list = new TreeSet<>();
        for (int key : hashMap.keySet())
            if (hashMap.get(key) > cntMax)
                cntMax = hashMap.get(key);

        for (int key : hashMap.keySet())
            if (hashMap.get(key) == cntMax)
                list.add(key);
        if (list.size() >= 2)
            list.remove(list.first());

        sb.append((int) Math.round((double) sum / nums.length)).append("\n");
        sb.append(nums[nums.length / 2]).append("\n");
        sb.append(list.first()).append("\n");
        sb.append(max - min).append("\n");
    }
}