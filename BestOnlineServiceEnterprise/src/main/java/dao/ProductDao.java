package dao;

import models.Product;

import java.util.List;

/**
 * Created by Marat on 10.05.2017.
 */
public interface ProductDao extends BaseDao<Product>{
    List<Product> findAllById(int id);
    List<Product> findAllByPrice(int price);
}
