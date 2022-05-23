package com.ITPM.Model;

public class Loans {
	
	private int id;
	private int employeeID;
	private String loanType;
	private String startDate;
	private String endDate;
	private int total;
	

	public Loans(int id, int employeeID, String loanType, String startDate, String endDate, int total) {
		super();
		this.id = id;
		this.employeeID = employeeID;
		this.loanType = loanType;
		this.startDate = startDate;
		this.endDate = endDate;
		this.total = total;
	}


	public Loans(int employeeID, String loanType, String startDate, String endDate, int total) {
		super();
		this.employeeID = employeeID;
		this.loanType = loanType;
		this.startDate = startDate;
		this.endDate = endDate;
		this.total = total;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public int getEmployeeID() {
		return employeeID;
	}


	public void setEmployeeID(int employeeID) {
		this.employeeID = employeeID;
	}


	public String getLoanType() {
		return loanType;
	}


	public void setLoanType(String loanType) {
		this.loanType = loanType;
	}


	public String getStartDate() {
		return startDate;
	}


	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}


	public String getEndDate() {
		return endDate;
	}


	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}


	public int getTotal() {
		return total;
	}


	public void setTotal(int total) {
		this.total = total;
	}
	
	
	
	

}
