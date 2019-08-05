package com.revature.beans;

public class Employee {
	private int id;			//unique id number
	private int reportsTo;	//unique id number of immidiate supervisor
	private String name;	//name
	private String username;
	private String password;
	
	public Employee(int id, int reportsTo, String name, String username, String password) {
		super();
		this.id = id;
		this.reportsTo = reportsTo;
		this.name = name;
		this.username = username;
		this.password = password;
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
	
	
	

}
