// 209205665 Roy Amit

import biuoop.KeyboardSensor;

import java.util.ArrayList;
import java.util.List;

/**
 * This class will be in charge of creating the different levels, and moving from one level to the next.
 */
public class GameFlow {

    private final KeyboardSensor keyboardSensor;
    private final AnimationRunner animationRunner;
    private final Counter score;

    /**
     * This function is the constructor.
     *
     * @param ar - AnimationRunner object.
     * @param ks - KeyboardSensor object.
     */
    public GameFlow(AnimationRunner ar, KeyboardSensor ks) {
        this.keyboardSensor = ks;
        this.animationRunner = ar;
        this.score = new Counter();
    }

    /**
     * This function is in charge of running the levels in the game.
     *
     * @param levels - a list of level information.
     */
    public void runLevels(List<LevelInformation> levels) {
        boolean isWin = true;
        for (LevelInformation levelInfo : levels) {
            SpriteCollection sprites = new SpriteCollection(new ArrayList<>());
            GameEnvironment environment = new GameEnvironment();
            GameLevel level = new GameLevel(levelInfo, sprites, environment, this.keyboardSensor,
                    this.animationRunner, this.score);
            level.initialize();

            level.run();

            if (level.getRemainingBalls() == 0) {
                isWin = false;
                break;
            }

            if (level.isComplete()) {
                this.score.increase(100);
            }
        }
        EndScreen endScreen = new EndScreen(this.keyboardSensor, this.score, isWin);
        this.animationRunner.run(new KeyPressStoppableAnimation(this.keyboardSensor,
                KeyboardSensor.SPACE_KEY, endScreen));
    }
}