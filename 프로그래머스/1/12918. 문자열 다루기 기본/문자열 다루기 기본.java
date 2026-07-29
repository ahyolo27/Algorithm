class Solution {
    public boolean solution(String s) {
        
        if (s.length() != 4 && s.length() != 6) return false;
        for(char c: s.toCharArray())
            if (c<48 || c>57) return false;
        
        return true;
    }
}