package db.and.service;

import bean.Base;

public interface BaseCrudRepository<T extends Base> {
    T add(T bean);
    T get(Integer id);
    Boolean update(T newBean);
    Boolean delete(Integer id);
}
