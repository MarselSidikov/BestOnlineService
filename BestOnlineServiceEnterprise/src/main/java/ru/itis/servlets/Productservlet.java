package ru.itis.servlets;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.itis.config.app.AppConfig;
import ru.itis.models.Product;

import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

import ru.itis.services.ProductService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * Created by Marat on 23.05.2017.
 */
public class Productservlet extends HttpServlet {

    private ProductService productService;


    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
       productService = (ProductService) config.getServletContext().getAttribute("productService");
    }

    @Override
    public void init() throws ServletException {
        super.init();

        GenericXmlApplicationContext context = new GenericXmlApplicationContext();
        ConfigurableEnvironment environment = context.getEnvironment();
        environment.addActiveProfile("dev");
        context.load("ru.itis\\context.xml");
/*
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        productService = context.getBean(ProductService.class);
    }*/

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // в запрос кладу атрибут users, который из себя представляет список людей
        String price = request.getParameter("price");
        String name = request.getParameter("name");
        String method = request.getParameter("method");
        if (method != null && method.equals("post")) {
            request.getRequestDispatcher("/jsp/productPost.jsp").forward(request, response);
        }
        if (price != null && name != null) {
            int priceAsInt = Integer.parseInt(price);
            request.setAttribute("products", productService.getAllProductsByNameAndPrice(name, priceAsInt));
        } else if (price != null && name == null) {
            int priceAsInt = Integer.parseInt(price);
            request.setAttribute("products", productService.getAllProductsByPrice(priceAsInt));
        } else {
            request.setAttribute("products", productService.getAll());
        }

        // я пераправляю запрос на jsp-страницу
        request.getRequestDispatcher("/jsp/productsAll.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String dateRelease = req.getParameter("dateRelease");
        String manufacturer = req.getParameter("manufacturer");
        int price = Integer.parseInt(req.getParameter("price"));

        Product newProduct = new Product.Builder()
                .name(name)
                .price(price)
                .manufacturer(manufacturer)
                .dateRelease(dateRelease)
                .build();

        productService.register(newProduct);
        req.setAttribute("products", productService.getAll());
        req.getRequestDispatcher("/jsp/productsAll.jsp").forward(req, resp);
    }
}




/*
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
        // в запрос кладу атрибут product, который из себя представляет список товаров
        //Метод getParameter() возвращает значение параметров в формате String>в котором указывается имя параметра, приведенное в HTML.
        String name = request.getParameter("name");
        String manufacturer = request.getParameter("manufacturer");
        String dateRelease = request.getParameter("dateRelease");
        String price = request.getParameter("price");
        String method = request.getParameter("method");
        if (method != null && method.equals("post")) {
            request.getRequestDispatcher("/jsp/ProductPost.jsp").forward(request, response);

        if(method != null && method.equals("Post")){
            request.getRequestDispatcher("jsp/productPost.jsp").forward(request,response);
        }
        if(name != null){
            request.setAttribute("products",productService.findByName(name));
        } else if(manufacturer != null){
            request.setAttribute("products",productService.findByManufacturer(manufacturer));
        } else if(dateRelease != null){
            request.setAttribute("products",productService.findBydateRelease(dateRelease));
        } else if(price != null){
            request.setAttribute("products",productService.findByprice(price));
        }else{
            request.setAttribute("products",productService.findAll());
        }
            // я пераправляю запрос на jsp-страницу
        request.getRequestDispatcher("/jsp/products.jsp").forward(request,response);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String manufacturer = req.getParameter("manufacturer");
        String dateRelease = req.getParameter("dateRelease");
        String price = req.getParameter("price");

        Product Product = new Product.Builder()
                .name(name)
                .manufacturer(manufacturer)
                .dateRelease(dateRelease)
                .price(price)
                .build();
        ProductService.register(Product);
        req.setAttribute("Product", ProductService.findAll());
        req.getRequestDispatcher("/jsp/Product.jsp").forward(req,resp);

    }*/


