package resource;

import bean.ApiResponce;
import bean.User;
import db.and.service.UserRepository;

public class UserResource implements BaseCrudResource<User> {
    UserRepository repository = new UserRepository();


    public ApiResponce login(User user) {
        UserRepository repository = new UserRepository();
        User newUser = repository.login(user);
        return newUser == null ? new ApiResponce(400, "User not found", null) :
                new ApiResponce(200, "Success", newUser);
    }


    @Override
    public ApiResponce add(User user) {
        User newUser = repository.add(user);
        return newUser == null ? new ApiResponce(400, "User already exist", null) :
                new ApiResponce(200, "Successfully created", newUser);
    }

    @Override
    public ApiResponce get(Integer id) {
        return null;
    }

    @Override
    public ApiResponce update(User newBean) {
        return null;
    }

    @Override
    public ApiResponce delete(Integer id) {
        return null;
    }
}
