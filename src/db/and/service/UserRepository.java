package db.and.service;

import bean.User;

import java.util.List;

public class UserRepository implements BaseCrudRepository<User> {
    static List<User> users = DB.getUsers();


    @Override
    public User add(User user) {
        for (int i = 0; i < users.size(); i++) {
            if (user.getPassword().equals(users.get(i).getPassword()) &&
                    user.getLogin().equals(users.get(i).getLogin())) {
                return null;
            }
        }
        user.setId(users.size());
        users.add(user);
        return user;
    }

    @Override
    public User get(Integer id) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getId().equals(id)) {
                return users.get(i);
            }
        }
        return null;
    }

    @Override
    public Boolean update(User newBean) {
        return null;
    }

    @Override
    public Boolean delete(Integer id) {
        return null;
    }


    public User login(User user) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getLogin().equals(user.getLogin()) &&
                    users.get(i).getPassword().equals(user.getPassword())) {
                return users.get(i);
            }
        }
        return null;
    }
}
