import java.io.*;

class Main {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int arr[] = new int[n];
        boolean visited[] = new boolean[n];
        
        back(arr, visited, n, 0);
    }

    public static void back(int arr[], boolean visited[], int n, int depth) {
        if (depth == n) {
            for (int i = 0; i < arr.length; i++)
                System.out.print(arr[i] + " ");
            System.out.println();
            return;
        }
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                visited[i] = true;
                arr[depth] = i + 1;
                back(arr, visited, n, depth + 1);
                visited[i] = false;
            }
        }
    }
}