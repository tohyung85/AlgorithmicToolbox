import java.util.*;
import java.io.*;
import java.util.concurrent.ThreadLocalRandom;

public class GCD {
    static int gcd(int a, int b) {
        if(b == 0) return a;

        int remainder = a % b;
        return gcd(b, remainder);
    }


    public static void main(String[] args) {
        FastScanner scanner = new FastScanner(System.in);
        int num1 = scanner.nextInt();
        int num2 = scanner.nextInt();

        System.out.println(gcd(num1, num2));
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