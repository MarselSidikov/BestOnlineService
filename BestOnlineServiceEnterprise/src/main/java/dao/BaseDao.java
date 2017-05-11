package dao;

import java.util.List;

/**
 * 04.05.2017
 * BaseDao
 *
 * @author
 * @version v1.0
 */
public interface BaseDao<T> {
    T find(int id);
    int save(T model);
    void delete(int id);
    void update(T model);

    List<T> findAll();

}