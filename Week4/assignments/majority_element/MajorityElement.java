import java.util.*;
import java.io.*;

public class MajorityElement {
    // Divide and Conquer method
    
    private static int getMajorityElement(int[] a, int left, int right) {
        if (left == right) {
            return -1;
        }
        if (left + 1 == right) {
            return a[left];
        }

        int mid = left + (right - left) / 2 - 1;

        int leftMajority = getMajorityElement(a, left, mid);
        int rightMajority = getMajorityElement(a, mid + 1, right);

        int majority = isMajority(a, leftMajority, left, right);
        if(majority != -1) return leftMajority;
        majority = isMajority(a, rightMajority, left, right);
        if(majority != -1) return rightMajority;
        
        return -1;
    }

    private static int isMajority(int[] a, int num, int left, int right) {
        if(num == -1) return -1;
        int count = 0;

        for(int i = left; i < right; i++) {
            if(a[i] == num) count++;
        }

        if(count * 2 > right - left) {
            return num;        
        } else {
            return -1;
        }
    }
    /* Use a hasmap for faster implementation!
    private static int getMajorityElement(int[] a, int left, int right) {
        // use hashmap
        int[] sizes = new int[1000000001];

        for(int i = left; i < right; i++) {
            int index = a[i];
            sizes[index] += 1;
        }

        for(int i = left; i < right; i++) {
            int index = a[i];
            if(sizes[index] * 2 > a.length) return a[i];
        }

        return -1;
    }
    */
    public static void main(String[] args) {
        FastScanner scanner = new FastScanner(System.in);
        int n = scanner.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
        }
        if (getMajorityElement(a, 0, a.length) != -1) {
            System.out.println(1);
        } else {
            System.out.println(0);
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

