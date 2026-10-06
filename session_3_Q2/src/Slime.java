public class Slime extends Monster {
    private int size;

    public Slime(String name, int size) {
        super(name);
        this.size = size;
    }

    @Override
    public double power() {
        return size * 20;
    }
}
