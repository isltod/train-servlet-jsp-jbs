package com.mycompany.ordersystem.controller;

import com.mycompany.ordersystem.domain.Customer;
import com.mycompany.ordersystem.domain.Order;
import com.mycompany.ordersystem.domain.OrderItem;
import com.mycompany.ordersystem.domain.Product;
import com.mycompany.ordersystem.order.service.OrderServiceImpl;
import com.mycompany.ordersystem.server.OrderSystemService;
import com.mycompany.ordersystem.services.CustomerService;
import com.mycompany.ordersystem.services.OrderService;
import com.mycompany.ordersystem.services.ProductService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "orderServlet", value = "/order")
public class OrderServlet extends HttpServlet {
    private OrderService orderService = OrderSystemService.createInstance().getOrderService();
    private CustomerService customerService = OrderSystemService.createInstance().getCustomerService();
    private ProductService productService = OrderSystemService.createInstance().getProductService();

    @Override
    protected void doGet(
            HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(
            HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        // 한글 출력 깨져서 최후의 방법으로...
        System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8.name()));
        final String pre = "/order/";
        final String post = ".jsp";
        String action = request.getParameter("action");
        // 근데 이건 왜 해야 하지? 널로 들어오는 경우는 없을텐데...
        if (action == null) action = "list";
        Order order;
        List<Order> orders;
        Customer customer;
        List<Customer> customers;
        Product product;
        List<Product> products;
        String customer_name, customer_id;
        switch (action) {
            case "create_order":
                // 주문 생성할 때 주문은 일단 빈 주문으로 만들어 둬야 한다? 왜지? 나중에 edit과 함께 쓰려고 그러나?
                customer = new Customer();
                List<OrderItem> items = new ArrayList<>();
                order = new Order(customer, items);
                // 근데 여기서 저장 안하면 어떻게 된다는 거냐?
                session.setAttribute("order", order);
                products = productService.getProducts();
                // 그러니까 여기서 세션이 아니라, 아래처럼 request에 저장해도 가긴 가는데...
                // request.setAttribute("products", products);
                session.setAttribute("products", products);
                break;
            case "input_customer":
                customer_name = request.getParameter("customer_name");
                customers = customerService.getCustomersByName(customer_name);
                product = new Product();
                request.setAttribute("customers", customers);
                // 이렇게 서버로 왔다가 같은 페이지로 되돌리는 건 좀 그렇구나...그래서 SPA라는 걸...
                action = "create_order";
                break;
            case "cancel_order":
                order = (Order) session.getAttribute("order");
                order.setCustomer(new Customer());
                order.getItems().clear();
                break;
            case "select_customer":
                customer_id = request.getParameter("customer_id");
                customer = customerService.getCustomer(Long.valueOf(customer_id));
                order = (Order) session.getAttribute("order");
                // 참조 객체라서 그런가 얻어오기만 했는데도 세션 안의 주문도 바뀐다...
                order.setCustomer(customer);
                action = "add_to_cart";
                break;
            case "add_to_cart":
                // select 태그의 이름이 product였으니까...
                String product_id = request.getParameter("product");
                Long quantity = Long.valueOf(request.getParameter("quantity"));
                product = productService.getProduct(Long.valueOf(product_id));
                OrderItem orderItem = new OrderItem(product, quantity);
                order = (Order) session.getAttribute("order");
                order.getItems().add(orderItem);
                break;
            case "remove_from_cart":
                // 주문 여기서 지우면 세션도 지워지니까...여기서 지우고 되돌려 보내면 된다...
                int index = Integer.valueOf(request.getParameter("index"));
                order = (Order) session.getAttribute("order");
                order.getItems().remove(index);
                action = "add_to_cart";
                break;
            case "place_order":
                // 이건 그냥 주문확인서로 보낸다는 말인데...세션에 다 들어있어서 아무것도 안해도 괜찮은건가?
                break;
            case "confirm_order":
                order = (Order) session.getAttribute("order");
                orderService.purchaseOrder(order);
                session.invalidate();
                break;
            case "list_order":
                // 주문 목록 보여주는 페이지가 원래 빈 채로 시작하도록 되어 있어서...
                session.invalidate();
                break;
            case "list_order_input_customer":
                customer_name = request.getParameter("customer_name");
                customers = customerService.getCustomersByName(customer_name);
                request.setAttribute("customers", customers);
                // 이 orders는 위에 order랑 다르게 목록 관련 세션 변수
                session.removeAttribute("orders");
                action = "list_order";
                break;
            case "list_order_select_customer":
                customer_id = request.getParameter("customer_id");
                customer = customerService.getCustomer(Long.valueOf(customer_id));
                session.setAttribute("customer", customer);
                orders = orderService.getOrders(customer);
                session.setAttribute("orders", orders);
                // 이건 위에 case "list_order":로 가는게 아니라 list_order.jsp로 간다...
                // 페이지 이동 action과 명령어 action이 구분되질 않아서...헛갈린다...
                action = "list_order";
                break;
            case "list_cancel_order":
                String order_id = request.getParameter("order_id");
                order = orderService.getOrder(Long.valueOf(order_id));
                orderService.cancelOrder(order);
                // 이 경우는 세션 orders, 여기 orders 외에 디비 orders를 따로 지워줘야 한다...그래서..
                index = Integer.valueOf(request.getParameter("index"));
                orders = (List<Order>) session.getAttribute("orders");
                // 이렇게 하면 여기와 세션 orders 같이 지워지고...
                orders.remove(index);
                // 이건 위에 case "list_order":로 가는게 아니라 list_order.jsp로 간다...
                // 페이지 이동 action과 명령어 action이 구분되질 않아서...헛갈린다...
                action = "list_order";
                break;
            case "show_order":
                order_id = request.getParameter("order_id");
                order = orderService.getOrder(Long.valueOf(order_id));
                request.setAttribute("order", order);
                break;
            default:
                return;
        }

        String uri = pre + action + post;
        request.getRequestDispatcher(uri).forward(request, response);
    }
}
