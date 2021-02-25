package controller;

import model.Product;
import service.product.IProductService;
import service.product.ProductService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/products")
public class ProductController extends HttpServlet {
    private static final IProductService productService = new ProductService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        if (action.equals("")) {
            showAllProduct(request, response);
        } else if (action.equals("update")) {
            Product product = productService.searchById(Integer.parseInt(request.getParameter("id")));
            request.setAttribute("product", product);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/product/update.jsp");
            dispatcher.forward(request, response);
        } else if (action.equals("remove")){
            productService.remove(Integer.parseInt(request.getParameter("id")));
            response.sendRedirect("/products");
        }
    }

    private void showAllProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Product> products = productService.getAll();
        request.setAttribute("products", products);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/product/list.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        if (action.equals("add")) {
            addProduct(request, response);
        } else if (action.equals("update")) {
            updateProduct(request, response);
        } else if (action.equals("search")){
            System.out.println("search run!");
            List<Product> products=productService.searchByName(request.getParameter("name"));
            request.setAttribute("products",products);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/product/list.jsp");
            dispatcher.forward(request, response);
        }
    }

    private void updateProduct(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        int price = Integer.parseInt(request.getParameter("price"));
        int amount = Integer.parseInt(request.getParameter("amount"));
        String color = request.getParameter("color");
        String description = request.getParameter("description");
        String category = request.getParameter("category");
        productService.update(id, new Product(-1, name, price, amount, color, description, category));
        response.sendRedirect("/products");
    }

    private void addProduct(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String name = request.getParameter("name");
        int price = Integer.parseInt(request.getParameter("price"));
        int amount = Integer.parseInt(request.getParameter("amount"));
        String color = request.getParameter("color");
        String description = request.getParameter("description");
        String category = request.getParameter("category");
        productService.add(new Product(-1, name, price, amount, color, description, category));
        response.sendRedirect("/products");
    }
}
