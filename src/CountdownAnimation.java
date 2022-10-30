// 209205665 Roy Amit

import biuoop.DrawSurface;

import java.awt.Color;

/**
 * The CountdownAnimation will display the given gameScreen, for numOfSeconds seconds, and on top of them it will show
 * a countdown from countFrom back to 1, where each number will appear on the screen for
 * (numOfSeconds / countFrom) seconds, before it is replaced with the next one.
 */
public class CountdownAnimation implements Animation {

    private final double numOfSeconds;
    private int countFrom;
    private final SpriteCollection gameScreen;
    private boolean stop;

    /**
     * This function is the constructor.
     *
     * @param numOfSeconds - the number of seconds the countdown displays.
     * @param countFrom    - starting the countdown from 3 to 1.
     * @param gameScreen   - SpriteCollection object.
     */
    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen) {
        this.numOfSeconds = numOfSeconds;
        this.countFrom = countFrom + 1;
        this.gameScreen = gameScreen;
        this.stop = false;
    }

    /**
     * This function is in charge of the logic.
     *
     * @param d - DrawSurface
     */
    @Override
    public void doOneFrame(DrawSurface d) {
        this.gameScreen.drawAllOn(d);
        d.setColor(Color.WHITE);
        if (this.countFrom == 1) {
            d.drawText(300, 300, "START!", 60);
        } else {
            d.drawText(380, 300, Integer.toString(this.countFrom - 1), 60);
        }
        this.countFrom--;
        if (this.countFrom == 0) {
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