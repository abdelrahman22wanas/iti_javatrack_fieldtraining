public class Gadget extends product {
    private String brand;
    private int id;
    private String name;
    private double price;
    Gadget(String brand, String name, double price, int id) {
        super(name, price);
        this.id = id;
        this.name = name;
        this.price = price;
        this.brand=brand;
    }

    public Gadget(String name,double price) {
        super(name, price);
    }
    @Override
    String label(){
        return null;
    }
}
