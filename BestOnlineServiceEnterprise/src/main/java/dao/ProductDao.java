package dao;

import models.Product;

import java.util.List;

/**
 * Created by Marat on 10.05.2017.
 */
public interface ProductDao extends BaseDao<Product>{
    List<Product> findById(int id);
    List<Product> findByManufacturer(String manufacturer);
    List<Product> findByDate_release(String date_release);
    List<Product> findByPrice(int price);
    void delete(Product product);

    Product getById(int id);

    List<Product> getAll();
}
