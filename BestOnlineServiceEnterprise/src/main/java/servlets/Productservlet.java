package servlets;
import models.Product;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import services.ProductService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * Created by Marat on 23.05.2017.
 *//*
public class Productservlet extends HttpServlet {

    private ProductService productService;

    @Override
    public void init() throws ServletException {
        super.init();
        GenericXmlApplicationContext context = new  GenericXmlApplicationContext();
        ConfigurableEnvironment environment = context.getEnvironment();
        environment.addActiveProfile("dev");
        context.load("ru.itis\\spring\\context.xml");
        context.refresh();
        productService = context.getBean(ProductService.class);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String manufacturer = request.getParameter("manufacturer");
        String dateRelease = request.getParameter("dateRelease");
        String price = request.getParameter("price");
        if(method != null && method.equals("add")){
            request.getRequestDispatcher("jsp/productAdd.jsp").forward(request,response);
        }
        if(name != null){
            request.setAttribute("products",productService.findByName(name));
        } else if(manufacturer != null){
            request.setAttribute("products",productService.findBymanufacturer(manufacturer));
        } else if(dateRelease != null){
            request.setAttribute("products",productService.findBydateRelease(dateRelease));
        } else if(price != null){
            request.setAttribute("products",productService.findByprice(price));
        }else{
            request.setAttribute("products",productService.findAll());
        }

        request.getRequestDispatcher("/jsp/products.jsp").forward(request,response);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String manufacturer = req.getParameter("manufacturer");
        String dateRelease = req.getParameter("dateRelease");
        String price = req.getParameter("price");

        Product film = new Product.Builder()
                .name(name)
                .manufacturer(manufacturer)
                .dateRelease(dateRelease)
                .price(price)
                .build();
        ProductService.register(Product);
        req.setAttribute("Product", ProductService.findAll());
        req.getRequestDispatcher("/jsp/Product.jsp").forward(req,resp);

    }
}
 */


