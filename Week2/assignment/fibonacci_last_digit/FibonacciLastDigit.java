import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class FibonacciLastDigit {
    private static int getFibonacciLastDigitNaiveSlow(int n) {
        if (n <= 1)
            return n;

        int previous = 0;
        int current  = 1;

        for (int i = 0; i < n - 1; ++i) {
            int tmp_previous = previous;
            previous = current;
            current = tmp_previous + current;
        }

        return current % 10;
    }

    private static int getFibonacciLastDigit(int n) {
        if (n <= 1) return n;

        int[] fibLastDigits = new int[n + 1];
        fibLastDigits[0] = 0;
        fibLastDigits[1] = 1;

        for(int i = 2; i <= n; i++) {
            fibLastDigits[i] = (fibLastDigits[i - 1] + fibLastDigits[i - 2]) % 10;
        }

        return fibLastDigits[n];
    }

    private static void stressTest() { // Stress Testing only
        while(true) {
            int randomNum = ThreadLocalRandom.current().nextInt(0, 15);
            long result1 = getFibonacciLastDigitNaiveSlow(randomNum);
            long result2 = getFibonacciLastDigit(randomNum);

            if(result1 != result2) {
                System.out.println("Answers not equal!");
                System.out.println("Input: " + randomNum);
                System.out.println("Result1: " + result1 + " vs " + result2);
            } else {
                System.out.println("OK!");
            }
        }
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        System.out.println(getFibonacciLastDigit(n));
    }
}

