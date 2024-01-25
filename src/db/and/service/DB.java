package db.and.service;

import bean.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DB {
    private static List<User> users = new ArrayList<>();
    private static List<Reservation> reservations = new ArrayList<>();
    private static List<Meal> meals = new ArrayList<>();
    private static List<Table> tables = new ArrayList<>();
    public static User session = null;

    static {
        //To Create Table
        for (int i = 0; i < 10; i++) {
            Table table = new Table();
            table.setId(i);
            DB.getTables().add(table);
        }

        //To Add Meal
        Meal meal1 = new Meal("Osh", 50_000D);
        meal1.setId(0);
        Meal meal2 = new Meal("Kabob", 100_000D);
        meal2.setId(1);
        Meal meal3 = new Meal("Jizz", 120_000D);
        meal3.setId(2);
        Meal meal4 = new Meal("Tandir", 200_000D);
        meal4.setId(3);
        Meal meal5 = new Meal("Shi", 40_000D);
        meal5.setId(4);
        DB.getMeals().add(meal1);
        DB.getMeals().add(meal2);
        DB.getMeals().add(meal3);
        DB.getMeals().add(meal4);
        DB.getMeals().add(meal5);

        //To Add Staff
        User user1 = new User("Zinnur", "admin", "admin");
        user1.setAdmin(true);
        user1.setId(0);
        User user2 = new User("Abdulmajid", "user", "user");
        user2.setId(1);
        DB.getUsers().add(user1);
        DB.getUsers().add(user2);

        //To Reserve
        Meal[] meals1 = new Meal[10];
        meals1[0] = DB.getMeals().get(0);
        meals1[1] = DB.getMeals().get(2);

        Date date1 = new Date(2024, 2, 1, 9, 0);
        Reservation reservation1 = new Reservation(0, date1, 4, 0, meals1, "zin");
        Meal[] meals2 = new Meal[10];
        DB.getTables().get(0).setReserve(true);
        meals2[0] = DB.getMeals().get(1);

        Date date2 = new Date(2024, 2, 1, 13, 0);
        Reservation reservation2 = new Reservation(1, date2, 6, 1, meals2, "zin");
        Meal[] meals3 = new Meal[10];
        DB.getTables().get(1).setReserve(true);
        meals3[0] = DB.getMeals().get(1);
        meals3[1] = DB.getMeals().get(3);
        meals3[2] = DB.getMeals().get(4);

        Date date3 = new Date(2024, 2, 2, 16, 0);
        Reservation reservation3 = new Reservation(2, date3, 10, 2, meals3, "user");
        DB.getReservations().add(reservation1);
        DB.getTables().get(2).setReserve(true);
        DB.getReservations().add(reservation2);
        DB.getReservations().add(reservation3);
    }

    public static List<Table> getTables() {
        return tables;
    }

    public static void setTables(List<Table> tables) {
        DB.tables = tables;
    }

    public static List<Meal> getMeals() {
        return meals;
    }

    public static void setMeals(List<Meal> meals) {
        DB.meals = meals;
    }

    public static List<User> getUsers() {
        return users;
    }

    public static void setUsers(List<User> users) {
        DB.users = users;
    }

    public static List<Reservation> getReservations() {
        return reservations;
    }

    public static void setReservations(List<Reservation> reservations) {
        DB.reservations = reservations;
    }
}
