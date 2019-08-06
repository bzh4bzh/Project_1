package com.revature.beans;

public class Employee {
	private int id;					//unique id number
	private int reportsTo;			//unique id number of immidiate supervisor
	private int directSup;			//0 no, 1 YES
	private int departmentHead;		//0 no, 1 YES
	private int BenCon;				//0 not, 1 YES
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

	public Employee(int id, int reportsTo, int departmentHead, int benCon, String name, String username,
			String password, int department) {
		super();
		this.id = id;
		this.reportsTo = reportsTo;
		this.departmentHead = departmentHead;
		this.BenCon = benCon;
		this.name = name;
		this.username = username;
		this.password = password;
		this.department = department;
	}

	public Employee(int id, int reportsTo, String name, String username, String password) {
		super();
		this.id = id;
		this.reportsTo = reportsTo;
		this.name = name;
		this.username = username;
		this.password = password;
		this.departmentHead = 0;
		this.BenCon = 0;
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

	public int getDirectSup() {
		return directSup;
	}

	public void setDirectSup(int directSup) {
		this.directSup = directSup;
	}

	public int getDepartmentHead() {
		return departmentHead;
	}

	public void setDepartmentHead(int departmentHead) {
		this.departmentHead = departmentHead;
	}

	public int getBenCon() {
		return BenCon;
	}

	public void setBenCon(int benCon) {
		BenCon = benCon;
	}
	
	
	

}
