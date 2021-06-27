package web;

import pojo.Book;
import pojo.Page;
import service.BookService;
import service.impl.BookServiceImpl;
import utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * @Author Rookie
 * @Date 2021/6/24
 */
public class BookServlet extends BaseServlet{
    private BookService bookService = new BookServiceImpl();
    protected void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, InvocationTargetException, IllegalAccessException {
        Book book = WebUtils.copyParamToBean(req.getParameterMap(),new Book());
        bookService.addBook(book);
        resp.sendRedirect(req.getContextPath()+"/manager/bookServlet?action=page&pageNo="+req.getParameter("pageNo"));
    }
    protected void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        bookService.deleteBookById(Integer.parseInt(id));
        resp.sendRedirect(req.getContextPath()+"/manager/bookServlet?action=page&pageNo="+req.getParameter("pageNo"));
    }
    protected void getBook(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        req.setAttribute("book",bookService.queryBookById(id));
        req.getRequestDispatcher("/pages/manager/book_edit.jsp").forward(req,resp);
    }
    protected void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, InvocationTargetException, IllegalAccessException {
        Book book = WebUtils.copyParamToBean(req.getParameterMap(),new Book());
        bookService.updateBook(book);
        resp.sendRedirect(req.getContextPath()+"/manager/bookServlet?action=page&pageNo="+req.getParameter("pageNo"));
    }
    protected void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Book> list = bookService.queryBooks();
        req.setAttribute("books",list);
        req.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(req,resp);
    }
    protected void page(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, InvocationTargetException, IllegalAccessException {
        int pageNo = Integer.parseInt(req.getParameter("pageNo")==null?"1":req.getParameter("pageNo"));
        int pageSize = Integer.parseInt(req.getParameter("pageSize")==null?String.valueOf(Page.PAGE_SIZE):req.getParameter("pageSize"));
        Page<Book> page = bookService.queryPage(pageNo,pageSize);
        page.setUrl("manager/bookServlet?action=page");
        req.setAttribute("page",page);
        req.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(req,resp);
    }
}
