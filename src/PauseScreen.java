// 209205665 Roy Amit

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * This class represents the pause option when pressing the 'p' key.
 */
public class PauseScreen implements Animation {

    private final KeyboardSensor keyboard;
    private boolean stop;

    /**
     * This function pauses the game when we press 'p' key.
     *
     * @param k - KeyboardSensor object.
     */
    public PauseScreen(KeyboardSensor k) {
        this.keyboard = k;
        this.stop = false;
    }

    /**
     * This function is in charge of the logic. the logic from the previous run method goes here.
     * the `return` or `break` statements should be replaced with this.running = false;.
     *
     * @param d - DrawSurface
     */
    @Override
    public void doOneFrame(DrawSurface d) {
        d.drawText(170, d.getHeight() / 2, "Paused -- press space to continue", 32);
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