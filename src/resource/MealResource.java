package resource;

import bean.ApiResponce;
import bean.Meal;
import db.and.service.MealRepository;

public class MealResource implements BaseCrudResource<Meal> {
    MealRepository mealRepository = new MealRepository();


    @Override
    public ApiResponce add(Meal meal) {
        Meal newMeal = mealRepository.add(meal);
        return newMeal == null ? new ApiResponce(400, "The meal is already exist", null) :
                meal.getId() == null ? new ApiResponce(400, "Menu has got ten item! You can not add", newMeal) :
                        new ApiResponce(200, "Success", newMeal);
    }

    @Override
    public ApiResponce get(Integer id) {
        Meal meal = mealRepository.get(id);
        return meal == null ? new ApiResponce(400, "Meal not found", null) :
                new ApiResponce(200, "Success", meal);
    }

    @Override
    public ApiResponce update(Meal newMeal) {
        Boolean isUpdate = mealRepository.update(newMeal);
        return !isUpdate ? new ApiResponce(400, "Meal not found", false) :
                new ApiResponce(200, "Updated", true);
    }

    @Override
    public ApiResponce delete(Integer id) {
        Boolean isDelete = mealRepository.delete(id);
        return !isDelete ? new ApiResponce(400, "Meal id is not found", false) :
                new ApiResponce(200, "Deleted", true);
    }
}
