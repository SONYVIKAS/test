class Node {
    private Object data;
    private Node next;

    public Node(Object data) {
        this.data = data;
        this.next = null;
    }

    public Object getData() {
        return data;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }
}

class LinkedList {
    private Node head;
    private Node tail;

    public LinkedList() {
        this.head = null;
        this.tail = null;
    }

    public void insert(Object data, int position) {
        Node newNode = new Node(data);

        if (position == 0) {
            newNode.setNext(head);
            head = newNode;
        } else if (position == size()) {
            tail.setNext(newNode);
            tail = newNode;
        } else {
            Node prev = null;
            Node curr = head;
            int index = 0;

            while (curr!= null) {
                if (position == index) {
                    prev.setNext(newNode);
                    newNode.setNext(curr);
                    return;
                }

                index++;
                prev = curr;
                curr = curr.getNext();
            }
        }
    }

    public int size() {
        int size = 0;
        Node curr = head;

        while (curr!= null) {
            size++;
            curr = curr.getNext();
        }

        return size;
    }

    public Node search(Object data) {
        Node curr = head;

        while (curr!= null) {
            if (curr.getData().equals(data)) {
                return curr;
            }

            curr = curr.getNext();
        }

        throw new IllegalArgumentException("Data not in linked list");
    }

    public void delete(Object data) {
        Node prev = null;
        Node curr = head;

        while (curr!= null) {
            if (curr.getData().equals(data)) {
                if (curr == head) {
                    head = curr.getNext();
                } else {
                    prev.setNext(curr.getNext());
                }

                return;
            }

            prev = curr;
            curr = curr.getNext();
        }

        throw new IllegalArgumentException("Data not in linked list");
    }