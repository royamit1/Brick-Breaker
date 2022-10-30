// 209205665 Roy Amit

import java.util.ArrayList;

/**
 * This class represents a line object.
 * A line (actually a line-segment) connects two points - a start point and an end point.
 * Lines have lengths, and may intersect with other lines.
 * It can also tell if it is the same as another line segment.
 */
public class Line {

    public static final double ERROR_RANGE = Math.pow(10, -10);

    private final Point start;
    private final Point end;

    /**
     * This function is the constructor, where we create 'Line' objects,
     * using 2 'Point' objects.
     *
     * @param start - represent 1 of the points at one end of the line
     * @param end   - represent the second point at the other end of the line
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    /**
     * This function is another constructor, where we create 'Line' objects,
     * using 4 double variables.
     *
     * @param x1 - represent the x value of the first point
     * @param y1 - represent the y value of the first point
     * @param x2 - represent the x value of the second point
     * @param y2 - represent the y value of the second point
     */
    public Line(double x1, double y1, double x2, double y2) {
        this(new Point(x1, y1), new Point(x2, y2));
    }

    /**
     * This function returns the length of the line.
     *
     * @return - length of the line
     */
    public double length() {
        return this.start.distance(this.end);
    }

    /**
     * This function returns the middle point of the line.
     *
     * @return - middle point of the line.
     */
    public Point middle() {
        double midX = (this.end.getX() + this.start.getX()) / 2;
        double midY = (this.end.getY() + this.start.getY()) / 2;
        return new Point(midX, midY);
    }

    /**
     * This function returns the start point of the line.
     *
     * @return - start point of the line.
     */
    public Point start() {
        return this.start;
    }

    /**
     * This function returns the end point of the line.
     *
     * @return - end point of the line.
     */
    public Point end() {
        return this.end;
    }

    /**
     * This function returns true if the lines intersect, false otherwise.
     *
     * @param other - Line object
     * @return - true if the lines intersect, false otherwise.
     */
    public boolean isIntersecting(Line other) {
        // Are the lines vertical?
        if (this.isVertical() && other.isVertical()) {
            // Do the lines have a similar x value? if not return false.
            if (this.start.getX() != other.start.getX()) {
                return false;
            }
            // Now check if they have a common point. If they have 1 or more common points, return true,
            // If they have no common points, return false.
            if ((!inRange(this.start, other.start, this.end) && !inRange(this.start, other.end, this.end))
                    && (!inRange(other.start, this.start, other.end)
                    && !inRange(other.start, this.end, other.end))) {
                return false;
            }
            return true;

        } else { // The lines are not vertical!
            if (this.sameEquation(other)) { // Do the lines have the same linear equation (y = mx + b)?
                if ((!inRange(this.start, other.start, this.end) && !inRange(this.start, other.end, this.end))
                        && (!inRange(other.start, this.start, other.end)
                        && !inRange(other.start, this.end, other.end))) {
                    return false;
                }
                return true;
            }
        }
        return this.nonVerticalLinesIntersect(other) != null;
    }

    /**
     * This function returns the intersection point if the lines intersect,
     * and null otherwise. I'm using the equation 'y = mx + b'
     * (and using 3 other functions: getSlope, getIntersectionY and inRange) for calculating
     * the x and y values and check if the lines intersect.
     *
     * @param other - Line object
     * @return - intersection point if the lines intersect, and null otherwise.
     */
    public Point intersectionWith(Line other) {
        // Are the lines vertical?
        if (this.isVertical() && other.isVertical()) {
            // Do the lines have a similar x value? if not return false.
            if (this.start.getX() != other.start.getX()) {
                return null;
            }
            // Now check if they have a common point, if they have no common point, return the null,
            // otherwise, check if they have 1 intersection point or more.
            if ((!inRange(this.start, other.start, this.end) && !inRange(this.start, other.end, this.end))
                    && (!inRange(other.start, this.start, other.end)
                    && !inRange(other.start, this.end, other.end))) {
                return null;
            }
            return this.isLineContainedWithSameEquation(other);

        } else { // The lines are not vertical!
            if (this.sameEquation(other)) { // Do the lines have the same linear equation (y = mx + b)?
                if ((!inRange(this.start, other.start, this.end) && !inRange(this.start, other.end, this.end))
                        && (!inRange(other.start, this.start, other.end)
                        && !inRange(other.start, this.end, other.end))) {
                    return null;
                }
                return this.isLineContainedWithSameEquation(other);
            }
        }
        return this.nonVerticalLinesIntersect(other);
    }

