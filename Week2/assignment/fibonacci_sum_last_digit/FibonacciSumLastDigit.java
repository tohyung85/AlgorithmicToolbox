import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class FibonacciSumLastDigit {
    private static long getFibonacciSumNaive(long n) {
        if (n <= 1)
            return n;

        long previous = 0;
        long current  = 1;
        long sum      = 1;

        for (long i = 0; i < n - 1; ++i) {
            long tmp_previous = previous;
            previous = current;
            current = tmp_previous + current;
            sum += current;
        }

        return sum % 10;
    }

    private static long getFibonacciSum(long n) {
        if(n <= 1) return n;
        
        long prev = 0;
        long curr = 1;
        long length = 0;

        int[] fibSumsLastDigits = new int[60]; // Pisano Period Size is 60, see commented loop below.
        fibSumsLastDigits[0] = 0;
        fibSumsLastDigits[1] = 1;

        for(int i = 2; i< fibSumsLastDigits.length; i++) {
            fibSumsLastDigits[i] = (fibSumsLastDigits[i - 1] + fibSumsLastDigits[i - 2] + 1) % 10;       
        }

        int returnKey = (int)(n % 60); // last digit repeats for Pisano Length
        return fibSumsLastDigits[returnKey];

        // while(true) { // Test for Pisano Period Length
        //     length++;
        //     long tmp_curr = curr;
        //     curr = (curr + prev + 1) % 10;
        //     prev = tmp_curr;            
        //     if(curr == 1 && prev == 0) return length; // return when Pisano Period found
        // }

        // return curr;
    }
    
    private static void stressTest() { // Stress Testing only
      while(true) {
          long randomNum1 = ThreadLocalRandom.current().nextInt(0, 60);
          long result1 = getFibonacciSumNaive(randomNum1);
          long result2 = getFibonacciSum(randomNum1);

          if(result1 != result2) {
              System.out.println("Answers not equal!");
              System.out.println("Inputs: " + randomNum1);
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
        System.out.println(getFibonacciSum(n));

        // stressTest();
    }
}

