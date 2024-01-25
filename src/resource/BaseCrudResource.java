package resource;

import bean.ApiResponce;
import bean.Base;

public interface BaseCrudResource<T extends Base> {
    ApiResponce add(T bean);

    ApiResponce get(Integer id);

    ApiResponce update(T newBean);

    ApiResponce delete(Integer id);
}