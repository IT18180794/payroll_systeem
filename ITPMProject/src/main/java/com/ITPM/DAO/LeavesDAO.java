package com.ITPM.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ITPM.Model.Leaves;


public class LeavesDAO {
	
	private String jdbcURL = "jdbc:mysql://localhost:3306/itpm_assignment?serverTimezone=UTC";
	private String jdbcUsername = "root";
	private String jdbcPassword = "";

	private static final String INSERT_USERS_SQL = "INSERT INTO leaves" + "  (department, leavetype, evidence, date, availableLeaves) VALUES "
			+ " (?, ?, ?, ?, ?);";

	private static final String SELECT_USER_BY_ID = "select id,department,leavetype,evidence,date,availableLeaves from leaves where id =?";
	private static final String SELECT_ALL_USERS = "select * from leaves";
	private static final String DELETE_USERS_SQL = "delete from leaves where id = ?;";
	private static final String UPDATE_USERS_SQL = "update leaves set department = ?, leavetype = ?, evidence = ?, date= ?, availableLeaves =? where id = ?;";

	public LeavesDAO() {
	}

	protected Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;
	}

	public void insertUser(Leaves leaves) throws SQLException {
		System.out.println(INSERT_USERS_SQL);
		// try-with-resource statement will auto close the connection.
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL)) {
			preparedStatement.setString(1, leaves.getDepartment());
			preparedStatement.setString(2, leaves.getLeavetype());
			preparedStatement.setString(3, leaves.getEvidence());
			preparedStatement.setString(4, leaves.getDate());
			preparedStatement.setInt(5, leaves.getAvailableLeaves());
			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			printSQLException(e);
		}
	}

	public Leaves selectUser(int id) {
		Leaves leaves = null;
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();
				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_ID);) {
			preparedStatement.setInt(1, id);
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();
		
			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				String department = rs.getString("department");
				String leavetype = rs.getString("leavetype");
				String evidence = rs.getString("evidence");
				String date = rs.getString("date");
				int availableLeaves = rs.getInt("availableLeaves");
				leaves = new Leaves(id, department, leavetype, evidence, date, availableLeaves);
				
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return leaves;
	}

	public List<Leaves> selectAllUsers() {

		// using try-with-resources to avoid closing resources (boiler plate code)
		List<Leaves> leaves = new ArrayList<>();
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();

				// Step 2:Create a statement using connection object
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_USERS);) {
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				int id = rs.getInt("id");
				String department = rs.getString("department");
				String leavetype = rs.getString("leavetype");
				String evidence = rs.getString("evidence");
				String date = rs.getString("date");
				int availableLeaves = rs.getInt("availableLeaves");
				leaves.add(new Leaves(id, department, leavetype, evidence, date, availableLeaves));
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return leaves;
	}

	public boolean deleteUser(int id) throws SQLException {
		boolean rowDeleted;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_USERS_SQL);) {
			statement.setInt(1, id);
			rowDeleted = statement.executeUpdate() > 0;
		}
		return rowDeleted;
	}

	public boolean updateUser(Leaves leaves) throws SQLException {
		boolean rowUpdated;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_USERS_SQL);) {
			System.out.println("updated USer:"+statement);
			statement.setString(1, leaves.getDepartment());
			statement.setString(2, leaves.getLeavetype());
			statement.setString(3, leaves.getEvidence());
			statement.setString(4, leaves.getDate());
			statement.setInt(5, leaves.getAvailableLeaves());
			statement.setInt(6, leaves.getId());

			rowUpdated = statement.executeUpdate() > 0;
		}
		return rowUpdated;
	}

	private void printSQLException(SQLException ex) {
		for (Throwable e : ex) {
			if (e instanceof SQLException) {
				e.printStackTrace(System.err);
				System.err.println("SQLState: " + ((SQLException) e).getSQLState());
				System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
				System.err.println("Message: " + e.getMessage());
				Throwable t = ex.getCause();
				while (t != null) {
					System.out.println("Cause: " + t);
					t = t.getCause();
				}
			}
		}
	}

}
