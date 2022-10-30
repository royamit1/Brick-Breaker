// 209205665 Roy Amit

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * This class represents a decorator-class that will wrap an existing animation,
 * and add a "waiting-for-key" behavior to it.
 */
public class KeyPressStoppableAnimation implements Animation {

    private boolean isAlreadyPressed = true;
    private final KeyboardSensor sensor;
    private final String key;
    private final Animation animation;
    private boolean stop;

    /**
     * This function is the constructor.
     *
     * @param sensor    - KeyboardSensor object.
     * @param key       - String object.
     * @param animation - Animation object.
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation animation) {
        this.sensor = sensor;
        this.key = key;
        this.animation = animation;
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
        this.animation.doOneFrame(d);
        if ((this.sensor.isPressed(this.key)) && (!this.isAlreadyPressed)) {
            this.stop = true;
        } else if (!this.sensor.isPressed(this.key)) {
            this.isAlreadyPressed = false;
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