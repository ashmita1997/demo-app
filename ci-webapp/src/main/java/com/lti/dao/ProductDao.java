package com.lti.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.lti.entity.Product;

public class ProductDao {
	
	public Product select (int id) {
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try
		{
			
			conn = ConnManager.connect();
			String sql = "SELECT * FROM tbl_product WHERE id = ?";
			pst = conn.prepareStatement(sql);
			pst.setInt(1,id);
			rs = pst.executeQuery();
			if(rs.next()) {
				Product product = new Product();
				product.setId(rs.getInt("id"));
				product.setName(rs.getString("name"));
				product.setPrice(rs.getDouble("price"));
				return product;
			}
			return null;
		}
		catch(Exception e) {
			e.printStackTrace();
			return null;
		}
		finally {
			try {conn.close();} catch(Exception e) {}
		}
		
	}
	public List<Product> selectAll(){
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try
		{
			conn = ConnManager.connect();
			String sql = "SELECT * FROM tbl_product";
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();
			List<Product> list = new ArrayList<>();			while(rs.next()) {
				Product product = new Product();
				product.setId(rs.getInt("id"));
				product.setName(rs.getString("name"));
				product.setPrice(rs.getDouble("price"));
				list.add(product);
			}
			return list;
		}
		catch(Exception e) {
			e.printStackTrace();
			return null;
		}
		finally {
			try {conn.close();} catch(Exception e) {}
		}
		
		
		
	}
	public void insert(Product product) {
		Connection conn = null;
		PreparedStatement pst = null;
		try
		{
			conn = ConnManager.connect();
			pst = conn.prepareStatement("INSERT INTO tbl_product VALUES(?,?,?)");
			pst.setInt(1,product.getId());
			pst.setString(2,product.getName());
			pst.setDouble(3,product.getPrice());
			pst.executeUpdate();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			try {conn.close();} catch(Exception e) {}
		}
		//Loading the JDBC driver
	}
		
		
	}

