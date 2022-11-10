import java.util.Iterator;

public class DLList<E> implements Iterable<E> {
    private Node head;
    private Node tail;
    private int size;

    public DLList() {
        head = null;
        tail = null;
        size = 0;
    }

    public DLList(E[] initialData) {
        this();
        for (E data : initialData) {
            add(data);
        }
    }

    private DLList(Node head, Node tail, int size) {
        this.head = head;
        this.tail = tail;
        this.size = size;
    }

    public int size() {
        return size;
    }

    public E getFirst() {
        return head.get();
    }

    private Node getFirstNode() {
        return head;
    }

    public E getLast() {
        return tail.get();
    }

    private Node getLastNode() {
        return tail;
    }

    public E get(int index) {
        Node node = getNode(index);
        E res = node.get();
        return res;
    }

    public void set(int index, E data) {
        getNode(index).set(data);
    }

    public void add(E data) {
        Node node = new Node(data);
        if (head == null) {
            head = node;
        } else {
            tail.setNext(node);
            node.setPrev(tail);
        }
        tail = node;
        size++;
    }

    public void add(int index, E data) {
        Node node = new Node(data);
        Node current = getNode(index);
        // Connect from the node's perspective
        node.setNext(current);
        node.setPrev(current.prev());
        // Connect on either side of the node
        current.prev().setNext(node);
        current.setPrev(node);
    }

    public void addAll(E[] values) {
        for (E data : values) {
            add(data);
        }
    }

    public void addAll(int index, E[] values) {
        addAll(index, toDLList(values));
    }

    public DLList<E> toDLList(E[] values) {
        Node head = new Node(values[0]);
        Node current = head;
        for (int i = 1; i < values.length; i++) {
            current.setNext(new Node(values[i]));
            current = current.next();
        }
        return new DLList<>(head, current, values.length);
    }


    public void addAll(DLList<E> values) {
        tail.setNext(values.getFirstNode());
        tail = values.getLastNode();
    }

    public void addAll(int index, DLList<E> values) {
        Node current = getNode(index);
        // Connect from the values' perspective
        values.getFirstNode().setPrev(current.prev());
        values.getLastNode().setNext(current.next());
        // Connect on either side of the values
        current.prev().setNext(values.getFirstNode());
        current.setPrev(values.getLastNode());
    }

    public boolean remove(E data) {
        Node target = getNode(data);
        if (target == null) {
            return false;
        }

        remove(target);
        size--;
        return true;
    }

    public E remove(int index) {
        Node target = getNode(index);
        if (target == null) {
            return null;
        }
        size--;
        return remove(target);
    }

    public E pop() {
        return remove(size - 1);
    }

    public String toString() {
        String res = "";
        if (head == null) {
            return "";
        }
        Node current = head;
        while (current != null) {
            res += current.get().toString() + "; ";
            current = current.next();
        }
        return res.substring(0, res.length() - 2);
    }

    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private Node currentNode = head;

            public boolean hasNext() {
                return currentNode != null;
            }

            public E next() {
                E res = currentNode.get();
                currentNode = currentNode.next();

                return res;
            }
        };
    }

    private E remove(Node node) {
        if (node == head) {
            head = node.next();
        } else if (node == tail) {
            tail = node.prev();
        }
        return node.disconnect();
    }

    private Node getNode(int index) {
        Node current = head;
        for (int i = 0; i < index; i++) {
            current = current.next();
        }
        return current;
    }

    private Node getNode(E data) {
        Node current = head;
        for (int i = 0; i < size; i++) {
            if (current.get().equals(data)) {
                return current;
            }
            current = current.next();
        }
        return null;
    }

    private class Node {
        private E data;
        private Node next;
        private Node prev;

        public Node(E data) {
            this.data = data;
        }

        public E get() {
            return data;
        }

        public void set(E data) {
            this.data = data;
        }

        public Node next() {
            return next;
        }

        public Node prev() {
            return prev;
        }

        public void setNext(Node next) {
            this.next = next;
        }

        public void setPrev(Node prev) {
            this.prev = prev;
        }

        public String toString() {
            String res = get().toString();
            if (prev != null) {
                res = prev.get() + " <--> " + res;
            }
            if (next != null) {
                res += " <--> " + next.get();
            }
            return res;
        }

        public E disconnect() {
            if (next != null) {
                next.setPrev(prev);
            }
            if (prev != null) {
                prev.setNext(next);
            }
            return data;
        }
    }
}
