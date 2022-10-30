// 209205665 Roy Amit

/**
 * This class will be in charge of removing balls, and updating an available-balls counter.
 */
public class BallRemover implements HitListener {

    private final GameLevel game;
    private final Counter remainingBalls;

    /**
     * This function is the constructor.
     *
     * @param game           - Game object.
     * @param remainingBalls - Counter object.
     */
    public BallRemover(GameLevel game, Counter remainingBalls) {
        this.game = game;
        this.remainingBalls = remainingBalls;
    }

    /**
     * When a ball hits the "death-region" it should be removed from the game.
     *
     * @param beingHit - Block object.
     * @param hitter   - Ball object.
     */
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(this.game);
        this.remainingBalls.decrease(1);
    }
}