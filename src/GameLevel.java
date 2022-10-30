// 209205665 Roy Amit

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.Color;
import java.util.List;

/**
 * This class represent the Game object.
 * it holds the sprites and the collidables, and will be in charge of the animation.
 */
public class GameLevel implements Animation {

    private static final int FRAME_WIDTH = 800;
    private static final int FRAME_HEIGHT = 600;
    private static final Point FRAME_SCORE_BLOCK = new Point(0, 0);
    private static final Point FRAME_BLOCK1 = new Point(0, 20);
    private static final Point FRAME_BLOCK2 = new Point(0, 20);
    private static final Point FRAME_BLOCK3 = new Point(785, 20);
    private static final Point FRAME_BLOCK4 = new Point(15, 610);
    private static final int SCORE_BLOCK_WIDTH = 800;
    private static final int SCORE_BLOCK_HEIGHT = 20;
    private static final int FRAME_SIZE_CONST = 15;
    private static final int DEFAULT_RADIUS = 4;
    private static final int COUNTDOWN_NUM_OF_SEC = 2;
    private static final int COUNTDOWN_START = 3;
    private static final int FRAMES_PER_SEC = 60;

    private final SpriteCollection sprites;
    private final GameEnvironment environment;
    private final LevelInformation levelInfo;
    private final Counter ballCounter;
    private final Counter blockCounter;
    private final Counter score;
    private ScoreTrackingListener scoreTrack;
    private final ScoreIndicator scoreIndicator;
    private final AnimationRunner runner;
    private boolean running;
    private final KeyboardSensor keyboard;
    private final Sprite background;
    private final Sprite levelName;

    /**
     * This function is the constructor.
     * It creates a Game object holding 'SpriteCollection' and 'GameEnvironment' objects.
     *
     * @param sprites     - SpriteCollection object.
     * @param environment - GameEnvironment object.
     * @param levelInfo   - LevelInformation
     * @param ks          - KeyboardSensor.
     * @param ar          - AnimationRunner
     * @param score       - counter score
     */
    public GameLevel(LevelInformation levelInfo, SpriteCollection sprites, GameEnvironment environment,
                     KeyboardSensor ks, AnimationRunner ar, Counter score) {
        this.sprites = sprites;
        this.environment = environment;
        this.ballCounter = new Counter();
        this.blockCounter = new Counter();
        this.score = score;
        this.scoreIndicator = new ScoreIndicator(this.score, Color.BLACK);
        this.runner = ar;
        this.keyboard = ks;
        this.levelInfo = levelInfo;
        this.background = this.levelInfo.getBackground();
        this.levelName = new LevelName(this.levelInfo.levelName(), Color.BLACK);
    }

    /**
     * This function adds the given collidable to the environment.
     *
     * @param c - Collidable object.
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    /**
     * This function adds the given Sprite to the sprite collection.
     *
     * @param s - Sprite object.
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    /**
     * This function Initializes a new game: creates the Blocks and Ball (and Paddle).
     * and add them to the game.
     */
    public void initialize() {
        this.background.addToGame(this);
        BlockRemover blockRemover = new BlockRemover(this, this.blockCounter);
        BallRemover ballRemover = new BallRemover(this, this.ballCounter);
        this.scoreTrack = new ScoreTrackingListener(this.score);
        addBallsToTheGame(this.levelInfo.initialBallVelocities());
        addBlocksToGame(this.levelInfo.blocks(), blockRemover, ballRemover);
        this.scoreIndicator.addToGame(this);
        this.levelName.addToGame(this);
        Paddle paddle = new Paddle(this.keyboard,
                this.levelInfo.paddleWidth(), this.levelInfo.paddleSpeed());
        paddle.setGameEnvironment(this.environment);
        paddle.addToGame(this);
    }

    /**
     * This function returns the current number of balls in the game.
     *
     * @return - the current number of balls in the game.
     */
    public int getRemainingBalls() {
        return this.ballCounter.getValue();
    }

    /**
     * This function returns true if the level complete (no blocks left).
     *
     * @return This function returns true if the level complete, false otherwise.
     */
    public boolean isComplete() {
        return this.blockCounter.getValue() == 0;
    }

