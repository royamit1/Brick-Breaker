// 209205665 Roy Amit

/**
 * This class represents a HitListener called ScoreTrackingListener.
 * which updates counter when blocks are being hit and removed.
 */
public class ScoreTrackingListener implements HitListener {

    private final Counter currentScore;

    /**
     * This function is the constructor.
     *
     * @param scoreCounter - Counter object.
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    /**
     * Blocks that are hit should be removed from the game.
     * Remember to remove this listener from the block that is being removed from the game.
     *
     * @param beingHit - Block object.
     * @param hitter   - Ball object.
     */
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        this.currentScore.increase(5);
    }
}