    /**
     * This function checks whether 2 lines (Lines that are not vertical,
     * and lines that do not have the same linear equation y = mx + b) have an intersection point.
     * if so it returns the point, otherwise, returns null.
     *
     * @param other - Line object
     * @return - returns the point, otherwise, returns null.
     */
    public Point nonVerticalLinesIntersect(Line other) {
        Point interP;
        if (!this.isVertical() && other.isVertical()) {
            interP = this.interPointWithVert(other);
        } else if (this.isVertical() && !other.isVertical()) {
            interP = other.interPointWithVert(this);
        } else {
            interP = this.interPointNonVert(other);
        }
        // Here we check if the intersection point is in the range of the lines (with the function inRange),
        // We use 2 function called inRange - 1 for Points, and 1 for doubles (Point's values).
        if (inRange(this.start(), interP, this.end()) && inRange(other.start(), interP, other.end())) {
            return interP;
        }
        return null;
    }

    /**
     * This function creates a linear equation for the non-vertical line,
     * and calculates the x and y values of the intersection point
     * of the non-vertical line with the vertical line.
     *
     * @param vert - a vertical line (for example x=5).
     * @return the intersection point of the infinite lines.
     */
    public Point interPointWithVert(Line vert) {
        double m = this.getSlope();
        double b = this.getIntersectionY();
        double y = m * vert.start.getX() + b;
        return new Point(vert.start.getX(), y);
    }

    /**
     * This function calculate the linear equation for both of the lines.
     * It returns the intersection point of the linear equation as detailed below.
     *
     * @param other - Line object
     * @return - intersection point of the linear equation as detailed below.
     */
    public Point interPointNonVert(Line other) {
        // 'y = m1x + b1'
        double m1 = this.getSlope();
        double b1 = this.getIntersectionY();
        // 'y = m2x + b2'
        double m2 = other.getSlope();
        double b2 = other.getIntersectionY();
        // calculate the x and y value of the intersection point.
        // m2x + b2 = m1x + b1 means that x = (b2 - b1) / (m1 - m2)
        double x = (b2 - b1) / (m1 - m2);
        double y = other.start.getY();
        return new Point(x, y);
    }

    /**
     * This function returns true if the line is vertical, false otherwise.
     *
     * @return - true if the line is vertical, false otherwise.
     */
    public boolean isVertical() {
        return this.start.getX() == this.end.getX();
    }

    /**
     * This function calculates and returns the slope of the line (m = (y1-y2) / (x1-x2)).
     *
     * @return - returns the slope of the line
     */
    public double getSlope() {
        return (this.end.getY() - this.start.getY()) / (this.end.getX() - this.start.getX());
    }

    /**
     * This function returns the intersection point of the infinite line with y-axis.
     * Here we want to calculate the equation for the line using 1 of the points we defined.
     * (y = mx + b) which means (b = y - mx)
     *
     * @return - intersection point of the infinite line with y-axis.
     */
    public double getIntersectionY() {
        return this.start.getY() - (this.getSlope() * this.start.getX());
    }

    /**
     * This function checks whether the lines have the same linear equation (y = mx + b)
     * using the 'getSlope' and 'getIntersectionY' methods.
     *
     * @param other - Line object
     * @return true of they have the same linear equation (y = mx + b), false otherwise.
     */
    public boolean sameEquation(Line other) {
        return (this.getSlope() == other.getSlope()) && (this.getIntersectionY() == other.getIntersectionY());
    }

