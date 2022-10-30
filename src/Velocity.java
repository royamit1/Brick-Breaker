// 209205665 Roy Amit

/**
 * This class represents the velocity of a ball.
 * specifies the change in position on the `x` and the `y` axes.
 */
public class Velocity {

    private double dx;
    private double dy;

    /**
     * This function is the constructor, where we create 'Velocity' objects,
     * using 2 doubles - dx and dy.
     *
     * @param dx - dx value of velocity.
     * @param dy - dy value of velocity.
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * This function returns the dx value of this velocity.
     *
     * @return - dx value of this velocity.
     */
    public double getDx() {
        return this.dx;
    }

    /**
     * This function return the dy value of this velocity.
     *
     * @return - dy value of this velocity.
     */
    public double getDy() {
        return this.dy;
    }

    /**
     * This function sets the dx value of this velocity.
     *
     * @param dx - value of this velocity.
     */
    public void setDx(double dx) {
        this.dx = dx;
    }

    /**
     * This function sets the dy value of this velocity.
     *
     * @param dy - value of this velocity.
     */
    public void setDy(double dy) {
        this.dy = dy;
    }

    /**
     * This function calculates the velocity of the ball,
     * using angle and speed (in each axis) and returns it.
     *
     * @param angle - the direction of the ball
     * @param speed - the speed of the ball
     * @return - the velocity of the ball
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double dx = speed * (float) Math.sin(Math.toRadians(angle));
        double dy = (-1) * speed * (float) Math.cos(Math.toRadians(angle));
        return new Velocity(dx, dy);
    }

    /**
     * This function takes a point with position (x,y) and return a new point.
     * with position (x+dx, y+dy).
     *
     * @param p - Point object
     * @return - new point with position (x+dx, y+dy).
     */
    public Point applyToPoint(Point p) {
        return new Point(this.dx + p.getX(), this.dy + p.getY());
    }
}