import java.util.Scanner;

public class FractionalKnapsack {
    private static double getOptimalValue(int capacity, int[] values, int[] weights) {
        double value = 0;
        // Sort followed by greedy algo O(NlgN)
        int[][] items = new int[values.length][2];
        for(int i = 0; i < items.length; i++) { // 2D array of items with value and weight data
            items[i][0] = values[i];
            items[i][1] = weights[i];            
        }

        java.util.Arrays.sort(items, new java.util.Comparator<int[]>() { //sort array by value per weight
            public int compare(int[] a, int[] b) {
                return Double.compare((double)b[0]/b[1], (double)a[0]/a[1]);
            }
        });

        double spaceLeft = (double)capacity;

        for(int i = 0; i < items.length; i++) {
            double valPerWeight = (double)items[i][0] / items[i][1];
            double a;
            if(items[i][1] <= spaceLeft) {
                a = (double)items[i][1];
            } else {
                a = spaceLeft;
            }
            value += a * valPerWeight;
            spaceLeft -= a;
            if(spaceLeft < 0.0001) return value;
        }

        // Greedy algo without sorting O(n2)
        /*
        double spaceLeft = (double)capacity;
        while(spaceLeft > 0.0001) {
            double maxValPerWeight = -1;
            int maxValPerWeightItem = -1;
            for(int i = 0; i < values.length; i++) { // Find item with max value per weight
                if(weights[i] == 0) continue;
                double valPerWeight = (double)values[i] / weights[i];
                if(valPerWeight > maxValPerWeight) {
                    maxValPerWeight = valPerWeight;
                    maxValPerWeightItem = i;
                }
            }
            if(maxValPerWeightItem == -1) return value;
            double a;
            if(weights[maxValPerWeightItem] <= spaceLeft) {
                a = (double)weights[maxValPerWeightItem];
            } else {
                a = spaceLeft;
            }
            value += a * maxValPerWeight;
            spaceLeft -= a;
            weights[maxValPerWeightItem] -= a;
        }
        */

        return value;
    }

    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int capacity = scanner.nextInt();
        int[] values = new int[n];
        int[] weights = new int[n];
        for (int i = 0; i < n; i++) {
            values[i] = scanner.nextInt();
            weights[i] = scanner.nextInt();
        }
        System.out.printf("%.4f", getOptimalValue(capacity, values, weights));
    }
} 
