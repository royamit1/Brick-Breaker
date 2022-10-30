// 209205665 Roy Amit

/**
 * This class is a simple class that is used for counting things.
 */
public class Counter {

    private int counter;

    /**
     * This function is the constructor.
     */
    public Counter() {
        this.counter = 0;
    }

    /**
     * add number to current count.
     *
     * @param number - int
     */
    public void increase(int number) {
        this.counter += number;
    }

    /**
     * subtract number from current count.
     *
     * @param number - int
     */
    public void decrease(int number) {
        this.counter -= number;
    }

    /**
     * get current count.
     *
     * @return the current count.
     */
    public int getValue() {
        return this.counter;
    }
}
