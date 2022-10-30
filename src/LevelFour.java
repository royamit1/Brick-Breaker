// 209205665 Roy Amit

import java.util.Random;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * This class represents the forth level in the game.
 */
public class LevelFour implements LevelInformation {

    private static final int GAME_BLOCK_WIDTH = 51;
    private static final int GAME_BLOCK_HEIGHT = 30;
    private static final int BALL_SPEED = 5;
    private static final int DEFAULT_ANGLE = 345;
    private static final int PADDLE_SPEED = 8;
    private static final int NUM_OF_BALLS = 3;
    private static final int NUM_OF_BLOCKS = 112;
    private static final int PADDLE_WIDTH = 120;

    /**
     * This function returns the number of balls.
     *
     * @return - the number of balls.
     */
    @Override
    public int numberOfBalls() {
        return NUM_OF_BALLS;
    }

    /**
     * This function initials velocity of each ball. Note that initialBallVelocities().size() == numberOfBalls().
     *
     * @return - list of velocities.
     */
    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> initialVelocities = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            Velocity vel = Velocity.fromAngleAndSpeed(DEFAULT_ANGLE + i * 15, BALL_SPEED);
            initialVelocities.add(vel);
        }
        return initialVelocities;
    }

    /**
     * This function returns the speed of the paddle.
     *
     * @return - the speed of the paddle.
     */
    @Override
    public int paddleSpeed() {
        return PADDLE_SPEED;
    }

    /**
     * This function returns the width of the paddle.
     *
     * @return - the width of the paddle.
     */
    @Override
    public int paddleWidth() {
        return PADDLE_WIDTH;
    }

    /**
     * This function returns the name of the level.
     * the level name will be displayed at the top of the screen.
     *
     * @return - the name of the level.
     */
    @Override
    public String levelName() {
        return new String("Final Four");
    }

    /**
     * This function returns a sprite with the background of the level.
     *
     * @return - sprite with the background of the level
     */
    @Override
    public Sprite getBackground() {
        return new ChangeColorSprite();
    }

    /**
     * This function creates an array of colors for each row of blocks in the game.
     *
     * @return - array of colors.
     */
    public Color[] blocksColors() {
        Color[] colors = new Color[8];
        colors[0] = Color.CYAN;
        colors[1] = Color.PINK;
        colors[2] = Color.BLUE;
        colors[3] = Color.GREEN;
        colors[4] = Color.YELLOW;
        colors[5] = Color.ORANGE;
        colors[6] = Color.RED;
        colors[7] = Color.MAGENTA;
        return colors;
    }

    /**
     * This function returns a list of the Blocks that make up this level.
     * each block contains its size, color and location.
     *
     * @return - a list of all the blocks.
     */
    @Override
    public List<Block> blocks() {
        List<Block> blocks = new ArrayList<>();
        Color[] colors = blocksColors();
        Random rand = new Random();
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 15; j++) {
                Block block = new Block(new Point(733 - j * 51, 130 + i * 30),
                        GAME_BLOCK_WIDTH, GAME_BLOCK_HEIGHT);
                int random = rand.nextInt(8);
                block.setColor(colors[random]);
                blocks.add(block);
            }
        }
        return blocks;
    }

    /**
     * This function returns the number of blocks that should be removed,
     * before the level is considered to be "cleared".
     * This number should be <= blocks.size();.
     *
     * @return -  the number of blocks that should be removed.
     */
    @Override
    public int numberOfBlocksToRemove() {
        return NUM_OF_BLOCKS;
    }
}
