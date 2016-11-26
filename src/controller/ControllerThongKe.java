/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import bean.Food;

import java.awt.Dimension;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import library.LibraryDimension;
import model.ModelFood;
import model.ModelThongKe;

/**
 *
 * @author HungPham
 */
public class ControllerThongKe {

    ModelThongKe modelItem;
    
    public ControllerThongKe() {
        modelItem = new ModelThongKe();
    }

    public void loadTable(JTable table, DefaultTableModel model) {
        table.setModel(model);
        model.setDataVector(vRows(), vCols());
        setWidthHeightTable(table);
    }

    public Vector<String> vCols() {
        Vector<String> vCols = new Vector<String>();
        vCols.add("ID");
        vCols.add("Tên Thức Uống");
        vCols.add("Số Lượng Bán");
        vCols.add("Tổng Số Tiền");
        return vCols;
    }

    public Vector<Vector<Object>> vRows() {
        Vector<Vector<Object>> vRows = new Vector<Vector<Object>>();
        ModelFood modelFood=new ModelFood();
        
        int i=1;
        ArrayList<Food> flist = modelFood.getList(null);
        for (Food item : flist) {
            Vector<Object> v = new Vector<Object>();
            v.add(i);
            v.add(item);
            int t=modelItem.soluongbanra(item.getMafood(),new Timestamp(System.currentTimeMillis()));
            v.add(t);
            v.add(t*item.getPrice());
            vRows.add(v);
            i++;
        }
        return vRows;

    }

   /* public int add(NhapHang item) {
        int result = 0;
        if (isValid(item, "add")) {
            modelItem.add(item);
            result = 1;
        }
        return result;
    }*/

  /*  public int edit(ChucVu item) {
        int result = 0;
        if (isValid(item, "edit")) {
            modelItem.edit(item);
            result = 1;
        }
        return result;
    }*/

    public void setWidthHeightTable(JTable table) {
        table.getTableHeader().setPreferredSize(new Dimension(table.getPreferredSize().width, LibraryDimension.CHUCVU_HEAD_HEIGHT));
        table.setRowHeight(22);
        DefaultTableCellRenderer leftRenderer = new DefaultTableCellRenderer();
        leftRenderer.setHorizontalAlignment(SwingConstants.LEFT);
        table.getColumnModel().getColumn(0).setCellRenderer(leftRenderer);
        table.getColumnModel().getColumn(1).setCellRenderer(leftRenderer);
        table.getColumnModel().getColumn(2).setCellRenderer(leftRenderer);

        table.getColumnModel().getColumn(0).setPreferredWidth(100);
        table.getColumnModel().getColumn(1).setPreferredWidth(250);
        table.getColumnModel().getColumn(2).setPreferredWidth(250);

    }

   /* public int del(ChucVu item) {
        int result = 0;
        if (isValid(item, "del")) {
            modelItem.del(item);
            result = 1;
        }
        return result;
    }*/

   /* private boolean isValid(ChucVu item, String method) {
        switch (method) {
            case "add":
                if ("".equals(item.getChucvu())) {
                    JOptionPane.showMessageDialog(null, "Vui lòng nhập chức vụ");
                    return false;
                }
                if ("".equals(item.getLuongcoban())) {
                    JOptionPane.showMessageDialog(null, "Vui lòng nhập lương");
                    return false;
                }
                break;
            case "del":
                if (item.getMachucvu() == 0) {
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn một dòng để xóa");
                    return false;
                }
                break;
            case "edit":
                if ("".equals(item.getChucvu())) {
                    JOptionPane.showMessageDialog(null, "Vui lòng nhập chức vụ");
                    return false;
                }
                if ("".equals(item.getLuongcoban())) {
                    JOptionPane.showMessageDialog(null, "Vui lòng nhập lương");
                    return false;
                }
                break;

        }
        return true;
    }*/
}
