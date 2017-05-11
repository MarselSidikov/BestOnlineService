package dao;

import models.Book;

import java.util.List;

/**
 * 11.05.2017
 *
 * @author Shaikhutdinov Artur (First Software Engineering Platform)
 * @version v1.0
 */
public interface BaseBookDao extends BaseDao{
    List<Book> findAllByName(int age);
    List<Book> findAllByAuthor(int author);
    List<Book> findAllBy(String type);

}
