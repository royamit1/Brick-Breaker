// 209205665 Roy Amit

import biuoop.DrawSurface;

import java.awt.Color;

/**
 * This class represents a Paddle object.
 * It is a rectangle that is controlled by the arrow keys, and moves according to the player key presses.
 */
public class Paddle implements Sprite, Collidable {

    private static final int FRAME_WIDTH = 800;
    private static final int FRAME_SIZE_CONST = 15;
    private static final int PADDLE_START_Y = 565;
    private static final int PADDLE_HEIGHT = 20;
    private static final int DEFAULT_SPEED_BALL = 5;
    private static final int ADD_ANGLE = 30;
    private static final int STARTING_ANGLE = 270;
    private static final int REGION_1 = 1;
    private static final int REGION_2 = 2;
    private static final int REGION_3 = 3;
    private static final int REGION_4 = 4;
    private static final int REGION_5 = 5;

    private final biuoop.KeyboardSensor keyboard;
    private final Rectangle rect;
    private GameEnvironment gameEnv;
    private final int width;
    private final int speed;

    /**
     * This function is the constructor to create a paddle.
     *
     * @param keyboard - biuoop.KeyboardSensor.
     * @param width    - the width of the paddle - int.
     * @param speed    - the speed of the paddle - int.
     */
    public Paddle(biuoop.KeyboardSensor keyboard, int width, int speed) {
        this.width = width;
        this.speed = speed;
        this.keyboard = keyboard;
        this.rect = new Rectangle(new Point(400 - (double) this.width / 2, PADDLE_START_Y),
                this.width, PADDLE_HEIGHT);
        this.gameEnv = new GameEnvironment();
    }

    /**
     * This function sets a new game environment for the Paddle.
     *
     * @param gameEnv - GameEnvironment object.
     */
    public void setGameEnvironment(GameEnvironment gameEnv) {
        this.gameEnv = gameEnv;
    }

    /**
     * This function moves the paddle to the left.
     */
    public void moveLeft() {
        Line[] rectLines = rect.getRectangleLines();
        for (Ball ball : gameEnv.getBallList()) {
            // Here we check if the balls are getting in the way of the paddle (in the range of movement).
            if (rectLines[0].inRange(this.rect.getUpperLeft().getX() - this.speed,
                    ball.getCenter().getX(), this.rect.getUpperLeft().getX())
                    && rectLines[3].inRange(this.rect.getUpperLeft().getY(), ball.getCenter().getY(),
                    this.rect.getDownLeft().getY())) {
                // We check if the ball is getting to the borders of the screen.
                // If so, stop the movement of the paddle till the ball is out of the way.
                if (ball.getCenter().getX() < FRAME_SIZE_CONST + this.speed) {
                    this.rect.setUpperLeftX(FRAME_SIZE_CONST + this.speed);
                    return;
                } else {
                    // Otherwise, move the ball according to the movement of the paddle.
                    ball.getCenter().setX(this.rect.getUpperLeft().getX() - this.speed - 3);
                    // Here we check whether the ball hits the paddle on the left side.
                    if (ball.getVelocity().getDx() > 0) {
                        ball.setVelocity(ball.getVelocity().getDx() * (-1), ball.getVelocity().getDy());
                    }
                }
            }
        } // Set the paddle's location, and make sure it doesn't go out of the borders.
        this.rect.setUpperLeftX(this.rect.getUpperLeft().getX() - this.speed);
        if (this.rect.getUpperLeft().getX() < FRAME_SIZE_CONST) {
            this.rect.setUpperLeftX(FRAME_SIZE_CONST);
        }
    }

    /**
     * This function moves the paddle to the right.
     */
    public void moveRight() {
        Line[] rectLines = rect.getRectangleLines();
        for (Ball ball : gameEnv.getBallList()) {
            // Here we check if the balls are getting in the way of the paddle (in the range of movement).
            if (rectLines[0].inRange(this.rect.getUpperRight().getX(), ball.getCenter().getX(),
                    this.rect.getUpperRight().getX() + this.speed)
                    && rectLines[3].inRange(this.rect.getUpperLeft().getY(), ball.getCenter().getY(),
                    this.rect.getDownLeft().getY())) {
                // We check if the ball is getting to the borders of the screen.
                // If so, stop the movement of the paddle till the ball is out of the way.
                if (ball.getCenter().getX() > FRAME_WIDTH - FRAME_SIZE_CONST - this.speed) {
                    this.rect.setUpperLeftX(FRAME_WIDTH - FRAME_SIZE_CONST - this.width - this.speed);
                    return;
                } else {
                    // Otherwise, move the ball according to the movement of the paddle.
                    ball.getCenter().setX(this.rect.getUpperRight().getX() + this.speed + 3);
                    // Here we check whether the ball hits the paddle on the right side.
                    if (ball.getVelocity().getDx() < 0) {
                        ball.setVelocity(ball.getVelocity().getDx() * (-1), ball.getVelocity().getDy());
                    }
                }
            }
        } // Set the paddle's location, and make sure it doesn't go out of the borders.
        this.rect.setUpperLeftX(this.rect.getUpperLeft().getX() + this.speed);
        if (this.rect.getUpperRight().getX() > FRAME_WIDTH - FRAME_SIZE_CONST) {
            this.rect.setUpperLeftX(FRAME_WIDTH - FRAME_SIZE_CONST - this.width);
        }
    }

