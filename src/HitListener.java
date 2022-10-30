// 209205665 Roy Amit

/**
 * Objects that want to be notified of hit events, should implement the HitListener interface.
 */
public interface HitListener {

    /**
     * This method is called whenever the beingHit object is hit.
     * The hitter parameter is the Ball that's doing the hitting.
     *
     * @param beingHit - Block object.
     * @param hitter   - Ball object.
     */
    void hitEvent(Block beingHit, Ball hitter);
}
