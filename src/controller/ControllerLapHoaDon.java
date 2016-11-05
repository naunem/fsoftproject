/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import bean.Bill;
import bean.BillDetail;
import bean.Food;

import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import model.ModelFood;
import model.ModelLapHoaDon;

/**
 *
 * @author HungPham
 */
public class ControllerLapHoaDon {

    ModelLapHoaDon modelItem;
    @SuppressWarnings("rawtypes")
	Vector<Vector> vRows;
    int i = 1;
    int giamsl;
   
    
    @SuppressWarnings("rawtypes")
	public Vector<Vector> getvRows() {
        return vRows;
    }

    @SuppressWarnings("rawtypes")
	public void setvRows(Vector<Vector> vRows) {
        this.vRows = vRows;
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
	public ControllerLapHoaDon() {
        modelItem = new ModelLapHoaDon();
        //System.out.println("lap hoa don");
        vRows = new Vector();
    }
    

    public void loadTable(JTable table, DefaultTableModel model, Bill item) {
        table.setModel(model);
        model.setDataVector(vRows, vCols());
    }

    public Vector<String> vCols() {
        Vector<String> vCols = new Vector<String>();
        vCols.add("ID");
        vCols.add("Tên Thức Uống");
        vCols.add("Giá bán");
        vCols.add("Số lượng");

        return vCols;
    }

    public void DBvRows(Bill fitem) {
        ArrayList<BillDetail> flist = modelItem.getList(fitem);
        for (BillDetail item : flist) {
        	//System.out.println("tai sao chay truoc");
            Vector<Object> v = new Vector<Object>();
            v.add(i++);
            ModelFood modelFood=new ModelFood();
            Food f=modelFood.getFood(item.getIdfood());
            v.add(f);
            v.add(f.getPrice());
            v.add(item.getSoluong());
            vRows.add(v);
        }
    }

    @SuppressWarnings("unchecked")
	public void addVRows(BillDetail item, int method,Food foodlayve) {
        int check = -1;
        for (int i = 0; i < vRows.size(); i++) {
            Food f = (Food) vRows.get(i).get(1);
            int soluong = 0;
            if (f.getMafood() == item.getIdfood()) {
                soluong = (int) vRows.get(i).get(3) + item.getSoluong();
                if(method == 2){
                    soluong = item.getSoluong();
                }
                vRows.get(i).remove(3);
                vRows.get(i).add(soluong);
                check = 1;
                break;
            }
           
        }
        if (check == -1) {
            Vector<Object> v = new Vector<Object>();
            v.add(i++);
            v.add(foodlayve);
            v.add(foodlayve.getPrice());
            v.add(item.getSoluong());
            vRows.add(v);
        }
    }
    

    public int getThanhTien(Bill item) {
        return modelItem.getThanhTien(item);
    }

    @SuppressWarnings("rawtypes")
	public int addDB(Vector<Vector> vRows, Bill fitem) {
        
        return modelItem.addDBDetail(vRows, fitem);
    }
    @SuppressWarnings("rawtypes")
	public int editDB(Vector<Vector> vRows, Bill fitem){
        
        return modelItem.editDB(vRows, fitem);
    }

    public int updateThanhToan(Bill item) {
        return modelItem.updateThanhToan(item);
    }
}
