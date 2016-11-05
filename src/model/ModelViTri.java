/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import bean.Ban;
import bean.KhuVuc;
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
 * @author Bi
 */
public class ModelViTri {

    LibraryDBConnect libraryDBConnect;
    Connection conn;
    Connection conn2;
    Statement st;
    Statement stt;
    PreparedStatement pst;
    ResultSet rs;
    ResultSet rss;

    public ModelViTri() {
        libraryDBConnect = new LibraryDBConnect();
    }

    public ArrayList<KhuVuc> getList() {
        ArrayList<KhuVuc> alItem = new ArrayList<KhuVuc>();

        try {
            conn = libraryDBConnect.getConnectMySQL();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ModelFood.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ModelFood.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ModelFood.class.getName()).log(Level.SEVERE, null, ex);
        }

        String sql = "SELECT makhuvuc, tenkhuvuc FROM khuvuc";

        try {
            st = conn.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                KhuVuc item = new KhuVuc(rs.getInt(1), rs.getString(2));
                alItem.add(item);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ModelFood.class.getName()).log(Level.SEVERE, null, ex);

        } finally {
            try {
                st.close();
                rs.close();
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(ModelViTri.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return alItem;
    }

    public ArrayList<Ban> getBan(int idkhuvuc) {
        ArrayList<Ban> alItem = new ArrayList<Ban>();

        try {
            conn2 = libraryDBConnect.getConnectMySQL();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ModelFood.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ModelFood.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ModelFood.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        String sql = "SELECT soban,makhuvuc FROM ban WHERE makhuvuc = " + idkhuvuc;

        try {
            st = conn2.createStatement();

            rs = st.executeQuery(sql);
            while (rs.next()) {
                Ban item = new Ban(rs.getInt(1), rs.getInt(2));
                alItem.add(item);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ModelFood.class.getName()).log(Level.SEVERE, null, ex);
        }
        for (Ban item : alItem) {
            sql = "SELECT mahd FROM hoadonban WHERE makhuvuc = " + item.getIdkhuvuc() + "and ban =" + item.getSoban() + " and thanhtoan = 1";
            try {
                stt = conn2.createStatement();
                
                rss = stt.executeQuery(sql);
                while (rss.next()) {
                    item.setTinhtrang(rss.getInt(1));
                }
            } catch (SQLException ex) {
                Logger.getLogger(ModelFood.class.getName()).log(Level.SEVERE, null, ex);
            } finally {

            }

        }
        try {
            stt.close();
            rss.close();
            conn2.close();
        } catch (SQLException ex) {
            Logger.getLogger(ModelViTri.class.getName()).log(Level.SEVERE, null, ex);
        }
        return alItem;

    }
    
     public Ban getBanOne(int idkhuvuc, int soban) {
        try {
            conn2 = libraryDBConnect.getConnectMySQL();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ModelFood.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ModelFood.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ModelFood.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        String sql = "SELECT soban,makhuvuc FROM ban WHERE makhuvuc = " + idkhuvuc+" and soban = "+soban;
        Ban item = null;
        try {
            st = conn2.createStatement();

            rs = st.executeQuery(sql);
            while (rs.next()) {
                item = new Ban(rs.getInt(1), rs.getInt(2));

                
            }
        } catch (SQLException ex) {
            Logger.getLogger(ModelFood.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            try {
            stt.close();
            rss.close();
            conn2.close();
        } catch (SQLException ex) {
            Logger.getLogger(ModelViTri.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
        return item;
    }
    public KhuVuc getKhuVucbyID(int idkhuvuc) {
        KhuVuc item = null;

        try {
            conn2 = libraryDBConnect.getConnectMySQL();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ModelFood.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ModelFood.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ModelFood.class.getName()).log(Level.SEVERE, null, ex);
        }

        String sql = "SELECT tenkhuvuc,makhuvuc FROM khuvuc WHERE makhuvuc = " + idkhuvuc;

        try {
            st = conn2.createStatement();

            rs = st.executeQuery(sql);
            while (rs.next()) {
                item = new KhuVuc(rs.getInt("makhuvuc"), rs.getString("tenkhuvuc"));

                
            }
        } catch (SQLException ex) {
            Logger.getLogger(ModelFood.class.getName()).log(Level.SEVERE, null, ex);
        }
        

        

        
        try {
            stt.close();
            rss.close();
            conn2.close();
        } catch (SQLException ex) {
            Logger.getLogger(ModelViTri.class.getName()).log(Level.SEVERE, null, ex);
        }
        return item;

    }

    public int add(KhuVuc item) {
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
       
        String sql = "INSERT INTO khuvuc(tenkhuvuc) values (?)";
        try {

            pst = (PreparedStatement) conn.prepareStatement(sql);

            pst.setString(1, item.getKhuvuc());

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
            } finally {
                try {
                    st.close();
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ModelViTri.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return result;
    }

    public int add(Ban item) {
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
        Ban fitem = getBanOne(item.getIdkhuvuc(), item.getSoban());
       
        if (fitem != null){
            JOptionPane.showMessageDialog(null, "Bàn số "+fitem.getSoban()+" đã tồn tại");
            return result;
            
        }
        String sql = "INSERT INTO ban(makhuvuc, soban) values (?,?)";
        try {

            pst = (PreparedStatement) conn.prepareStatement(sql);

            pst.setInt(1, item.getIdkhuvuc());
            pst.setInt(2, item.getSoban());

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
            } finally {
                try {
                    st.close();
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ModelViTri.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return result;
    }

    public int edit(KhuVuc item) {
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

        String sql = "UPDATE khuvuc SET tenkhuvuc = ? WHERE makhuvuc = ?";
        try {
            pst = (PreparedStatement) conn.prepareStatement(sql);
            pst.setString(1, item.getKhuvuc());
            pst.setInt(2, item.getIdkhuvuc());
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
            } finally {
                try {
                    st.close();
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ModelViTri.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return result;
    }

    public int del(KhuVuc item) {
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
            String sql = "DELETE FROM khuvuc WHERE khuvuc.makhuvuc = ?";
            pst = (PreparedStatement) conn.prepareStatement(sql);
            pst.setInt(1, item.getIdkhuvuc());

            pst.executeUpdate();
            sql = "DELETE FROM ban WHERE makhuvuc = ?";
            pst = (PreparedStatement) conn.prepareStatement(sql);
            pst.setInt(1, item.getIdkhuvuc());
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
            } finally {
                try {
                    st.close();
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ModelViTri.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return result;
    }

    public int del(Ban item) {
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

            String sql = "DELETE FROM ban WHERE makhuvuc = ? and soban = ?";
            pst = (PreparedStatement) conn.prepareStatement(sql);
            pst.setInt(1, item.getIdkhuvuc());
            pst.setInt(2, item.getSoban());
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
            } finally {
                try {
                    st.close();
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ModelViTri.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return result;
    }

}
