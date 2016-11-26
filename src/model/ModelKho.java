package model;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

import library.LibraryDBConnect;
import model.ModelChucVu;

public class ModelKho {
	LibraryDBConnect libraryDBConnect;
    Connection conn;
    Statement st;
    PreparedStatement pst;
    ResultSet rs;

    public ModelKho() {
            libraryDBConnect = new LibraryDBConnect();
    }

   public int updateKho(int idthucuong,int soluong){
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
       String sql = "UPDATE kho set soluongcon=soluongcon+? where idthucpham=?";
       try {

           pst = (PreparedStatement) conn.prepareStatement(sql);
           pst.setInt(2, idthucuong);
           pst.setInt(1, soluong);
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
   
   public int checkKho(int idthucuong, int soluong){
	   int temp = 0;
	   System.out.println("hoang : " + soluong);
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
       String sql = "select soluongcon from kho where idthucpham= '"+idthucuong+"'";
       
       try {
           st = conn.createStatement();
           rs = st.executeQuery(sql);
           while (rs.next()) {
        	   temp = rs.getInt(1);
        	   System.out.println("hung :"+temp);
               break;
           }
           if(soluong <= temp){
        	   System.out.println("vo day roi");
        	   result = 1;
           }
       } catch (SQLException ex) {
           JOptionPane.showMessageDialog(null, "Lỗi câu lệnh SQL");
           ex.printStackTrace();
       } finally {
           try {
               st.close();
               rs.close();
               conn.close();
           } catch (SQLException ex) {
               JOptionPane.showMessageDialog(null, "Lỗi đóng SQL");
               ex.printStackTrace();
           }
       }
       return result;
   }
   /*public int deleteThucPham(int idthucpham){
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
       String sql = "DELETE FROM chitietnhaphang WHERE idthucpham = ?";
       try {

           pst = (PreparedStatement) conn.prepareStatement(sql);
           pst.setInt(1, idthucpham);
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
   }*/
   public int insertKho(int idthucuong,int soluong){
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
       String sql = "INSERT INTO kho(idthucpham,soluongcon) values(?,?)";
       try {

           pst = (PreparedStatement) 
           conn.prepareStatement(sql);
           pst.setInt(1, idthucuong);
           pst.setInt(2, soluong);
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
   public boolean checkExitsFood(int idfood){
	   try {
           conn = libraryDBConnect.getConnectMySQL();
       } catch (ClassNotFoundException ex) {
           Logger.getLogger(ModelFood.class.getName()).log(Level.SEVERE, null, ex);
       } catch (SQLException ex) {
           Logger.getLogger(ModelFood.class.getName()).log(Level.SEVERE, null, ex);
       } catch (IOException ex) {
           Logger.getLogger(ModelFood.class.getName()).log(Level.SEVERE, null, ex);
       }

       String sql = "SELECT idthucpham as idthucpham, soluongcon FROM kho where idthucpham= '"+idfood+"'";
       try {
       	
           st = conn.createStatement();
           rs = st.executeQuery(sql);
           while (rs.next()) {
              return true;
           }
       } catch (SQLException ex) {
           Logger.getLogger(ModelFood.class.getName()).log(Level.SEVERE, null, ex);
       }
       return false;
   }
   public int laysoluong(int idfood){
	   int t=0;
	   try {
           conn = libraryDBConnect.getConnectMySQL();
       } catch (ClassNotFoundException ex) {
           Logger.getLogger(ModelFood.class.getName()).log(Level.SEVERE, null, ex);
       } catch (SQLException ex) {
           Logger.getLogger(ModelFood.class.getName()).log(Level.SEVERE, null, ex);
       } catch (IOException ex) {
           Logger.getLogger(ModelFood.class.getName()).log(Level.SEVERE, null, ex);
       }

       String sql = "SELECT idthucpham as idthucpham, soluongcon FROM kho where idthucpham= '"+idfood+"'";
       try {
       	
           st = conn.createStatement();
           rs = st.executeQuery(sql);
           while (rs.next()) {
              return rs.getInt(2);
           }
       } catch (SQLException ex) {
           Logger.getLogger(ModelFood.class.getName()).log(Level.SEVERE, null, ex);
       }
       return t;
   }
   /*public void khoview(){
	   try {
           conn = libraryDBConnect.getConnectMySQL();
       } catch (ClassNotFoundException ex) {
           Logger.getLogger(ModelChucVu.class.getName()).log(Level.SEVERE, null, ex);
       } catch (SQLException ex) {
           Logger.getLogger(ModelChucVu.class.getName()).log(Level.SEVERE, null, ex);
       } catch (IOException ex) {
           Logger.getLogger(ModelChucVu.class.getName()).log(Level.SEVERE, null, ex);
       }
  	  try{ 
       int result = 0;
       String sql="CREATE VIEW khoview AS SELECT idthucpham, soluongcon FROM kho";
       st = conn.createStatement();
       rs = st.executeQuery(sql);
       st.close();
       rs.close();
	     }catch(Exception e){
	   	
	  }
   }*/
}