    /**
     * This function draws the sprite (Paddle) to the screen.
     *
     * @param d - DrawSurface object.
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.YELLOW);
        d.fillRectangle((int) this.rect.getUpperLeft().getX(), (int) this.rect.getUpperLeft().getY(),
                (int) this.rect.getWidth(), (int) this.rect.getHeight());
        d.setColor(Color.BLACK);
        d.drawRectangle((int) this.rect.getUpperLeft().getX(), (int) this.rect.getUpperLeft().getY(),
                (int) this.rect.getWidth(), (int) this.rect.getHeight());
    }

    /**
     * This function notifies the sprite (Paddle) that time has passed.
     * By calling the moveRight and moveLeft functions.
     */
    @Override
    public void timePassed() {
        if (keyboard.isPressed(biuoop.KeyboardSensor.RIGHT_KEY)) {
            moveRight();
        }
        if (keyboard.isPressed(biuoop.KeyboardSensor.LEFT_KEY)) {
            moveLeft();
        }
    }

    /**
     * This function returns the "collision shape" of the object.
     *
     * @return - "collision shape" of the object.
     */
    @Override
    public Rectangle getCollisionRectangle() {
        return this.rect;
    }

    /**
     * This function Notifies the object that we collided with it at collisionPoint with a given velocity.
     * The return is the new velocity expected after the hit (based on the force the object inflicted on us).
     *
     * @param collisionPoint  - the collision point.
     * @param currentVelocity - the current velocity.
     * @return - new velocity expected after the hit.
     */
    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        int regionConst = this.width / 5;
        Line[] rectLines = this.rect.getRectangleLines();
        // Here we check if the hit occurred on the sides of the paddle
        if (rectLines[2].inRange(rectLines[2].start(), collisionPoint, rectLines[2].end())) {
            currentVelocity.setDx(currentVelocity.getDx() * (-1));
        }
        if (rectLines[3].inRange(rectLines[3].start(), collisionPoint, rectLines[3].end())) {
            currentVelocity.setDx(currentVelocity.getDx() * (-1));
        }
        // Hit on region 1
        if ((collisionPoint.getX() >= this.rect.getUpperLeft().getX())
                && (collisionPoint.getX() < this.rect.getUpperLeft().getX() + regionConst)
                && collisionPoint.getY() == this.rect.getUpperLeft().getY()) {
            currentVelocity = changeRegionHitAngle(REGION_1);

            // Hit on region 2
        } else if ((collisionPoint.getX() >= this.rect.getUpperLeft().getX() + regionConst)
                && (collisionPoint.getX() < this.rect.getUpperLeft().getX() + regionConst * REGION_2)) {
            currentVelocity = changeRegionHitAngle(REGION_2);

            // Hit on region 3
        } else if ((collisionPoint.getX() >= this.rect.getUpperLeft().getX() + regionConst * REGION_2)
                && (collisionPoint.getX() <= this.rect.getUpperLeft().getX() + regionConst * REGION_3)) {
            currentVelocity.setDy(currentVelocity.getDy() * (-1));

            // Hit on region 4
        } else if ((collisionPoint.getX() > this.rect.getUpperLeft().getX() + regionConst * REGION_3)
                && (collisionPoint.getX() <= this.rect.getUpperLeft().getX() + regionConst * REGION_4)) {
            currentVelocity = changeRegionHitAngle(REGION_4);

            // Hit on region 5
        } else if ((collisionPoint.getX() > this.rect.getUpperLeft().getX() + regionConst * REGION_4)
                && (collisionPoint.getX() <= this.rect.getUpperLeft().getX() + regionConst * REGION_5)
                && collisionPoint.getY() == this.rect.getUpperLeft().getY()) {
            currentVelocity = changeRegionHitAngle(REGION_5);
        }
        return currentVelocity;
    }

    /**
     * This function changes the direction of the ball based on the region it hit.
     *
     * @param i - the region that the hit occurred.
     * @return - the new velocity depends on the region.
     */
    public Velocity changeRegionHitAngle(int i) {
        return Velocity.fromAngleAndSpeed(STARTING_ANGLE + (i * ADD_ANGLE), DEFAULT_SPEED_BALL);
    }

    /**
     * This function adds this paddle to the game.
     *
     * @param g - game object.
     */
    public void addToGame(GameLevel g) {
        g.addCollidable(this);
        g.addSprite(this);
    }
}