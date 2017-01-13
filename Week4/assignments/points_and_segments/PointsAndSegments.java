import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;
import java.util.Arrays;

public class PointsAndSegments {

    private static int[] fastCountSegments(int[] starts, int[] ends, int[] points) {
        int[] cnt = new int[points.length];
        Arrays.sort(starts); // sort start points
        Arrays.sort(ends); // sort end points

        // each point do a binary search, find no. start points on left and no. end points on left
        for(int i = 0; i < points.length; i++){
            int numberStartsBeforePoint = findPointStartIndex(starts, points[i], 0, starts.length - 1) + 1;
            int numberEndsBeforePoint = findPointEndIndex(ends, points[i], 0, ends.length - 1) + 1;

            cnt[i] = numberStartsBeforePoint - numberEndsBeforePoint;// start points on left - end points on left = segments
        }
                
        return cnt;
    }

    private static int findPointStartIndex(int[] arr, int p, int left, int right) {
        if(right <= left) {
            if(arr[left] > p) return left -1;
            return left;
        }
        int ave = (left + right) / 2;
        if(arr[ave] <= p) {
            return findPointStartIndex(arr, p, ave + 1, right);
        } else {
            return findPointStartIndex(arr, p, left, ave - 1);
        }
    }

    private static int findPointEndIndex(int[] arr, int p, int left, int right) {
        if(right <= left) {
            if(arr[left] >= p) return left -1;
            return left;
        }
        int ave = (left + right) / 2;
        if(arr[ave] < p) {
            return findPointEndIndex(arr, p, ave + 1, right);
        } else {
            return findPointEndIndex(arr, p, left, ave - 1);
        }
    }    

    private static int[] naiveCountSegments(int[] starts, int[] ends, int[] points) {
        int[] cnt = new int[points.length];
        for (int i = 0; i < points.length; i++) {
            for (int j = 0; j < starts.length; j++) {
                if (starts[j] <= points[i] && points[i] <= ends[j]) {
                    cnt[i]++;
                }
            }
        }
        return cnt;
    }

    // Stress Test only
    // public static void stressTest() {
    //     while(true) {
    //         int numberSegments = ThreadLocalRandom.current().nextInt(1, 5);
    //         int numberPoints = ThreadLocalRandom.current().nextInt(1, 5);
    //         int[] starts = new int[numberSegments];
    //         int[] ends = new int[numberSegments];
    //         int[] points = new int[numberPoints];

    //         for(int i = 0; i < numberSegments; i++) {
    //             starts[i] = ThreadLocalRandom.current().nextInt(-5, 5);
    //             ends[i] = starts[i] + ThreadLocalRandom.current().nextInt(0, 5);
    //         }
    //         for(int i = 0; i < numberPoints; i++) {
    //             points[i] = ThreadLocalRandom.current().nextInt(-5, 5);
    //         }

    //         System.out.println("Starts: ");
    //         printArray(starts);
    //         System.out.println("Ends: ");
    //         printArray(ends);
    //         System.out.println("Points: ");
    //         printArray(points);                    

    //         int[] countNaive = naiveCountSegments(starts, ends, points);
    //         int[] countFast = fastCountSegments(starts, ends, points);
    //         boolean correct = true;
    //         for(int i = 0; i < countNaive.length; i++) {
    //             if(countNaive[i] != countFast[i]) {
    //                 correct = false;
    //                 break;
    //             }
    //         }

    //         if(correct) {
    //             System.out.println("OK!");
    //         } else {
    //             System.out.println("Error");
    //             printArray(countNaive);
    //             printArray(countFast);
    //             break;
    //         }
    //     }
    // }

    private static void printArray(int[] arr) {
        for(int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println("");
    }

    public static void main(String[] args) {
        // stressTest();

        Scanner scanner = new Scanner(System.in);
        int n, m;
        n = scanner.nextInt();
        m = scanner.nextInt();
        int[] starts = new int[n];
        int[] ends = new int[n];
        int[] points = new int[m];
        for (int i = 0; i < n; i++) {
            starts[i] = scanner.nextInt();
            ends[i] = scanner.nextInt();
        }
        for (int i = 0; i < m; i++) {
            points[i] = scanner.nextInt();
        }
        //use fastCountSegments
        int[] cnt = fastCountSegments(starts, ends, points);
        for (int x : cnt) {
            System.out.print(x + " ");
        }
    }
}

