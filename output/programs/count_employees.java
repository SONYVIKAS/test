import java.util.ArrayList;
import java.util.List;

// Node class representing each employee
class Node {
    String name;
    List<Node> children;

    // Node constructor
    public Node(String name, List<Node> children) {
        this.name = name;
        this.children = children;
    }

    public Node(String name) {
        this.name = name;
        this.children = new ArrayList<>();
    }

    // Method to count the number of employees under a manager
    public int countEmployees() {
        // If the node has no children, return 0
        if (this.children.isEmpty()) {
            return 0;
        }

        int count = 0;

        // For each child, add 1 to the count and recursively count the child's employees
        for (Node child : this.children) {
            count += 1 + child.countEmployees();
        }

        return count;
    }
}

// Main class
public class Main {
    public static void main(String[] args) {
        // Create nodes
        Node henri = new Node("Henri");
        List<Node> childrenNora = new ArrayList<>();
        childrenNora.add(henri);
        Node nora = new Node("Nora", childrenNora);

        Node nick = new Node("Nick");
        List<Node> childrenJanet = new ArrayList<>();
        childrenJanet.add(nick);
        childrenJanet.add(nora);
        Node janet = new Node("Janet", childrenJanet);

        Node al = new Node("Al");
        Node bob = new Node("Bob");
        Node jen = new Node("Jen");
        List<Node> childrenJessica = new ArrayList<>();
        childrenJessica.add(al);
        childrenJessica.add(bob);
        childrenJessica.add(jen);
        Node jessica = new Node("Jessica", childrenJessica);

        List<Node> childrenJane = new ArrayList<>();
        childrenJane.add(jessica);
        childrenJane.add(janet);
        Node jane = new Node("Jane", childrenJane);

        // Print the number of employees each person manages
        System.out.println(henri.countEmployees()); // 0
        System.out.println(janet.countEmployees()); // 3
        System.out.println(jessica.countEmployees()); // 3
        System.out.println(jane.countEmployees()); // 8
    }