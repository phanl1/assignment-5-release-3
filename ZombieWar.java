

import java.util.Random;

public class ZombieWar {

    static Survivor[] survivors;
    static Zombie[] zombies;

    public static void main(String[] args) {
        survivors = generateSurvivors();
        zombies = generateZombies();

        startWar newWar = new startWar(survivors, zombies);

        newWar.Start();

        System.out.println();
    }

    static Survivor[] generateSurvivors() {
        Random rand = new Random();
        int randSurvivors = rand.nextInt(10) + 1; // +1 to prevent zero bound (1-10 survivors)
        survivors = new Survivor[randSurvivors];
        int numSoldiers = rand.nextInt(randSurvivors);
        int numTeachers = rand.nextInt(randSurvivors - numSoldiers);
        int numChildren = randSurvivors - numSoldiers - numTeachers;

        int counter = 0;
        for (int x = 0; x < numSoldiers; x++) {
            survivors[counter] = new Soldier(x + 1); // +1 so ID starts at 1 versus zero
            counter++;
        }
        for (int x = 0; x < numTeachers; x++) {
            survivors[counter] = new Teacher(x + 1);
            counter++;
        }
        for (int x = 0; x < numChildren; x++) {
            survivors[counter] = new Child(x + 1);
            counter++;
        }

        return survivors;
    }

    static Zombie[] generateZombies() {
        Random rand = new Random();
        int numZombies = rand.nextInt(9) + 1;
        zombies = new Zombie[numZombies];
        int numCommonInfected = rand.nextInt(numZombies);
        int numTanks = numZombies - numCommonInfected;

        int counter = 0;
        for (int x = 0; x < numCommonInfected; x++) {
            zombies[counter] = new CommonInfected(x + 1); // +1 so ID starts at 1 versus zero
            counter++;
        }
        for (int x = 0; x < numTanks; x++) {
            zombies[counter] = new Tank(x + 1);
            counter++;
        }

        return zombies;
    }
}

abstract class Survivor implements ICharacters{
    int id;
    int health;
    int attack;
    boolean isDead;
    String name;

    protected int getID(){
        return id;
    }

    @Override
    public int getHealth() {
        return this.health;
    }

    @Override
    public void setHealth(int attack) {
        this.health = health - attack;
    }

    @Override
    public int getAttack() {
        return attack;
    }

    public void attack(Zombie zombie, int attack) {
        zombie.setHealth(attack);
    }

    @Override
    public void isDead(int health) {
        if (health <= 0) {
            isDead = true;
        }
    }

    public String getName(){
        return name + " " + id;
    }

}

abstract class Zombie implements ICharacters {
    int id;
    int health;
    int attack;
    boolean isDead;
    String name;

    protected int getID(){
        return id;
    }

    @Override
    public int getHealth() {
        return this.health;
    }

    @Override
    public void setHealth(int attack) {
        this.health = health - attack;
    }

    @Override
    public int getAttack() {
        return attack;
    }

    public void attack(Survivor survivor, int attack) {
        survivor.setHealth(attack);
    }

    @Override
    public void isDead(int health) {
        if (health <= 0) {
            isDead = true;
        }
    }

    public String getName() {
        return name;
    }
}

class Child extends Survivor {
    public Child(int id) {
        this.id = id;
        health = 20;
        attack = 2;
        name = "Child";
    }

    @Override
    protected int getID() {
        return id;
    }

    @Override
    public int getHealth() {
        return health;
    }

    @Override
    public void setHealth(int attack) {
        this.health = health - attack;
    }

    @Override
    public int getAttack() {
        return attack;
    }

    @Override
    public void attack(Zombie zombie, int attack) {
        zombie.setHealth(attack);
    }
    @Override
    public String getName(){
        return name + " " + id;
    }
}

class Teacher extends Survivor {
    public Teacher(int id) {
        this.id = id;
        health = 50;
        attack = 5;
        name = "Teacher";
    }

    @Override
    protected int getID() {
        return id;
    }

    @Override
    public int getHealth() {
        return health;
    }

    @Override
    public void setHealth(int attack) {
        this.health = health - attack;
    }

    @Override
    public int getAttack() {
        return attack;
    }

    @Override
    public void attack(Zombie zombie, int attack) {
        zombie.setHealth(attack);
    }

    @Override
    public String getName(){
        return name + " " + id;
    }
}

class Soldier extends Survivor {
    public Soldier(int id) {
        this.id = id;
        health = 100;
        attack = 10;
        name = "Soldier";
    }

    @Override
    protected int getID() {
        return id;
    }

    @Override
    public int getHealth() {
        return health;
    }

    @Override
    public void setHealth(int attack) {
        this.health = health - attack;
    }

    @Override
    public int getAttack() {
        return attack;
    }

    @Override
    public void attack(Zombie zombie, int attack) {
        zombie.setHealth(attack);
    }

    @Override
    public String getName(){
        return name + " " + id;
    }
}

class CommonInfected extends Zombie {
    public CommonInfected(int id) {
        this.id = id;
        health = 30;
        attack = 5;
        name = "CommonInfected";
    }

    @Override
    protected int getID() {
        return id;
    }

    @Override
    public int getHealth() {
        return this.health;
    }

    @Override
    public void setHealth(int attack) {
        this.health = health - attack;
    }

    @Override
    public int getAttack() {
        return attack;
    }

    @Override
    public void attack(Survivor survivor, int attack) {
        survivor.setHealth(attack);
    }

    @Override
    public String getName() {
        return name + " " + id;
    }
}

class Tank extends Zombie {
    public Tank(int id) {
        this.id = id;
        health = 150;
        attack = 20;
        name = "Tank";
    }

    @Override
    protected int getID() {
        return id;
    }

    @Override
    public int getHealth() {
        return health;
    }

    @Override
    public void setHealth(int attack) {
        this.health = health - attack;
    }

    @Override
    public int getAttack() {
        return attack;
    }

    @Override
    public void attack(Survivor survivor, int attack) {
        survivor.setHealth(attack);
    }

    @Override
    public String getName() {
        return name + " " + id;
    }
}

class Weapon {
    Random rand = new Random();
    private int damage;
    private int accuracy;
    private final String weaponName;

    String[] weapons = new String[] {
            "Shotgun", "Submachine Gun", "Assault Rifle",
            "Pistol", "Axe", "Crowbar", "Frying Pan"};

    Weapon() {
        int randGun = rand.nextInt(7);
        weaponName = weapons[randGun];

        switch (weaponName) {
            case "Shotgun": {
                damage = 100;
                accuracy = 30;
            }
            case "Submachine Gun" : {
                damage = 50;
                accuracy = 70;
            }
            case "Assault Rifle" : {
                damage = 70;
                accuracy = 50;
            }
            case "Pistol" : {
                damage = 25;
                accuracy = 80;
            }
            case "Axe" : {
                damage = 50;
                accuracy = 100;
            }
            case "Crowbar" : {
                damage = 20;
                accuracy = 100;
            }
            case "Frying Pan" :{
                damage = 5;
                accuracy = 1;
            }
        }
     }

    public int getDamage() {
        return damage;
     }

    public int getAccuracy() {
       return accuracy;
    }

    public String getWeaponName() {
       return weaponName;
    }

    public boolean checkAccuracy(int accuracy) {
        boolean weaponHit;
        Random random = new Random();
        int compareAccuracy = random.nextInt(100);

        weaponHit = accuracy >= compareAccuracy;

        return weaponHit;
    }
}

