import java.util.Arrays;
import java.util.HashMap;

public class Room {

    private String name;
    private String description;
    private Weapon weapon;
    private Treasure treasure;
    private Monster monster;
    private final HashMap<String, Room> neighbors = new HashMap<String, Room>();

   public Room (String name, String description){
        this.name = name;
        this.description = description;
    };


    public String getName(){
        return name;
    };

    public String getDescription() {
        return description;
    }

    public void addNeighbor(String direction, Room neighbor){
        neighbors.put(direction, neighbor);
    }

    public Room getNeighbor(String direction) {
        return neighbors.get(direction);
    }

    public String listExits(){
        String [] list = neighbors.keySet().toArray(new String[neighbors.size()]);
        return Arrays.toString(list);
    }

    public void setTreasure(Treasure treasure) {
        this.treasure = treasure;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public void setMonster(Monster monster) {
        this.monster = monster;
    }

    public Treasure getTreasure() {
        return treasure;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public Monster getMonster() {
        return monster;
    }
}
