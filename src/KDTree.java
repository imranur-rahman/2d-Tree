import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

public class KDTree {
    Node root;

    final double epsilon = 0.0000001;//etar dorkar ache kina dekhte hobe

    public Node buildKDTree(Point[] arr, int from, int to, int depth, Range range)
    {
        if(from == to)//only one point
        {
            Point point = arr[from];
            Range newRange = new Range(point.x, point.y, point.x, point.y);
            return new Node(point, depth, newRange);
        }

        int median;
        Point point;

        Range newRange;
        Node left, right;

        if(depth % 2 == 0)// x - coordinate
        {
            // sort [from, to)
            Arrays.sort(arr, from, to + 1, new Comparator<Point>() {
                @Override
                public int compare(Point o1, Point o2) {
                    return Double.valueOf(o1.x).compareTo(o2.x);
                }
            });

            median = from + (to - from) / 2;
            point = arr[median];

            //System.out.print("from: " + from + ", to: " + to + " ");
            //System.out.println(Arrays.toString(arr));

            newRange = new Range(range.xmin, range.ymin, point.x, range.ymax);
            left = buildKDTree(arr, from, median, depth + 1, newRange);

            newRange = new Range(point.x + epsilon, range.ymin, range.xmax, range.ymax);
            right = buildKDTree(arr, median + 1, to, depth + 1, newRange);
        }
        else
        {
            // sort [from, to)
            Arrays.sort(arr, from, to + 1, new Comparator<Point>() {
                @Override
                public int compare(Point o1, Point o2) {
                    return Double.valueOf(o1.y).compareTo(o2.y);
                }
            });

            median = from + (to - from) / 2;
            point = arr[median];

            //System.out.print("from: " + from + ", to: " + to + " ");
            //System.out.println(Arrays.toString(arr));

            newRange = new Range(range.xmin, range.ymin, range.xmax, point.y);
            left = buildKDTree(arr, from, median, depth + 1, newRange);

            newRange = new Range(range.xmin, point.y + epsilon, range.xmax, range.ymax);
            right = buildKDTree(arr, median + 1, to, depth + 1, newRange);
        }


        return new Node(point, left, right, depth, range);
    }

    public void query(Range range)
    {
        Set<Point>allPoints = new HashSet<>();
        queryUtil(root, range, allPoints);
        //System.out.println(range);
        System.out.println(allPoints);
        System.out.println(allPoints.size());
    }

    private void queryUtil(Node now, Range range, Set<Point> allPoints) {
        //System.out.println(now);
        if(now.isLeaf())
        {
            reportSubtree(now, allPoints);
            return;
        }

        if(now.left != null) {
            if (now.left.range.isFullyContainedIn(range))
                reportSubtree(now.left, allPoints);
            else if (now.left.range.intersects(range))
                queryUtil(now.left, range, allPoints);
        }

        if(now.right != null) {
            if (now.right.range.isFullyContainedIn(range))
                reportSubtree(now.right, allPoints);
            else if (now.right.range.intersects(range))
                queryUtil(now.right, range, allPoints);
        }
    }

    private void reportSubtree(Node now, Set<Point> allPoints) {
        if(now.isLeaf())
        {
            allPoints.add(now.point);
            return;
        }
        if(now.left != null)
            reportSubtree(now.left, allPoints);
        if(now.right != null)
            reportSubtree(now.right, allPoints);
    }

    public void printTree(Node now)
    {
        if(now == null) return;
        printTree(now.left);
        printTree(now.right);
        System.out.println(now);
    }
}
