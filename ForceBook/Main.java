import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        LinkedHashMap<String, ArrayList<String>> forceBook = new LinkedHashMap<>();
        while (true) {
            String input = scan.nextLine();
            if (input.equals("Lumpawaroo"))
                break;
            if (input.contains("|")) {
                String[] newInput = input.split(" \\| ");
                String forceSide = newInput[0];
                String forceUser = newInput[1];
                forceBook.putIfAbsent(forceSide, new ArrayList<>());
                if (!forceBook.get(forceSide).contains(forceUser))
                    forceBook.get(forceSide).add(forceUser);
            } else if (input.contains(" -> ")) {
                String[] newInput = input.split(" -> ");
                String forceUser = newInput[0];
                String forceSide = newInput[1];
                for (var entry : forceBook.entrySet()) {
                    for (String user : entry.getValue()) {
                        if (!entry.getKey().equals(forceSide) && user.equals(forceUser)) {
                            entry.getValue().remove(user);
                            break;
                        }
                    }
                }
                if (!forceBook.get(forceSide).contains(forceUser)) {
                    forceBook.get(forceSide).add(forceUser);
                    System.out.printf("%s joins the %s side!%n", forceUser, forceSide);
                }
            }
        }

        forceBook.entrySet().stream()
                .sorted(Comparator.comparing(Map.Entry::getKey))
                .sorted((e1, e2) -> Integer.compare(e2.getValue().size(), e1.getValue().size()))
                .forEach(e -> {
                    if (!e.getValue().isEmpty()) {
                        System.out.printf("Side: %s, Members: %d%n", e.getKey(), e.getValue().size());
                        e.getValue().sort((a,b) -> a.compareTo(b));
                        for (var user : e.getValue())
                            System.out.println("! " + user);
                    }
                });
    }
}
