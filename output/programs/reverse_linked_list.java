class Node {
    int data;
    Node next;

    Node(int data) {
        this.data = data;
        this.next = null;
    }

    Node(int data, Node next) {
        this.data = data;
        this.next = next;
    }

    String asString() {
        StringBuilder sb = new StringBuilder();
        Node curr = this;
        while (curr!= null) {
            sb.append(curr.data);
            curr = curr.next;
        }
        return sb.toString();
    }