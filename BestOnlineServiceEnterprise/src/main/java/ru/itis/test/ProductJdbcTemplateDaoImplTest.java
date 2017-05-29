package ru.itis.test;

import ru.itis.dao.impl.ProductDaoJdbcImpl;
import ru.itis.models.Product;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.dao.EmptyResultDataAccessException;



import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by Marat on 23.05.2017.
 */
public class ProductJdbcTemplateDaoImplTest {
    @Autowired
    private ProductDaoJdbcImpl ProductDao;
    private final int PRODUCT_ID_6 = 6;
    private final String Name = "Book_Name";
    private final String manufacturer = "Zavod";
    private final String dateRelease = "01.01.1990";

    private final Product PRODUCT_6 = new Product.Builder()
            .id(6)
            .name("Book_Name")
            .manufacturer("Zavod")
            .dateRelease("01.01.1990")
            .price(100)
            .build();

    private final int PRODUCT_6_ID = 6;
    private final Product Product_6 = new Product.Builder()
            .id(PRODUCT_6_ID)
            .name("Book_Name")
            .manufacturer("Zavod")
            .dateRelease("01.01.1990")
            .price(100)
            .build();

    private final Product INSERT_PRODUCT = new Product.Builder()
            .id(PRODUCT_6_ID)
            .name("Book_Name")
            .manufacturer("Zavod")
            .dateRelease("01.01.1990")
            .price(100)
            .build();

    private final Product UPDATE_PRODUCT_6 = new Product.Builder()
            .id(PRODUCT_6_ID)
            .name("Book_Name")
            .manufacturer("Zavod")
            .dateRelease("01.01.1990")
            .price(100)
            .build();




    @Before
    public void setUp() throws Exception {
        GenericXmlApplicationContext context = new  GenericXmlApplicationContext();
        ConfigurableEnvironment environment = context.getEnvironment();
        environment.addActiveProfile("ru/itis/test");
        context.load("ru.itis\\spring\\context.xml");
        context.refresh();
        ProductDao = context.getBean(ProductDaoJdbcImpl.class);

    }

    @Test
    public void testFind() throws Exception {
        Product expected = PRODUCT_6;
        Product actual = ProductDao.find(PRODUCT_6_ID);

        assertEquals(expected, actual);
    }
    @Test
    public void findByName() throws Exception {
        List<Product> expected = new ArrayList<Product>();
        expected.add(PRODUCT_6);
        List<Product> actual = ProductDao.findByName(Name);
        assertEquals(expected,actual);
    }

    @Test
    public void findAll() throws Exception {
        List<Product> expected = new ArrayList<Product>();
        expected.add(PRODUCT_6);
        List<Product> actual = ProductDao.findAll();
        assertEquals(expected, actual);
    }

    @Test(expected = EmptyResultDataAccessException.class)
    public void testDelete() {
        ProductDao.delete(PRODUCT_6_ID);
        ProductDao.find(PRODUCT_6_ID);
    }
}

