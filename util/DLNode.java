public class DLNode<E> {
    private E data;
    private DLNode<E> next;
    private DLNode<E> prev;

    public DLNode(E data) {
        this.data = data;
    }

    public E get() {
        return data;
    }

    public void set(E data) {
        this.data = data;
    }

    public DLNode<E> next() {
        return next;
    }

    public DLNode<E> prev() {
        return prev;
    }

    public void setNext(DLNode<E> next) {
        this.next = next;
    }

    public void setPrev(DLNode<E> prev) {
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
        System.out.println(this);
        if (next != null) {
            next.setPrev(prev);
        }
        if (prev != null) {
            prev.setNext(next);
        }
        return data;
    }
}