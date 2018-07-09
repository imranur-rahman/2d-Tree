import java.util.Objects;

public class Point {
    double x, y;
    int id;
    Point() {}

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Point(double x, double y, int id) {
        this.x = x;
        this.y = y;
        this.id = id;
    }

    @Override
    public String toString() {
        return "(" + x + "," + y + ")";
        //return String.valueOf(id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return Double.compare(point.x, x) == 0 &&
                Double.compare(point.y, y) == 0 &&
                id == point.id;
    }

    @Override
    public int hashCode() {

        return Objects.hash(x, y, id);
    }
}
