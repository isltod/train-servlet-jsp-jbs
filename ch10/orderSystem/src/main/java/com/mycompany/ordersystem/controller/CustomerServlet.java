package com.mycompany.ordersystem.controller;

import com.mycompany.ordersystem.domain.Customer;
import com.mycompany.ordersystem.server.OrderSystemService;
import com.mycompany.ordersystem.services.CustomerService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "customerServlet", value = "/customer")
public class CustomerServlet extends HttpServlet {

    private CustomerService customerService = OrderSystemService.createInstance().getCustomerService();

    @Override
    protected void doGet(
            HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final String pre = "/customer/";
        final String post = ".jsp";
        String action = request.getParameter("action");
        // 근데 이건 왜 해야 하지? 널로 들어오는 경우는 없을텐데...
        if (action == null) action = "list";
        Customer customer;
        List<Customer> customers;
        switch (action) {
            case "list":
                customers = customerService.getCustomers();
                request.setAttribute("customers", customers);
                break;
            case "edit":
                if (!request.isUserInRole("ROLE_USER")) {
                    action = "error";
                    break;
                }
                customer = new Customer();
                request.setAttribute("customer", customer);
                break;
            case "update":
                // 이건 이미 request에 고칠 사용자 정보가 다 들어있으니까 그냥 보내고 edit.jsp에서 처리한다?...
                action = "edit";
            case "delete":
                // 가 아니라 update면 여기도 들러서 id로 customer 객체를 받아 간다?
                String id = request.getParameter("id");
                customer = customerService.getCustomer(Integer.valueOf(id));
                request.setAttribute("customer", customer);
                break;
            case "logout":
                request.getSession().invalidate();
                response.sendRedirect("/");
                // 여긴 처리 없이 아래 return 문 만나야 하니까 break 없음
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
        String id, name, address, email;
        id = request.getParameter("id");
        String action = request.getParameter("action");
        switch (action) {
            case "save":
                // 포스트로 왔으니까 다 getParameter
                name = request.getParameter("name");
                address = request.getParameter("address");
                email = request.getParameter("email");
                Customer customer = new Customer();
                customer.setId(Long.valueOf(id));
                customer.setName(name);
                customer.setAddress(address);
                customer.setEmail(email);
                customerService.saveCustomer(customer);
                break;
            case "delete":
                Customer old = customerService.getCustomer(Long.valueOf(id));
                if (old != null) {
                    customerService.deleteCustomer(old.getId());
                }
                break;
            default:
        }
        request.getRequestDispatcher(uri).forward(request, response);
    }
}
