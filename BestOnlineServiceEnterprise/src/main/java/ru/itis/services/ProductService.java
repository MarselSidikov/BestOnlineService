package ru.itis.services;

import ru.itis.models.Product;

import java.util.List;

/**
 * 06.05.2017
 * ProductService
 *
 *  Marat
 *  v1.0
 */
public interface ProductService {
    void register(Product product);
    /*boolean isRegistered(String Name);*/
    List<Product> getAllProductsByPrice(int price);
    List<Product> getAll();
    List<Product> getAllProductsByNameAndPrice(String name, int price);
}
