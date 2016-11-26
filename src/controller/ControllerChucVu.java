/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import bean.ChucVu;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import library.LibraryDimension;
import model.ModelChucVu;

/**
 *
 * @author HungPham
 */
public class ControllerChucVu {

    ModelChucVu modelItem;
    
    public ControllerChucVu() {
        modelItem = new ModelChucVu();
    }

    public void loadTable(JTable table, DefaultTableModel model, ChucVu fitem) {
        table.setModel(model);
        model.setDataVector(vRows(fitem), vCols());
        setWidthHeightTable(table);
    }

    public Vector<String> vCols() {
        Vector<String> vCols = new Vector<String>();
        vCols.add("ID");
        vCols.add("Chức vụ");
        vCols.add("Lương cơ bản");
        return vCols;
    }

    @SuppressWarnings("rawtypes")
	public Vector<Vector<Comparable>> vRows(ChucVu fitem) {
        Vector<Vector<Comparable>> vRows = new Vector<Vector<Comparable>>();
        modelItem.CreateViewChucVu();
        ArrayList<ChucVu> flist = modelItem.CreatViewChucVu(fitem);
        for (ChucVu item : flist) {
            Vector<Comparable> v = new Vector<Comparable>();
            v.add(item.getMachucvu());
            v.add(item.getChucvu());
            v.add(item.getLuongcoban());
            vRows.add(v);
        }
        return vRows;

    }

    public int add(ChucVu item) {
        int result = 0;
        if (isValid(item, "add")) {
            modelItem.add(item);
            result = 1;
        }
        return result;
    }

    public int edit(ChucVu item) {
        int result = 0;
        if (isValid(item, "edit")) {
            modelItem.edit(item);
            result = 1;
        }
        return result;
    }

    public void setWidthHeightTable(JTable table) {
    	table.getTableHeader().setPreferredSize(new Dimension(table.getPreferredSize().width, LibraryDimension.HOADON_HEAD_HEIGHT));
        table.setRowHeight(22);
        
        DefaultTableCellRenderer leftRenderer = new DefaultTableCellRenderer();
        leftRenderer.setHorizontalAlignment(SwingConstants.LEFT);
        table.getColumnModel().getColumn(0).setCellRenderer(leftRenderer);
        table.getColumnModel().getColumn(1).setCellRenderer(leftRenderer);
        table.getColumnModel().getColumn(2).setCellRenderer(leftRenderer);
    }

    public int del(ChucVu item) {
        int result = 0;
        if (isValid(item, "del")) {
            modelItem.del(item);
            result = 1;
        }
        return result;
    }

    private boolean isValid(ChucVu item, String method) {
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
                if(modelItem.isExist(item)){
                	JOptionPane.showMessageDialog(null, "Tên chức vụ đã tồn tại");
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
    }
}
