import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        ArrayList<String> aloha = new ArrayList<>();
        ArrayList<Integer[]> members = new ArrayList<>();

        //Aloha 멤버들의 별자리 정리
        for (int i = 0; i < 7; i++) {
            int month = scan.nextInt();
            int day = scan.nextInt();
            String star = getZodiac(month, day);

            if (!aloha.contains(star)) //별자리 안 겹치게 저장
                aloha.add(star);
        }

        int n = scan.nextInt();
        for (int i = 0; i < n; i++) {
            int month = scan.nextInt();
            int day = scan.nextInt();
            String star = getZodiac(month, day);

            if (!aloha.contains(star)) //별자리가 안 겹치는 경우
                members.add(new Integer[]{month, day});
        }

        //멤버 출력하기
        if (members.isEmpty())
            System.out.println("ALL FAILED");
        else {
            Collections.sort(members, new Comparator<Integer[]>() {
                @Override
                public int compare(Integer[] o1, Integer[] o2) {
                    return o1[0] != o2[0] ? o1[0] - o2[0] : o1[1] - o2[1];
                }
            });

            for (int i = 0; i < members.size(); i++) {
                System.out.println(members.get(i)[0] + " " + members.get(i)[1]);
            }
        }


        scan.close();
    }

    public static String getZodiac(int m, int d) {
        String star = "";

        if (m == 1) {
            if (d < 20) star = "염소";
            else star = "물병";
        } else if (m == 2) {
            if (d < 19) star = "물병";
            else star = "물고기";
        } else if (m == 3) {
            if (d <= 20) star = "물고기";
            else star = "양";
        } else if (m == 4) {
            if (d <= 19) star = "양";
            else star = "황소";
        } else if (m == 5) {
            if (d <= 20) star = "황소";
            else star = "쌍둥이";
        } else if (m == 6) {
            if (d <= 21) star = "쌍둥이";
            else star = "게";
        } else if (m == 7) {
            if (d <= 22) star = "게";
            else star = "사자";
        } else if (m == 8) {
            if (d <= 22) star = "사자";
            else star = "처녀";
        } else if (m == 9) {
            if (d <= 22) star = "처녀";
            else star = "천칭";
        } else if (m == 10) {
            if (d <= 22) star = "천칭";
            else star = "전갈";
        } else if (m == 11) {
            if (d <= 22) star = "전갈";
            else star = "사수";
        } else {
            if (d <= 21) star = "사수";
            else star = "염소";
        }
        return star;
    }
}