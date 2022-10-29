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
        }
        return null;
    }

    public void remove(E data) {
        DLNode<E> target = getNode(data);
        if (target == null) {
            return;
        }
        target.disconnect();
        size--;
    }

    public E remove(int index) {
        DLNode<E> target = getNode(index);
        if (target == null) {
            return null;
        }
        size--;
        return target.disconnect();
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
            private DLNode<E> last;

            public boolean hasNext() {
                return last != tail;
            }

            public E next() {
                E res = last.get();
                last = last.next();
                return res;
            }
        };
    }

    public int size() {
        return size;
    }
}
