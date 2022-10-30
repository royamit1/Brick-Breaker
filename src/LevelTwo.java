// 209205665 Roy Amit

import biuoop.DrawSurface;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;


/**
 * This class represents the second level in the game.
 */
public class LevelTwo implements LevelInformation {

    private static final int FRAME_WIDTH = 800;
    private static final int FRAME_HEIGHT = 600;
    private static final int GAME_BLOCK_WIDTH = 50;
    private static final int GAME_BLOCK_HEIGHT = 30;
    private static final int BALL_SPEED = 5;
    private static final int DEFAULT_ANGLE = 315;
    private static final int PADDLE_SPEED = 8;
    private static final int NUM_OF_BALLS = 10;
    private static final int NUM_OF_BLOCKS = 16;
    private static final int PADDLE_WIDTH = 600;

    private final Counter counter1 = new Counter();
    private final Counter counter2 = new Counter();

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
        for (int i = 0; i < 10; i++) {
            Velocity vel = Velocity.fromAngleAndSpeed(DEFAULT_ANGLE + i * 10, BALL_SPEED);
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
        return new String("Wide Easy");
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
                // drawing the sky.
                for (int i = 0; i < 40; i++) {
                    d.setColor(new Color(95 + i * 4, 191 + i, 235 + i / 3));
                    d.fillRectangle(0, 0, FRAME_WIDTH, FRAME_HEIGHT - i * 20);
                }
                // drawing the sun and the sun rays.
                for (int i = 0; i < 80; i++) {
                    d.setColor(new Color(255, 230, 0));
                    d.drawLine(120, 130, 15 + i * 10, 250);
                }
                d.setColor(new Color(255, 215, 0));
                d.fillCircle(120, 130, 55);
                d.setColor(new Color(255, 215, 0));
                d.fillCircle(120, 130, 50);
                d.setColor(new Color(255, 230, 0));
                d.fillCircle(120, 130, 40);
                d.setColor(new Color(255, 230, 0));
                d.fillCircle(120, 130, 30);
                d.setColor(new Color(255, 230, 0));
                d.fillCircle(120, 130, 20);

                // drawing all the clouds.
                d.setColor(new Color(202, 202, 202));
                d.fillCircle(558 - counter1.getValue(), 105, 25);
                d.setColor(new Color(202, 202, 202));
                d.fillCircle(658 - counter1.getValue(), 105, 25);
                d.setColor(new Color(207, 207, 207));
                d.fillCircle(595 - counter1.getValue(), 125, 25);
                d.setColor(new Color(192, 192, 192));
                d.fillCircle(580 - counter1.getValue(), 90, 25);
                d.setColor(new Color(187, 187, 187));
                d.fillCircle(623 - counter1.getValue(), 120, 25);
                d.setColor(new Color(197, 197, 197));
                d.fillCircle(610 - counter1.getValue(), 95, 25);
                d.setColor(new Color(202, 202, 202));
                d.fillCircle(635 - counter1.getValue(), 92, 25);
                d.setColor(new Color(187, 187, 187));
                d.fillCircle(258 + counter1.getValue(), 135, 25);
                d.setColor(new Color(202, 202, 202));
                d.fillCircle(358 + counter1.getValue(), 135, 25);
                d.setColor(new Color(202, 202, 202));
                d.fillCircle(295 + counter1.getValue(), 155, 25);
                d.setColor(new Color(192, 192, 192));
                d.fillCircle(280 + counter1.getValue(), 120, 25);
                d.setColor(new Color(202, 202, 202));
                d.fillCircle(323 + counter1.getValue(), 150, 25);
                d.setColor(new Color(197, 197, 197));
                d.fillCircle(310 + counter1.getValue(), 125, 25);
                d.setColor(new Color(207, 207, 207));
                d.fillCircle(335 + counter1.getValue(), 122, 25);
                counter2.increase(1);
                if (counter2.getValue() % 5 == 0) {
                    counter1.increase(1);
                }
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
        Color[] colors = blocksColors();
        for (int i = 0; i < 16; i++) {
            Block block = new Block(new Point(735 - i * 48, 250),
                    GAME_BLOCK_WIDTH, GAME_BLOCK_HEIGHT);
            block.setColor(colors[i / 2]);
            blocks.add(block);
        }
        return blocks;
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