    /**
     * This function return true if the lines are equal, false otherwise.
     *
     * @param other - Line object
     * @return - true if the lines are equal, false otherwise.
     */
    public boolean equals(Line other) {
        return (this.start.equals(other.start) && this.end.equals(other.end))
                || (this.start.equals(other.end) && this.end.equals(other.start));
    }

    /**
     * This function checks if the intersection point is in the range of the line.
     *
     * @param start - the start point of the line
     * @param inter - the intersection point of the infinite lines
     * @param end   - the end point of the line
     * @return true if it's in range, false otherwise.
     */
    public boolean inRange(Point start, Point inter, Point end) {
        return (inRange(start.getX(), inter.getX(), end.getX()) && inRange(start.getY(), inter.getY(), end.getY()));
    }

    /**
     * This function checks if the intersection point is in the range of the line.
     *
     * @param start - the x or y value of the start point of the line
     * @param inter - the x or y value of the intersection point of the infinite lines
     * @param end   - the x or y value of the end point of the line
     * @return true if it's in range, false otherwise.
     */
    public boolean inRange(double start, double inter, double end) {
        if (start > end) {
            return ((inter <= start && inter >= end) || (Math.abs(start - inter) < ERROR_RANGE
                    || Math.abs(inter - end) < ERROR_RANGE));
        } else {
            return ((inter <= end && inter >= start) || (Math.abs(end - inter) < ERROR_RANGE
                    || Math.abs(inter - start) < ERROR_RANGE));
        }
    }

    /**
     * This function checks whether 2 lines are contained in each other in some way possible.
     * in each 'if' condition, we check a different way of containment as detailed below.
     *
     * @param other - Line object
     * @return true if one line contained in the other line, false otherwise.
     */
    public Point isLineContainedWithSameEquation(Line other) {
        // We enter this function only in two cases: if the lines are vertical and have the same x value,
        // and if the lines have the same linear equation (y = mx + b)
        // We check if the lines are completely contained in each other
        if (inRange(this.start, other.start, this.end) && inRange(this.start, other.end, this.end)) {
            return null;
        }
        if (inRange(other.start, this.start, other.end) && inRange(other.start, this.end, other.end)) {
            return null;
        }
        // Check if one of the points (start or end) in one line is equal to one of the points of the other line.
        if (other.start.equals(this.start) || other.start.equals(this.end)) {
            return other.start;
        }
        if (other.end.equals(this.start) || other.end.equals(this.end)) {
            return other.end;
        }
        return null;
    }

    /**
     * This function checks if this line does not intersect with the rectangle, return null.
     * Otherwise, return the closest intersection point to the start of the line.
     *
     * @param rect - Rectangle object.
     * @return - return null if this line does not intersect with the rectangle.
     * Otherwise, return the closest intersection point to the start of the line.
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        double minLength = this.length();
        int index = 0;
        java.util.List<Point> interPoints = this.intersectionPointWithLine(rect);
        if (!interPoints.isEmpty()) {
            for (Point point : interPoints) {
                if (minLength >= this.start.distance(point)) {
                    minLength = this.start.distance(point);
                    index = interPoints.indexOf(point);
                }
            }
            return interPoints.get(index);
        }
        return null;
    }

    /**
     * This function takes an array of lines (that represent the rectangle's sides).
     * Then checks for intersection point between the specific line and the rectangle lines.
     * If there are no such points, return an empty list.
     *
     * @param rect - rectangle object.
     * @return - an array of intersection points, otherwise an empty list.
     */
    public java.util.List<Point> intersectionPointWithLine(Rectangle rect) {
        java.util.List<Point> interPoints = new ArrayList<>();
        Line[] rectLines = rect.getRectangleLines();
        for (Line line : rectLines) {
            if (this.intersectionWith(line) != null) {
                interPoints.add(this.intersectionWith(line));
            }
        }
        return interPoints;
    }
}
