import java.util.ArrayList;

// Define Node class
public class Node {
    // Node attributes
    private String name;
    private ArrayList<Node> children;

    // Node constructor
    public Node(String name, ArrayList<Node> children) {
        this.name = name;
        this.children = children;
    }

    // Overloaded Node constructor for nodes without children
    public Node(String name) {
        this.name = name;
        this.children = new ArrayList<>();
    }

    // Method to count employees
    public int countEmployees() {
        // If node has no children, return 0
        if (this.children.isEmpty()) {
            return 0;
        }

        int count = 0;

        // Iterate over children nodes
        for (Node child : this.children) {
            // Increment count by 1 plus the number of employees under the child
            count += 1 + child.countEmployees();
        }

        return count;
    }

    public static void main(String[] args) {
        // Test cases
        Node henri = new Node("Henri");
        ArrayList<Node> noraChildren = new ArrayList<>();
        noraChildren.add(henri);
        Node nora = new Node("Nora", noraChildren);

        Node nick = new Node("Nick");
        ArrayList<Node> janetChildren = new ArrayList<>();
        janetChildren.add(nick);
        janetChildren.add(nora);
        Node janet = new Node("Janet", janetChildren);

        Node al = new Node("Al");
        Node bob = new Node("Bob");
        Node jen = new Node("Jen");
        ArrayList<Node> jessicaChildren = new ArrayList<>();
        jessicaChildren.add(al);
        jessicaChildren.add(bob);
        jessicaChildren.add(jen);
        Node jessica = new Node("Jessica", jessicaChildren);

        ArrayList<Node> janeChildren = new ArrayList<>();
        janeChildren.add(jessica);
        janeChildren.add(janet);
        Node jane = new Node("Jane", janeChildren);

        // Print test results
        System.out.println(henri.countEmployees());  // Expected output: 0
        System.out.println(janet.countEmployees());  // Expected output: 3
        System.out.println(jessica.countEmployees());  // Expected output: 3
        System.out.println(jane.countEmployees());  // Expected output: 8
    }