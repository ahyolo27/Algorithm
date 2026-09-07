class Solution {
    public String solution(String new_id) {        
        // 1단계
        new_id = new_id.toLowerCase();
        
        // 2단계
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<new_id.length();i++) {
            char target = new_id.charAt(i);
            
            if (sb.length()>0 && sb.charAt(sb.length()-1) == target && target == '.') continue; // 3단계
            
            if (target=='-' || target == '_' || target == '.' || ('a'<= target && target <= 'z') || ('0' <= target && target <= '9'))
                sb.append(target);
        }
        
        // 4단계
        if (sb.length()!=0 && sb.charAt(sb.length()-1) == '.') sb.deleteCharAt(sb.length()-1);
        if (sb.length()!=0 && sb.charAt(0) == '.') sb.deleteCharAt(0);
        
        // 5단계
        if (sb.length() == 0) sb.append("a");
        
        // 6단계
        if (sb.length() >= 16) {
            sb.setLength(15);
            if (sb.charAt(sb.length()-1) == '.') sb.deleteCharAt(sb.length()-1);
        }
        
        // 7단계
        if (sb.length() <= 2) {
            char c = sb.charAt(sb.length()-1);
            while(sb.length() < 3)
                sb.append(c);
        }

        return sb.toString();
    }
}