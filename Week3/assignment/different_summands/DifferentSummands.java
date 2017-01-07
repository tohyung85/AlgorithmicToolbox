import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class DifferentSummands {
    private static List<Integer> optimalSummands(int n) {
        List<Integer> summands = new ArrayList<Integer>();
        //write your code here
        int j = 1;
        while((long)j * 2 + 1 <= n) {
            int prize = j; // prize amount
            summands.add(prize); // add prize to list
            n -= j;
            j++;
        }

        summands.add(n); // add balance candies

        // addSummands(n, 1, summands); Recursion formula works for smaller int inputs, cannot work at larger inputs due to stack overflow.
        return summands;

    }

/* Recursion method code
    private static void addSummands(int n, int j, List<Integer> summands) {
        if( j * 2 + 1 > n) { // add balance candies if insufficient candies left for subsequent prizes
            summands.add(n);
            return;
        }

        int prize = j; // prize amount

        summands.add(prize); // add prize to list
        addSummands(n - prize, prize+1, summands); // recurse until no balance candies
    }
*/

/* Stress test only
    private static void stressTest() {
        while(true) {
            int randomNum = ThreadLocalRandom.current().nextInt(0, 1000000000);
            List<Integer> summands = optimalSummands(randomNum);
            System.out.println(summands.size());
            for (Integer summand : summands) {
                System.out.print(summand + " ");
            }
        }
    }
*/
    public static void main(String[] args) {
        // stressTest();
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        List<Integer> summands = optimalSummands(n);
        System.out.println(summands.size());
        for (Integer summand : summands) {
            System.out.print(summand + " ");
        }
    }
}

