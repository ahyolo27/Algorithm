import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int n = scan.nextInt();
        String ans = "";

        for (int i = 0; i < n; i++) {
            if (i == 0) 
                ans = scan.next(); //ans 초기화
            else {
                String comp = scan.next();
                for (int j = 0; j < ans.length(); j++) { 
                    if (ans.charAt(j) != comp.charAt(j)) { //비교 후 치환
                        ans = ans.substring(0, j) + "?" + ans.substring(j+1);
                    }
                }
            }
        }

        System.out.println(ans);

        scan.close();
    }
}