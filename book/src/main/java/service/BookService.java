package service;

import pojo.Book;
import pojo.Page;

import java.util.List;

/**
 * @Author Rookie
 * @Date 2021/6/24
 */
public interface BookService {
    public void addBook(Book book);
    public void deleteBookById(Integer id);
    public void updateBook(Book book);
    public Book queryBookById(Integer id);
    public List<Book> queryBooks();
    public Page<Book> queryPage(Integer pageNo,Integer pageSize);

    Page<Book> queryPageByPrice(int pageNo, int pageSize, int min, int max);
}
