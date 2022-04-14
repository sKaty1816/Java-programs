import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = Integer.parseInt(scan.nextLine());
        LinkedHashMap<String, LinkedHashMap<String, Dragon>> dragons = new LinkedHashMap<>();

        for (int i = 0; i < n; i++) {
            String[] line = scan.nextLine().split(" ");
            if (line.length != 5) {
                System.out.println("Too little information! Try again: ");
                line = scan.nextLine().split(" ");
            }
            String colour = line[0];
            String name = line[1];
            Integer damage;
            Integer health;
            Integer armor;
            if (line[2].equals("null"))
                damage = null;
            else
                damage = Integer.parseInt(line[2]);
            if (line[3].equals("null"))
                health = null;
            else
                health = Integer.parseInt(line[3]);
            if (line[4].equals("null"))
                armor = null;
            else
                armor = Integer.parseInt(line[4]);
            Dragon dragon = new Dragon(colour, name, damage, health, armor);
            if (!dragons.containsKey(colour))
                dragons.put(colour, new LinkedHashMap<>());
            dragons.get(colour).put(name, dragon);

        }

        for (var dragon1 : dragons.entrySet()) {
            System.out.print(dragon1.getKey() + "::");
            printAvg(dragon1.getValue());
            dragon1.getValue().entrySet().stream().sorted(Comparator.comparing(Map.Entry::getKey))
                    .forEach(e ->
                            e.getValue().print());
        }
    }

    public static void printAvg(LinkedHashMap<String, Dragon> stats) {
        double avgDmg = 0;
        double avgHp = 0;
        double avgAc = 0;
        int count = 0;
        for (var dr : stats.entrySet()) {
            avgDmg += dr.getValue().getDamage();
            avgHp += dr.getValue().getHealth();
            avgAc += dr.getValue().getArmor();
            count++;
        }
        avgDmg /= count;
        avgHp /= count;
        avgAc /= count;
        System.out.printf("(%.2f/%.2f/%.2f)%n", avgDmg, avgHp, avgAc);
    }

}

