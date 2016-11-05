package model;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

import library.LibraryDBConnect;
import bean.ChiTietNhapHang;
import bean.NhapHang;

public class ModelNhapHang {
	LibraryDBConnect libraryDBConnect;
    Connection conn;
    Statement st;
    PreparedStatement pst;
    ResultSet rs;

    public ModelNhapHang() {
            libraryDBConnect = new LibraryDBConnect();
    }

    public ArrayList<ChiTietNhapHang> getList(ChiTietNhapHang fitem) {
        ArrayList<ChiTietNhapHang> alItem = new ArrayList<ChiTietNhapHang>();
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
            String sql = "SELECT idnhaphang, idthucpham, soluong, gianhap FROM chitietnhaphang WHERE 1 ";
           
            if (fitem != null){
                 if ((fitem.getIdNhapHang()) > 0) {
                sql += " and idnhaphang = " + fitem.getIdNhapHang();
                }
                if (!("").equals(fitem.getIdThucPham())){
                    sql += " and idthucpham =" + fitem.getIdThucPham()+ "";
                }
            } 
            st = conn.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                ChiTietNhapHang item = new ChiTietNhapHang(rs.getInt(1), rs.getInt(2), rs.getInt(3),rs.getFloat(4));
                alItem.add(item);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ModelChucVu.class.getName()).log(Level.SEVERE, null, ex);
        }
        return alItem;
    }

    public int add(ChiTietNhapHang item) {
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
        String max1 = "SELECT MAX(idnhaphang) as idnhaphang FROM nhaphang";
        int idnhaphang = -1;
        try {
            st = conn.createStatement();
            rs = st.executeQuery(max1);
            while (rs.next()) {
                idnhaphang = rs.getInt("idnhaphang");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ModelFood.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (idnhaphang == -1) {
            idnhaphang = 1;
        }
        String sql = "INSERT INTO chitietnhaphang(idnhaphang,idthucpham,soluong,gianhap) values (?,?,?,?)";
        try {

            pst = (PreparedStatement) conn.prepareStatement(sql);

            pst.setInt(1, idnhaphang);
            pst.setInt(2, item.getIdThucPham());
            pst.setInt(3, item.getSoluong());
            pst.setFloat(4, item.getGianhap());
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
    public int addNhaphang(NhapHang item){
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
        String sql = "INSERT INTO nhaphang(ngaynhap,tongtien) values (?,?)";
        try {

            pst = (PreparedStatement) conn.prepareStatement(sql);
            Date date = new Date();
            java.sql.Date sqlDate = new java.sql.Date(date.getTime());
            pst.setDate(1, sqlDate);
            pst.setFloat(2, item.getTongtien());
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
    public int edit(ChiTietNhapHang item) {
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

        String sql = "UPDATE chitietnhaphang SET idthucpham = ?, soluong=? ,gianhap = ? where idnhaphang=?";
        try {

            pst = (PreparedStatement) conn.prepareStatement(sql);
            pst.setInt(1, item.getIdThucPham());
            pst.setInt(2, item.getSoluong());
            pst.setFloat(3, item.getGianhap());
            pst.setInt(4, item.getIdNhapHang());
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

    public int del(ChiTietNhapHang item) {
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

        String sql = "DELETE FROM chitietnhaphang WHERE idnhaphang = ?";
        try {

            pst = (PreparedStatement) conn.prepareStatement(sql);
            pst.setInt(1, item.getIdNhapHang());
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
}
