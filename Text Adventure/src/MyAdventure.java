public class MyAdventure {

    private int score;
    private Room curr_Room;
    private int Weapon_damage;

    public static void main(String[] args){
        new MyAdventure().run();
    }

    /** Runs the game. */
    public void run() {
        instruction();
        StdOut.println();
        while (true) {
            StdOut.println("You are in the " + curr_Room.getName() + ".");
            StdOut.print("> ");
            handleCommand(StdIn.readLine());
            StdOut.println();
        }
    }

    /** Create the game's world*/
    public MyAdventure(){

        // Create rooms:
        Room start_point = new Room("entrance", "front of a great venture!");
        Room cave_entrance = new Room("cave", "a dimmed cave, filled with dripping stalactites");
        Room chamber = new Room("chamber", "a holy chamber");
        Room throne = new Room("throne","a throne room belonged to an old King");
        Room jungle = new Room("jungle", "an open field");
        Room river = new Room("river", "a strong water current river");
        Room mountain_path = new Room("path", "a narrow path leading to an ice mountain");
        Room lair = new Room("lair", "icy cage is the home of the Eternal Yeti");

        // Create connections:
        start_point.addNeighbor("west", cave_entrance);
        start_point.addNeighbor("east", jungle);
        start_point.addNeighbor("north", mountain_path);
        cave_entrance.addNeighbor("east", start_point);
        cave_entrance.addNeighbor("north", chamber);
        cave_entrance.addNeighbor("west", throne);
        chamber.addNeighbor("south", cave_entrance);
        throne.addNeighbor("east", cave_entrance);
        jungle.addNeighbor("west", start_point);
        jungle.addNeighbor("east", river);
        river.addNeighbor("west", jungle);
        mountain_path.addNeighbor("south", start_point);
        mountain_path.addNeighbor("north", lair);
        lair.addNeighbor("south", mountain_path);

        // Create and install monsters:
        cave_entrance.setMonster(new Monster("skeletons", 2, "deadly, aggressive skeletons"));
        chamber.setMonster(new Monster("knight", 5, "used to be King's guard but now a fallen knight"));
        throne.setMonster(new Monster("dragon", 9, "a dragon with a fire breath that can melt any armor"));
        jungle.setMonster(new Monster("trolls", 3, "stone trolls with dangerous close range roll attack"));
        river.setMonster(new Monster("KingKong", 6, "KingKong, an enormous ancient creature, smash through everything"));
        mountain_path.setMonster(new Monster("bear", 8,"a giant ice bear with dangerous claws and thicken pelt that normal weapon cannot penetrate"));
        lair.setMonster(new Monster("yeti", 8, "eternal yeti with the ability to freeze its surrounding"));

        // Create and install weapons:
        cave_entrance.setWeapon(new Weapon("longbow", 4, "a longbow left behind by previous adventurers"));
        jungle.setWeapon(new Weapon("longsword", 7, "a longsword belong to great hero"));
        river.setWeapon(new Weapon("excalibur", 9, "sword of the King, excalibur"));
        lair.setWeapon(new Weapon("crossbow", 10, "An Duong Vuong crossbow, an ancient legendary crossbow of Vietnamese people"));

        // Create and install treasures:
        chamber.setTreasure(new Treasure("crown", 20, "a golden crown attached with valuable gems"));
        jungle.setTreasure(new Treasure("bible", 10, "a bible lost in the ancient war"));
        throne.setTreasure(new Treasure("egg", 30, "a dragon egg embedded in melted gold and lava"));
        lair.setTreasure(new Treasure("stone", 40, "a magic stone used by a great wizard"));

        // Initialize starting position
        Weapon_damage = 3;
        curr_Room = start_point;
    }

    /** Print out basic instruction*/
    public void instruction(){
        StdOut.println("Example of commands");
        StdOut.println("    attack <name of enemies>");
        StdOut.println("    go <direction>");
        StdOut.println("    look");
        StdOut.println("    look beyond");
        StdOut.println("    take <name of treasure or weapon>");
    }
    /** Handle the input as command */
    public void handleCommand(String command){
        String[] commands = command.split(" ");
        if (curr_Room.getMonster() != null && !(commands[0].equals("attack") || commands[0].equals("look"))) {
            StdOut.println("You can't do that with unfriendlies around.");
            instruction();
        } else if (commands[0].equals("attack")){
            attack(commands[1]);
        } else if (commands[0].equals("look")){
            if ( commands.length == 1) {look();}
            else if (commands[1].equals("beyond")) {look_beyond();}
        } else if (commands[0].equals("go")){
            go(commands[1]);
        } else if (commands[0].equals("take")){
            take(commands[1]);
        } else {
            StdOut.println("I don't understand your command.");
            instruction();
        }
    }

    /** Attack enemies*/
    public void attack(String name) {
        Monster monster = curr_Room.getMonster();
        if (monster != null && monster.getName().equals(name)) {
            if (Weapon_damage > monster.getArmor()) {
                StdOut.println("You strike it dead!");
                curr_Room.setMonster(null);
            } else {
                StdOut.println("Your blow bounces off harmlessly.");
                StdOut.println("The " + monster.getName() + " eats your head!");
                StdOut.println();
                StdOut.println("GAME OVER");
                System.exit(0);
            }
        } else {
            StdOut.println("There is no " + name + " here.");
        }
    }

    /** Moves in the specified direction, if possible. */
    public void go(String direction) {
        Room destination = curr_Room.getNeighbor(direction);
        if (destination == null) {
            StdOut.println("You can't go that way from here.");
        } else {
            curr_Room = destination;
        }
    }

    /** Prints a description of the current room and its contents. */
    public void look() {
        StdOut.println("You are in " + curr_Room.getDescription() + ".");
        if (curr_Room.getMonster() != null) {
            StdOut.println("There is "
                    + curr_Room.getMonster().getDescription() + " here.");
        }
        if (curr_Room.getWeapon() != null) {
            StdOut.println("There is "
                    + curr_Room.getWeapon().getDescription() + " here.");
        }
        if (curr_Room.getTreasure() != null) {
            StdOut.println("There is "
                    + curr_Room.getTreasure().getDescription() + " here.");
        }
        StdOut.println("Exits: " + curr_Room.listExits());
    }

    public void look_beyond(){
        StdOut.println("You are in " + curr_Room.getDescription() + ".");
        String[] lst = {"west", "north", "east", "south"};
        Room neighbor;
        for (String i: lst){
            neighbor = curr_Room.getNeighbor(i);
            if (neighbor != null){
                StdOut.println("On your " + i + ", there are: ");
                StdOut.println("    " + neighbor.getDescription());
                if (neighbor.getMonster() != null){
                    StdOut.println("    " + neighbor.getMonster().getDescription());
                }
                if (neighbor.getWeapon() != null){
                    StdOut.println("    " + neighbor.getWeapon().getDescription());
                }
                if (neighbor.getTreasure() != null){
                    StdOut.println("    " + neighbor.getTreasure().getDescription());
                }
                StdOut.println();
            }
        }

        StdOut.println("Exits: " + curr_Room.listExits());
    }

    /** Attempts to pick up the specified treasure or weapon. */
    public void take(String name) {
        Treasure treasure = curr_Room.getTreasure();
        Weapon weapon = curr_Room.getWeapon();
        if (treasure != null && treasure.getName().equals(name)) {
            curr_Room.setTreasure(null);
            score += treasure.getValue();
            StdOut.println("Your score is now " + score + " out of 100.");
            if (score == 100) {
                StdOut.println();
                StdOut.println("YOU WIN!");
                System.exit(0);
            }
        } else if (weapon != null && weapon.getName().equals(name)) {
            curr_Room.setWeapon(null);
            if (weapon.getDamage() > Weapon_damage) {
                Weapon_damage = weapon.getDamage();
                StdOut.println("You'll be a more effective fighter with this!");
            }
        } else {
            StdOut.println("There is no " + name + " here.");
        }
    }


}
