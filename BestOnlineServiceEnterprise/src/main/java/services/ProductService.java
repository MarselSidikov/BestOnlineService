package services;

import models.Product;

/**
 * 06.05.2017
 * ProductService
 *
 *  Marat
 *  v1.0
 */
public interface ProductService {
    void register(Product product);
    boolean isRegistered(String productName);
}