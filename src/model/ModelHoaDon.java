/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import bean.Bill;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

import library.LibraryDBConnect;

/**
 *
 * @author Bi
 */
public class ModelHoaDon {

    LibraryDBConnect libraryDBConnect;
    Connection conn;
    Statement st;
    PreparedStatement pst;
    ResultSet rs;
    public ModelHoaDon(){
        libraryDBConnect= new LibraryDBConnect();
    }
    public ArrayList<Bill> getList() {
        ArrayList<Bill> alItem = new ArrayList<Bill>();
        
        try {
            conn = libraryDBConnect.getConnectMySQL();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ModelFood.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ModelFood.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ModelFood.class.getName()).log(Level.SEVERE, null, ex);
        }
        String sql = "SELECT DISTINCT hoadonban.mahd, ngaythanhtoan, username, hoadonban.makhuvuc, "
        		+ "ISNULL(tenkhuvuc,'khu vuc da bi xoa') as tenkhuvuc, ISNULL(ban,0) as ban, "
        		+ "ISNULL(SUM(food.giaban*soluong),0) as thanhtien, thanhtoan"
                 + " FROM hoadonban LEFT JOIN khuvuc on khuvuc.makhuvuc = hoadonban.makhuvuc LEFT JOIN cthdban "
                 + "ON hoadonban.mahd = cthdban.mahd LEFT JOIN food ON cthdban.mathucpham = food.mafood"
                 + " GROUP BY hoadonban.mahd, ngaythanhtoan, username, hoadonban.makhuvuc, tenkhuvuc, ban,thanhtoan "
                 + "ORDER BY hoadonban.mahd DESC";    
        try {
            st = conn.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                
                Bill item = new Bill();
                item.setIdbill(rs.getInt(1));
                item.setNgaythanhtoan(rs.getDate(2));
                item.setUsername(rs.getString(3));
                item.setIdkhuvuc(rs.getInt(4));
                item.setKhuvuc(rs.getString(5));
                item.setBan(rs.getInt(6));
                item.setThanhtien(rs.getInt(7));
                item.setThanhtoan(rs.getInt(8));
                alItem.add(item);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ModelFood.class.getName()).log(Level.SEVERE, null, ex);
        }
        return alItem;
    }

    public int del(Bill b) {
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

        
        String sql = "DELETE FROM hoadonban WHERE mahd = ?";
        try {

            pst = (PreparedStatement) conn.prepareStatement(sql);
            pst.setInt(1, b.getIdbill());
            pst.executeUpdate();
            result = 1;

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Lỗi câu lệnh SQL");
            ex.printStackTrace();
        } finally {
           
        }
        sql = "DELETE FROM cthdban WHERE mahd = "+b.getIdbill();

        try {
            pst = (PreparedStatement) conn.prepareStatement(sql);
            pst.executeUpdate();
            result = 1;
        } catch (SQLException ex) {
            Logger.getLogger(ModelFood.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public ArrayList<Bill> getListToDay(int d, int m, int y, String date) {
        ArrayList<Bill> alItem = new ArrayList<Bill>();
        
        try {
            conn = libraryDBConnect.getConnectMySQL();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ModelFood.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ModelFood.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ModelFood.class.getName()).log(Level.SEVERE, null, ex);
        } 
        String sql = "SELECT DISTINCT hoadonban.mahd, ngaythanhtoan, username, hoadonban.makhuvuc, ISNULL(tenkhuvuc,'khu vực đã bị xóa') as tenkhuvuc, ISNULL(ban,0) as ban, ISNULL(SUM(food.giaban*soluong),0) as thanhtien, thanhtoan"
                + " FROM hoadonban LEFT JOIN khuvuc on khuvuc.makhuvuc = hoadonban.makhuvuc LEFT JOIN cthdban ON hoadonban.mahd = cthdban.mahd LEFT JOIN food ON cthdban.mathucpham = food.mafood"
                + " WHERE hoadonban.thanhtoan=0 ";
            if (d>0){
                sql+= " and DAY(ngaythanhtoan) = "+d;
            }
            if (m>0){
                sql+= " and MONTH(ngaythanhtoan)= "+m;
            }
            if (y>0){
                sql+= " and YEAR(ngaythanhtoan) ="+y;
            }
            if (!"".equals(date)){
                String [] arTime = date.split("---");
                System.out.println("hung cho :"+ arTime[0]);
                System.out.println("hung cho :"+ arTime[1]);
                sql += " and ngaythanhtoan BETWEEN '"+arTime[0]+"' AND '" + arTime[1]+"' ";
            }
            sql+= " GROUP BY hoadonban.mahd, ngaythanhtoan, username, hoadonban.makhuvuc, tenkhuvuc, ban,thanhtoan "
                + "ORDER BY thanhtoan DESC";
        try {
            st = conn.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                
            	Bill item = new Bill();
                item.setIdbill(rs.getInt(1));
                item.setNgaythanhtoan(rs.getDate(2));
                item.setUsername(rs.getString(3));
                item.setIdkhuvuc(rs.getInt(4));
                item.setKhuvuc(rs.getString(5));
                item.setBan(rs.getInt(6));
                item.setThanhtien(rs.getInt(7));
                item.setThanhtoan(rs.getInt(8));
                alItem.add(item);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ModelFood.class.getName()).log(Level.SEVERE, null, ex);
        }
        return alItem;
    }
   /* public void hoadonview(){
    	 ArrayList<Bill> alItem = new ArrayList<Bill>();
         
         try {
             conn = libraryDBConnect.getConnectMySQL();
         } catch (ClassNotFoundException ex) {
             Logger.getLogger(ModelFood.class.getName()).log(Level.SEVERE, null, ex);
         } catch (SQLException ex) {
             Logger.getLogger(ModelFood.class.getName()).log(Level.SEVERE, null, ex);
         } catch (IOException ex) {
             Logger.getLogger(ModelFood.class.getName()).log(Level.SEVERE, null, ex);
         }

         String sql = "CREATE VIEW hoadonview AS SELECT DISTINCT hoadonban.mahd, ngaythanhtoan, username, hoadonban.makhuvuc, ISNULL(tenkhuvuc,'khu vuc da bi xoa') as tenkhuvuc, ISNULL(ban,0) as ban, ISNULL(SUM(food.giaban*soluong),0) as thanhtien, thanhtoan"
                 + " FROM hoadonban LEFT JOIN khuvuc on khuvuc.makhuvuc = hoadonban.makhuvuc LEFT JOIN cthdban ON hoadonban.mahd = cthdban.mahd LEFT JOIN food ON cthdban.mathucpham = food.mafood"
                 + " GROUP BY hoadonban.mahd, ngaythanhtoan, username, hoadonban.makhuvuc, tenkhuvuc, ban,thanhtoan "
                 + "ORDER BY hoadonban.thanhtoan DESC, hoadonban.ngaythanhtoan DESC";

         
         try {
             st = conn.createStatement();
             rs = st.executeQuery(sql);
         } catch (SQLException ex) {
             Logger.getLogger(ModelFood.class.getName()).log(Level.SEVERE, null, ex);
         }  
    }*/
}
