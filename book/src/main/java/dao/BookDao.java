package dao;

import pojo.Book;
import pojo.Page;

import java.util.List;

/**
 * @Author Rookie
 * @Date 2021/6/24
 */
public interface BookDao {
    public int addBook(Book book);
    public int deleteBookById(Integer id);
    public int updateBook(Book book);
    public Book queryBookById(Integer id);
    public List<Book> queryBooks();
    public Integer queryCount();
    public List<Book> queryPage(Integer begin, Integer pageSize);

    Integer queryCountByPrice(int min, int max);

    List<Book> queryPageByPrice(int begin, int pageSize, int min, int max);
}
