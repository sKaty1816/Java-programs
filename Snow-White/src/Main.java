import java.sql.Array;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        String command = scan.nextLine();
        LinkedHashMap<String, ArrayList<Dwarf>> dwarfs = new LinkedHashMap<>();

        while (!command.equals("Once upon a time")) {
            String[] line = command.split(" <:> ");
            String name = line[0];
            String hatColour = line[1];
            int physics = Integer.parseInt(line[2]);
            if (!dwarfs.containsKey(hatColour)) {
                dwarfs.put(hatColour, new ArrayList<>());
                Dwarf dwarf = new Dwarf(name, hatColour, physics);
                dwarfs.get(hatColour).add(dwarf);
            } else {
                boolean contains = false;
                for (Dwarf d : dwarfs.get(hatColour)) {
                    if (d.getName().equals(name)) {
                        contains = true;
                        if (d.getPhysics() < physics)
                        {
                            d.setPhysics(physics);
                            break;
                        }
                    }
                }
                if(!contains){
                    Dwarf dwarf = new Dwarf(name, hatColour, physics);
                    dwarfs.get(hatColour).add(dwarf);
                }
            }
            command = scan.nextLine();
        }

        List<Dwarf> dwarfList = new ArrayList<>();

        dwarfs.entrySet().stream().sorted((e1,e2) -> Integer.compare(e2.getValue().size(), e1.getValue().size()))
                        .forEach(pair -> pair.getValue().forEach(d -> dwarfList.add(d)));

       dwarfList.stream().sorted((a,b) -> Integer.compare(b.getPhysics(), a.getPhysics())).forEach(e -> e.print());



    }

}
