// 209205665 Roy Amit

import biuoop.DrawSurface;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * This class represents the first level in the game.
 */
public class LevelOne implements LevelInformation {

    private static final int FRAME_WIDTH = 800;
    private static final int FRAME_HEIGHT = 600;
    private static final int GAME_BLOCK_WIDTH = 50;
    private static final int GAME_BLOCK_HEIGHT = 30;
    private static final int BALL_SPEED = 5;
    private static final int DEFAULT_ANGLE = 0;
    private static final int PADDLE_SPEED = 8;
    private static final int NUM_OF_BALLS = 1;
    private static final int NUM_OF_BLOCKS = 1;
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
        Velocity vel = Velocity.fromAngleAndSpeed(DEFAULT_ANGLE, BALL_SPEED);
        initialVelocities.add(vel);
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
        return new String("Direct Hit");
    }

    /**
     * This function returns a sprite with the background of the level.
     *
     * @return - sprite with the background of the level
     */
    @Override
    public Sprite getBackground() {
        return new Sprite() {

            /**
             * This function draws the sprite to the screen.
             *
             * @param d - DrawSurface object
             */
            @Override
            public void drawOn(DrawSurface d) {
                d.setColor(Color.BLACK);
                d.fillRectangle(0, 0, FRAME_WIDTH, FRAME_HEIGHT);
                d.setColor(Color.BLUE);
                d.drawCircle(400, 175, 135);
                d.drawCircle(400, 175, 95);
                d.drawCircle(400, 175, 55);
                d.drawLine(220, 175, 580, 175);
                d.drawLine(400, 15, 400, 330);
            }

            /**
             * This function notifies the sprite that time has passed.
             */
            @Override
            public void timePassed() {
            }

            /**
             * This function adds a sprite to the game.
             *
             * @param g - GameLevel object.
             */
            @Override
            public void addToGame(GameLevel g) {
                g.addSprite(this);
            }
        };
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
        Block singleBlock = new Block(new Point(375, 160), GAME_BLOCK_WIDTH, GAME_BLOCK_HEIGHT);
        singleBlock.setColor(Color.RED);
        blocks.add(singleBlock);
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
