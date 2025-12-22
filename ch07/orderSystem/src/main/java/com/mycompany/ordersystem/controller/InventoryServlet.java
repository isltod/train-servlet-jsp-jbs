package com.mycompany.ordersystem.controller;

import com.mycompany.ordersystem.domain.Inventory;
import com.mycompany.ordersystem.domain.Product;
import com.mycompany.ordersystem.server.OrderSystemService;
import com.mycompany.ordersystem.services.InventoryService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "inventoryServlet", value = "/inventory")
public class InventoryServlet extends HttpServlet {

    private InventoryService inventoryService = OrderSystemService.createInstance().getInventoryService();

    @Override
    protected void doGet(
            HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final String pre = "/inventory/";
        final String post = ".jsp";
        String action = request.getParameter("action");
        // 근데 이건 왜 해야 하지? 널로 들어오는 경우는 없을텐데...
        if (action == null) action = "list";
        Inventory inventory;
        List<Inventory> inventories;
        switch (action) {
            case "list":
                inventories = inventoryService.getInventories();
                request.setAttribute("inventories", inventories);
                break;
            case "stock":
                // 제품과 연결해서 이름이나 설명 등은 따라가니까, 여기서는 재고 번호와 수량만...
                String id = request.getParameter("id");
                inventory = inventoryService.getInventory(Long.valueOf(id));
                request.setAttribute("inventory", inventory);
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
        String id, stock;
        String action = request.getParameter("action");
        switch (action) {
            case "stock":
                // 포스트로 왔으니까 다 getParameter
                id = request.getParameter("id");
                stock = request.getParameter("stock");
                inventoryService.stockInventory(Long.valueOf(id), Long.valueOf(stock));
                break;
            default:
        }
        request.getRequestDispatcher(uri).forward(request, response);
    }
}
