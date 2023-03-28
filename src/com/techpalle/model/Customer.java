package com.techpalle.model;

public class Customer {

	private int id;
	private String name;
	private String email;
	private long mobail;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public long getMobail() {
		return mobail;
	}
	public void setMobail(long mobail) {
		this.mobail = mobail;
	}
	//update operation and display operation
	public Customer(int id, String name, String email, long mobail) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.mobail = mobail;
	}
	//insert operation
	public Customer(String name, String email, long mobail) {
		super();
		this.name = name;
		this.email = email;
		this.mobail = mobail;
	}
	
	
	
}
