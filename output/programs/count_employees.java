class Node {
    private String name; // Name of the node
    private List<Node> children; // List of child nodes

    // Constructor to initialize the node with a name and optional children
    public Node(String name, List<Node> children) {
        this.name = name;
        this.children = (children != null) ? children : new ArrayList<>();
    }

    // Method to count the number of employees managed by this node
    public int countEmployees() {
        // If there are no children, return 0
        if (children.isEmpty()) {
            return 0;
        }

        int count = 0;

        // Iterate over each child and count them and their subordinates
        for (Node child : children) {
            count += 1 + child.countEmployees();
        }

        return count;
    }

    // Main method to test the functionality
    public static void main(String[] args) {
        Node henri = new Node("Henri", null);
        Node nora = new Node("Nora", Arrays.asList(henri));
        Node nick = new Node("Nick", null);
        Node janet = new Node("Janet", Arrays.asList(nick, nora));
        Node al = new Node("Al", null);
        Node bob = new Node("Bob", null);
        Node jen = new Node("Jen", null);
        Node jessica = new Node("Jessica", Arrays.asList(al, bob, jen));
        Node jane = new Node("Jane", Arrays.asList(jessica, janet));

        // Test cases
        System.out.println(henri.countEmployees()); // Expected output: 0
        System.out.println(janet.countEmployees()); // Expected output: 3
        System.out.println(jessica.countEmployees()); // Expected output: 3
        System.out.println(jane.countEmployees()); // Expected output: 8
    }