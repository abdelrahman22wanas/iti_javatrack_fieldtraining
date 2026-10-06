public class Phone extends Gadget{
    private int storageGb;
    private String brand;
    private String name;
    private double price;
    public Phone(int storageGb,String brand,String name,double price){
        super(name,price);
        this.storageGb=storageGb;
        this.brand=brand;
        this.name=name;

    }
@Override
    String label(){return "phone "+brand+" "+name+" ("+storageGb+")";}
}
