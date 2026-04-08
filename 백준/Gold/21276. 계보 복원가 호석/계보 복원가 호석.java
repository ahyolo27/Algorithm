import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Tree {
        Node root;
        Map<String, Node> list;

        Tree() {
            root = new Node(null);
            list = new TreeMap<>();
        }

        class Node {
            Node parent;
            TreeSet<Node> children;
            String name;

            Node(String name) {
                parent = root;
                children = new TreeSet<>(Comparator.comparing(o -> o.name));
                this.name = name;
            }
        }

        void addNode(String name) {
            list.put(name, new Node(name));
            root.children.add(list.get(name));
        }

        void makeTree(String childName, String parentName) {
            Node parent = list.get(parentName);
            Node child = list.get(childName);

            if (checkParents(childName, parentName)) return; // 이미 완성된 트리인 경우 종료

            // 자식의 부모 -> 자식 제거
            child.parent.children.remove(child);
            // 자식의 부모 변경
            child.parent = parent;
            // 부모 -> 자식 추가
            parent.children.add(child);
        }

        boolean checkParents(String childName, String targetName) { // 부모를 거슬러 올라가며 target이 있으면 true를 반환하는 메서드
            Node child = list.get(childName);
            Node parent = child.parent;

            while (parent != root) {
                if (parent.name.equals(targetName)) return true;

                parent = parent.parent;
            }

            return false;
        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        Tree tree = new Tree();

        int N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            String name = st.nextToken();
            tree.addNode(name);
        }

        int M = Integer.parseInt(br.readLine());
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            String child = st.nextToken();
            String parent = st.nextToken();

            tree.makeTree(child, parent);
        }

        // output
        StringBuilder sb = new StringBuilder();
        sb.append(tree.root.children.size()).append("\n");
        for (Tree.Node child : tree.root.children)
            sb.append(child.name).append(" ");
        sb.append("\n");
        for (String name : tree.list.keySet()) {
            sb.append(name).append(" ");
            sb.append(tree.list.get(name).children.size()).append(" ");
            if (!tree.list.get(name).children.isEmpty()) {
                for (Tree.Node child : tree.list.get(name).children)
                    sb.append(child.name).append(" ");
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }
}