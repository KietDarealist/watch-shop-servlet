package com.wepr.watchshop.controller.admin;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "CustomerManagementServlet", value = "/customer")
public class CustomerManagementServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = "/admin/customerManagement.jsp";
        UserDAO userDAO = new UserDAO();
        List<User> userList = userDAO.findAllUser();
        request.setAttribute("userList", userList);
        getServletContext()
                .getRequestDispatcher(url)
                .forward(request, response);
    }
}
