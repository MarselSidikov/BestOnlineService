import dao.AfishaJdbcTemplteDaoImpl;
import dao.AfishaNamedJdbcTemplateDaoImpl;
import dao.BaseAfishaDao;
import models.Movie;

/**
 * 08.05.2017
 * Main @author Ayupov Ayaz (First Software Engineering Platform)
 *
 * @version v1.0 /
 */
public class Main {
    public static void main(String[] args) {
        BaseAfishaDao afishaDao = new AfishaNamedJdbcTemplateDaoImpl();
        Movie movie43 = new Movie.Builder().name("movie43").releaseDate("2012").genre("Комедия").country("США")
                .lasting(2.5).description("Самый упоротый фильм2").actors("Хью Джекман").id(1)
                .picture("2.jpg").producer("Стивен Брилл").build();


        System.out.println( afishaDao.findByProducer("Стивен Брилл"));
    }
}
