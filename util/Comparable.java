// package util;

public interface Comparable<T> {
    /**
     * Compares this to other.
     * 
     * @param other The object to compare to
     * @return Whether this is greater than, less than, or equal to other. Follows
     *         the following form:
     *              this > other == this.compareTo(other) > 0
     *              this < other == this.compareTo(other) < 0
     *              this.equals(other) == this.compareTo(other) == 0
     */
    public int compareTo(T other);
    
    /**
     * 
     * @param other
     * @return
     */
    @Override
    public boolean equals(Object other);
}