    /**
     * This function returns true if it should stop, otherwise, false.
     *
     * @return - returns true if it should stop, otherwise, false.
     */
    @Override
    public boolean shouldStop() {
        return !this.running;
    }

    /**
     * This function is in charge of the logic. the logic from the previous run method goes here.
     * the `return` or `break` statements should be replaced with this.running = false;.
     *
     * @param d - DrawSurface
     */
    @Override
    public void doOneFrame(DrawSurface d) {
        d.setColor(Color.BLACK);
        d.fillRectangle(0, 0, FRAME_WIDTH, FRAME_HEIGHT);

        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed();

        if (this.blockCounter.getValue() == 0) {
            this.running = false;
        }

        if (this.ballCounter.getValue() <= 0) {
            this.running = false;
        }

        if (this.keyboard.isPressed("p")) {
            this.runner.run(new PauseScreen(this.keyboard));
        }
    }

    /**
     * This function Runs the game -- starts the animation loop.
     */
    public void run() {
        this.runner.setFramesPerSecond(COUNTDOWN_NUM_OF_SEC * 1000 / (COUNTDOWN_START + 1));
        this.runner.run(new CountdownAnimation(COUNTDOWN_NUM_OF_SEC, COUNTDOWN_START, this.sprites));
        this.runner.setFramesPerSecond(1000 / FRAMES_PER_SEC);
        this.running = true;
        this.runner.run(this);
    }

    /**
     * This function removes a collidable from the list.
     *
     * @param c - collidable
     */
    public void removeCollidable(Collidable c) {
        this.environment.getCollideList().remove(c);
    }

    /**
     * This function removes a sprite from the list.
     *
     * @param s - sprite
     */
    public void removeSprite(Sprite s) {
        this.sprites.getSpriteCollection().remove(s);
    }

    /**
     * This function adds all the blocks to the game (including the border blocks).
     *
     * @param blockRemover - BlockRemover object.
     * @param ballRemover  - BallRemover object.
     * @param blocks       - list of all the blocks.
     */
    public void addBlocksToGame(List<Block> blocks, BlockRemover blockRemover, BallRemover ballRemover) {
        for (Block block : blocks) {
            block.addToGame(this);
            this.blockCounter.increase(1);
            block.addHitListener(blockRemover);
            block.addHitListener(this.scoreTrack);
        }
        // Here we create all the border blocks to prevent the ball from going outside the screen.
        Block scoreBlock = new Block(FRAME_SCORE_BLOCK, SCORE_BLOCK_WIDTH, SCORE_BLOCK_HEIGHT);
        Block block1 = new Block(FRAME_BLOCK1, FRAME_WIDTH, FRAME_SIZE_CONST);
        Block block2 = new Block(FRAME_BLOCK2, FRAME_SIZE_CONST, FRAME_HEIGHT - FRAME_SIZE_CONST);
        Block block3 = new Block(FRAME_BLOCK3, FRAME_SIZE_CONST, FRAME_HEIGHT - FRAME_SIZE_CONST);
        Block block4 = new Block(FRAME_BLOCK4, FRAME_WIDTH - ((FRAME_SIZE_CONST) * 2), 1);
        scoreBlock.setColor(Color.YELLOW);
        block1.setColor(Color.DARK_GRAY);
        block2.setColor(Color.DARK_GRAY);
        block3.setColor(Color.DARK_GRAY);
        block4.setColor(Color.CYAN);
        scoreBlock.addToGame(this);
        block1.addToGame(this);
        block2.addToGame(this);
        block3.addToGame(this);
        block4.addToGame(this);
        block4.addHitListener(ballRemover);
    }

    /**
     * This function adds the balls to the game.
     *
     * @param initialBallVelocities - list of velocities.
     */
    public void addBallsToTheGame(List<Velocity> initialBallVelocities) {
        for (Velocity ballVel : initialBallVelocities) {
            Ball ball = new Ball(new Point(400, 560), DEFAULT_RADIUS, Color.WHITE, ballVel);
            ball.setGameEnvironment(this.environment);
            this.ballCounter.increase(1);
            ball.addToGame(this);
        }
    }
}