// 209205665 Roy Amit

import biuoop.DrawSurface;

/**
 * This interface represents the animation.
 */
public interface Animation {

    /**
     * This function is in charge of the logic.
     *
     * @param d - DrawSurface
     */
    void doOneFrame(DrawSurface d);

    /**
     * This function returns true if it should stop, otherwise, false.
     *
     * @return - returns true if it should stop, otherwise, false.
     */
    boolean shouldStop();
}