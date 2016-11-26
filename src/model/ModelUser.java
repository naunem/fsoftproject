/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import bean.Food;
import bean.User;
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

/**
 *
 * @author HungPham
 */
public class ModelUser {

	Connection con;
	Statement st;
	PreparedStatement pst;
	ResultSet rs;
	ResultSetMetaData rsmd;
	LibraryDBConnect libraryDBConnect = null;
	public ModelUser() {
		libraryDBConnect = new LibraryDBConnect();
	}

	public ArrayList<User> getList(User tfitem) throws ClassNotFoundException, SQLException, IOException {
		con = libraryDBConnect.getConnectMySQL();
		ArrayList<User> alItem = new ArrayList<User>();
		String sql = "SELECT * FROM nhanvien INNER JOIN chucvu ON nhanvien.machucvu = chucvu.machucvu ";
		int t = 0;
		if (tfitem != null) {

			if (!"".equals(tfitem.getUsername())) {
				t = 1;
				sql += "WHERE username LIKE '%" + tfitem.getUsername() + "%'";
			}
			if (!("").equals(tfitem.getFullname())) {
				if (t == 1)
					sql += " and fullname LIKE '%" + tfitem.getFullname() + "%'";
				else
					sql += "WHERE fullname LIKE '%" + tfitem.getFullname() + "%'";
			}

		}
		System.out.println(sql);
		try {
			st = con.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				User item = new User(rs.getInt("id"),rs.getInt("machucvu"), rs.getString("username"), rs.getString("tenchucvu"),
						rs.getString("password"), rs.getString("fullname"), rs.getString("address"),
						rs.getString("email"));
				alItem.add(item);
			}
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "Lỗi câu lệnh SQL");
			ex.printStackTrace();
		} finally {
			try {
				rs.close();
				st.close();
				con.close();
			} catch (SQLException ex) {
				JOptionPane.showMessageDialog(null, "Lỗi đóng SQL");
				ex.printStackTrace();
			}
		}
		return alItem;
	}

	public boolean isExist(User fitem) {
		try {
			con = libraryDBConnect.getConnectMySQL();
		} catch (ClassNotFoundException ex) {
			Logger.getLogger(ModelFood.class.getName()).log(Level.SEVERE, null, ex);
		} catch (SQLException ex) {
			Logger.getLogger(ModelFood.class.getName()).log(Level.SEVERE, null, ex);
		} catch (IOException ex) {
			Logger.getLogger(ModelFood.class.getName()).log(Level.SEVERE, null, ex);
		}

		String sql = "SELECT * FROM nhanvien where username='" + fitem.getUsername() + "'";
		System.out.println("naunem : "+sql);
		try {
			st = con.createStatement();
			rs = st.executeQuery(sql);
			rsmd = rs.getMetaData();
			int num = 0;
			int id = 0;
			while (rs.next()) {
				num++;
				id = rs.getInt(1);
				break;
			}
			System.out.println("toi se co : " + id);
			System.out.println("toi se co1 : " + fitem.getId());
			if (num > 0 && fitem.getId() != id) {
				return true;
			}
		} catch (SQLException ex) {
			Logger.getLogger(ModelFood.class.getName()).log(Level.SEVERE, null, ex);
		}

		return false;
	}

	public User getUserByUser(String username) throws ClassNotFoundException, SQLException, IOException {
		con = libraryDBConnect.getConnectMySQL();
		User item = null;
		String sql = "SELECT * FROM nhanvien INNER JOIN chucvu ON nhanvien.machucvu = chucvu.machucvu WHERE username = '"
				+ username + "'";
		System.out.println(sql);
		try {
			st = con.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				item = new User(rs.getInt("id"),rs.getInt("machucvu"), rs.getString("username"), rs.getString("tenchucvu"),
						rs.getString("password"), rs.getString("fullname"), rs.getString("address"),
						rs.getString("email"));

			}
		} catch (Exception ex) {
			// JOptionPane.showMessageDialog(null, "Lỗi câu lệnh SQL");
			ex.printStackTrace();
		} finally {
			try {
				rs.close();
				st.close();
				con.close();
			} catch (SQLException ex) {
				JOptionPane.showMessageDialog(null, "Lỗi đóng SQL");
				ex.printStackTrace();
			}
		}
		return item;
	}

	public int add(User item) throws ClassNotFoundException, IOException, SQLException {
		con = libraryDBConnect.getConnectMySQL();
		int result = 0;
		String sql = "INSERT INTO nhanvien(username,password,fullname,"
				+ "machucvu,address, email) values (?,?,?,?,?,?)";
		try {

			pst = (PreparedStatement) con.prepareStatement(sql);
			pst.setString(1, item.getUsername());
			pst.setString(2, item.getPassword());
			pst.setString(3, item.getFullname());
			pst.setInt(4, item.getId_chucvu());
			pst.setString(5, item.getNoio());
			pst.setString(6, item.getEmail());

			pst.executeUpdate();
			result = 1;

		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Lỗi câu lệnh SQL");
			ex.printStackTrace();
		} finally {
			try {
				pst.close();

				con.close();
			} catch (SQLException ex) {
				JOptionPane.showMessageDialog(null, "Lỗi đóng SQL");
				ex.printStackTrace();
			}
		}
		return result;
	}

	public int del(User item) throws ClassNotFoundException, SQLException, IOException {
		con = libraryDBConnect.getConnectMySQL();
		int result = 0;
		String sql = "DELETE FROM nhanvien WHERE username=?";
		try {

			pst = (PreparedStatement) con.prepareStatement(sql);
			pst.setString(1, item.getUsername());

			pst.executeUpdate();
			result = 1;

		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Lỗi câu lệnh SQL");
			ex.printStackTrace();
		} finally {
			try {
				pst.close();

				con.close();
			} catch (SQLException ex) {
				JOptionPane.showMessageDialog(null, "Lỗi đóng SQL");
				ex.printStackTrace();
			}
		}
		return result;
	}

	public int edit(User item) throws ClassNotFoundException, SQLException, IOException {
		con = libraryDBConnect.getConnectMySQL();
		int result = 0;
		String sql = "";
		try {
			if (!item.getPassword().isEmpty()) {
				sql = "UPDATE nhanvien SET username = ?,password = ?,fullname =?," 
						+"address = ?, email = ?, machucvu =?";
				StringBuffer sb = new StringBuffer(sql);
				sb.append(" WHERE id = ?");
				System.out.println("nhân id: "+item.getId());
				sql = sb.toString();
				pst = (PreparedStatement) con.prepareStatement(sql);

				pst.setString(1, item.getUsername());
				pst.setString(2, item.getPassword());
				pst.setString(3, item.getFullname());				
				pst.setString(4, item.getNoio());
				pst.setString(5, item.getEmail());
				pst.setInt(6, item.getId_chucvu());
				pst.setInt(7, item.getId());
			} 
//			else {
//				sql = "UPDATE nhanvien SET username = ?,fullname =?, active = ?  WHERE id_user = ? LIMIT 1";
//				pst = (PreparedStatement) con.prepareStatement(sql);
//				pst.setString(1, item.getUsername());
//
//				pst.setString(2, item.getFullname());
//				pst.setBoolean(3, item.isActive());
//
//			}

			pst.executeUpdate();
			result = 1;

		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Lỗi câu lệnh SQL");
			System.out.println("nhân : "+ sql);
			ex.printStackTrace();
		} finally {
			try {
				pst.close();

				con.close();
			} catch (SQLException ex) {
				JOptionPane.showMessageDialog(null, "Lỗi đóng SQL");
				ex.printStackTrace();
			}
		}
		return result;
	}

	public boolean isUser(User item) throws ClassNotFoundException, SQLException, IOException {

		con = libraryDBConnect.getConnectMySQL();
		boolean result = false;
		String sql = "SELECT * FROM nhanvien WHERE username =? and password = ?";
		try {

			pst = (PreparedStatement) con.prepareStatement(sql);
			pst.setString(1, item.getUsername());
			pst.setString(2, item.getPassword());

			rs = pst.executeQuery();
			if (rs.next()) {

				result = true;
			}

		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Lỗi câu lệnh SQL");
			ex.printStackTrace();
		} finally {
			try {
				pst.close();

				con.close();
			} catch (SQLException ex) {
				JOptionPane.showMessageDialog(null, "Lỗi đóng SQL");
				ex.printStackTrace();
			}
		}
		return result;
	}

}
