package com.google.sps.servlets;

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.gson.*;
import com.google.sps.models.Database;
import com.google.sps.models.User;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/User")
public class UserServlet extends HttpServlet {
  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    UserService userService = UserServiceFactory.getUserService();
    if (userService.isUserLoggedIn()) {
      HttpSession session = request.getSession(true);

      String userEmail = userService.getCurrentUser().getEmail();
      String nickname = userEmail.substring(0, userEmail.indexOf("@"));

      User user = Database.logInUser(userEmail, nickname);
      session.setAttribute("userID", user.getUserID());

      response.sendRedirect("/user-home.jsp");

    } else {
      request.getSession(false).invalidate();
      response.sendRedirect("/");
    }
  }
}