import java.io.*;

class Main {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String st = br.readLine();
        int ans = 0;
        if (st.isEmpty() || st.isBlank()) {
            System.out.println(0);
            return;
        } else if (st.charAt(0) == ' ')
            ans = -1;

        String arr[] = st.split(" ");

        System.out.println(ans + arr.length);
    }
}