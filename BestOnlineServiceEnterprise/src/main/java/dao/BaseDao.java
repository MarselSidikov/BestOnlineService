package dao;

import java.util.List;
import models.Product;
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
    void update(T model);
    void delete(int id);
    List<T> findAll();

}