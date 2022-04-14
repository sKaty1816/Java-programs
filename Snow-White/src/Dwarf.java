public class Dwarf {
    private String name;
    private String hatColour;
    private int physics;

    public Dwarf(String name, String hatColour, int physics) {
        this.name = name;
        this.hatColour = hatColour;
        this.physics = physics;
    }

    public String getName(){
        return name;
    }

    public int getPhysics() {
        return physics;
    }

    public void setPhysics(int physics) {
        this.physics = physics;
    }

    public void print(){
        System.out.printf("(%s) %s <-> %d%n", hatColour, name, physics);
    }
}
