import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static Tree tree;
    static Tree.Node root;
    static String list[][];

    static class Tree {
        static class Node {
            Object data;
            Node left;
            Node right;

            Node(Object data) {
                this.data = data;
                left = null;
                right = null;
            }

            void addLeft(Node node) {
                left = node;
            }

            void addRight(Node node) {
                right = node;
            }
        }

        Node addNode(Object data) {
            Node n = new Node(data);
            return n;
        }

        void preOrder(Node node) { // 전위
            if (node == null)
                return;
            System.out.print(node.data);
            preOrder(node.left);
            preOrder(node.right);
        }

        void inOrder(Node node) { // 중위
            if (node == null)
                return;
            inOrder(node.left);
            System.out.print(node.data);
            inOrder(node.right);
        }

        void postOrder(Node node) { // 후위
            if (node == null)
                return;
            postOrder(node.left);
            postOrder(node.right);
            System.out.print(node.data);
        }
    }

    public static void main(String[] args) throws IOException {

        input(); // 입력
        setTree(); // 트리 만들기

        tree.preOrder(root); // 전위
        System.out.println();

        tree.inOrder(root); // 중위
        System.out.println();

        tree.postOrder(root); // 후위
        System.out.println();
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        list = new String[N][3];

        for (int tc = 0; tc < N; tc++) {
            st = new StringTokenizer(br.readLine());
            list[tc][0] = st.nextToken();
            list[tc][1] = st.nextToken();
            list[tc][2] = st.nextToken();
        }
    }

    static void setTree() {
        tree = new Tree();
        ArrayList<Tree.Node> nodes = new ArrayList<>();

        // 노드 생성
        for (int i = 0; i < N; i++) {
            Tree.Node node = tree.addNode(list[i][0]);
            nodes.add(node);
        }

        // 트리 연결관계 생성
        for (int i = 0; i < N; i++) {
            Tree.Node node = nodes.get(i);
            for (int j = i + 1; j < N; j++) {
                if (nodes.get(j).data.equals(list[i][1]))
                    node.addLeft(nodes.get(j));
                if (nodes.get(j).data.equals(list[i][2]))
                    node.addRight(nodes.get(j));
            }
            if (i == 0) // 루트 노드
                root = node;
        }
    }
}