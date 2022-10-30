// 209205665 Roy Amit

/**
 * This class represents a point object.
 * A point has an x and a y value, and can measure the distance to other points,
 * and if it is equal to another point.
 */
public class Point {

    public static final double ERROR_RANGE = Math.pow(10, -10);

    private double x;
    private double y;

    /**
     * This function is the constructor, where we create 'Point' objects.
     *
     * @param x - represent the x value of the point
     * @param y - represent the y value of the point
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * This function returns the distance of this point to the other point.
     *
     * @param other - Point object
     * @return - distance of this point to the other point.
     */
    public double distance(Point other) {
        double lenX = this.x - other.x;
        double lenY = this.y - other.y;
        return Math.sqrt((lenX * lenX) + (lenY * lenY));
    }

    /**
     * This function returns true if the points are equal, false otherwise.
     *
     * @param other - Point object
     * @return - true if the points are equal, false otherwise.
     */
    public boolean equals(Point other) {
        return Math.abs(this.x - other.getX()) < ERROR_RANGE && Math.abs(this.y - other.getY()) < ERROR_RANGE;
    }

    /**
     * This function returns the x value of this point.
     *
     * @return - x value of this point.
     */
    public double getX() {
        return this.x;
    }

    /**
     * This function return the y value of this point.
     *
     * @return - y value of this point.
     */
    public double getY() {
        return this.y;
    }

    /**
     * This function sets the x value of this point.
     *
     * @param x - x value of this point
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * This function sets the y value of this point.
     *
     * @param y - y value of this point.
     */
    public void setY(double y) {
        this.y = y;
    }

}
