import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        double money = Double.parseDouble(scan.nextLine());
        int students = Integer.parseInt(scan.nextLine());
        double lightsabers = Double.parseDouble(scan.nextLine());
        double robes = Double.parseDouble(scan.nextLine());
        double belts = Double.parseDouble(scan.nextLine());

        double price = (lightsabers+robes+belts)*students;
        price += lightsabers*Math.ceil(students*0.1);
        double freeBelts = (students/6);
        price -= freeBelts*belts;

        if(money >= price)
            System.out.printf("The money is enough - it would cost %.2f lv.%n", price);
        else
            System.out.printf("Ivan Cho will need %.2flv more.", price-money);
    }
}
