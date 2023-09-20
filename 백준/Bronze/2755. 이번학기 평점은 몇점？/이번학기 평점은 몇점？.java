import java.util.Scanner;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int n = scan.nextInt(); // 과목 수

        ArrayList<String> subject = new ArrayList<String>(); // 과목 이름
        ArrayList<Integer> num = new ArrayList<Integer>(); // 학점 수
        ArrayList<String> grade = new ArrayList<String>(); // 등급

        for (int i = 0; i < n; i++) {
            String a = scan.next();
            int b = scan.nextInt();
            String c = scan.next();
            subject.add(a);
            num.add(b);
            grade.add(c);
        }

        // 총 학점 수
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += num.get(i);
        }

        double score = 0;
        for (int i = 0; i < n; i++) {
            score += (num.get(i) * getScore(grade.get(i)));
        }

        double answer = score / sum;

        System.out.printf("%.2f", answer);

        scan.close();
    }

    static double getScore(String grade) {
        double rate = 0;
        switch (grade) {
            case "A+":
                rate = 4.3;
                break;
            case "A0":
                rate = 4.0;
                break;
            case "A-":
                rate = 3.7;
                break;
            case "B+":
                rate = 3.3;
                break;
            case "B0":
                rate = 3.0;
                break;
            case "B-":
                rate = 2.7;
                break;
            case "C+":
                rate = 2.3;
                break;
            case "C0":
                rate = 2.0;
                break;
            case "C-":
                rate = 1.7;
                break;
            case "D+":
                rate = 1.3;
                break;
            case "D0":
                rate = 1.0;
                break;
            case "D-":
                rate = 0.7;
                break;
            case "F":
                rate = 0.0;
        }

        return rate;
    }

}
