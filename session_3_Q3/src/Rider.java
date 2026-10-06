public class Rider {
    String name;
    int height;

    public Rider(String name, int height) {
        this.name = name;
        this.height = height;
    }

    @Override
    public String toString()
    {
        return name + " - " + height + " cm";
    }

    public int getHeight() {
        return height;
    }
}
