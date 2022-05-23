package com.ITPM.Model;

public class Leaves {
	
	private int id;
	private String department;
	private String leavetype;
	private String evidence;
	private String date;
	private int availableLeaves;
	
	
	public Leaves(int id, String department, String leavetype, String evidence, String date, int availableLeaves) {
		super();
		this.id = id;
		this.department = department;
		this.leavetype = leavetype;
		this.evidence = evidence;
		this.date = date;
		this.availableLeaves = availableLeaves;
	}


	public Leaves(String department, String leavetype, String evidence, String date, int availableLeaves) {
		super();
		this.department = department;
		this.leavetype = leavetype;
		this.evidence = evidence;
		this.date = date;
		this.availableLeaves = availableLeaves;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getDepartment() {
		return department;
	}


	public void setDepartment(String department) {
		this.department = department;
	}


	public String getLeavetype() {
		return leavetype;
	}


	public void setLeavetype(String leavetype) {
		this.leavetype = leavetype;
	}


	public String getEvidence() {
		return evidence;
	}


	public void setEvidence(String evidence) {
		this.evidence = evidence;
	}


	public String getDate() {
		return date;
	}


	public void setDate(String date) {
		this.date = date;
	}


	public int getAvailableLeaves() {
		return availableLeaves;
	}


	public void setAvailableLeaves(int availableLeaves) {
		this.availableLeaves = availableLeaves;
	}
	

}
