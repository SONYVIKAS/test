public class Node {
    private int data;
    private List<Node> children;

    public Node(int data) {
        this.data = data;
        this.children = new ArrayList<Node>();
    }

    public void addChild(Node child) {
        this.children.add(child);
    }
}

public class Main {
    public static int numNodes(Node tree) {
        int nodes = 1;

        if (tree == null) {
            return 0;
        }

        for (Node child : tree.children) {
            nodes += numNodes(child);
        }

        return nodes;
    }

    public static void main(String[] args) {
        Node one = new Node(1);
        Node two = new Node(2);
        Node three = new Node(3);
        one.addChild(two);
        one.addChild(three);
        System.out.println(numNodes(one)); // 3

        Node four = new Node(4);
        Node five = new Node(5);
        two.addChild(four);
        two.addChild(five);
        System.out.println(numNodes(one)); // 5

        Node six = new Node(6);
        three.addChild(six);
        System.out.println(numNodes(one)); // 6
    }