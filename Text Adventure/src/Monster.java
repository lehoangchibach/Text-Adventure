public class Monster {

    public String name;
    public int armor;
    public String description;

    public Monster(String name, int armor, String description){
        this.name = name;
        this.armor = armor;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }

    public int getArmor() {
        return armor;
    }
}
