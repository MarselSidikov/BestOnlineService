package servlets;

import models.Film;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import services.AfishaService;
import services.impl.AfishaServiceImpl;

import javax.security.auth.login.Configuration;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 21.05.2017
 * AfishaServlet @author Ayupov Ayaz (First Software Engineering Platform)
 *
 * @version v1.0 /
 */
public class AfishaServlet extends HttpServlet{

    private AfishaService afishaService;

    @Override
    public void init() throws ServletException {
        super.init();
       GenericXmlApplicationContext context = new  GenericXmlApplicationContext();
       ConfigurableEnvironment environment = context.getEnvironment();
       environment.addActiveProfile("dev");
       context.load("ru.itis\\spring\\context.xml");
       context.refresh();
        afishaService = context.getBean(AfishaService.class);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String genre = request.getParameter("genre");
        String country = request.getParameter("country");
        String producer = request.getParameter("producer");
        String actors = request.getParameter("actor");
        String method = request.getParameter("film");
        if(method != null && method.equals("add")){
            request.getRequestDispatcher("jsp/filmsAdd.jsp").forward(request,response);
        }
        if(name != null){
            request.setAttribute("films",afishaService.findByName(name));
        } else if(genre != null){
            request.setAttribute("films",afishaService.findByGenre(genre));
        } else if(country != null){
            request.setAttribute("films",afishaService.findByCountry(country));
        } else if(producer != null){
            request.setAttribute("films",afishaService.findByProducer(producer));
        } else if(actors != null){
            request.setAttribute("films",afishaService.findByActors(actors));
        }else{
            request.setAttribute("films",afishaService.findAll());
        }

        request.getRequestDispatcher("/jsp/films.jsp").forward(request,response);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String releaseDate = req.getParameter("releaseDate");
        String genres = req.getParameter("genre");
        String country = req.getParameter("country");
        String producer = req.getParameter("producer");
        String lasting = req.getParameter("lasting");
        String description = req.getParameter("description");
        String actors = req.getParameter("actors");

        String actorsAsArray[] = actors.split(",");
        String genresAsArray[] = genres.split(",");
        List<String> genresAsList = new ArrayList<>();
        List<String> actorsAsList = new ArrayList<>();
        for(int i = 0; i < actorsAsArray.length; i ++){
            actorsAsList.add(actorsAsArray[i]);
        }
        for(int i = 0; i < genresAsArray.length; i ++){
            genresAsList.add(genresAsArray[i]);
        }
        Film film = new Film.Builder()
                .name(name)
                .releaseDate(releaseDate)
                .actors(actorsAsList)
                .country(country)
                .producer(producer)
                .lasting(Integer.parseInt(lasting))
                .description(description)
                .genre(genresAsList)
                .build();

        afishaService.register(film);
        req.setAttribute("films", afishaService.findAll());
        req.getRequestDispatcher("/jsp/films.jsp").forward(req,resp);

    }
}
