/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;


import bean.User;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
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
    LibraryDBConnect libraryDBConnect = null;

    public ModelUser() {
        libraryDBConnect = new LibraryDBConnect();
    }

    public ArrayList<User> getList(User tfitem) throws ClassNotFoundException, SQLException, IOException {
        con = libraryDBConnect.getConnectMySQL();
        ArrayList<User> alItem = new ArrayList<User>();
        String sql = "SELECT * FROM nhanvien INNER JOIN chucvu ON nhanvien.machucvu = chucvu.machucvu ";
        int t=0;
        if (tfitem != null) {
            
            if (!"".equals(tfitem.getUsername())) {
            	t=1;
                sql += "WHERE username LIKE '%" + tfitem.getUsername() + "%'";
            }
            if (!("").equals(tfitem.getFullname())) {
            	if(t==1)
                sql += " and fullname LIKE '%" + tfitem.getFullname() + "%'";
            	else sql += "WHERE fullname LIKE '%" + tfitem.getFullname() + "%'";
            }
           
        }
        System.out.println(sql);
        try {
            st = con.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                User item = new User(rs.getInt("machucvu"), rs.getString("username"), rs.getString("tenchucvu"), rs.getString("password"), rs.getString("fullname"), rs.getString("address"), rs.getString("email"));
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

  

    public User getUserByUser(String username) throws ClassNotFoundException, SQLException, IOException {
        con = libraryDBConnect.getConnectMySQL();
        User item = null;
        String sql = "SELECT * FROM nhanvien INNER JOIN chucvu ON nhanvien.machucvu = chucvu.machucvu WHERE username = '" + username + "'";
        System.out.println(sql);
        try {
            st = con.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                item = new User(rs.getInt("machucvu"), rs.getString("username"), rs.getString("tenchucvu"), rs.getString("password"), rs.getString("fullname"), rs.getString("address"), rs.getString("email"));

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
        return item;
    }

    public int add(User item) throws ClassNotFoundException, IOException, SQLException {
        con = libraryDBConnect.getConnectMySQL();
        int result = 0;
        String sql = "INSERT INTO nhanvien(username,password,fullname,"
                        + "machucvu,address, email) values (?,?,?,?,?,?,?,?)";
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
                sql = "UPDATE nhanvien SET password = ?,fullname =?,"
                        + "machucvu =?,address = ?, email = ?"
                        + "   WHERE username = ? LIMIT 1";
                pst = (PreparedStatement) con.prepareStatement(sql);
                
                pst.setString(1, item.getPassword());
                pst.setString(2, item.getFullname());
                pst.setInt(3, item.getId_chucvu());
                pst.setString(4, item.getNoio());
                pst.setString(5, item.getEmail());
                pst.setString(8, item.getUsername());
               
            } else {
                sql = "UPDATE users SET username = ?,fullname =?, active = ?  WHERE id_user = ? LIMIT 1";
                pst = (PreparedStatement) con.prepareStatement(sql);
                pst.setString(1, item.getUsername());

                pst.setString(2, item.getFullname());
                pst.setBoolean(3, item.isActive());
                
            }

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
