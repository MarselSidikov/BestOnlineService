import dao.BaseFilmsDao;
import dao.FilmsNamedJdbcTemplateDaoImpl;
import models.Film;

/**
 * 08.05.2017
 * Main @author Ayupov Ayaz (First Software Engineering Platform)
 *
 * @version v1.0 /
 */
public class Main {
    public static void main(String[] args) {
        BaseFilmsDao afishaDao = new FilmsNamedJdbcTemplateDaoImpl();
        Film film = new Film.Builder()
                .name("Доспехи бога: В поисках сокровищ")
                .releaseDate("8 мая 2017")
                .genre("комедия")
                .country("Китай")
                .lasting(98.0)
                .description("Профессора археологии Джека Си ждет по-настоящему увлекательное приключение.")
                .actors("Джеки Чан")
                .producer("Джонатан Шень")
                .picture("default.jpg")
                .build();

        System.out.println(afishaDao.save(film));
    }
}
