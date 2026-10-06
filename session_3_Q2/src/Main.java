import java.util.*;

public class Main {
    public static void main(String[] args) {

        Dragon dragon = new Dragon("Draco", 5);
        Slime slime = new Slime("Slimy", 3);
        Boss boss = new Boss(" Demon", 10);
        ArrayList<Monster> monsters = new ArrayList<>();
        monsters.add(dragon);
        monsters.add(slime);
        monsters.add(boss);
        for (Monster monster : monsters) {
            monster.roar();
            System.out.println("Battle Power: " + monster.power());
            System.out.println();
        }
        boss.fly();
        boss.heal(5);
        System.out.println("Boss New Battle Power: " + boss.power());


    }
}