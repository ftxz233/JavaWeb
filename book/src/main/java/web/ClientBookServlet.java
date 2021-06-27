package web;

import pojo.Book;
import pojo.Page;
import service.BookService;
import service.impl.BookServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

/**
 * @Author Rookie
 * @Date 2021/6/25
 */
public class ClientBookServlet extends BaseServlet{
    private BookService bookService = new BookServiceImpl();
    protected void page(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, InvocationTargetException, IllegalAccessException {
        int pageNo = Integer.parseInt(req.getParameter("pageNo")==null?"1":req.getParameter("pageNo"));
        int pageSize = Integer.parseInt(req.getParameter("pageSize")==null?String.valueOf(Page.PAGE_SIZE):req.getParameter("pageSize"));
        Page<Book> page = bookService.queryPage(pageNo,pageSize);
        page.setUrl("client/bookServlet?action=page");
        req.setAttribute("page",page);
        req.getRequestDispatcher("/pages/client/index.jsp").forward(req,resp);
    }
    protected void pageByPrice(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, InvocationTargetException, IllegalAccessException {
        int pageNo = Integer.parseInt(req.getParameter("pageNo")==null?"1":req.getParameter("pageNo"));
        int pageSize = Integer.parseInt(req.getParameter("pageSize")==null?String.valueOf(Page.PAGE_SIZE):req.getParameter("pageSize"));
        int min = Integer.parseInt(req.getParameter("min").equals("")?"0":req.getParameter("min"));
        int max = Integer.parseInt(req.getParameter("max").equals("")?"10000":req.getParameter("max"));
        Page<Book> page = bookService.queryPageByPrice(pageNo,pageSize,min,max);
        StringBuilder sb = new StringBuilder("client/bookServlet?action=pageByPrice");
        sb.append("&min=").append(min);
        sb.append("&max=").append(max);
        page.setUrl(sb.toString());
        req.setAttribute("page",page);
        req.getRequestDispatcher("/pages/client/index.jsp").forward(req,resp);
    }
}
