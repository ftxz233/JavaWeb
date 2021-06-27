package service.impl;

import dao.BookDao;
import dao.impl.BookDaoImpl;
import pojo.Book;
import pojo.Page;
import service.BookService;

import java.util.List;

/**
 * @Author Rookie
 * @Date 2021/6/24
 */
public class BookServiceImpl implements BookService {
    BookDao bookDao = new BookDaoImpl();

    public void addBook(Book book) {
        bookDao.addBook(book);
    }

    public void deleteBookById(Integer id) {
        bookDao.deleteBookById(id);
    }

    public void updateBook(Book book) {
        bookDao.updateBook(book);
    }

    public Book queryBookById(Integer id) {
        return bookDao.queryBookById(id);
    }

    public List<Book> queryBooks() {
        return bookDao.queryBooks();
    }

    public Page<Book> queryPage(Integer pageNo, Integer pageSize) {
        Page page = new Page();
        page.setPageSize(pageSize);
        Integer pageTotalCount = bookDao.queryCount();
        page.setPageTotalCount(pageTotalCount);
        Integer pageTotal = (int)Math.ceil((1.0*pageTotalCount/pageSize));
        page.setPageTotal(pageTotal);
        page.setPageNo(pageNo);
        List<Book> items = bookDao.queryPage((page.getPageNo()-1)*pageSize,pageSize);
        page.setItems(items);
        return page;
    }

    public Page<Book> queryPageByPrice(int pageNo, int pageSize, int min, int max) {
        Page page = new Page();
        page.setPageSize(pageSize);
        Integer pageTotalCount = bookDao.queryCountByPrice(min,max);
        page.setPageTotalCount(pageTotalCount);
        Integer pageTotal = (int)Math.ceil((1.0*pageTotalCount/pageSize));
        page.setPageTotal(pageTotal);
        page.setPageNo(pageNo);
        List<Book> items = bookDao.queryPageByPrice((page.getPageNo()-1)*pageSize,pageSize,min,max);
        page.setItems(items);
        return page;
    }
}
