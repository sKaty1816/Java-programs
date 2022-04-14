import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        LinkedHashMap<String, Integer> keyMaterials = new LinkedHashMap<>(){{
            put("shards", 0);
            put("fragments", 0);
            put("motes", 0);
        }};

        LinkedHashMap<String, Integer> junk = new LinkedHashMap<>();
        boolean flag = false;

        while(true){
            String[] input = scan.nextLine().split(" ");
            for(int i = 0; i < input.length; i += 2){
                int quantity = Integer.parseInt(input[i]);
                String material = input[i+1].toLowerCase();
                if(keyMaterials.containsKey(material)){

                    int currentQuantity = keyMaterials.get(material);
                    keyMaterials.put(material, currentQuantity += quantity);

                    if(keyMaterials.get("shards") >= 250){
                        System.out.println("Shadowmourne obtained!");
                        int newQuantity = keyMaterials.get("shards");
                        keyMaterials.put("shards", newQuantity-250);
                        flag = true;
                        break;
                    }
                    else if(keyMaterials.get("fragments") >= 250){
                        System.out.println("Valanyr obtained!");
                        int newQuantity = keyMaterials.get("fragments");
                        keyMaterials.put("fragments", newQuantity-250);
                        flag = true;
                        break;
                    }
                    else if(keyMaterials.get("motes") >= 250){
                        System.out.println("Dragonwrath obtained!");
                        int newQuantity = keyMaterials.get("motes");
                        keyMaterials.put("motes", newQuantity-250);
                        flag = true;
                        break;
                    }
                }
                else{
                    if(junk.containsKey(material)){
                        int currentQuantity = junk.get(material);
                        junk.put(material, currentQuantity += quantity);
                    }
                    else
                        junk.put(material, quantity);
                }
            }
            if(flag) break;
        }

        keyMaterials.entrySet().stream()
                .sorted((a,b) -> {
                    int res = b.getValue().compareTo(a.getValue());
                    if(res == 0)
                        res = a.getKey().compareTo(b.getKey());
                    return res;
                })
                .forEach(e -> System.out.println(e.getKey() + ": " + e.getValue()));

        junk.entrySet().stream()
                .sorted((a,b) -> a.getKey().compareTo(b.getKey()))
                .forEach(e -> System.out.println(e.getKey() + ": " + e.getValue()));
    }
}
