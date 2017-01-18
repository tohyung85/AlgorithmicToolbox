import java.util.*;

public class PrimitiveCalculator {
    private static List<Integer> optimal_sequence(int n) {
        List<Integer> sequence = new ArrayList<Integer>();

        int[] minMoves = new int[n + 1];
        minMoves[0] = -1;
        sequence.add(n);

        // Generate array of minimum moves
        for(int i = 1; i <= n; i++) {
            int min = minMoves[i-1] + 1;

            if(i % 3 == 0) {
                int a = minMoves[i/3] + 1;
                if(a < min) {
                    min = a;
                }
            }

            if(i % 2 == 0) {
                int b = minMoves[i/2] + 1;
                if(b < min) {
                    min = b;
                }
            }
            minMoves[i] = min;
        }

        // Reconstruct sequence of moves
        while(n != 1) {
            if(n%3 == 0 && minMoves[n] - 1 == minMoves[n/3]) { // if n/3 is part of optimum sequence it should be equal to minimum move of n -1
                sequence.add(n/3);
                n /= 3;
                continue;
            }
            if(n%2 == 0 && minMoves[n] - 1 == minMoves[n/2]) { // if n/2 is part of optimum sequence it should be equal to minimum move of n -1
                sequence.add(n/2);
                n /=2;
                continue;
            }
            sequence.add(n-1);
            n--;
        }

        Collections.reverse(sequence);
        return sequence;
    }

    // private static List<Integer> minMoves(int n, List<Integer>[] moves) {
    //     if(n == 1) {
    //         List<Integer> list = new new ArrayList<Integer>();
    //         list.add(1);
    //         return list;
    //     }

    //     int min;
    //     int value = n - 1;

    //     if(moves[n-1] != -1) {
    //         min = moves[n-1] + 1;
    //     } else {
    //         min = minMoves(sequence, n, moves) + 1;
    //     }

    //     if(n % 3 == 0) {
    //         int a;
    //         if(moves[n/3] != -1) {
    //             a = moves[n/3] + 1;
    //         } else {
    //             a = minMoves(sequence, n/3, moves) + 1;
    //         }            
    //         if(a < min) {
    //             min = a;
    //             value = n/3;
    //         }
    //     }

    //     if(n % 2 == 0) {
    //         int b;
    //         if(moves[n/2] != -1) {
    //             b = moves[n/2] + 1;
    //         } else {
    //             b = minMoves(sequence, n/3, moves) + 1;
    //         }
    //         if(b < min) {
    //             min = b;
    //             value = n/2;
    //         }
    //     }

    //     return min;
    // }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        List<Integer> sequence = optimal_sequence(n);
        System.out.println(sequence.size() - 1);
        for (Integer x : sequence) {
            System.out.print(x + " ");
        }
    }
}

