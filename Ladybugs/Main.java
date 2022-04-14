import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int fieldSize = Integer.parseInt(scan.nextLine());
        int[] field = new int[fieldSize];
        int[] ladybugs = Arrays
                .stream(scan.nextLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        for (int i = 0; i < ladybugs.length; i++) {
            if(ladybugs[i] < fieldSize && ladybugs[i] > 0)
                field[ladybugs[i]] = 1;
        }

        String[] input = scan.nextLine().split(" ");
        while(!input[0].equals("end")){
            int bug = Integer.parseInt(input[0]);
            int distance = Integer.parseInt(input[2]);
            if(input[1].equals("right") && bug >= 0 && bug < fieldSize && field[bug] == 1)
            {
                field[bug] = 0;
                while(bug + distance < fieldSize){
                    if (field[bug + distance] == 1)
                        distance++;
                    else
                    {
                        field[bug + distance] = 1;
                        break;
                    }

                }
            }
            else if(input[1].equals("left") && bug >= 0 && bug < fieldSize && field[bug] == 1)
            {
                field[bug] = 0;
                while(bug - distance >= 0){
                    if(field[bug - distance] == 1 )
                        distance++;
                    else
                    {
                        field[bug - distance] = 1;
                        break;
                    }

                }
            }
            input = scan.nextLine().split(" ");
        }

       for(int n : field)
           System.out.print(n + " ");

    }
}
