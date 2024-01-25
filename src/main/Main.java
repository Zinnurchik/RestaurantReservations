package main;

import bean.*;

import java.util.Date;
import java.util.Scanner;
import resource.MealResource;
import resource.ReservationResource;
import resource.UserResource;

import static db.and.service.DB.*;
import static java.lang.System.exit;


public class Main {

    static Scanner intSc = new Scanner(System.in);
    static Scanner stSc = new Scanner(System.in);


    public static void main(String[] args) {
        showMenu();
    }


    private static void showMenu() {
        if (session == null) {
            System.out.println("1. Login");
            System.out.println("2. Register");
        } else {
            if (session.getAdmin()) {
                showStaffMenu();
            } else {
                showCustomerMenu();
            }
        }
        System.out.println("0. Exit");
        System.out.print("Choose: ");
        int choice = intSc.nextInt();
        switch (choice) {
            case 0 -> exit(0);
            case 1 -> login();
            case 2 -> register();
        }
    }


    private static void customerMenu() {
        System.out.println("WELCOME");

        System.out.println("1. Show restaurant details");
        System.out.println("2. Show menu");

        System.out.println("3. Reserve tables");
        System.out.println("4. Cancel reserve");
        System.out.println("5. Update reserve");
        System.out.println("6. Show reservation");
    }


    private static void showCustomerMenu() {
        customerMenu();

        System.out.println("7. Logout");
        System.out.print("Choose: ");
        int choice = intSc.nextInt();
        switch (choice) {
            case 1 -> showRestaurantDetails();
            case 2 -> showMenuItem();
            case 3 -> reserveTable();
            case 4 -> cancelReservation();
            case 5 -> updateReservation();
            case 6 -> showReservations();
            case 7 -> logout();
        }
    }


    private static void showStaffMenu() {
        customerMenu();
        System.out.println("7. Add meal to menu");
        System.out.println("8. Delete meal from menu");
        System.out.println("9. Update meal from menu");

        System.out.println("10. Logout");
        System.out.print("Choose: ");
        int choice = intSc.nextInt();
        switch (choice) {
            case 1 -> showRestaurantDetails();
            case 2 -> showMenuItem();
            case 3 -> reserveTable();
            case 4 -> cancelReservation();
            case 5 -> updateReservation();
            case 6 -> showReservations();
            case 7 -> addItem();
            case 8 -> deleteItem();
            case 9 -> updateItem();

            case 10 -> logout();
        }
    }


    private static void updateReservation() {
        ReservationResource reservationResource = new ReservationResource();
        System.out.print("Old Reservation id => ");
        Integer resId = intSc.nextInt();


        int j = 0;
        while (true) {
            ApiResponce responce = reservationResource.get(j++);
            if (responce.getData() != null) {
                Reservation r = (Reservation) responce.getData();
                if (!r.getDeleted())
                    if (!session.getAdmin()) {
                        if (r.getUserLogin().equals(session.getLogin()) && r.getId().equals(resId)) {
                            MealResource mealResource = new MealResource();
                            Meal[] meals = new Meal[10];
                            for (int i = 0; i < 10; i++) {
                                System.out.print("Choose meal id => ");
                                int id = intSc.nextInt();
                                responce = mealResource.get(id);
                                if (responce.getData() != null) {
                                    meals[i] = (Meal) responce.getData();
                                    System.out.println("1. Add meal");
                                    System.out.println("2. Complete to choosing");
                                    System.out.print(" => ");
                                    int choose = intSc.nextInt();
                                    if (choose != 1) {
                                        break;
                                    }
                                } else {
                                    i--;
                                }
                            }
                            System.out.print("Number of people => ");
                            int people = intSc.nextInt();
                            String userLogin = session.getLogin();
                            if (session.getAdmin()) {
                                System.out.print("User login => ");
                                userLogin = stSc.next();
                            }
                            ApiResponce apiResponce = reservationResource.update(new Reservation(resId, people, meals, userLogin));

                            System.out.println(apiResponce.getMessage());
                            break;
                        }
                    } else {
                        MealResource mealResource = new MealResource();
                        Meal[] meals = new Meal[10];
                        for (int i = 0; i < 10; i++) {
                            System.out.print("Choose meal id => ");
                            int id = intSc.nextInt();
                            responce = mealResource.get(id);
                            if (responce.getData() != null) {
                                meals[i] = (Meal) responce.getData();
                                System.out.println("1. Add meal");
                                System.out.println("2. Complete to choosing");
                                System.out.print(" => ");
                                int choose = intSc.nextInt();
                                if (choose != 1) {
                                    break;
                                }
                            } else {
                                i--;
                            }
                        }
                        System.out.print("Number of people => ");
                        int people = intSc.nextInt();
                        String userLogin = session.getLogin();
                        if (session.getAdmin()) {
                            System.out.print("User login => ");
                            userLogin = stSc.next();
                        }
                        ApiResponce apiResponce = reservationResource.update(new Reservation(resId, people, meals, userLogin));

                        System.out.println(apiResponce.getMessage());
                        break;
                    }

            } else {
                break;
            }
        }
        showMenu();
    }


