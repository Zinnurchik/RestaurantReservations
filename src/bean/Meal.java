package bean;

public class Meal extends Base {
    private String name;
    private Double price;
    private Boolean isDeleted = false;


    public Meal(String name, Double price) {
        this.name = name;
        this.price = price;
    }

    //This constructor is for update item
    public Meal(Integer id, String name, Double price) {
        this.name = name;
        this.price = price;
        super.setId(id);
    }


    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }


    @Override
    public String toString() {
        return "Meal{ id= " + super.getId() + ", name= " + name + ", price= " + price + "}";
    }
}
