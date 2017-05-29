package ru.itis.dao;

import ru.itis.models.Product;

import java.util.List;

/**
 * Created by Marat on 10.05.2017.
 */
public interface ProductDao extends BaseDao<Product>{
    List<Product> findByName(String name);
    List<Product> findByManufacturer(String manufacturer);
    List<Product> findByDateRelease(String dateRelease);
    List<Product> findByPrice(int price);
    List<Product> findAllProductsByNameAndPrice(String name, int price);
}
