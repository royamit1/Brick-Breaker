// 209205665 Roy Amit

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * This class represents the ending screen.
 * if you win you'll see the message - "You Win! Your score is " and the score.
 * if you lose you'll see the message - "Game Over. Your score is " and the score.
 */
public class EndScreen implements Animation {

    private final KeyboardSensor keyboard;
    private boolean stop;
    private final Counter endScore;
    private final boolean isWin;

    /**
     * This function checks if the player won or lost, and display a winning screen or a losing screen.
     *
     * @param k        - KeyboardSensor object.
     * @param endScore - score object.
     * @param isWin    - boolean that says if the player won or lost.
     */
    public EndScreen(KeyboardSensor k, Counter endScore, boolean isWin) {
        this.keyboard = k;
        this.stop = false;
        this.endScore = endScore;
        this.isWin = isWin;
    }

    /**
     * This function is in charge of the logic.
     *
     * @param d - DrawSurface
     */
    @Override
    public void doOneFrame(DrawSurface d) {
        if (this.isWin) {
            d.drawText(180, d.getHeight() / 2, "You Win! Your score is " + this.endScore.getValue(), 32);
        } else {
            d.drawText(170, d.getHeight() / 2, "Game Over. Your score is " + this.endScore.getValue(), 32);
        }
        if (this.keyboard.isPressed(KeyboardSensor.SPACE_KEY)) {
            this.stop = true;
        }
    }

    /**
     * This function returns true if it should stop, otherwise, false.
     *
     * @return - returns true if it should stop, otherwise, false.
     */
    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
