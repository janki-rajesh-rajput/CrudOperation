package com.techpalle.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.techpalle.model.Customer;

public class CustomerDao {
	
	private static final String dbUrl="jdbc:mysql://localhost:3306/customer_management";
	private static final String dbUsername="root";
	private static final String dbPassword="janki@123";
	
	private static final String customerListQuery="select * from customer";
	
	private static final String customerInsertQuery="insert into customer(name,email,mobail) values(?,?,?)";
	
	private static final String customerEditQuery="select * from customer where id=?";
	
	private static final String customerUpdateQuery="update customer set name=?,email=?,mobail=? where id=?";
	
	private static final String customerDeleteQuery="delete from customer where id=?";
	
	private static Connection con=null;
	private static PreparedStatement ps=null;
	private static Statement stm=null;
	private static ResultSet rs=null;

	public static void deleteCustomer(int id)
	{
		try {
			con = getConnectionDef();
			
			ps=con.prepareStatement(customerDeleteQuery);
			
			ps.setInt(1, id);
			 
			ps.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			if(ps!=null)
			{
				try {
					ps.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(con!=null)
			{
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}	
		}

	}
	
	public static void editCustomer(Customer c)
	{
		try {
			con = getConnectionDef();
			
			ps=con.prepareStatement(customerUpdateQuery);
			
			ps.setString(1, c.getName());
			ps.setString(2, c.getEmail());
			ps.setLong(3, c.getMobail());
			ps.setInt(4, c.getId());
			
			ps.executeUpdate();
		}
		 catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		finally
		{
			if(ps!=null)
			{
				try {
					ps.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(con!=null)
			{
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}	
		}
	}
	
	public static Customer getOneCustomer(int id)
	{
		Customer c=null;
		try {
			con = getConnectionDef();
			
			ps=con.prepareStatement(customerEditQuery);
			
			ps.setInt(1, id);
			
			rs=ps.executeQuery();
			rs.next();
			
			int i=rs.getInt("id");
			String n=rs.getString("name");
			String e=rs.getString("email");
			long m=rs.getLong("mobail");
			
			 c=new Customer(i,n,e,m);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			if(rs!=null)
			{
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(ps!=null)
			{
				try {
					ps.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(con!=null)
			{
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return c;
	}

	public static void addCustomer(Customer customer)
	{
		try {
			con = getConnectionDef();
			ps=con.prepareStatement(customerInsertQuery);
			
			ps.setString(1,customer.getName());
			ps.setString(2, customer.getEmail());
			ps.setLong(3, customer.getMobail());
			
			ps.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			if(ps!=null)
			{
				try {
					ps.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(con!=null)
			{
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
	}
	
	public static Connection getConnectionDef()
	{
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection(dbUrl,dbUsername,dbPassword);

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return con;
		
	}
	public static ArrayList<Customer> getAllCustomer()
	{
		ArrayList<Customer> al=new ArrayList<Customer>();
		try {
			con=getConnectionDef();
			stm=con.createStatement();
			rs=stm.executeQuery(customerListQuery);
			while(rs.next())
			{
				int i=rs.getInt("id");
				String n=rs.getString("name");
				String e=rs.getString("email");
				long m=rs.getLong("mobail");
				
				Customer c=new Customer(i,n,e,m);
				
				al.add(c);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			if(rs!=null)
			{
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(stm!=null)
			{
				try {
					stm.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(con!=null)
			{
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return al;
	}
}
