public class Dragon {

    private String colour;
    private String name;
    private Integer damage;
    private Integer health;
    private Integer armor;

    Dragon() {
        this.colour = "";
        this.name = "";
        this.damage = 45;
        this.health = 250;
        this.armor = 10;
    }

    Dragon(String colour, String name, Integer damage, Integer health, Integer armor) {
        this.colour = colour;
        this.name = name;
        if (damage != null)
            this.damage = damage;
        else
            this.damage = 45;
        if (health != null)
            this.health = health;
        else
            this.health = 250;
        if (armor != null)
            this.armor = armor;
        else
            this.armor = 10;
    }

    public String getColour() {
        return colour;
    }

    public String getName() {
        return name;
    }

    public int getDamage() {
        return damage;
    }

    public int getHealth() {
        return health;
    }

    public int getArmor() {
        return armor;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void setArmor(int armor) {
        this.armor = armor;
    }

    public void print() {
        System.out.println("-" + name + " -> damage: " + damage + ", health: " + health + ", armor: " + armor);
    }
}
