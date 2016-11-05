package model;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import library.LibraryDBConnect;

public class ModelThongKe {
	LibraryDBConnect libraryDBConnect;
	    Connection conn;
	    Statement st;
	    PreparedStatement pst;
	    ResultSet rs;
    public ModelThongKe() {
            libraryDBConnect = new LibraryDBConnect();
    }
    public int soluongbanra(int idthucuong,Date date){
    	 date=new Date();
    	 String moi=new SimpleDateFormat("yyyy-MM-dd").format(date);
    	 try {
             conn = libraryDBConnect.getConnectMySQL();
         } catch (ClassNotFoundException ex) {
             Logger.getLogger(ModelFood.class.getName()).log(Level.SEVERE, null, ex);
         } catch (SQLException ex) {
             Logger.getLogger(ModelFood.class.getName()).log(Level.SEVERE, null, ex);
         } catch (IOException ex) {
             Logger.getLogger(ModelFood.class.getName()).log(Level.SEVERE, null, ex);
         }

         String sql = "SELECT SUM(cthdban.soluong) FROM cthdban INNER JOIN hoadonban ON cthdban.mahd=hoadonban.mahd where hoadonban.thanhtoan= "+0+" and ngaythanhtoan='"+moi+"' and cthdban.mathucpham='"+idthucuong+"'";
         try {
         	
             st = conn.createStatement();
             rs = st.executeQuery(sql);
             while (rs.next()) {
                return rs.getInt(1);
             }
         } catch (SQLException ex) {
             Logger.getLogger(ModelFood.class.getName()).log(Level.SEVERE, null, ex);
         }
         return 0;
     }
    
}
