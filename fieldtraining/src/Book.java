public  class Book extends product {
    public String author;
    public String name;
    Book(String name, double price) {
        super(name, price);
    }
    @Override
    String label(){
        return "Book:"+name+" by "+author;

    }
}
