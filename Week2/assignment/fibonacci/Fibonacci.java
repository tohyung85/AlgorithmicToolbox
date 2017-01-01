import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class Fibonacci {
  private static long calc_fib_slow(int n) {
    if (n <= 1)
      return n;

    return calc_fib(n - 1) + calc_fib(n - 2);
  }

  private static long calc_fib(int n) {
    if(n <= 1) return n;

    long[] fibs = new long[n+1];
    fibs[0] = 0;
    fibs[1] = 1;

    for(int i = 2; i <= n; i++) {
      fibs[i] = fibs[i - 1] + fibs[i - 2];
    }

    return fibs[n];
  }

  public static void main(String args[]) {
    Scanner in = new Scanner(System.in);
    int n = in.nextInt();

    System.out.println(calc_fib(n));

    /*
    // Stress Testing
    while(true) {
      int randomNum = ThreadLocalRandom.current().nextInt(0, 15);
      long result1 = calc_fib_slow(randomNum);
      long result2 = calc_fib(randomNum);

      if(result1 != result2) {
        System.out.println("Answers no equal!");
        System.out.println("Input: " + randomNum);
        System.out.println("Result1: " + result1 + " vs " + result2);
      } else {
        System.out.println("OK!");
      }
    }
    */

  }
}
