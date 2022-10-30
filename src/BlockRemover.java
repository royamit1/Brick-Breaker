// 209205665 Roy Amit

/**
 * A BlockRemover is in charge of removing blocks from the game.
 * as well as keeping count of the number of blocks that remain.
 */
public class BlockRemover implements HitListener {

    private final GameLevel game;
    private final Counter remainingBlocks;

    /**
     * This function is the constructor.
     *
     * @param game          - Game object.
     * @param removedBlocks - Counter object.
     */
    public BlockRemover(GameLevel game, Counter removedBlocks) {
        this.game = game;
        this.remainingBlocks = removedBlocks;
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
        beingHit.removeFromGame(this.game);
        beingHit.removeHitListener(this);
        this.remainingBlocks.decrease(1);
    }
}
