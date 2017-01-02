import java.util.*;
import java.util.concurrent.ThreadLocalRandom;


public class FibonacciHuge {
    private static long getFibonacciHugeNaive(long n, long m) {
        if (n <= 1)
            return n;

        long previous = 0;
        long current  = 1;

        for (long i = 0; i < n - 1; ++i) {
            long tmp_previous = previous;
            previous = current;
            current = tmp_previous + current;
        }

        return current % m;
    }


    private static void stressTest() { // Stress Testing only
      while(true) {
          int randomNum1 = ThreadLocalRandom.current().nextInt(0, 1000);
          int randomNum2 = ThreadLocalRandom.current().nextInt(0, 1000);
          long result1 = getFibonacciHugeNaive(randomNum1, randomNum2);
          long result2 = getFibbonacciHuge(randomNum1, randomNum2);

          if(result1 != result2) {
              System.out.println("Answers not equal!");
              System.out.println("Inputs: " + randomNum1 + " " + randomNum2);
              System.out.println("Result1: " + result1 + " vs " + result2);
              break;
          } else {
              System.out.println("OK!");
          }
      }
    }     
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long n = scanner.nextLong();
        long m = scanner.nextLong();
        System.out.println(getFibonacciHugeNaive(n, m));
    }
}

