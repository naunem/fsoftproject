/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import bean.Ban;
import bean.Bill;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import library.LibraryDimension;
import model.ModelHoaDon;

/**
 *
 * @author HungPham
 */
public class ControllerHoaDon {
    ModelHoaDon modelItem;
    int count = 0;
    int sum = 0;
    @SuppressWarnings("rawtypes")
	Vector<Vector> vR;
    @SuppressWarnings("rawtypes")
    public Vector<Vector> getvR() {
        return vR;
    }
    @SuppressWarnings("rawtypes")
    public void setvR(Vector<Vector> vR) {
        this.vR = vR;
    }
    
    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }
    
    public void ControlerHoaDon(){
        modelItem = new ModelHoaDon();
    }

    
    public void loadTable(JTable table, DefaultTableModel model) {
        modelItem = new ModelHoaDon();
        table.setModel(model);
        model.setDataVector(vRows(), vCols());
        setWidthHeightTable(table);
    }
    

    public void setWidthHeightTable(JTable table) {
        table.getTableHeader().setPreferredSize(new Dimension(table.getPreferredSize().width, LibraryDimension.HOADON_HEAD_HEIGHT));
        table.setRowHeight(22);
        
        DefaultTableCellRenderer leftRenderer = new DefaultTableCellRenderer();
        leftRenderer.setHorizontalAlignment(SwingConstants.LEFT);
        table.getColumnModel().getColumn(0).setCellRenderer(leftRenderer);
        table.getColumnModel().getColumn(1).setCellRenderer(leftRenderer);
        table.getColumnModel().getColumn(2).setCellRenderer(leftRenderer);
        table.getColumnModel().getColumn(3).setCellRenderer(leftRenderer);
        table.getColumnModel().getColumn(4).setCellRenderer(leftRenderer);
        table.getColumnModel().getColumn(5).setCellRenderer(leftRenderer);
        
        table.getColumnModel().getColumn(0).setPreferredWidth(20);
        table.getColumnModel().getColumn(1).setPreferredWidth(130);
        table.getColumnModel().getColumn(2).setPreferredWidth(130);
        table.getColumnModel().getColumn(3).setPreferredWidth(100);
        table.getColumnModel().getColumn(4).setPreferredWidth(80);
        
    }

    public Vector<String> vCols() {
        Vector<String> vCols = new Vector<String>();
        vCols.add("ID");
        vCols.add("Ngày Thanh Toán");
        vCols.add("Người lập");
        vCols.add("Bàn");
        vCols.add("Thành tiền");
        vCols.add("Tình trạng");
        return vCols;
    }

    
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Vector<Vector<Object>> vRows() { 
        count = 0;
        sum = 0;
        Vector<Vector<Object>> vRows = new Vector<Vector<Object>>();
        vR = new Vector();
        ArrayList<Bill> flist = modelItem.getList();
        for (Bill item : flist) {
            Vector v = new Vector();
            v.add(item.getIdbill());
            if (item.getThanhtoan() == 1){
                v.add("");
            } else{
                v.add(item.getNgaythanhtoan());
            }
            
            v.add(item.getUsername());
            Ban b = new Ban(item.getIdkhuvuc(), item.getKhuvuc(), item.getBan());
            v.add(b);
            v.add(item.getThanhtien());
            if (item.getThanhtoan() == 1){
                v.add("Đang chờ");
            }
            else{
                v.add("Đã thanh toán");
            }
            v.add(item.getThanhtoan());
            count++;
            sum+=item.getThanhtien();
            vRows.add(v);
            vR.add(v);
        }
        return vRows;

    }

    public int del(Bill b) {
        return modelItem.del(b);
    }

     

    public void loadTable(JTable table, DefaultTableModel model, int d, int m, int y) {
         modelItem = new ModelHoaDon();
        table.setModel(model);
        model.setDataVector(vRows(d,m,y), vCols());
        setWidthHeightTable(table);
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
	private Vector<Vector<Object>> vRows(int d, int m, int y) {
        count =0;
        sum = 0;
        Vector<Vector<Object>> vRows = new Vector<Vector<Object>>();
        vR = new Vector();
        ArrayList<Bill> flist = modelItem.getListToDay(d,m,y,"");
        for (Bill item : flist) {
            Vector<Object> v = new Vector<Object>();
            v.add(item.getIdbill());
            if (item.getThanhtoan() == 1){
                v.add("");
            } else{
                v.add(item.getNgaythanhtoan());
            }
            
            v.add(item.getUsername());
            Ban b = new Ban(item.getIdkhuvuc(), item.getKhuvuc(), item.getBan());
            v.add(b);
            v.add(item.getThanhtien());
            if (item.getThanhtoan() == 1){
                v.add("Đang chờ");
            }
            else{
                v.add("Đã thanh toán");
            }
            v.add(item.getThanhtoan());
            sum+=item.getThanhtien();
            count++;
            vRows.add(v);
            vR.add(v);
        }
        return vRows;
    }

    public void loadTable(JTable table, DefaultTableModel model, String date_giovao_search) {
        modelItem = new ModelHoaDon();
        table.setModel(model);
        model.setDataVector(vRows(date_giovao_search), vCols());
        setWidthHeightTable(table);
    }
    @SuppressWarnings({ "rawtypes", "unchecked" })
	private Vector<Vector<Object>> vRows(String date_giovao_search) {
        count =0;
        sum = 0;
        Vector<Vector<Object>> vRows = new Vector<Vector<Object>>();
        vR = new Vector();
        ArrayList<Bill> flist = modelItem.getListToDay(-1, -1, -1, date_giovao_search);
        for (Bill item : flist) {
            Vector v = new Vector();
            v.add(item.getIdbill());
            if (item.getThanhtoan() == 1) {
                v.add("");
            } else {
                v.add(item.getNgaythanhtoan());
            }

            v.add(item.getUsername());
            Ban b = new Ban(item.getIdkhuvuc(), item.getKhuvuc(), item.getBan());
            v.add(b);
            v.add(item.getThanhtien());
            if (item.getThanhtoan() == 1) {
                v.add("Đang chờ");
            } else {
                v.add("Đã thanh toán");
            }
            v.add(item.getThanhtoan());
            count++;
            sum+=item.getThanhtien();
            vRows.add(v);
            vR.add(v);
        }
        return vRows;
    }

   
}
