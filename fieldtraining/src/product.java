
abstract class product {
private int id;
private String name;
private double price;
private static int counter =0;
public product(String name, double price) {
    this.id = ++counter;
    this.name = name;
    this.price = price;
}




    public double getPrice() {
        return price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setName(String name) {
        this.name = name;
    }
private String recipt (){return " # "+id+" "+name+" $ "+price;}
abstract String label();

double applyDiscount(double percent){
    return price*percent;
}
void applyDiscount(double percent,boolean isMember){
    price =applyDiscount(price)+(5/100)*price;
    System.out.println(price);
}
}
