import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class Inversions {

    private static long getNumberOfInversions(int[] a, int[] b, int left, int right) {
        long numberOfInversions = 0;
        if (right <= left) {
            return numberOfInversions;
        }
        int ave = (left + right) / 2;
        numberOfInversions += getNumberOfInversions(a, b, left, ave); // Sort and count inversions on left
        numberOfInversions += getNumberOfInversions(a, b, ave + 1, right); // Sort and count inversions on right

        numberOfInversions += merge(a, b, left, right, ave); // Add number of inversions when merge sorting
        return numberOfInversions;
    }

    private static long longMethod(int[] a, int[] b, int left, int right) {
        long inversions = 0;
        for(int i = 0; i < a.length; i++) {
            for(int j = i + 1; j < a.length; j++) {
                if(a[i] > a[j]) inversions++;
            }
        }

        return inversions;
    }

    private static long merge(int[] a, int[] b, int left, int right, int ave) {
        int p = left;
        int q = ave + 1;
        long inversions = 0; // Must be long data type. It can exceed int for large arrays
        for(int i = left; i <= right; i++) { // For populating array b which is the sorted array.
            if(p > ave) { // if left pointer exceeds mid point, add elements on right of array
                b[i] = a[q++];
            } else if(q > right) { // if right pointer exceeds right, add elements on left of array
                b[i] = a[p++];
            } else if(a[p] > a[q]) { // Compare and add smaller element first.
                inversions += q-i;
                b[i] = a[q++];                
            } else {
                b[i] = a[p++];
            }
        }

        for(int i = left; i <= right; i++) { // a serves as auxilary array. Update changes.
            a[i] = b[i];
        }

        return inversions;
    }
/* Stress Test
    private static void stressTest() {
        while(true) {
            int randomNum = ThreadLocalRandom.current().nextInt(0, 100);
            int[] a = new int[randomNum];
            for(int i = 0; i < randomNum; i++) {
                int rand = ThreadLocalRandom.current().nextInt(0, 100);
                a[i] = rand;
            }
            int[] b = new int[randomNum];

            System.out.println("Input: ");
            for(int i = 0; i < a.length; i++) {
                System.out.print(a[i] + " ");
            }
            System.out.println("");
            long slow = longMethod(a, b, 0, a.length -1);
            long quick = getNumberOfInversions(a, b, 0, a.length -1);
            if(quick == slow) {
                System.out.println("OK");
            } else {
                System.out.println("Error");
                System.out.println("Fast: " + quick);
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
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
        }
        int[] b = new int[n];    
        System.out.println(getNumberOfInversions(a, b, 0, a.length -1));
    }
}

