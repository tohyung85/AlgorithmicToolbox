import java.util.Scanner;

public class Change {

    private static final int[] COINS = {
      10,
      5,
      1
    };

    private static int getChange(int m) {
        //write your code here
        int numberOfCoins = 0;
        int changeValue = m;

        for(int i = 0; i < COINS.length; i++) {
          int coinValue = COINS[i];
          int a = changeValue / coinValue;
          changeValue -= a * coinValue;
          numberOfCoins += a;
          if(changeValue == 0) return numberOfCoins;
        }
        return numberOfCoins;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int m = scanner.nextInt();
        System.out.println(getChange(m));
    }
}

