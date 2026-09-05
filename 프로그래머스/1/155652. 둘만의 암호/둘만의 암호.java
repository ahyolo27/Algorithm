import java.util.*;

class Solution {
    public String solution(String s, String skip, int index) {
        StringBuilder sb = new StringBuilder();
        
        Set<Character> skipSet = new HashSet<>();
        for(char c: skip.toCharArray())
            skipSet.add(c);
        
        for(int i=0;i<s.length();i++) {
            char c = s.charAt(i);
            
            int cnt = 0;
            while (cnt<index) {
                c++;
                c = (char)((c-'a'+26)%26 +'a'); // 범위 보정
                if (!skipSet.contains(c)) cnt++;
            }
            
            sb.append(c);
        }
        
        
        return sb.toString();
    }
}