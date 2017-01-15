import java.util.*;
import java.lang.*;
import java.util.concurrent.ThreadLocalRandom;

public class Closest {

    private static class Point {
        int x;
        int y;
    }

    private static double minDistance(Point[] a) {
        //write your code here
        double result = 0;
        Point[] pY = new Point[a.length];
        Point[] pX = new Point[a.length];
        Point[] aux = new Point[a.length];
        for(int i=0; i< a.length; i++) {
            pX[i] = a[i];
        }

        Arrays.sort(pX, new java.util.Comparator<Point>() { //sort array by X
            public int compare(Point p, Point q) {
                if(p.x < q.x) return -1;
                if(p.x > q.x) return 1;
                return 0;
            }
        });

        for(int i =0; i< a.length - 1; i++) { // If 2 identical points found just return 0
            if(pX[i].x == pX[i+1].x && pX[i].y == pX[i + 1].y) return 0.0;
        }

        for(int i = 0; i < a.length; i++) { // Create array pY which will be sorted by Y value. (Not sorted yet, to sort recursively)
            pY[i] = pX[i];
        }

        return minDistance(pX, pY, aux, 0, a.length - 1);
    }

    private static double minDistance(Point[] pX, Point[] pY, Point[] aux, int lo, int hi) {
        if(hi - lo <= 3) { // If only 4 points just use brute force instead of divide and conquer. Use selection sort to sort by Y
            selectionSortY(pY, lo, hi);
            return bruteForce(pX, lo, hi);
        }

        // Point[] strip = new Point[hi-lo + 1]; //*** Minimize creation and initialization of arrays in loops/recursions. Slows program significantly.
        int mid = lo + (hi - lo) / 2;
        Point midPoint = pX[mid];

        double leftMin = minDistance(pX, pY, aux, lo, mid); // get min distance on left
        double rightMin = minDistance(pX, pY, aux, mid+1, hi); // get min distance on right

        mergePy(pY, aux, lo, mid, hi); // recursively sort half by Y value 

        double d = Math.min(leftMin, rightMin);

        int k = 0;
        for(int i = lo; i <= hi; i++) {
            if(Math.abs(pY[i].x - midPoint.x) < d) {
                aux[k++] = pY[i]; // Reuse aux to store points sorted by Y within strip
            }
        }

        double dStrip = stripMin(aux, k, d); // Compare points within strip to check if there is min distance between points on left and right half

        return Math.min(dStrip, d);
    }

    private static void selectionSortY(Point[] pY, int lo, int hi) {
        for(int i = lo; i <= hi; i++) {
            for(int j=i+1; j<=hi; j++) {
                if(pY[j].y < pY[i].y) {
                    Point temp = pY[i];
                    pY[i] = pY[j];
                    pY[j] = temp;
                }
            }
        }
    }

    private static void mergePy(Point[] pY, Point[] aux, int lo, int mid, int hi) {
        for(int i = lo; i <= hi; i++) {
            aux[i] = pY[i];
        }

        int j = lo;
        int k = mid + 1;

        for(int i = lo; i <= hi; i++) {
            if(j > mid) {
                pY[i] = aux[k++];
            } else if(k > hi) {
                pY[i] = aux[j++];
            } else if(aux[j].y <= aux[k].y) {
                pY[i] = aux[j++];
            } else {
                pY[i] = aux[k++];
            }
        }
    }

    private static double stripMin(Point[] s, int sz, double d) {
        double result = d;
        for(int i = 0; i < sz; i++) {
            for(int j = i + 1; j < sz && s[j].y <= s[i].y + d; j++) { // At most 6 points so not is O(n) not O(n2). If comparison point is more than distance of d away along y axis move on to next point, since s is sorted by Y.
                double dist = calcDistance(s[i].x, s[i].y, s[j].x, s[j].y);
                if(dist < result) result = dist;                        
            }
        }

        return result;
    }

    private static double bruteForce(Point[] a, int lo, int hi) {
        double result = Double.MAX_VALUE;

        for(int i = lo; i<= hi; i ++) {
            for(int j = i + 1; j <= hi; j++) {
                double distBetweenPoints = calcDistance(a[i].x, a[i].y, a[j].x, a[j].y);
                if ( distBetweenPoints < result) {
                    result = distBetweenPoints;
                }
            }
        }
        return result;
    }    

    private static double calcDistance(int px, int py, int qx, int qy) { // Calculate Euclidean distance, can optimise further by returning squared distance
        return Math.sqrt((px - qx) * (px - qx) + (py - qy) * (py - qy));
    }

/* Helper and stress test methods
    private static double minDistanceNaive(Point[] a) { // For stress test comparison only
        double result = Double.MAX_VALUE;

        for(int i = 0; i< a.length; i ++) {
            for(int j = i + 1; j < a.length; j++) {
                double distBetweenPoints = calcDistance(a[i].x, a[i].y, a[j].x, a[j].y);
                if ( distBetweenPoints < result) result = distBetweenPoints;
            }
        }
        return result;
    }

    private static void printArr(Point[] a) { // Convenience method for printing array
        for(int i = 0; i < a.length; i++) {
            System.out.print("Point: (" + a[i].x + ", "+a[i].y+")");
        }
        System.out.println("");
    }    


    private static void stressTest() { // stress test only
        while(true) {
            int numberSegments = ThreadLocalRandom.current().nextInt(1, 10);    
            Point[] a = new Point[numberSegments];
            for(int i = 0; i < numberSegments; i++) {
                Point p = new Point();
                p.x = ThreadLocalRandom.current().nextInt(-10, 10);
                p.y = ThreadLocalRandom.current().nextInt(-10, 10);
                a[i] = p;
            }

            System.out.println("Input: ");
            for(int i = 0; i < a.length; i++) {
                System.out.print("Point: ("+ a[i].x + ", "+a[i].y +")");
            }
            System.out.println("");                

            double fast = minDistance(a);
            double slow = minDistanceNaive(a);

            if(Double.compare(fast, slow) == 0) {
                System.out.println("OK");
            } else {
                System.out.println("Fast: " + fast);
                System.out.println("Slow: " + slow);
                break;
            }
        }
    }
*/
    public static void main(String[] args) {
        // stressTest();
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        Point[] a = new Point[n];
        for (int i = 0; i < n; i++) {
            Point p = new Point();
            p.x = scanner.nextInt();
            p.y = scanner.nextInt();
            a[i] = p;
        }

        System.out.println(minDistance(a));
        // System.out.println(minDistanceNaive(a));
    }
}

