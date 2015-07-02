package com.javawebdemo.bigbest.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javawebdemo.bigbest.dao.UserDao;
import com.javawebdemo.bigbest.entities.User;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet(description = "用户servlet", urlPatterns = { "/user/*" })
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private User user;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		boolean userLogin = false;

		String islog;
		islog=request.getParameter("islog");
		if(islog==null){
			Cookie[] cookies = request.getCookies();
			for (int i = 0; i < cookies.length; i++) {
				System.out.println(cookies[i].getName() + ":"
						+ cookies[i].getValue());
				if (cookies[i].getName().equals("userName")) {
					userLogin = true;
					break;
				}
			}
		}else {
			Cookie[] cookies = request.getCookies();
			for (int i = 0; i < cookies.length; i++) {
				System.out.println(cookies[i].getName() + ":"
						+ cookies[i].getValue());
				if (cookies[i].getName().equals("userName")) {
					cookies[i].setMaxAge(0);
				}
				if (cookies[i].getName().equals("password")) {
					cookies[i].setMaxAge(0);
				}
			}
		}
	
		if (userLogin) {
			response.setContentType("text/html;charset=UTF-8");
			// this.getServletContext().setAttribute("user", this.user);
			RequestDispatcher reDis = request
					.getRequestDispatcher("/WEB-INF/views/welcome.jsp");
			reDis.forward(request, response);
		} else {

			this.user = null;
			String userName = null;
			String password = null;
			boolean rememberme = false;

			if (request.getParameter("userName") != null) {
				userName = request.getParameter("userName");
			}
			if (request.getParameter("password") != null) {
				password = request.getParameter("password");
			}

			user = new User(userName, password);
			UserDao userDao = new UserDao();
			try {
				user = userDao.userLogin(user);
			} catch (SQLException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
			if (this.user == null) {
				RequestDispatcher reDis = request
						.getRequestDispatcher("/WEB-INF/views/error.jsp");
				reDis.forward(request, response);
			} else {
				if (request.getParameter("remenberme") != null) {
					Cookie cookieUserName = new Cookie("userName",
							this.user.getName());
					Cookie cookiePassword = new Cookie("password",
							this.user.getPassword());
					cookieUserName.setMaxAge(100);
					cookiePassword.setMaxAge(100);
					response.addCookie(cookieUserName);
					response.addCookie(cookiePassword);
				}
				response.setContentType("text/html;charset=UTF-8");
				this.getServletContext().setAttribute("user", this.user);
				RequestDispatcher reDis = request
						.getRequestDispatcher("/WEB-INF/views/welcome.jsp");
				reDis.forward(request, response);
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
