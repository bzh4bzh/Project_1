package com.revature.beans;

public class Employee {
	private int id;					//unique id number
	private int reportsTo;			//unique id number of immidiate supervisor
	private int authority;			//user authourity
	private String name;			//name
	private String username;		//username
	private String password;		//pword
	private int department;			// type of department
	private double remainingBalance;
	
	
	
	public int getDepartment() {
		return department;
	}

	public void setDepartment(int department) {
		this.department = department;
	}

	public Employee(int id, String name, String username,
			String password, int reportsTo, int authority, int department, double remainingBal) {
		super();
		this.id = id;
		this.reportsTo = reportsTo;
		this.authority = authority; 
		this.name = name;
		this.username = username;
		this.password = password;
		this.department = department;
		this.remainingBalance = remainingBal;
	}

	public Employee(int id, int reportsTo, String name, String username, String password) {
		super();
		this.id = id;
		this.reportsTo = reportsTo;
		this.name = name;
		this.username = username;
		this.password = password;

	}

	
	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getReportsTo() {
		return reportsTo;
	}

	public void setReportsTo(int reportsTo) {
		this.reportsTo = reportsTo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getAuthority() {
		return authority;
	}

	public void setAuthority(int authority) {
		this.authority = authority;
	}

	public double getRemainingBalance() {
		return remainingBalance;
	}

	public void setRemainingBalance(double remainingBalance) {
		this.remainingBalance = remainingBalance;
	}

	
	
	
	

}
