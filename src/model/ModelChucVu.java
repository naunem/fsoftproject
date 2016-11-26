/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import bean.ChucVu;
import bean.Food;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
public class ModelChucVu {

	LibraryDBConnect libraryDBConnect;
	Connection conn;
	Statement st;
	PreparedStatement pst;
	ResultSet rs;

	public ModelChucVu() {
		libraryDBConnect = new LibraryDBConnect();
	}
	
	public boolean isExist(ChucVu fitem) {
		try {
			conn = libraryDBConnect.getConnectMySQL();
		} catch (ClassNotFoundException ex) {
			Logger.getLogger(ModelFood.class.getName()).log(Level.SEVERE, null, ex);
		} catch (SQLException ex) {
			Logger.getLogger(ModelFood.class.getName()).log(Level.SEVERE, null, ex);
		} catch (IOException ex) {
			Logger.getLogger(ModelFood.class.getName()).log(Level.SEVERE, null, ex);
		}

		String sql = "SELECT * FROM chucvu where tenchucvu='"+fitem.getChucvu()+"'";

		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
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

	public int add(ChucVu item) {
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

		String sql = "INSERT INTO chucvuview(tenchucvu, luongcoban) values (?,?)";
		try {

			pst = (PreparedStatement) conn.prepareStatement(sql);

			pst.setString(1, item.getChucvu());
			pst.setInt(2, item.getLuongcoban());

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

	public int edit(ChucVu item) {
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
		String sql = "UPDATE chucvuview SET tenchucvu = ?, luongcoban=? WHERE machucvu = ?";
		try {

			pst = (PreparedStatement) conn.prepareStatement(sql);
			pst.setString(1, item.getChucvu());
			pst.setInt(2, item.getLuongcoban());
			pst.setInt(3, item.getMachucvu());
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

	public int del(ChucVu item) {
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

		String sql = "DELETE FROM chucvuview WHERE machucvu = ?";
		try {

			pst = (PreparedStatement) conn.prepareStatement(sql);
			pst.setInt(1, item.getMachucvu());
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

	public ArrayList<ChucVu> CreatViewChucVu(ChucVu fitem) {
		ArrayList<ChucVu> alItem = new ArrayList<ChucVu>();
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
		String sql = "SELECT * from chucvuview";
		try {
			int t = 0;
			if (fitem != null) {
				if ((fitem.getMachucvu()) > 0) {
					t = 1;
					sql += "  WHERE machucvu = " + fitem.getMachucvu();
				}
				if (!("").equals(fitem.getChucvu())) {
					if (t == 1)
						sql += " and tenchucvu LIKE '%" + fitem.getChucvu() + "%'";
					else
						sql += " WHERE tenchucvu LIKE '%" + fitem.getChucvu() + "%'";
				}
			}
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				ChucVu item = new ChucVu(rs.getInt(1), rs.getString(2), rs.getInt(3));
				alItem.add(item);
			}
			st.close();
			rs.close();
		} catch (Exception e) {

		}
		return alItem;
	}

	public void CreateViewChucVu() {

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
			String sql = "CREATE chucvuview AS SELECT machucvu, tenchucvu, luongcoban FROM CHUCVU";
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			st.close();
			rs.close();
		} catch (Exception e) {

		}

	}
}
