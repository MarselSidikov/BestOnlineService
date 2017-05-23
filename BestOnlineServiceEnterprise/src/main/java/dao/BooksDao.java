package dao;

import models.Book;

import java.util.List;

/**
 * 11.05.2017
 *
 * @author Shaikhutdinov Artur (First Software Engineering Platform)
 * @version v1.0
 */
public interface BooksDao extends BaseDao<Book>{
    List<Book> findByName(String name);
    List<Book> findByAuthor(String author);
    List<Book> findByType(String type);
    List<Book> findByGenre(String genre);
    List<Book> findByYearOfIssue(int yearOfIssue);

}
