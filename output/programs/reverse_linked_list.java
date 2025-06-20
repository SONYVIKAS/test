class Node {
    int data;
    Node next;

    Node(int data, Node next) {
        this.data = data;
        this.next = next;
    }

    String asString() {
        StringBuilder sb = new StringBuilder();
        Node current = this;
        while (current!= null) {
            sb.append(current.data);
            current = current.next;
        }
        return sb.toString();
    }
}

Node reverseLinkedList(Node head) {
    Node prev = null;
    Node curr = head;
    while (curr!= null) {
        Node next = curr.next;
        curr.next = prev;
        prev = curr;
        curr = next;
    }
    return prev;