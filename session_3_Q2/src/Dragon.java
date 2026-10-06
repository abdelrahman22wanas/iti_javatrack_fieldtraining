public class Dragon extends Monster {
    private int fireLevel;

    public Dragon(String name, int fireLevel) {
        super(name);
        this.fireLevel = fireLevel;
    }

    @Override
    public double power() {
        return fireLevel * 100;
    }
}
