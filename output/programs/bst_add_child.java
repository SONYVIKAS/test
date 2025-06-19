class Node {
    int data;
    Node left;
    Node right;

    Node(int data) {
        this.data = data;
        this.left = null;
        this.right = null;
    }

    void insert(int new_data) {
        if (new_data < this.data) {
            if (this.left == null) {
                this.left = new Node(new_data);
            } else {
                this.left.insert(new_data);
            }
        } else {
            if (this.right == null) {
                this.right = new Node(new_data);
            } else {
                this.right.insert(new_data);
            }
        }
    }