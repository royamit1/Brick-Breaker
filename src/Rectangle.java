// 209205665 Roy Amit

/**
 * This class represents a rectangle. this object holds a point (upper left), width and height.
 */
public class Rectangle {

    private final Point upperLeft;
    private final double width;
    private final double height;

    /**
     * This function is the constructor. It creates a new rectangle with location and width/height.
     *
     * @param upperLeft - the upper left point of the rectangle
     * @param width     - the width of the rectangle.
     * @param height    - the height of the rectangle.
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
    }

    /**
     * This function return a (possibly empty) List of intersection points with the specified line.
     *
     * @param line - line object.
     * @return - List of intersection points with the specified line.
     */
    public java.util.List<Point> intersectionPoints(Line line) {
        return line.intersectionPointWithLine(this);
    }

    /**
     * This function return the width of the rectangle.
     *
     * @return - width of the rectangle.
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * This function return the height of the rectangle.
     *
     * @return - height of the rectangle.
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * This function returns the upper-left point of the rectangle.
     *
     * @return - upper-left point of the rectangle.
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }

    /**
     * This function returns the upper-right point of the rectangle.
     *
     * @return - upper-right point of the rectangle.
     */
    public Point getUpperRight() {
        return new Point(this.getUpperLeft().getX() + this.getWidth(), this.getUpperLeft().getY());
    }

    /**
     * This function returns the lower-left point of the rectangle.
     *
     * @return - lower-left point of the rectangle.
     */
    public Point getDownLeft() {
        return new Point(this.getUpperLeft().getX(), this.getUpperLeft().getY() + this.getHeight());
    }

    /**
     * This function returns the lower-right point of the rectangle.
     *
     * @return - lower-right point of the rectangle.
     */
    public Point getDownRight() {
        return new Point(this.getUpperLeft().getX() + this.getWidth(),
                this.getUpperLeft().getY() + this.getHeight());
    }

    /**
     * This function takes the rectangle sides and turns them into an array of lines.
     *
     * @return - an array of lines that together creates a rectangle.
     */
    public Line[] getRectangleLines() {
        Line[] rectLines = new Line[4];
        rectLines[0] = new Line(this.getUpperLeft(), this.getUpperRight());
        rectLines[1] = new Line(this.getDownLeft(), this.getDownRight());
        rectLines[2] = new Line(this.getUpperLeft(), this.getDownLeft());
        rectLines[3] = new Line(this.getUpperRight(), this.getDownRight());
        return rectLines;

    }

    /**
     * This function sets the x value of the upper-left point of the rectangle.
     *
     * @param upperLeftX - new upper-left point for rectangle.
     */
    public void setUpperLeftX(double upperLeftX) {
        this.upperLeft.setX(upperLeftX);
    }

}