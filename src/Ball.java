// 209205665 Roy Amit

import biuoop.DrawSurface;

import java.awt.Color;

/**
 * This class represents a ball object. ball has a center point, radius, color, velocity and GameEnvironment.
 */
public class Ball implements Sprite {

    private final Point center;
    private final int r;
    private final java.awt.Color color;
    private final Velocity vel;
    private GameEnvironment gameEnv;

    /**
     * This function is the constructor, where we create 'Ball' objects.
     * using center point, radius, color and velocity.
     *
     * @param center - Point object that represent the center of the ball.
     * @param r      - the radius of the ball.
     * @param color  - the color of the ball.
     * @param vel    - the velocity of the ball.
     */
    public Ball(Point center, int r, java.awt.Color color, Velocity vel) {
        if (r < 0) {
            this.r = Math.abs(r);
        } else {
            this.r = r;
        }
        this.center = center;
        this.color = color;
        this.vel = vel;
    }

    /**
     * This function is the constructor, where we create 'Ball' objects.
     * using center point, radius and color.
     *
     * @param center - Point object that represent the center of the ball.
     * @param r      - the radius of the ball.
     * @param color  - the color of the ball.
     */
    public Ball(Point center, int r, java.awt.Color color) {
        this(center, r, color, new Velocity(0, 0));
    }

    /**
     * This function is the constructor, where we create 'Ball' objects.
     * using double values x and y (represent point), radius and color.
     *
     * @param x     - x value of the center point.
     * @param y     - y value of the center point.
     * @param r     - the radius of the ball.
     * @param color - the color of the ball.
     */
    public Ball(double x, double y, int r, java.awt.Color color) {
        this(new Point(x, y), r, color, new Velocity(0, 0));
    }

    /**
     * This function returns the center point of the ball.
     *
     * @return - the center point.
     */
    public Point getCenter() {
        return this.center;
    }

    /**
     * This function returns the x value of center point.
     *
     * @return - x value of center point.
     */
    public int getX() {
        return (int) this.center.getX();
    }

    /**
     * This function returns the y value of center point.
     *
     * @return - y value of center point.
     */
    public int getY() {
        return (int) this.center.getY();
    }

    /**
     * This function returns the radius of the ball.
     *
     * @return - radius of the ball.
     */
    public int getSize() {
        return this.r;
    }

    /**
     * This function returns the color of the ball.
     *
     * @return - color of the ball.
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    /**
     * This function returns the velocity of the ball.
     *
     * @return - velocity of the ball.
     */
    public Velocity getVelocity() {
        return this.vel;
    }

    /**
     * This function get a Velocity object, and sets the ball's velocity values to the new values.
     *
     * @param v - Velocity object.
     */
    public void setVelocity(Velocity v) {
        this.vel.setDx(v.getDx());
        this.vel.setDy(v.getDy());
    }

    /**
     * This function get velocity values on both axes, and sets the ball's velocity values to the new values.
     *
     * @param dx - velocity on x-axis.
     * @param dy - velocity on y-axis.
     */
    public void setVelocity(double dx, double dy) {
        this.vel.setDx(dx);
        this.vel.setDy(dy);
    }

    /**
     * This function sets a new game environment for the ball.
     *
     * @param gameEnv - GameEnvironment object.
     */
    public void setGameEnvironment(GameEnvironment gameEnv) {
        this.gameEnv = gameEnv;
        this.gameEnv.addBall(this);
    }

    /**
     * This function moves the ball one step according to the velocity that the ball has.
     */
    public void moveOneStep() {
        Line trajectory = getTrajectory();
        CollisionInfo info = this.gameEnv.getClosestCollision(trajectory);
        if (info == null) {
            this.center.setX(this.center.getX() + this.getVelocity().getDx());
            this.center.setY(this.center.getY() + this.getVelocity().getDy());
        } else {
            this.center.setX(info.collisionPoint().getX() - (Math.signum(this.getVelocity().getDx() * 3)));
            this.center.setY(info.collisionPoint().getY() - (Math.signum(this.getVelocity().getDy() * 3)));
            this.setVelocity(info.collisionObject().hit(this, info.collisionPoint(), this.getVelocity()));
        }
    }

    /**
     * This function return the trajectory of a ball.
     *
     * @return - trajectory of a ball.
     */
    public Line getTrajectory() {
        return new Line(this.center,
                new Point(this.center.getX() + this.getVelocity().getDx(),
                        this.center.getY() + this.getVelocity().getDy()));
    }

    /**
     * This function draws the ball on the given DrawSurface.
     *
     * @param surface - DrawSurface object.
     */
    @Override
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.getColor());
        surface.fillCircle(this.getX(), this.getY(), this.getSize());
        surface.setColor(Color.BLACK);
        surface.drawCircle(this.getX(), this.getY(), this.getSize());
    }

    /**
     * This function notifies the sprite (ball) that time has passed.
     */
    @Override
    public void timePassed() {
        this.moveOneStep();
    }

    /**
     * This function adds a ball to the game.
     *
     * @param g - Game object.
     */
    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }

    /**
     * This function removes a block from the game.
     *
     * @param game - Game object.
     */
    public void removeFromGame(GameLevel game) {
        game.removeSprite(this);
    }
}