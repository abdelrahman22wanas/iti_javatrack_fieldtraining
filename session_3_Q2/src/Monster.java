public abstract class Monster {
    protected String name;

    public Monster(String name) {
        this.name = name;
    }

    public void roar() {
        System.out.println(name + " roars!");
    }

    public abstract double power();
}

