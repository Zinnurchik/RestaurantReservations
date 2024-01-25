package bean;

import java.util.Date;

public class Reservation extends Base {
    private Date date;
    private Integer numberOfPeople;
    private Integer tableId;
    private Meal[] meals;
    private String userLogin;
    private Boolean isDeleted = false;


    public Reservation(Date date, Integer numberOfPeople, Integer tableId, Meal[] meals, String userLogin) {
        this.date = date;
        this.numberOfPeople = numberOfPeople;
        this.tableId = tableId;
        this.meals = meals;
        this.userLogin = userLogin;
    }

    public Reservation(Integer id, Date date, Integer numberOfPeople, Integer tableId, Meal[] meals, String userLogin) {
        this.date = date;
        this.numberOfPeople = numberOfPeople;
        this.tableId = tableId;
        this.meals = meals;
        this.userLogin = userLogin;
        super.setId(id);
    }

    public Reservation(Integer id, Integer numberOfPeople, Meal[] meals, String userLogin) {
        this.numberOfPeople = numberOfPeople;
        this.meals = meals;
        this.userLogin = userLogin;
        super.setId(id);
    }


    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getNumberOfPeople() {
        return numberOfPeople;
    }

    public void setNumberOfPeople(Integer numberOfPeople) {
        this.numberOfPeople = numberOfPeople;
    }

    public Integer getTableId() {
        return tableId;
    }

    public void setTableId(Integer tableId) {
        this.tableId = tableId;
    }

    public Meal[] getMeals() {
        return meals;
    }

    public void setMeals(Meal[] meals) {
        this.meals = meals;
    }


    @Override
    public String toString() {
        String meals = "";
        for (int i = 0; i < getMeals().length; i++) {
            StringBuilder s = new StringBuilder();
            if (getMeals()[i] != null) {
                meals = meals.concat(getMeals()[i].toString());
            }
        }
        return "Reservation{ Id= " + super.getId() + " date= " + date +
                ", \nnumberOfPeople= " + numberOfPeople + ", tableId= " + tableId +
                ", \nmeals= " + meals + ", isDeleted= " + isDeleted + '}';
    }
}
