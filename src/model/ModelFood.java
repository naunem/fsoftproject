/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import bean.Food;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

import library.LibraryDBConnect;
import view.PnFood;

/**
 *
 * @author HungPham
 */
public class ModelFood {

	LibraryDBConnect libraryDBConnect;
	Connection conn;
	Statement st;
	PreparedStatement pst;
	ResultSet rs;
	PnFood food;
	ResultSetMetaData rsmd;

	public ModelFood() {
		libraryDBConnect = new LibraryDBConnect();
	}

	public Food getFood(int idfood) {
		Food food = null;
		try {
			conn = libraryDBConnect.getConnectMySQL();
		} catch (ClassNotFoundException ex) {
			Logger.getLogger(ModelFood.class.getName()).log(Level.SEVERE, null, ex);
		} catch (SQLException ex) {
			Logger.getLogger(ModelFood.class.getName()).log(Level.SEVERE, null, ex);
		} catch (IOException ex) {
			Logger.getLogger(ModelFood.class.getName()).log(Level.SEVERE, null, ex);
		}

		String sql = "SELECT mafood, foodname, giaban FROM foodview where mafood= '" + idfood + "'";
		try {

			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				food = new Food(rs.getInt("mafood"), rs.getString("foodname"), rs.getInt("giaban"), "xxxxx");

			}
		} catch (SQLException ex) {
			Logger.getLogger(ModelFood.class.getName()).log(Level.SEVERE, null, ex);
		}
		return food;
	}

	public ArrayList<Food> getList(Food fitem) {

		ArrayList<Food> alItem = new ArrayList<Food>();

		try {
			conn = libraryDBConnect.getConnectMySQL();
		} catch (ClassNotFoundException ex) {
			Logger.getLogger(ModelFood.class.getName()).log(Level.SEVERE, null, ex);
		} catch (SQLException ex) {
			Logger.getLogger(ModelFood.class.getName()).log(Level.SEVERE, null, ex);
		} catch (IOException ex) {
			Logger.getLogger(ModelFood.class.getName()).log(Level.SEVERE, null, ex);
		}

		String sql = "SELECT mafood, foodname, giaban FROM foodview ";

		if (fitem != null) {
			int t = 0;
			if ((fitem.getMafood()) > 0) {
				t = 1;
				sql += " where mafood = " + fitem.getMafood();
			}
			if (!("").equals(fitem.getFoodname())) {
				if (t == 1)
					sql += " and foodname LIKE '%" + fitem.getFoodname() + "%'";
				else
					sql += " where foodname LIKE '%" + fitem.getFoodname() + "%'";
			}
		}
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				Food item = new Food(rs.getInt("mafood"), rs.getString("foodname"), rs.getInt("giaban"), "xmcnxmnc");
				alItem.add(item);
			}
		} catch (SQLException ex) {
			Logger.getLogger(ModelFood.class.getName()).log(Level.SEVERE, null, ex);
		}
		return alItem;

	}
	
	//check food name isexist
	public boolean isExist(Food fitem) {
		try {
			conn = libraryDBConnect.getConnectMySQL();
		} catch (ClassNotFoundException ex) {
			Logger.getLogger(ModelFood.class.getName()).log(Level.SEVERE, null, ex);
		} catch (SQLException ex) {
			Logger.getLogger(ModelFood.class.getName()).log(Level.SEVERE, null, ex);
		} catch (IOException ex) {
			Logger.getLogger(ModelFood.class.getName()).log(Level.SEVERE, null, ex);
		}

		String sql = "SELECT * FROM foodview where foodname='"+fitem.getFoodname()+"'";

		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			rsmd = rs.getMetaData();
			int num=0;
			while(rs.next()){
			  num++;
			  break;
			}
			System.out.println("toi se co"+num);
			if(num > 0){
				return true;
			}
		} catch (SQLException ex) {
			Logger.getLogger(ModelFood.class.getName()).log(Level.SEVERE, null, ex);
		}
		
		return false;
	}
	
	public boolean isExistEdit(Food fitem) {
		try {
			conn = libraryDBConnect.getConnectMySQL();
		} catch (ClassNotFoundException ex) {
			Logger.getLogger(ModelFood.class.getName()).log(Level.SEVERE, null, ex);
		} catch (SQLException ex) {
			Logger.getLogger(ModelFood.class.getName()).log(Level.SEVERE, null, ex);
		} catch (IOException ex) {
			Logger.getLogger(ModelFood.class.getName()).log(Level.SEVERE, null, ex);
		}

		String sql = "SELECT * FROM foodview where foodname='"+fitem.getFoodname()+"'";

		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			rsmd = rs.getMetaData();
			int num=0;
			int id = 0;
			while(rs.next()){
			  num++;
			  id = rs.getInt(1);
			  break;
			}
			System.out.println("toi se co"+id);
			if(num > 0 && fitem.getMafood() != id){
				return true;
			}
		} catch (SQLException ex) {
			Logger.getLogger(ModelFood.class.getName()).log(Level.SEVERE, null, ex);
		}
		
		return false;
	}

	public int add(Food item) {
		try {
			conn = libraryDBConnect.getConnectMySQL();
		} catch (ClassNotFoundException ex) {
			Logger.getLogger(ModelChucVu.class.getName()).log(Level.SEVERE, null, ex);
		} catch (SQLException ex) {
			Logger.getLogger(ModelChucVu.class.getName()).log(Level.SEVERE, null, ex);
		} catch (IOException ex) {
			Logger.getLogger(ModelChucVu.class.getName()).log(Level.SEVERE, null, ex);
		}
		int result = 0;
		String sql = "INSERT INTO foodview(foodname,giaban) VALUES (?,?)";
		try {
			pst = (PreparedStatement) conn.prepareStatement(sql);
			pst.setString(1, item.getFoodname());
			pst.setFloat(2, item.getPrice());
			pst.executeUpdate();
			result = 1;
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Lỗi câu lệnh SQL");
			ex.printStackTrace();
		} finally {
			try {
				pst.close();
				conn.close();
			} catch (SQLException ex) {
				JOptionPane.showMessageDialog(null, "Lỗi đóng SQL");
				ex.printStackTrace();
			}
		}

		return result;
	}

	public int del(Food item) {
		try {
			conn = libraryDBConnect.getConnectMySQL();
		} catch (ClassNotFoundException ex) {
			Logger.getLogger(ModelFood.class.getName()).log(Level.SEVERE, null, ex);
		} catch (SQLException ex) {
			Logger.getLogger(ModelFood.class.getName()).log(Level.SEVERE, null, ex);
		} catch (IOException ex) {
			Logger.getLogger(ModelFood.class.getName()).log(Level.SEVERE, null, ex);
		}
		int result = 0;

		String sql = "DELETE FROM foodview WHERE mafood = ?";
		try {

			pst = (PreparedStatement) conn.prepareStatement(sql);
			pst.setInt(1, item.getMafood());
			pst.executeUpdate();
			result = 1;

		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Lỗi câu lệnh SQL");
			ex.printStackTrace();
		} finally {
			try {
				pst.close();

				conn.close();
			} catch (SQLException ex) {
				JOptionPane.showMessageDialog(null, "Lỗi đóng SQL");
				ex.printStackTrace();
			}
		}
		return result;
	}

	public int edit(Food item, int price_current) {
		try {
			conn = libraryDBConnect.getConnectMySQL();
		} catch (ClassNotFoundException ex) {
			Logger.getLogger(ModelFood.class.getName()).log(Level.SEVERE, null, ex);
		} catch (SQLException ex) {
			Logger.getLogger(ModelFood.class.getName()).log(Level.SEVERE, null, ex);
		} catch (IOException ex) {
			Logger.getLogger(ModelFood.class.getName()).log(Level.SEVERE, null, ex);
		}
		int result = 0;

		try {
			String sql = "UPDATE foodview SET foodname = ?, giaban=?";
			StringBuffer sb = new StringBuffer(sql);
			sb.append(" WHERE mafood = ?");
			sql = sb.toString();
			pst = (PreparedStatement) conn.prepareStatement(sql);
			pst.setString(1, item.getFoodname());
			pst.setFloat(2, item.getPrice());
			pst.setInt(3, item.getMafood());
			pst.executeUpdate();
			result = 1;
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Lỗi câu lệnh SQL");
			ex.printStackTrace();
		} finally {
			try {
				pst.close();
				conn.close();
			} catch (SQLException ex) {
				JOptionPane.showMessageDialog(null, "Lỗi đóng SQL");
				ex.printStackTrace();
			}
		}
		return result;
	}

	public void foodview() {

		try {
			conn = libraryDBConnect.getConnectMySQL();
		} catch (ClassNotFoundException ex) {
			Logger.getLogger(ModelChucVu.class.getName()).log(Level.SEVERE, null, ex);
		} catch (SQLException ex) {
			Logger.getLogger(ModelChucVu.class.getName()).log(Level.SEVERE, null, ex);
		} catch (IOException ex) {
			Logger.getLogger(ModelChucVu.class.getName()).log(Level.SEVERE, null, ex);
		}
		try {
			int result = 0;
			String sql = "CREATE foodview AS SELECT mafood, foodname, giaban FROM food";
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			st.close();
			rs.close();
		} catch (Exception e) {

		}
	}
}
