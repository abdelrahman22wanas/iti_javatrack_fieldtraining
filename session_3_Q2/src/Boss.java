public class Boss extends Monster implements Flyable,Healable{
    private int strength;

    public Boss(String name, int strength) {
        super(name);
        this.strength = strength;
    }

    @Override
    public double power() {
        return strength * 200;
    }

    @Override
    public void fly() {
        System.out.println(name + " is flying!");
    }

        @Override
    public void heal(int amount) {
        strength += amount;
        System.out.println(name + " healed. Strength = " + strength);
    }
}
