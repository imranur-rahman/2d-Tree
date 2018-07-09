public class Range {
    double xmin, ymin, xmax, ymax;

    public Range() {}

    public Range(double xmin, double ymin, double xmax, double ymax) {
        this.xmin = xmin;
        this.ymin = ymin;
        this.xmax = xmax;
        this.ymax = ymax;
    }

    public boolean isFullyContainedIn(Range that){
        return this.xmin >= that.xmin  &&  this.xmax <= that.xmax  &&
                this.ymin >= that.ymin  &&  this.ymax <= that.ymax;
    }

    public boolean intersects(Range that) {
        return this.xmax >= that.xmin  &&  this.ymax >= that.ymin  &&
                that.xmax >= this.xmin  &&  that.ymax >= this.ymin;
    }

    @Override
    public String toString() {
        return "Range{" +
                "xmin=" + xmin +
                ", ymin=" + ymin +
                ", xmax=" + xmax +
                ", ymax=" + ymax +
                '}';
    }
}
