import java.util.*;

class Solution {
    public String solution(int[] numbers) {
        ArrayList<Integer> list = new ArrayList<>();
        for(int n: numbers)
            list.add(n);

        list.sort((o1, o2) -> {
            String s1 = o1.toString();
            String s2 = o2.toString();
            return (s2 + s1).compareTo(s1 + s2); // 사전순 역정렬
        });
        
        StringBuilder sb = new StringBuilder();
        for(int n: list)
            sb.append(n);
        
        if (sb.toString().charAt(0)=='0') return "0";
        else return sb.toString();
    }
}