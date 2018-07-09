import java.io.IOException;
import java.io.OutputStreamWriter;
import java.time.chrono.HijrahChronology;
import java.util.Comparator;

class Node {
    Node left, right;
    Point point;
    Range range;
    int depth;

    public Node() {}

    public Node(int x, int y) {
        this.point = new Point(x, y);
    }

    @Override
    public String toString() {
        return "Node{" +
                "point=" + point +
                ", range=" + range +
                ", depth=" + depth +
                ", isLeaf=" + isLeaf() +
                '}';
    }

    public Node(Point point, int depth)
    {
        this.point = point;
        this.depth = depth;
    }

    public Node(Point point, int depth, Range range)
    {
        this.point = point;
        this.depth = depth;
        this.range = range;
    }

    public Node(Point point, Node left, Node right, int depth)
    {
        this.point = point;
        this.left = left;
        this.right = right;
        this.depth = depth;
    }

    public Node(Point point, Node left, Node right, int depth, Range range)
    {
        this.point = point;
        this.left = left;
        this.right = right;
        this.depth = depth;
        this.range = range;
    }

    public boolean isLeaf()
    {
        if(left == null  &&  right == null)
            return true;
        return false;
    }

    public void printThisNodeOnly()
    {
        System.out.print("(" + point.x + ", " + point.y + ")");
    }

    public void printNode()
    {
        this.printNode();
        if(left != null) {
            System.out.print(", left: ");
            left.printThisNodeOnly();
        }
        if(right != null) {
            System.out.print(" right: ");
            right.printThisNodeOnly();
        }
        System.out.println();
    }

    public void printTree(OutputStreamWriter out) throws IOException {
        if (right != null) {
            right.printTree(out, true, "");
        }
        printNodeValue(out);
        if (left != null) {
            left.printTree(out, false, "");
        }
    }
    private void printNodeValue(OutputStreamWriter out) throws IOException {
        if (point == null) {
            out.write("<null>");
        } else {
            out.write(point.toString());
        }
        out.write('\n');
    }
    // use string and not stringbuffer on purpose as we need to change the indent at each recursion
    private void printTree(OutputStreamWriter out, boolean isRight, String indent) throws IOException {
        if (right != null) {
            right.printTree(out, true, indent + (isRight ? "        " : " |      "));
        }
        out.write(indent);
        if (isRight) {
            out.write(" /");
        } else {
            out.write(" \\");
        }
        out.write("----- ");
        printNodeValue(out);
        if (left != null) {
            left.printTree(out, false, indent + (isRight ? " |      " : "        "));
        }
    }
}