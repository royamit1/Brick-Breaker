// 209205665 Roy Amit

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

/**
 * This class represents an AnimationRunner that takes an Animation object and runs it.
 */
public class AnimationRunner {

    private final GUI gui;
    private int framesPerSecond;
    private final Sleeper sleeper;

    /**
     * This function is the constructor.
     *
     * @param gui             - GUI object.
     * @param framesPerSecond - int.
     */
    public AnimationRunner(GUI gui, int framesPerSecond) {
        this.gui = gui;
        this.framesPerSecond = framesPerSecond;
        this.sleeper = new Sleeper();
    }

    /**
     * This function returns the number of frames per second.
     *
     * @return - the number of frames per second.
     */
    public int getFramesPerSecond() {
        return this.framesPerSecond;
    }

    /**
     * This function sets the number of frames per second.
     *
     * @param framesPerSecond - the number of frames per second.
     */
    public void setFramesPerSecond(int framesPerSecond) {
        this.framesPerSecond = framesPerSecond;
    }

    /**
     * This function Runs the game -- starts the animation loop.
     *
     * @param animation - Animation object.
     */
    public void run(Animation animation) {
        int millisecondsPerFrame = getFramesPerSecond();
        while (!animation.shouldStop()) {
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = this.gui.getDrawSurface();

            animation.doOneFrame(d);

            this.gui.show(d);
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                this.sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }
}