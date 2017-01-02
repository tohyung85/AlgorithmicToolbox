import java.util.*;
import java.util.concurrent.ThreadLocalRandom;


public class FibonacciHuge {
    private static long getFibonacciHugeNaive(long n, long m) {
        if (n < 1)
            return n;

        if (m == 0) return n;
        long previous = 0;
        long current  = 1;

        for (long i = 0; i < n - 1; ++i) {
            long tmp_previous = previous;
            previous = current;
            current = tmp_previous + current;
            System.out.println(current % m);
        }

        return current % m;
    }

    private static long getFibonacciHuge(long n, long m) {
         if(m == 0) return n;
         if(n == 0 || m == 1) return 0;

        long length = 0;

        long previous = 0;
        long current = 1;
        boolean periodFound = false;

        for(long i = 2; i <= n; i++) {
            length++;
            long tmp_current = current;
            current = (current + previous) % m;
            previous = tmp_current;
            if(current == 1 && previous == 0) { // exit if Pisano Period found
                current = 1;
                previous = 0;
                periodFound = true;
                break;
            }
        }

        if(!periodFound) return current; // If no periods found until end of prior loop, current would be the answer as loop ran till n

        for(long j = 2; j<= n; j++) { // Loop to find modulo in Pisano period
            if(n % length == 0) return previous;
            if(n % length == 1) return current;
            long tmp_current = current;
            current = (current + previous) % m;
            previous = tmp_current;
            if(j == n % length) return current;            
        }

        return current;        
    }

    private static void stressTest() { // Stress Testing only
      while(true) {
          int randomNum1 = ThreadLocalRandom.current().nextInt(0, 50);
          int randomNum2 = ThreadLocalRandom.current().nextInt(0, 50);
          long result1 = getFibonacciHugeNaive(randomNum1, randomNum2);
          long result2 = getFibonacciHuge(randomNum1, randomNum2);

          if(result1 != result2) {
              System.out.println("Answers not equal!");
              System.out.println("Inputs: " + randomNum1 + " " + randomNum2);
              System.out.println("Result: " + result1 + " vs " + result2);
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
        System.out.println(getFibonacciHuge(n, m));
        // System.out.println("Naive ans: " + getFibonacciHugeNaive(n, m));

        // stressTest();
    }
}

