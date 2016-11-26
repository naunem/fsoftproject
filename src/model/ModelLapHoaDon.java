/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import bean.Bill;
import bean.BillDetail;
import bean.Food;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

import library.LibraryDBConnect;

/**
 *
 * @author HungPham
 */
public class ModelLapHoaDon {

    LibraryDBConnect libraryDBConnect;
    Connection conn;
    Statement st;
    PreparedStatement pst;
    ResultSet rs;

    public ModelLapHoaDon() {
        libraryDBConnect = new LibraryDBConnect();

    }
    
    //search food
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
        	int t=0;
            if ((fitem.getMafood()) > 0) {
             t=1;	
             sql += " where mafood = " + fitem.getMafood();
            }
            if (!("").equals(fitem.getFoodname())){
            	sql += " where foodname LIKE '%" + fitem.getFoodname()+ "%'";
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

    public ArrayList<BillDetail> getList(Bill fitem) {
    	
        ArrayList<BillDetail> alItem = new ArrayList<BillDetail>();

        try {
            conn = libraryDBConnect.getConnectMySQL();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ModelFood.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ModelFood.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ModelFood.class.getName()).log(Level.SEVERE, null, ex);
        }

        String sql = "SELECT food.mafood,cthdban.soluong FROM cthdban INNER JOIN food on cthdban.mathucpham = food.mafood WHERE cthdban.mahd = " + fitem.getIdbill();

        try {
            st = conn.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {

                BillDetail item = new BillDetail(fitem.getIdbill(), rs.getInt(1), rs.getInt(2));
                alItem.add(item);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ModelFood.class.getName()).log(Level.SEVERE, null, ex);
        }
        return alItem;
    }

    public int getThanhTien(Bill fitem) {
        int thanhtien = 0;

        try {
            conn = libraryDBConnect.getConnectMySQL();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ModelFood.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ModelFood.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ModelFood.class.getName()).log(Level.SEVERE, null, ex);
        }

        String sql = "SELECT SUM(food.giaban*cthdban.soluong) FROM cthdban INNER JOIN food ON (cthdban.mathucpham = food.mafood) WHERE mahd = " + fitem.getIdbill() + " GROUP BY mahd";

        try {
            st = conn.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {

                thanhtien = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ModelFood.class.getName()).log(Level.SEVERE, null, ex);
        }
        return thanhtien;
    }

    @SuppressWarnings("rawtypes")
	public int addDBDetail(Vector<Vector> vRows, Bill fitem) {
    	//System.out.println("toi muon the");
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
        try {
                String sql = "INSERT INTO hoadonban(username, makhuvuc, ban, thanhtoan,ngaythanhtoan) values (?,?,?,?,?)";
                pst = (PreparedStatement) conn.prepareStatement(sql);
                Date date = new Date();
                java.sql.Date sqlDate = new java.sql.Date(date.getTime());
                pst.setDate(5, sqlDate);
                pst.setString(1, fitem.getUsername());
                pst.setInt(2, fitem.getIdkhuvuc());
                pst.setInt(3, fitem.getBan());
                pst.setInt(4, 1);

                pst.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Lỗi câu lệnh SQL");
            ex.printStackTrace();
        } finally {
           
        }
        String max1 = "SELECT MAX(mahd) as mahd FROM hoadonban";
        int mahd = -1;
        try {
            st = conn.createStatement();
            rs = st.executeQuery(max1);
            while (rs.next()) {
                mahd = rs.getInt("mahd");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ModelFood.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (mahd == -1) {
            mahd = 1;
        }
        ArrayList<BillDetail> flist = new ArrayList<>();
        if(vRows.size() >0){
            for (int i = 0; i < vRows.size(); i++) {
                BillDetail item = new BillDetail();
                item.setIdbill(fitem.getIdbill());
                Food food=(Food) vRows.get(i).get(1);
                item.setIdfood(food.getMafood());
                item.setSoluong((int) vRows.get(i).get(3));
                flist.add(item);
            }
        } else{
            return 0;
        }
        try {
            for (BillDetail item : flist) {
                String sql = "INSERT INTO cthdban(mahd, mathucpham, soluong) values (?,?,?)";
                pst = (PreparedStatement) conn.prepareStatement(sql);
                pst.setInt(1, mahd);
                pst.setInt(2, item.getIdfood());
                pst.setInt(3, item.getSoluong());
                pst.executeUpdate();

                result = 1;
            }
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

    @SuppressWarnings("rawtypes")
	public int editDB(Vector<Vector> vRows, Bill fitem) {
        int result = 0;
        try {
            conn = libraryDBConnect.getConnectMySQL();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ModelFood.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ModelFood.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ModelFood.class.getName()).log(Level.SEVERE, null, ex);
        }
        //System.out.println("toi muon biet:"+fitem.getIdbill());
        String sql = "DELETE FROM cthdban WHERE mahd = "+fitem.getIdbill();

        try {
            pst = (PreparedStatement) conn.prepareStatement(sql);
            pst.executeUpdate();
            result = 1;
        } catch (SQLException ex) {
            Logger.getLogger(ModelFood.class.getName()).log(Level.SEVERE, null, ex);
        }
         ArrayList<BillDetail> flist = new ArrayList<>();
        for (int i = 0; i < vRows.size(); i++) {
            BillDetail item = new BillDetail();
            item.setIdbill(fitem.getIdbill());
            Food food=(Food)vRows.get(i).get(1);
            item.setIdfood(food.getMafood());
            item.setSoluong((int) vRows.get(i).get(3));
            flist.add(item);
        }

        try {
            conn = libraryDBConnect.getConnectMySQL();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ModelChucVu.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ModelChucVu.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ModelChucVu.class.getName()).log(Level.SEVERE, null, ex);
        }
         result = 0;
        
        try {
            for (BillDetail item : flist) {
                sql = "INSERT INTO cthdban(mahd, mathucpham, soluong) values (?,?,?)";
                pst = (PreparedStatement) conn.prepareStatement(sql);
                
                pst.setInt(1, item.getIdbill());
                pst.setInt(2, item.getIdfood());
                pst.setInt(3, item.getSoluong());

                pst.executeUpdate();

                result = 1;
            }
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

    public int updateThanhToan(Bill item) {
        int result;
         try {
            conn = libraryDBConnect.getConnectMySQL();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ModelChucVu.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ModelChucVu.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ModelChucVu.class.getName()).log(Level.SEVERE, null, ex);
        }
         result = 0;
        
        try {
            
                String sql = "UPDATE hoadonban SET thanhtoan = 0, ngaythanhtoan=? WHERE mahd = ?";
                pst = (PreparedStatement) conn.prepareStatement(sql);
                
                Date date = new Date();
                Timestamp tdate = new Timestamp(date.getTime());
                pst.setInt(2, item.getIdbill());
                pst.setTimestamp(1, tdate);
                

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
   /* public void laphoadonview(){
        try {
            conn = libraryDBConnect.getConnectMySQL();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ModelFood.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ModelFood.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ModelFood.class.getName()).log(Level.SEVERE, null, ex);
        }

        String sql = "CREATE VIEW laphoadonview AS SELECT food.mafood,cthdban.soluong FROM cthdban INNER JOIN food on cthdban.mathucpham = food.mafood";

        try {
            st = conn.createStatement();
            rs = st.executeQuery(sql);
        } catch (SQLException ex) {
            Logger.getLogger(ModelFood.class.getName()).log(Level.SEVERE, null, ex);
        }
    }*/

}
