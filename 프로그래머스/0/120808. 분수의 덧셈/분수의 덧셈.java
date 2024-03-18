class Solution {
    public int[] solution(int numer1, int denom1, int numer2, int denom2) {
        int answer[] = new int [2];
        
        int a = numer1 * denom2 + numer2 * denom1; //분자
        int b = denom1 * denom2; //분모
        
        while(true){
            int gcd = getGCD(a, b);
            if (gcd == 1)
                break;
            else {
                a /= gcd;
                b /= gcd;
            }
        }
        
        answer[0] = a;
        answer[1] = b;
        
        return answer;
    }
    
    public static int getGCD(int a, int b) {
        if (a % b == 0)
            return b;
        return getGCD(b, a%b);
    }
}