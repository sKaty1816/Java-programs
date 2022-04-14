import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = Integer.parseInt(scan.nextLine());
        int yieldSpice = n;
        int totalSpice = 0;
        int days = 0;
        while(yieldSpice >= 100 ){
            totalSpice += yieldSpice - 26;
            days++;
            yieldSpice -= 10;
        }
        totalSpice -= 26;
        System.out.print(days + "\n" + totalSpice);
    }
}

