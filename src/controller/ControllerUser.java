  /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import bean.User;

import java.awt.Dimension;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import library.LibraryDimension;
import model.ModelUser;

/**
 *
 * @author HungPham
 */
public class ControllerUser {
    
    ModelUser modelItem;
    User login;
    public ControllerUser() {
        modelItem = new ModelUser();
    }

    public void loadTable(JTable table, DefaultTableModel model, User fitem) throws ClassNotFoundException, SQLException, IOException { 
        table.setModel(model);
        model.setDataVector(vRows(fitem), vCols());
        setWidthHeightTable(table);
    }

    public void setWidthHeightTable(JTable table) {
        table.getTableHeader().setPreferredSize(new Dimension(table.getPreferredSize().width, LibraryDimension.CHUCVU_HEAD_HEIGHT));
        table.setRowHeight(22);
        table.getColumnModel().getColumn(1).setPreferredWidth(LibraryDimension.USER_HEAD_WIDTH_FULLNAME_COL_NAME);
        //set mau
        ((JComponent) table.getDefaultRenderer(Boolean.class)).setOpaque(true);
    }

    public Vector<String> vCols() {
        Vector<String> vCols = new Vector<String>();
        vCols.add("ID");
        vCols.add("Tên tài khoản");
        vCols.add("Tên người dùng");
        vCols.add("Chức vụ");
        return vCols;
    }

    public Vector<Vector<Object>> vRows(User tfitem) throws ClassNotFoundException, SQLException, IOException {
        Vector<Vector<Object>> vRows = new Vector<Vector<Object>>();
        ArrayList<User> alItem = modelItem.getList(tfitem);
        for (User it : alItem) {
            Vector<Object> v = new Vector<Object>();
            v.add(it.getId());
            v.add(it);     
            v.add(it.getFullname());
            v.add(it.getChucvu());
            vRows.add(v);
            
        }
        return vRows;
    }

    public int add(User item) throws ClassNotFoundException, IOException, SQLException {
        int result = 0;
        if (isValid(item, "add")) {
            modelItem.add(item);
            result = 1;
        }
        return result;
    }

    private boolean isValid(User item, String method) throws ClassNotFoundException, SQLException, IOException {
        switch (method) {
            case "add":
                    User user_old;
                     user_old = modelItem.getUserByUser(item.getUsername());
                    if (user_old != null){
                        JOptionPane.showMessageDialog(null, "Trùng tên đăng nhập");
                        return false;
                    }
                
                if ("".equals(item.getUsername())) {
                    JOptionPane.showMessageDialog(null, "Vui lòng nhập tên tài khoản");
                    return false;
                }
                if ("".equals(item.getFullname())) {
                    JOptionPane.showMessageDialog(null, "Vui lòng nhập họ và tên");
                    return false;
                }
                if ("".equals(item.getPassword())) {
                    JOptionPane.showMessageDialog(null, "Vui lòng nhập mật khẩu");
                    return false;
                }
                
                break;
            	
            case "del":
                if ("".equals(item.getUsername())) {
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn một dòng để xóa");
                    return false;
                }
                if(login.getUsername().equals(item.getUsername())){
                    JOptionPane.showMessageDialog(null, "Không thể xóa chính mình");
                    return false;
                }
            
                break;
            case "edit":
            	if ("".equals(item.getUsername())) {
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn một dòng để sửa");
                    return false;
                }
                User objUserById = modelItem.getUserByUser(item.getUsername());
                //Kiểm tra trùng username
//                if (!objUserById.getUsername().equals(item.getUsername())){
//                    User objUserByUsername = modelItem.getUserByUser(item.getUsername());
//                    if (objUserByUsername != null){
//                        JOptionPane.showMessageDialog(null, "Trùng tên đăng nhập");
//                        return false;
//                    }
//                }
                
                if ("".equals(item.getUsername())) {
                    JOptionPane.showMessageDialog(null, "Vui lòng nhập tên đăng nhập");
                    return false;
                }
                if ("".equals(item.getFullname())) {
                    JOptionPane.showMessageDialog(null, "Vui lòng nhập tên");
                    return false;
                }
                if ("".equals(item.getPassword())) {
                    JOptionPane.showMessageDialog(null, "Vui lòng nhập mật khẩu");
                    return false;
                }
                if(modelItem.isExist(item)){
                	JOptionPane.showMessageDialog(null, "Tên đăng nhập đã tồn tại");
                    return false;
                }
                break;

        }
        return true;
    }

    public int edit(User item) throws ClassNotFoundException, SQLException, IOException {
         int result = 0;
        if (isValid(item, "edit")) {
            modelItem.edit(item);
            result = 1;
        }
        return result;
    }

    public int del(User item, User login) throws ClassNotFoundException, SQLException, IOException {
       int result = 0;
       this.login = login;
        if (isValid(item, "del")) {
                
                modelItem.del(item);
                result = 1;
            }
           
        
        return result;
    }

    public boolean isUser(User item) throws ClassNotFoundException, SQLException, IOException {
        return modelItem.isUser(item);
    }
    public User getUserByUser(String username) throws ClassNotFoundException, SQLException, IOException  {
        return modelItem.getUserByUser(username);
    }
}
