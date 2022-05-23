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

import com.ITPM.DAO.LoansDAO;
import com.ITPM.Model.Loans;


/**
 * Servlet implementation class LoansServlet
 */
@WebServlet("/")
public class LoansServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private LoansDAO loansDAO;
	
	public void init() {
		loansDAO = new LoansDAO();
	}
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoansServlet() {
      
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getServletPath();

		try {
			switch (action) {
			case "/newLoan":
				showNewForm1(request, response);
				break;
			case "/insertloan":
				insertUser1(request, response);
				break;
			case "/deleteloan":
				deleteUser1(request, response);
				break;
			case "/editloan":
				showEditForm1(request, response);
				break;
			case "/updateloan":
				updateUser1(request, response);
				break;
			case "/getuserloan":
				getUser1(request, response);
				break;
			default:
				listUser1(request, response);
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
	
	private void listUser1(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<Loans> listUser = loansDAO.selectAllUsers();
		request.setAttribute("listUserLoan", listUser);
		RequestDispatcher dispatcher = request.getRequestDispatcher("user-list-loans.jsp");
		dispatcher.forward(request, response);
	}
	
	private void getUser1(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		int id = Integer.parseInt(request.getParameter("id"));
		Loans existingUser = loansDAO.selectUser(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("SelectLeaves.jsp");
		request.setAttribute("userLoans", existingUser);
		dispatcher.forward(request, response);
	}


	private void showNewForm1(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("loan-form.jsp");
		dispatcher.forward(request, response);
	}

	private void showEditForm1(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		Loans existingUser = loansDAO.selectUser(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("loan-form.jsp");
		request.setAttribute("userLoans", existingUser);
		dispatcher.forward(request, response);
	

	}
	
	private void insertUser1(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int eid = Integer.parseInt(request.getParameter("eid"));
		String loneType = request.getParameter("loneType");
		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");
		int total = Integer.parseInt(request.getParameter("total"));
		Loans loans = new Loans(eid,loneType,startDate,endDate,total);
		loansDAO.insertUser(loans);
		response.sendRedirect("listLoans");
	}

	private void updateUser1(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		int eid = Integer.parseInt(request.getParameter("eid"));
		String loneType = request.getParameter("loneType");
		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");
		int total = Integer.parseInt(request.getParameter("total"));

		Loans loans = new Loans(id,eid,loneType,startDate,endDate,total);
		loansDAO.updateUser(loans);
		response.sendRedirect("listLoans");
	}

	private void deleteUser1(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		loansDAO.deleteUser(id);
		response.sendRedirect("listLoans");

	}


}
