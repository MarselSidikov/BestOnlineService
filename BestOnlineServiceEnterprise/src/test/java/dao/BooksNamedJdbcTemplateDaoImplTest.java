package dao;
import models.Book;
import  org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
/**
 * 17.05.2017
 *
 * @author Shaikhutdinov Artur (First Software Engineering Platform)
 * @version v1.0
 */
public class BooksNamedJdbcTemplateDaoImplTest {


    private BooksNamedJdbcTemplateDaoImpl booksDao;
    private final int BOOK_1_ID = 1;
    private final Book BOOK_1 = new Book.Builder()
            .id(BOOK_1_ID)
            .name("Что делать?")
            .author("Николай Чернышевский")
            .type("Художественная литература")
            .genre("Роман")
            .publishingHouse("Современник")
            .yearOfIssue(1863)
            .numberOfPages(580)
            .language("русский")
            .description("Внешний сюжет романа — любовная история, однако в нём отражены новые экономические, философские и социальные идеи времени. Роман пронизан намёками на грядущую революцию.")
            .build();

    private final Book BOOK_2 = new Book.Builder()
            .id(2)
            .name("Бытие и ничто")
            .author("Жан Поль Сартр")
            .type("Философия")
            .genre("Философское эссе")
            .publishingHouse("АСТ Москва")
            .yearOfIssue(1943)
            .numberOfPages(928)
            .language("русский")
            .description("Во введении Сартр набросал собственную теорию сознания, бытия и феноменов при помощи критики как предшествующих феноменологов (прежде всего Гуссерля и Хайдеггера), так и других течений, идеализма, рационализма, и эмпиризма.")
            .build();




    @Before
    public void setUp() throws Exception {
        GenericXmlApplicationContext context = new GenericXmlApplicationContext();
        ConfigurableEnvironment environment = context.getEnvironment();
        environment.addActiveProfile("test");
        context.load("ru.itis\\spring\\context.xml");
        context.refresh();
        booksDao = context.getBean(BooksNamedJdbcTemplateDaoImpl.class);
    }

    @Test
    public void testFind() throws Exception {
        Book expected = BOOK_1;
        Book actual = booksDao.find(BOOK_1_ID);

        assertEquals(expected, actual);
    }

    @Test
    public void testFindByName() throws Exception {
        Book expected = BOOK_1;
        List<Book> book = booksDao.findByName("Что делать?");
        Book actual = book.get(0);

        assertEquals(expected, actual);
    }

    @Test
    public void testFindByAuthor() throws Exception {
        Book expected = BOOK_1;
        List<Book> book = booksDao.findByAuthor("Николай Чернышевский");
        Book actual = book.get(0);

        assertEquals(expected, actual);
    }

    @Test
    public void testFindByType() throws Exception {
        Book expected = BOOK_1;
        List<Book> book = booksDao.findByType("Художественная литература");
        Book actual = book.get(0);

        assertEquals(expected, actual);
    }

    @Test
    public void findByGenre() throws Exception {
        Book expected = BOOK_1;
        List<Book> book = booksDao.findByGenre("Роман");
        Book actual = book.get(0);

        assertEquals(expected, actual);
    }

    @Test
    public void findByYearOfIssue() throws Exception {
        Book expected = BOOK_1;
        List<Book> book = booksDao.findByYearOfIssue(1863);
        Book actual = book.get(0);

        assertEquals(expected, actual);
    }

    @Test
    public void testSave() throws Exception {
        Book expected = BOOK_1;
        int bookId = booksDao.save(BOOK_1);
        Book actual = booksDao.find(2);

        assertEquals(expected, actual);
    }

    @Test
    public void update() throws Exception {
        Book expected = BOOK_1;
        booksDao.update(BOOK_1);
        Book actual = booksDao.find(BOOK_1_ID);

        assertEquals(expected, actual);
    }

    @Test(expected = EmptyResultDataAccessException.class)
    public void testDelete()  {
        booksDao.delete(BOOK_1_ID);
        booksDao.find(BOOK_1_ID);
    }

    @Test
    public void findAll() throws Exception {

    }

}

