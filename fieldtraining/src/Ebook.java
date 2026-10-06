public class Ebook extends product {
   String name;
    Ebook(String name,double price) {
        super(name,price);

    }
@Override
    String label(){
    return "Ebook "+name+"(digital)";
}
}
