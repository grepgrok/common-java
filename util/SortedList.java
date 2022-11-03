// package util;

// import util.ArrayList;

public class SortedList<E extends Comparable<E>> extends ArrayList<E> {
    private boolean scrambled = false;

    public SortedList(int capacity) {
        super(capacity);
    }
    public SortedList() {
        super();
    }

    public void scramble() {
        super.scramble();
        scrambled = true;
    }

    public boolean isScrambled() {
        return scrambled;
    }

    public void sort() {
        mergeSort(0, size());
    }

    private void mergeSort(int low, int high) {
        int mid = (low + high) / 2;

        if (mid == low) {
            return;
        }

        mergeSort(low, mid); // Split the first half
        mergeSort(mid, high); // Split the second half

        // Once all the recursive calls are done
        // merge is called.
        merge(low, high);
    }

    private void merge(int start, int end) {

        // Split the array into two halves
        SortedList<E> temp = new SortedList<>(end - start);
        int mid = (start + end) / 2;

        // Set the starting position for each halves
        int i = start;
        int j = mid;

        // Place the numbers between the split array locations
        // into to a temp array where the lowest number
        // is placed first.
        int k = 0;// This is used to track the index of the temp array
        while (i < mid && j < end) {
            if (get(i).compareTo(get(j)) < 0) {
                temp.set(k, get(i));
                i++;
            } else {
                temp.set(k, get(j));
                j++;
            }
            k++;
        }

        // If i or j has left over numbers, place them at the end.
        while (i < mid) {
            temp.set(k, get(i));
            i++;
            k++;
        }
        while (j < end) {
            temp.set(k, get(j));
            j++;
            k++;
        }

        // Put the sorted temp array back into array.
        for (i = 0; i < end - start; i++) {
            set(start + i, temp.get(i));
        }

    }

    public boolean add(E element) {
        if (scrambled) {
            sort();
        }
        correctCapacity(1);

        int low = 0;
        int high = size();

        // ends when low = high = insertion point
        while (low < high) {
            var mid = (low + high) / 2; // Like binary search, check off the mid point
            // If mid is too small, adjust low end
            if (get(mid).compareTo(element) < 0) {
                // because of integer division, `+ 1` is required to avoid infinite loops with low = high - 1
                low = mid + 1;
                // else, adjust high end
            } else {
                high = mid;
            }
        }

        return super.add(low, element);
    }

    public int indexOf(E element) {
        if (scrambled) {
            sort();
        }
        return indexOf(element, 0, super.size() - 1);
    }

    private int indexOf(E element, int low, int high) {
        if (high > low) {
            int mid = (low + high) / 2;
            int comp = element.compareTo(super.get(mid));
            if (comp < 0) {
                return indexOf(element, low, mid);
            } else if (comp == 0) {
                return mid;
            } else {
                return indexOf(element, mid, high);
            }
        } else {
            return -1;
        }
    }
}
