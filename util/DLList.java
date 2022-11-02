import java.util.Iterator;

public class DLList<E> implements Iterable<E> {
    private DLNode<E> head;
    private DLNode<E> tail;
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

    public void add(E data) {
        DLNode<E> node = new DLNode<E>(data);
        if (head == null) {
            head = node;
        } else {
            tail.setNext(node);
            node.setPrev(tail);
        }
        tail = node;
        size++;
    }

    public void set(int index, E data) {
        getNode(index).set(data);
    }

    public E get(int index) {
        DLNode<E> node = getNode(index);
        E res = node.get();
        return res;
    }

    public E getHead() {
        return head.get();
    }

    public E getTail() {
        return tail.get();
    }

    private DLNode<E> getNode(int index) {
        DLNode<E> current = head;
        for (int i = 0; i < index; i++) {
            current = current.next();
        }
        return current;
    }

    private DLNode<E> getNode(E data) {
        DLNode<E> current = head;
        for (int i = 0; i < size; i++) {
            if (current.get().equals(data)) {
                return current;
            }
            current = current.next();
        }
        return null;
    }

    public boolean remove(E data) {
        DLNode<E> target = getNode(data);
        if (target == null) {
            return false;
        }

        remove(target);
        size--;
        return true;
    }

    public E remove(int index) {
        DLNode<E> target = getNode(index);
        if (target == null) {
            return null;
        }
        size--;
        return remove(target);
    }

    public E pop() {
        return remove(size - 1);
    }

    public void swap(int a, int b) {
        // Assert order of `a` and `b`
        if (a < b) {
            int t = a;
            a = b;
            b = t;
        } else if (a == b) {
            return;
        }

        // Get nodes a and b
        DLNode<E> nodeA = getNode(a);
        DLNode<E> nodeB = getNode(b);

        E temp = nodeA.get();
        nodeA.set(nodeB.get());
        nodeB.set(temp);
    }

    public void scramble() {
        for (int i = 0; i < size; i++) {
            swap(i, (int) (Math.random() * size));
        }
    }

    public String toString() {
        String res = "";
        DLNode<E> current = head;
        for (int i = 0; i < size; i++) {
            res += "    " + current.get().toString() + "\n";
            current = current.next();
        }
        return res.substring(0, res.length() - 1);
    }

    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private DLNode<E> currentNode = head;

            public boolean hasNext() {
                return currentNode != null;
            }

            public E next() {
                // System.out.println();
                // System.out.println(head);
                // System.out.println(currentNode);
                // System.out.println(currentNode.next());
                // System.out.println(tail);
                // System.out.println();
                
                E res = currentNode.get();
                currentNode = currentNode.next();

                return res;
            }
        };
    }

    public int size() {
        return size;
    }

    private E remove(DLNode<E> node) {
        if (node == head) {
            head = node.next();
        } else if (node == tail) {
            tail = node.prev();
        }
        return node.disconnect();
    }
}
