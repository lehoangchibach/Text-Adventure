public class Weapon {

    private String name;
    private String description;
    private int damage;

    public Weapon(String name, int damage, String description){
        this.name = name;
        this.damage = damage;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public int getDamage() {
        return damage;
    }

    public String getDescription() {
        return description;
    }
}
