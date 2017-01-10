import java.io.*;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class Sorting {
    private static Random random = new Random();

    private static int[] partition3(int[] a, int l, int r) {
      //write your code here
        int x = a[l];
        int m1 = l;
        int m2 = l;

        for(int i = 1 + l; i<= r; i++) { // Split into regions smaller or equal and greater than first element first
            if(a[i] <= x) {
                m2++;
                swap(a, m2, i);
            }
        }
        for(int i = 1 + l; i<= m2; i++) { // Split first region into smaller or equal elements
            if(a[i] < x) {
                m1++;
                swap(a, m1, i);
            }
        }        

        swap(a, m1, l);

        int[] m = {m1, m2};
   
        return m;
    }

    private static void swap(int[] a, int i, int j) {
        int t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    private static int partition2(int[] a, int l, int r) {
        int x = a[l];
        int j = l;
        for (int i = l + 1; i <= r; i++) {
            if (a[i] <= x) {
                j++;
                int t = a[i];
                a[i] = a[j];
                a[j] = t;
            }
        }
        int t = a[l];
        a[l] = a[j];
        a[j] = t;
        return j;
    }

    private static void randomizedQuickSort(int[] a, int l, int r) { // Use 3 region partition
        if (l >= r) {
            return;
        }
        int k = random.nextInt(r - l + 1) + l;
        int t = a[l];
        a[l] = a[k];
        a[k] = t;

        int[] m = partition3(a, l, r);

        randomizedQuickSort(a, l, m[0] - 1);
        randomizedQuickSort(a, m[1] + 1, r);
    }

    private static void randomizedQuickSortSlow(int[] a, int l, int r) { // Use 2 region partition
        if (l >= r) {
            return;
        }
        int k = random.nextInt(r - l + 1) + l;
        int t = a[l];
        a[l] = a[k];
        a[k] = t;

        int m = partition2(a, l, r);
        randomizedQuickSortSlow(a, l, m - 1);
        randomizedQuickSortSlow(a, m + 1, r);
    }    

    public static void main(String[] args) {
        FastScanner scanner = new FastScanner(System.in);
        int n = scanner.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
        }
        randomizedQuickSort(a, 0, n - 1);
        for (int i = 0; i < n; i++) {
            System.out.print(a[i] + " ");
        }
        // stressTest();
    }

    private static void stressTest() {
        while(true) {
            int randomNum = ThreadLocalRandom.current().nextInt(0, 10);
            int[] a = new int[randomNum];
            int[] b = new int[randomNum];

            boolean correct = true;
            System.out.println("Input: ");
            for(int i = 0; i < randomNum; i++) {
                int r = ThreadLocalRandom.current().nextInt(0, 10);
                a[i] = r;
                b[i] = r;
                System.out.print(r + " ");
            }
            randomizedQuickSort(a, 0, randomNum - 1);
            randomizedQuickSortSlow(b, 0, randomNum - 1);            

            for(int i = 0; i < randomNum; i++) {
                if(a[i] != b[i]) {
                    correct = false;
                    break;
                }
            }
            System.out.println(" ");

            if(correct) {
                System.out.println("OK");
            } else {
                System.out.println("Error");
                System.out.println("Fast Algo: ");
                for(int i = 0; i< randomNum; i++) {
                    System.out.print(a[i] + " ");
                }
                System.out.println(" ");
                System.out.println("Slow Algo: ");
                for(int i = 0; i< randomNum; i++) {
                    System.out.print(b[i] + " ");
                }
                break;
            }

        }
    }

    static class FastScanner {
        BufferedReader br;
        StringTokenizer st;

        FastScanner(InputStream stream) {
            try {
                br = new BufferedReader(new InputStreamReader(stream));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        String next() {
            while (st == null || !st.hasMoreTokens()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }
    }
}

