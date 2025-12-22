package com.mycompany.ordersystem.controller;

import com.mycompany.ordersystem.domain.Customer;
import com.mycompany.ordersystem.domain.Product;
import com.mycompany.ordersystem.server.OrderSystemService;
import com.mycompany.ordersystem.services.OrderService;
import com.mycompany.ordersystem.services.ProductService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "productServlet", value = "/product")
public class ProductServlet extends HttpServlet {

    private ProductService productService = OrderSystemService.createInstance().getProductService();

    @Override
    protected void doGet(
            HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final String pre = "/product/";
        final String post = ".jsp";
        String action = request.getParameter("action");
        // 근데 이건 왜 해야 하지? 널로 들어오는 경우는 없을텐데...
        if (action == null) action = "list";
        Product product;
        List<Product> products;
        switch (action) {
            case "list":
                products = productService.getProducts();
                request.setAttribute("products", products);
                break;
            case "edit":
                product = new Product();
                request.setAttribute("product", product);
                break;
            case "update":
                // 이건 이미 request에 고칠 제품 정보가 다 들어있으니까 그냥 보내고 edit.jsp에서 처리한다?...
                action = "edit";
            case "delete":
                // 가 아니라 update면 여기도 들러서 id로 product 객체를 받아 간다?
                String id = request.getParameter("id");
                product = productService.getProduct(Long.valueOf(id));
                request.setAttribute("product", product);
                break;
            default:
                return;
        }

        String uri = pre + action + post;
        request.getRequestDispatcher(uri).forward(request, response);
    }

    @Override
    protected void doPost(
            HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = "/index.jsp";
        String id, name, description, price;
        id = request.getParameter("id");
        String action = request.getParameter("action");
        switch (action) {
            case "save":
                // 포스트로 왔으니까 다 getParameter
                name = request.getParameter("name");
                description = request.getParameter("description");
                price = request.getParameter("price");
                Product product = new Product();
                product.setId(Long.valueOf(id));
                product.setName(name);
                product.setDescription(description);
                product.setPrice(Long.valueOf(price));
                productService.saveProduct(product);
                break;
            case "delete":
                Product old = productService.getProduct(Long.valueOf(id));
                System.out.println(old.toString());
                if (old != null) {
                    productService.deleteProduct(old.getId());
                }
                break;
            default:
        }
        request.getRequestDispatcher(uri).forward(request, response);
    }
}
