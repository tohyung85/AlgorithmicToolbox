import java.util.*;

public class LargestNumber {
    private static String largestNumber(String[] a) {
        //write your code here
        String result = "";
        for (int i = 0; i < a.length; i++) { // number of times to loop to add all numbers in a.
            String maxNum = "-1"; // input range between 1 and 1000
            int maxIndex = -1;
            for(int j = 0; j < a.length; j++) { // loop to find max in array
                if(isGreaterOrEqual(a[j], maxNum)) {
                    maxNum = a[j];
                    maxIndex = j;
                }
            }
            result += maxNum; // append max to result
            a[maxIndex] = "-1"; // delete element by set to -1, works because input range between 1 and 1000
        }
        return result;
    }

    private static boolean isGreaterOrEqual(String a, String b) {
        // Find shorter and longest number first
        String longestNumber = a;
        String shortestNumber = b;
        if(a.length() < b.length()) {
            longestNumber = b;
            shortestNumber = a;
        }

        // find gcm
        int greatestCommonMultiple = longestNumber.length() * shortestNumber.length() / gcd(longestNumber.length(), shortestNumber.length());

        String greaterNum = "equal"; // to be equal if numbers are neither larger or smaller than each other in loop below.
        // Need to loop to greatest common multiple, not just longest number length. Reference case: 3213 321
        for(int i = 0; i<greatestCommonMultiple; i++) { // loop to compare numbers digit by digit, starting from first digit
            int largeNum = Character.getNumericValue(longestNumber.charAt(i % longestNumber.length()));
            int smallNum = Character.getNumericValue(shortestNumber.charAt(i % shortestNumber.length()));
            if(largeNum > smallNum) { 
                greaterNum = longestNumber;
                break;
            }

            if(largeNum < smallNum) {
                greaterNum = shortestNumber;
                break;
            }
        }

        if(greaterNum.equals("equal") || greaterNum.equals(a)) {
            return true;
        } else {
            return false;            
        }
    }

    private static int gcd(int a, int b) {
        if(b == 0) return a;

        int aPrime = a % b;
        return gcd(b, aPrime);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        String[] a = new String[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.next();
        }
        System.out.println(largestNumber(a));
    }
}

