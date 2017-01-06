import java.util.*;

public class CoveringSegments {

    private static int[] optimalPoints(Segment[] segments) {
        //write your code here

        // Arrays.sort(segments, Collections.reverseOrder());
        // int[] points = new int[2 * segments.length];
        // String op = "";
        // int[] opArr = new int[segments.length];
        // for (int i = 0; i < segments.length; i++) {
        //     points[2 * i] = segments[i].start;
        //     points[2 * i + 1] = segments[i].end;
        // }

        // for (int i = 0; i < segments.length; i++) {
        //     int startOfRightMostSegment = 0;
        //     int j = 0;
        //     while(j < points.length) { // find start of rightmost segment
        //         if(points[j] > points[startOfRightMostSegment]) {
        //             startOfRightMostSegment = j;                    
        //         }
        //         j += 2;
        //     }
        //     if(points[startOfRightMostSegment])

        // }

        //Sort Segments before using greedy algorithm
        
        Arrays.sort(segments);
        int[] op = new int[segments.length];
        int[] points = new int[2 * segments.length];        
        for (int i = 0; i < segments.length; i++) {
            points[2 * i] = segments[i].start;
            points[2 * i + 1] = segments[i].end;
        }
        int startOfRightMostSegment = points[points.length - 2];
        op[0] = startOfRightMostSegment;
        int opIndex = 1;
        for (int i = points.length - 2; i >= 0; i-=2) {
            if(points[i + 1] < startOfRightMostSegment) {
                op[opIndex] = points[i];
                opIndex++;
                startOfRightMostSegment = points[i];
            }
        }

        int[] finalArr = new int[opIndex];
        for(int i = 0; i < opIndex; i++) {
            finalArr[i] = op[i];
        }
        
        return finalArr;
    }

    private static class Segment implements Comparable<Segment> {
        int start, end;

        Segment(int start, int end) {
            this.start = start;
            this.end = end;
        }

        public int compareTo(Segment that) {
            if(this.start < that.start) return -1;
            if(this.start > that.start) return 1;
            return 0;
        }
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        Segment[] segments = new Segment[n];
        for (int i = 0; i < n; i++) {
            int start, end;
            start = scanner.nextInt();
            end = scanner.nextInt();
            segments[i] = new Segment(start, end);
            // System.out.println(segments[i].start);
        }
        // System.out.println("---sorted---");
        // Arrays.sort(segments, Collections.reverseOrder());
        // for(int i = 0; i < n; i++) {
        //     System.out.println(segments[i].start);   
        // }

        int[] points = optimalPoints(segments);
        System.out.println(points.length);
        for (int point : points) {
            System.out.print(point + " ");
        }
    }
}
 
