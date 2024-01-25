package db.and.service;

import bean.Meal;

import java.util.List;

public class MealRepository implements BaseCrudRepository<Meal> {
    static List<Meal> meals = DB.getMeals();


    @Override
    public Meal add(Meal meal) {
        int count = 0;
        for (int i = 0; i < meals.size(); i++) {
            if (meal.getName().equals(meals.get(i).getName())) {
                return null;
            } else if (!meals.get(i).getDeleted()) {
                count++;
            }
        }
        if (count < 10) {
            meal.setId(meals.size());
            meals.add(meal);
            return meal;
        }
        return meal;
    }

    @Override
    public Meal get(Integer id) {
        for (int i = 0; i < meals.size(); i++) {
            if (meals.get(i).getId().equals(id)) {
                return meals.get(i);
            }
        }
        return null;
    }

    @Override
    public Boolean update(Meal newMeal) {
        for (int i = 0; i < meals.size(); i++) {
            if (meals.get(i).getId().equals(newMeal.getId())) {
                meals.get(i).setName(newMeal.getName());
                meals.get(i).setPrice(newMeal.getPrice());
                return true;
            }
        }
        return false;
    }

    @Override
    public Boolean delete(Integer id) {
        for (int i = 0; i < meals.size(); i++) {
            if (meals.get(i).getId().equals(id)) {
                meals.get(i).setDeleted(true);
                return true;
            }
        }
        return false;
    }
}
