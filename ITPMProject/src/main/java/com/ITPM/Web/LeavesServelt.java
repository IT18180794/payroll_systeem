package com.ITPM.Web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ITPM.DAO.LeavesDAO;
import com.ITPM.Model.Leaves;
/**
 * Servlet implementation class LeavesServelt
 */
@WebServlet("/")
public class LeavesServelt extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private LeavesDAO leavesDAO;
	
	public void init() {
		leavesDAO = new LeavesDAO();
	}
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LeavesServelt() {
      
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getServletPath();

		try {
			switch (action) {
			case "/newLeave":
				showNewForm(request, response);
				break;
			case "/insertleave":
				insertUser(request, response);
				break;
			case "/deleteleave":
				deleteUser(request, response);
				break;
			case "/editleave":
				showEditForm(request, response);
				break;
			case "/updateleave":
				updateUser(request, response);
				break;
			case "/searchleave":
				searchUser(request, response);
				break;
			case "/getuserleave":
				getUser(request, response);
				break;
			default:
				listUser(request, response);
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	private void listUser(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<Leaves> listUser = leavesDAO.selectAllUsers();
		request.setAttribute("listUserLeave", listUser);
		RequestDispatcher dispatcher = request.getRequestDispatcher("user-list-leaves.jsp");
		dispatcher.forward(request, response);
	}
	
	private void getUser(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		int id = Integer.parseInt(request.getParameter("id"));
		Leaves existingUser = leavesDAO.selectUser(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("SelectLeaves.jsp");
		request.setAttribute("userLeave", existingUser);
		dispatcher.forward(request, response);
	}


	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("leave-form.jsp");
		dispatcher.forward(request, response);
	}

	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		Leaves existingUser = leavesDAO.selectUser(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("leave-form.jsp");
		request.setAttribute("userLeave", existingUser);
		dispatcher.forward(request, response);
	

	}
	
	private void searchUser(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("SelectLeaves.jsp");
		dispatcher.forward(request, response);

	}

	private void insertUser(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		String department = request.getParameter("department");
		String leavetype = request.getParameter("leavetype");
		String evidence = request.getParameter("evidence");
		String date = request.getParameter("date");
		int availableLeaves = Integer.parseInt(request.getParameter("availableLeaves"));
		Leaves leave = new Leaves(department,leavetype,evidence,date,availableLeaves);
		leavesDAO.insertUser(leave);
		response.sendRedirect("listLeave");
	}

	private void updateUser(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String department = request.getParameter("department");
		String leavetype = request.getParameter("leavetype");
		String evidence = request.getParameter("evidence");
		String date = request.getParameter("date");
		int availableLeaves = Integer.parseInt(request.getParameter("availableLeaves"));

		Leaves leaves = new Leaves(id,department,leavetype,evidence,date,availableLeaves);
		leavesDAO.updateUser(leaves);
		response.sendRedirect("listLeave");
	}

	private void deleteUser(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		leavesDAO.deleteUser(id);
		response.sendRedirect("listLeave");

	}


}
