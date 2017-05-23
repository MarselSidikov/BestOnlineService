package test;

import dao.ProductJdbcTemplate;
import models.Product;
import models.User;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;


import static junit.framework.TestCase.assertEquals;

/**
 * Created by Marat on 23.05.2017.
 */
public class ProductNamedJdbcTemplateDaoImplTest  {
   /* @Autowired
    private ProductJdbcTemplate ProductDao;
    private final int Product_6_ID = 6;
    private final String Name = "Book_Name";
    private final String manufacturer = "Zavod";
    private final String dateRelease = ""

    private final Product Product_6 = new Product.Builder()
            .id(Product_6_ID)
            .name("Book")
            .manufacturer("Zavod")
            .dateRelease("01.01.1990")
            .price(100)
            .build();






    private final int Product_6_ID = 6;
    private final Product Product_6 = new Product.Builder()
            .id(Product_6_ID)
            .name("Book")
            .manufacturer("Zavod")
            .dateRelease("01.01.1990")
            .price(100)
            .build();

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void testFind() throws Exception {
        Product expected = Product_6;
        Product actual = ProductDao.find(Product_6_ID);

        assertEquals(expected, actual);
    }



    @Test(expected = EmptyResultDataAccessException.class)
    public void testDelete() {
        ProductDao.delete(Product_6_ID);
        ProductDao.find(Product_6_ID);
    }*/
}

