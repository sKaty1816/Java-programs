import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int lengthOfSequences  = Integer.parseInt(scan.nextLine());
        String input = scan.nextLine();
        int longest = 1;
        int biggerSum = 0;
        int bestIndex = 0;
        int generalCounter = 0;
        int bestNum = 1;
        int[] best = new int[lengthOfSequences ];
        while (!input.equals("Clone them!"))
        {
            int[] dna = Arrays.stream(input.split("!+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            int sum = 0;
            for(int num : dna)
                sum += num;
            int current = 1;
            generalCounter++;

            if(lengthOfSequences == 1 && dna[0] == 1)
            {
                best = dna;
                bestNum = generalCounter;
                biggerSum = sum;
                break;
            }

            for (int i = 0; i < lengthOfSequences -1; i++) {
                if(dna[i] == dna[i+1] && dna[i] == 1)
                    current++;

                if(current > longest)
                {
                    longest = current;
                    best = dna;
                    bestNum = generalCounter;
                    biggerSum = sum;
                    bestIndex = i + 1;
                }
                else if(current == longest)
                {
                    if(bestIndex > i+1 || biggerSum < sum)
                    {
                        bestIndex = i+1;
                        biggerSum = sum;
                        best = dna;
                        bestNum = generalCounter;
                    }
                }
            }
            input = scan.nextLine();
        }

        System.out.printf("Best DNA sample %d with sum: %d%n", bestNum, biggerSum);
        for(int num : best)
            System.out.print(num + " ");

    }
}