    private static void cancelReservation() {
        ReservationResource reservationResource = new ReservationResource();
        System.out.print("Reservation id => ");
        Integer id = intSc.nextInt();

        int i = 0;
        while (true) {
            ApiResponce responce = reservationResource.get(i++);
            if (responce.getData() != null) {
                Reservation r = (Reservation) responce.getData();
                if (!r.getDeleted())
                    if (!session.getAdmin()) {
                        if (r.getUserLogin().equals(session.getLogin()) && r.getId().equals(id)) {
                            responce = reservationResource.delete(id);
                            System.out.println(responce.getMessage());
                            break;
                        }
                    } else {
                        responce = reservationResource.delete(id);
                        System.out.println(responce.getMessage());
                        break;
                    }
            } else {
                break;
            }
        }
        showMenu();
    }


    private static void showReservations() {
        ReservationResource reservationResource = new ReservationResource();
        int i = 0;
        while (true) {
            ApiResponce responce = reservationResource.get(i++);
            if (responce.getData() != null) {
                Reservation r = (Reservation) responce.getData();
                if (!r.getDeleted()) if (session.getAdmin()) System.out.println(responce.getData());
                else if (r.getUserLogin().equals(session.getLogin())) System.out.println(responce.getData());
            } else {
                break;
            }
        }
        showMenu();
    }


    private static void reserveTable() {
        ReservationResource reservationResource = new ReservationResource();
        MealResource mealResource = new MealResource();
        ApiResponce responce;

        Meal[] meals = new Meal[10];
        for (int i = 0; i < 10; i++) {
            System.out.print("Choose meal id => ");
            int id = intSc.nextInt();
            responce = mealResource.get(id);
            if (responce.getData() != null) {
                meals[i] = (Meal) responce.getData();
                System.out.println("1. Add meal");
                System.out.println("2. Complete to choosing");
                System.out.print(" => ");
                int choose = intSc.nextInt();
                if (choose != 1) {
                    //you can set reservation price on here first create field with 0 value,
                    // move the people field to up then set price
                    break;
                }
            } else {
                i--;
            }
        }
        System.out.print("Year => ");
        int year = intSc.nextInt();
        System.out.print("Month => ");
        int month = intSc.nextInt();
        System.out.print("Day => ");
        int day = intSc.nextInt();
        System.out.print("Hour => ");
        int hour = intSc.nextInt();
        System.out.print("Minute => ");
        int minute = intSc.nextInt();
        Date date = new Date(year, month, day, hour, minute);
        System.out.print("Number of people => ");
        int people = intSc.nextInt();
        System.out.print("Table id => ");
        int table = intSc.nextInt();
        String userLogin = session.getLogin();
        if (session.getAdmin()) {
            System.out.print("User login => ");
            userLogin = stSc.next();
        }

        Reservation reservation = new Reservation(date, people, table, meals, userLogin);
        responce = reservationResource.add(reservation);
        System.out.println(responce.getMessage());
        showMenu();
    }


    private static void updateItem() {
        MealResource menuResource = new MealResource();
        System.out.print("Old meal id => ");
        Integer id = intSc.nextInt();
        System.out.print("Meal name  => ");
        String name = stSc.next();
        System.out.print("Meal price => ");
        Double price = intSc.nextDouble();

        ApiResponce apiResponce = menuResource.update(new Meal(id, name, price));

        System.out.println(apiResponce.getMessage());
        showMenu();
    }


    private static void deleteItem() {
        MealResource menuResource = new MealResource();
        System.out.print("Meal id => ");
        Integer id = intSc.nextInt();

        ApiResponce apiResponce = menuResource.delete(id);

        System.out.println(apiResponce.getMessage());
        showMenu();
    }


    private static void addItem() {
        MealResource menuResource = new MealResource();
        System.out.print("Meal name => ");
        String name = stSc.next();
        System.out.print("Meal price => ");
        Double price = intSc.nextDouble();

        ApiResponce apiResponce = menuResource.add(new Meal(name, price));

        System.out.println(apiResponce.getMessage());
        showMenu();
    }


    private static void showMenuItem() {
        MealResource menuResource = new MealResource();
        for (int i = 0; i < 10; i++) {
            ApiResponce apiResponce = menuResource.get(i);
            Meal meal = (Meal) apiResponce.getData();
            if (meal != null) if (!meal.getDeleted()) System.out.println(apiResponce.getData());
        }
        showMenu();
    }


    private static void showRestaurantDetails() {
        System.out.println("Name: Oasis \nAddress: Tree street \nPhone: +1 9544 555 44 44");
        showMenu();
    }


    private static void logout() {
        session = null;
        showMenu();
    }


    private static void register() {
        UserResource usersResource = new UserResource();
        stSc = new Scanner(System.in);

        System.out.print("Name: ");
        String name = stSc.next();
        System.out.print("Login: ");
        String login = stSc.next();
        System.out.print("Password: ");
        String password = stSc.next();

        ApiResponce apiResponce = usersResource.add(new User(name, login, password));
        System.out.println(apiResponce.getMessage());
        session = (User) apiResponce.getData();
        showMenu();
    }

    private static void login() {
        UserResource usersResource = new UserResource();

        System.out.print("Login: ");
        String login = stSc.next();
        System.out.print("Password: ");
        String password = stSc.next();

        ApiResponce apiResponce = usersResource.login(new User(login, password));

        System.out.println(apiResponce.getMessage());
        session = (User) apiResponce.getData();

        showMenu();
    }
}