import java.io.*;
import java.util.Scanner;

public class Main {
    static int n;
    static Point[] arr;
    static KDTree kdTree = new KDTree();
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(new FileReader("src/input.txt"));
        n = scanner.nextInt();
        arr = new Point[n];
        for(int i = 0; i < n; ++i)
        {
            double x = scanner.nextDouble();
            double y = scanner.nextDouble();
            arr[i] = new Point(x, y, i);
        }

        Range range = new Range(Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY,
                                Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);
        //Range range = new Range(-100, -100, 100, 100);

        kdTree.root = kdTree.buildKDTree(arr, 0, n - 1, 0, range);
        //kdTree.printTree(kdTree.root);
        /*OutputStreamWriter out = new OutputStreamWriter(System.out);
        kdTree.root.printTree(out);
        out.flush();*/
        while(scanner.hasNext())
        {
            char ch = scanner.next().charAt(0);
            if(ch == 'R')
            {
                range = new Range();
                range.xmin = scanner.nextDouble();
                range.ymin = scanner.nextDouble();
                range.xmax = scanner.nextDouble();
                range.ymax = scanner.nextDouble();

                kdTree.query(range);
            }
            else if(ch == 'N')
            {
                Point point = new Point();
                point.x = scanner.nextDouble();
                point.y = scanner.nextDouble();

                kdTree.nearestNeighbour(point);
            }
            else
            {
                System.out.println("error");
                break;
            }
        }
    }
}
