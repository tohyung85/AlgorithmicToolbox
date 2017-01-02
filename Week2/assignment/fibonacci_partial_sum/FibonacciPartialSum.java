import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class FibonacciPartialSum {
    private static long getFibonacciPartialSumNaive(long from, long to) { // incorrect where from = 0
        if (to <= 1)
            return to;

        long previous = 0;
        long current  = 1;

        for (long i = 0; i < from - 1; ++i) {
            long tmp_previous = previous;
            previous = current;
            current = tmp_previous + current;
        }

        long sum = current;

        for (long i = 0; i < to - from; ++i) {
            long tmp_previous = previous;
            previous = current;
            current = tmp_previous + current;
            sum += current;
        }

        return sum % 10;
    }

    private static long getFibonacciPartialSum(long from, long to) {
        if(from > to) return -1;

        int[] fibSumsLastDigits = new int[60]; // Pisano Period Size is 60 for modulo 10(last digit), see commented loop below.
        fibSumsLastDigits[0] = 0;
        fibSumsLastDigits[1] = 1;

        for(int i = 2; i< fibSumsLastDigits.length; i++) {
            fibSumsLastDigits[i] = (fibSumsLastDigits[i - 1] + fibSumsLastDigits[i - 2] + 1) % 10;       
        }

        int toKey = (int)(to % 60); // last digit repeats for Pisano Length        
        int fromKey = (int)((from - 1) % 60); // last digit repeats for Pisano Length        

        int toDigit = fibSumsLastDigits[toKey];
        int fromDigit;
        if(fromKey < 0) {
            fromDigit = 0;
        } else {
            fromDigit = fibSumsLastDigits[fromKey];
        }

        int difference = toDigit - fromDigit;

        if(difference < 0) {
            return 10 + difference;
        } else {
            return difference;
        }
    }

    private static void stressTest() { // Stress Testing only
      while(true) {
          long randomNum1 = ThreadLocalRandom.current().nextInt(0, 5);
          long randomNum2 = ThreadLocalRandom.current().nextInt(5, 10);
          long result1 = getFibonacciPartialSumNaive(randomNum1, randomNum2);
          long result2 = getFibonacciPartialSum(randomNum1, randomNum2);

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
        long from = scanner.nextLong();
        long to = scanner.nextLong();
        System.out.println(getFibonacciPartialSum(from, to));
        // System.out.println(getFibonacciPartialSumNaive(from, to));
        
        // stressTest();
    }
}

