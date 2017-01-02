import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class GCD {
  private static int gcd_naive(int a, int b) {
    if(a == 0) return b;
    if(b == 0) return a;

    int current_gcd = 1;
    for(int d = 2; d <= a && d <= b; ++d) {
      if (a % d == 0 && b % d == 0) {
        if (d > current_gcd) {
          current_gcd = d;
        }
      }
    }

    return current_gcd;
  }

  private static int gcd(int a, int b) {
    if (b == 0) return a;

    int a_prime = a % b;

    return gcd(b, a_prime);
  }


  private static void stressTest() { // Stress Testing only
      while(true) {
          int randomNum1 = ThreadLocalRandom.current().nextInt(0, 100);
          int randomNum2 = ThreadLocalRandom.current().nextInt(0, 100);
          long result1 = gcd_naive(randomNum1, randomNum2);
          long result2 = gcd(randomNum1, randomNum2);

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

    System.out.println(gcd(a, b));

    // stressTest();
  }
}
