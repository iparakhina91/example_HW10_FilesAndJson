import java.util.List;

public class Order {
    public Integer id;
    public String lastname;
    public String firstname;
    public List<Products> products;
    public static class Products {
        public String name;
        public String size;
        public Integer quantity;
    }
    public Boolean female;
    public List<String> contacts;
}