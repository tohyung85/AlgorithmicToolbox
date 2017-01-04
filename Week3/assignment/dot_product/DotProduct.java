import java.util.*;

public class DotProduct {
    private static long maxDotProduct(int[] a, int[] b) {
        //write your code here
        long result = 0;

        // Sort arrays first O(nlogn)
        // Create array of type Integer as sorting with comparator only works for Integer types
        Integer[] a1 = new Integer[a.length];
        Integer[] b1 = new Integer[b.length];

        for(int i = 0; i < a.length; i++) {
            a1[i] = new Integer(a[i]);
            b1[i] = new Integer(b[i]);
        }

        Arrays.sort(a1, Collections.reverseOrder());
        Arrays.sort(b1, Collections.reverseOrder());

        for(int i = 0; i < a1.length; i++) {
            result += (long)a1[i] * b1[i];
        }

        // Non sorting solution O(n2)
        /*
        for (int i = 0; i < a.length; i++) { // Loop through a.length times to include all elements of a or b
            int aMaxIndex = 0;
            int bMaxIndex = 0;
            for(int j = 0; j < a.length; j++) { // Find Max a
                if(a[j] > a[aMaxIndex]) aMaxIndex = j;
            }

            for(int j = 0; j < b.length; j++) { // Find Max b
                if(b[j] > b[bMaxIndex]) bMaxIndex = j;
            }

            result += (long)a[aMaxIndex] * (long)b[bMaxIndex]; // Cast to long to avoid integer overflow after multiplication
            a[aMaxIndex] = Integer.MIN_VALUE; // Remove a max
            b[bMaxIndex] = Integer.MIN_VALUE; // Remove b max
        }
        */

        return result;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
        }
        int[] b = new int[n];
        for (int i = 0; i < n; i++) {
            b[i] = scanner.nextInt();
        }
        System.out.println(maxDotProduct(a, b));
    }
}

