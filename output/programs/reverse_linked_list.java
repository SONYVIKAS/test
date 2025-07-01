class Node {
    int data;
    Node next;

    public Node(int data, Node next) {
        this.data = data;
        this.next = next;
    }

    public String asString() {
        StringBuilder sb = new StringBuilder();
        Node curr = this;

        while (curr!= null) {
            sb.append(curr.data);
            curr = curr.next;
        }

        return sb.toString();
    }
}

public Node reverseLinkedList(Node head) {
    Node prev = null;
    Node curr = head;

    while (curr!= null) {
        Node next = curr.next;
        curr.next = prev;
        prev = curr;
        curr = next;
    }

    return prev;