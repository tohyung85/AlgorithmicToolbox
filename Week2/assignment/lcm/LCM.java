import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class LCM {
  private static long lcm_naive(int a, int b) {
    for (long l = 1; l <= (long) a * b; ++l)
      if (l % a == 0 && l % b == 0)
        return l;

    return (long) a * b;
  }

  private static long lcm(int a, int b) {
    if(a == 0 && b == 0) return 0;

    return (long)a * b / gcd(a, b);
  }

  private static int gcd(int a, int b) {
    if(b == 0) return a;

    int a_prime = a % b;

    return gcd(b, a_prime);
  }

  private static void stressTest() { // Stress Testing only
      while(true) {
          int randomNum1 = ThreadLocalRandom.current().nextInt(0, 1000);
          int randomNum2 = ThreadLocalRandom.current().nextInt(0, 1000);
          long result1 = lcm_naive(randomNum1, randomNum2);
          long result2 = lcm(randomNum1, randomNum2);

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

  public static void main(String args[]) {
    Scanner scanner = new Scanner(System.in);
    int a = scanner.nextInt();
    int b = scanner.nextInt();

    System.out.println(lcm(a, b));

    // stressTest();
  }
}
