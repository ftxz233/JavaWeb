package dao.impl;

import dao.BookDao;
import pojo.Book;
import pojo.Page;

import java.util.List;

/**
 * @Author Rookie
 * @Date 2021/6/24
 */
public class BookDaoImpl extends BaseDao implements BookDao {
    public int addBook(Book book) {
        String sql = "insert into t_book(`id`,`name`,`author`,`price`,`sales`,`stock`,`img_path`)values(?,?,?,?,?,?,?)";
        return update(sql,book.getId(),book.getName(),book.getAuthor(),book.getPrice(),book.getSales(),book.getStock(),book.getImgPath());
    }

    public int deleteBookById(Integer id) {
        String sql = "delete from t_book where id = ?";
        return update(sql,id);
    }

    public int updateBook(Book book) {
        String sql = "update t_book set `name`=?,`author`=?,`price`=?,`sales`=?,`stock`=?,`img_path`=? where id = ?";
        return update(sql,book.getName(),book.getAuthor(),book.getPrice(),book.getSales(),book.getStock(),book.getImgPath(),book.getId());
    }

    public Book queryBookById(Integer id) {
        String sql = "select `id`,`name`,`author`,`price`,`sales`,`stock`,`img_path` imgPath from t_book where id = ?";
        return queryForOne(Book.class,sql,id);
    }

    public List<Book> queryBooks() {
        String sql = "select `id`,`name`,`author`,`price`,`sales`,`stock`,`img_path` imgPath from t_book";
        return queryForList(Book.class,sql);
    }

    public Integer queryCount() {
        String sql = "select count(*) from t_book";
        Number count = (Number) queryForSingleValue(sql);
        return count.intValue();
    }

    public List<Book> queryPage(Integer begin, Integer pageSize) {
        String sql = "select `id`,`name`,`author`,`price`,`sales`,`stock`,`img_path` imgPath from t_book limit ?,?";
        List<Book> list = queryForList(Book.class,sql,begin,pageSize);
        return list;
    }

    public Integer queryCountByPrice(int min, int max) {
        String sql = "select count(*) from t_book where price between ? and ?";
        Number count = (Number) queryForSingleValue(sql,min,max);
        return count.intValue();
    }

    public List<Book> queryPageByPrice(int begin, int pageSize, int min, int max) {
        String sql = "select `id`,`name`,`author`,`price`,`sales`,`stock`,`img_path` imgPath from t_book where price between ? and ? limit ?,?";
        List<Book> list = queryForList(Book.class,sql,min,max,begin,pageSize);
        return list;
    }
}
