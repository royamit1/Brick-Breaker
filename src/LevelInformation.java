// 209205665 Roy Amit

import java.util.List;

/**
 * This interface represents a single level information.
 */
public interface LevelInformation {

    /**
     * This function returns the number of balls.
     *
     * @return - the number of balls.
     */
    int numberOfBalls();

    /**
     * This function initials velocity of each ball. Note that initialBallVelocities().size() == numberOfBalls().
     *
     * @return - list of velocities.
     */
    List<Velocity> initialBallVelocities();

    /**
     * This function returns the speed of the paddle.
     *
     * @return - the speed of the paddle.
     */
    int paddleSpeed();

    /**
     * This function returns the width of the paddle.
     *
     * @return - the width of the paddle.
     */
    int paddleWidth();

    /**
     * This function returns the name of the level.
     * the level name will be displayed at the top of the screen.
     *
     * @return - the name of the level.
     */
    String levelName();

    /**
     * This function returns a sprite with the background of the level.
     *
     * @return - sprite with the background of the level
     */
    Sprite getBackground();

    /**
     * This function returns a list of the Blocks that make up this level.
     * each block contains its size, color and location.
     *
     * @return - a list of all the blocks.
     */
    List<Block> blocks();

    /**
     * This function returns the number of blocks that should be removed,
     * before the level is considered to be "cleared".
     * This number should be <= blocks.size();.
     *
     * @return -  the number of blocks that should be removed.
     */
    int numberOfBlocksToRemove();
}