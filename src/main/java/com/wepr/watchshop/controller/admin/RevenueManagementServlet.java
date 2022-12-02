package com.wepr.watchshop.controller.admin;

import com.wepr.watchshop.service.ExcelService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

@WebServlet(name = "RevenueManagementServlet", value = "/revenue")
public class RevenueManagementServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = "/admin/revenueManagement.jsp";
        String action = request.getParameter("action");
        OrderDAO orderDAO = new OrderDAO();
        String page = request.getParameter("page");

        if(page == null)
            page = "1";

        int paging = Integer.parseInt(page);
        request.setAttribute("page", paging);

        //8 lines per page
        List<Order> orders = orderDAO.getAllOrderPaging(paging,8);
        request.setAttribute("ordersList", orders);
        getServletContext()
                .getRequestDispatcher(url)
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = "/admin/revenueManagement.jsp";
        String fromDate = request.getParameter("fromDate");
        String toDate = request.getParameter("toDate");
        OrderDAO orderDAO = new OrderDAO();
        String page = request.getParameter("page");

        if(page == null)
            page = "1";

        int paging = Integer.parseInt(page);
        request.setAttribute("page", paging);


        //8 lines per page
        List<Order> orders = null;
        try {
            orders = orderDAO.getAllOrderPagingByDateTime(paging, 8, fromDate, toDate);
            ExcelService excelService = new ExcelService();

            excelService.printExcel(orderDAO.getAllOrderPagingByDateTime(paging,null, fromDate,toDate));
            System.out.println("Print successfully");
        } catch (ParseException e) {
            System.out.println("Print error");
            throw new RuntimeException(e);
        }
        request.setAttribute("ordersList", orders);
        getServletContext()
                .getRequestDispatcher(url)
                .forward(request, response);

    }
}
