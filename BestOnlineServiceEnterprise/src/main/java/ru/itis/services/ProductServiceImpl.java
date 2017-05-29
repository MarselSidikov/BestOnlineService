package ru.itis.services;

import ru.itis.dao.ProductDao;
import ru.itis.models.Product;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by Андрей on 26.05.2017.
 */
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;

    @Override
    public void register(Product product) {
        productDao.save(product);
    }

    @Override
    public List<Product> getAllProductsByPrice(int price) {
        return productDao.findByPrice(price);
    }

    @Override
    public List<Product> getAll() {
        return productDao.findAll();
    }

    @Override
    public List<Product> getAllProductsByNameAndPrice(String name, int price) {
        return productDao.findAllProductsByNameAndPrice(name, price);
    }
}

