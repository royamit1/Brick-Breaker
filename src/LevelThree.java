// 209205665 Roy Amit

import biuoop.DrawSurface;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * This class represents the third level in the game.
 */
public class LevelThree implements LevelInformation {

    private static final int FRAME_WIDTH = 800;
    private static final int FRAME_HEIGHT = 600;
    private static final int GAME_BLOCK_WIDTH = 50;
    private static final int GAME_BLOCK_HEIGHT = 30;
    private static final int BALL_SPEED = 5;
    private static final int DEFAULT_ANGLE = 340;
    private static final int PADDLE_SPEED = 8;
    private static final int NUM_OF_BALLS = 2;
    private static final int NUM_OF_BLOCKS = 57;
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
        for (int i = 0; i < 2; i++) {
            Velocity vel = Velocity.fromAngleAndSpeed(DEFAULT_ANGLE + i * 40, BALL_SPEED);
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
        return new String("Green 3");
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
                for (int i = 0; i < 40; i++) {
                    d.setColor(new Color(i * 4, 135 + i, i / 3));
                    d.fillRectangle(0, 0, FRAME_WIDTH, FRAME_HEIGHT - i * 20);
                }

                d.setColor(Color.DARK_GRAY);
                d.fillRectangle(80, 400, 110, 200);
                for (int i = 0; i < 4; i++) {
                    for (int j = 0; j < 6; j++) {
                        d.setColor(Color.WHITE);
                        d.fillRectangle(90 + i * 25, 410 + j * 31, 15, 25);
                    }
                }
                d.setColor(new Color(90, 90, 90));
                d.fillRectangle(115, 340, 40, 60);
                d.setColor(new Color(105, 105, 105));
                d.fillRectangle(130, 170, 12, 170);
                d.setColor(Color.ORANGE);
                d.fillCircle(135, 155, 15);
                d.setColor(Color.RED);
                d.fillCircle(135, 155, 10);
                d.setColor(Color.WHITE);
                d.fillCircle(135, 155, 4);
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
     * This function creates an array of colors for each row of blocks in the game.
     *
     * @return - array of colors.
     */
    public Color[] blocksColors() {
        Color[] colors = new Color[6];
        colors[0] = Color.GRAY;
        colors[1] = Color.RED;
        colors[2] = Color.YELLOW;
        colors[3] = Color.BLUE;
        colors[4] = Color.PINK;
        colors[5] = Color.GREEN;
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
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 12 - i; j++) {
                // Here we organize the blocks in a row and each row has a different color.
                Block block = new Block(new Point(735 - j * 50, 100 + i * 30),
                        GAME_BLOCK_WIDTH, GAME_BLOCK_HEIGHT);
                block.setColor(colors[i]);
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
