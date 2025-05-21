import java.io.*;
import java.util.*;

public class Main {
    static class Person {
        int idx, age;
        String name;

        Person(int idx, int age, String name) {
            this.idx = idx;
            this.age = age;
            this.name = name;
        }

        @Override
        public String toString() {
            return age + " " + name;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        List<Person> list = new ArrayList<>();

        int N = Integer.parseInt(br.readLine());
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int age = Integer.parseInt(st.nextToken());
            String name = st.nextToken();
            list.add(new Person(i, age, name));
        }

        list.sort((o1, o2) -> {
            if (o1.age == o2.age) {
                return o1.idx - o2.idx;
            } else
                return o1.age - o2.age;
        });

        for (Person person : list)
            sb.append(person.toString()).append("\n");

        System.out.println(sb);
    }
}