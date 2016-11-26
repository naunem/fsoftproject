/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import bean.Bill;
import bean.BillDetail;
import bean.Food;
import library.LibraryDimension;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import model.ModelFood;
import model.ModelKho;
import model.ModelLapHoaDon;

/**
 *
 * @author HungPham
 */
public class ControllerLapHoaDon {

	ModelFood modelFood;
	ModelLapHoaDon modelItem;
	@SuppressWarnings("rawtypes")
	Vector<Vector> vRows;
	Vector<Vector> vRowsChange;
	int i = 1;
	int giamsl;

	@SuppressWarnings("rawtypes")
	public Vector<Vector> getvRowsChange() {
		return vRowsChange;
	}

	@SuppressWarnings("rawtypes")
	public Vector<Vector<Comparable>> vRowsChange(Food fitem) {
		Vector<Vector<Comparable>> vRowsChange = new Vector<Vector<Comparable>>();
		modelFood.foodview();
		ArrayList<Food> flist = modelItem.getList(fitem);
		for (Food item : flist) {
			Vector<Comparable> v = new Vector<Comparable>();
			v.add(item.getFoodname());
			v.add(item.getPrice());
			vRows.add(v);
		}
		return vRowsChange;

	}

	@SuppressWarnings("rawtypes")
	public void setvRowsChange(Vector<Vector> vRowsChange) {
		this.vRowsChange = vRowsChange;
	}

	@SuppressWarnings("rawtypes")
	public Vector<Vector> getvRows() {
		return vRows;
	}

	@SuppressWarnings("rawtypes")
	public Vector<Vector<Comparable>> vRows(Food fitem) {
		Vector<Vector<Comparable>> vRows = new Vector<Vector<Comparable>>();
		modelFood.foodview();
		ArrayList<Food> flist = modelItem.getList(fitem);
		for (Food item : flist) {
			Vector<Comparable> v = new Vector<Comparable>();
			v.add(item.getMafood());
			v.add(item.getFoodname());
			v.add(item.getPrice());
			vRows.add(v);
		}
		return vRows;

	}

	@SuppressWarnings("rawtypes")
	public void setvRows(Vector<Vector> vRows) {
		this.vRows = vRows;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ControllerLapHoaDon() {
		modelItem = new ModelLapHoaDon();
		modelFood = new ModelFood();
		// System.out.println("lap hoa don");
		vRows = new Vector();
	}

	public void loadTableSearch(JTable table, DefaultTableModel model, Food fitem) {
		table.setModel(model);
		model.setDataVector(vRowsSearch(fitem), vColsSearch());
		setWidthHeightTable(table);
	}

	public void loadTable(JTable table, DefaultTableModel model, Bill item) {
		table.setModel(model);
		model.setDataVector(vRows, vCols());
		setWidthHeightTable(table);
	}

	public void setWidthHeightTable(JTable table) {
		table.getTableHeader()
				.setPreferredSize(new Dimension(table.getPreferredSize().width, LibraryDimension.HOADON_HEAD_HEIGHT));
		table.setRowHeight(22);

		DefaultTableCellRenderer leftRenderer = new DefaultTableCellRenderer();
		leftRenderer.setHorizontalAlignment(SwingConstants.LEFT);
		table.getColumnModel().getColumn(0).setCellRenderer(leftRenderer);
		table.getColumnModel().getColumn(1).setCellRenderer(leftRenderer);
		table.getColumnModel().getColumn(2).setCellRenderer(leftRenderer);

		table.getColumnModel().getColumn(0).setPreferredWidth(20);
		table.getColumnModel().getColumn(1).setPreferredWidth(130);
		table.getColumnModel().getColumn(2).setPreferredWidth(130);

	}

	public Vector<String> vColsSearch() {
		Vector<String> vCols = new Vector<String>();
		vCols.add("ID");
		vCols.add("Thức Uống");
		vCols.add("Giá bán");

		return vCols;
	}

	public Vector<String> vCols() {
		Vector<String> vCols = new Vector<String>();
		vCols.add("STT");
		vCols.add("Tên Thức Uống");
		vCols.add("Giá bán");
		vCols.add("Số lượng");

		return vCols;
	}

	@SuppressWarnings("rawtypes")
	public Vector<Vector<Comparable>> vRowsSearch(Food fitem) {
		Vector<Vector<Comparable>> vRows = new Vector<Vector<Comparable>>();
		modelFood.foodview();
		ArrayList<Food> flist = modelFood.getList(fitem);
		for (Food item : flist) {
			Vector<Comparable> v = new Vector<Comparable>();
			v.add(item.getMafood());
			v.add(item.getFoodname());
			v.add(item.getPrice());
			v.add(300);
			vRows.add(v);
		}
		return vRows;

	}

	public void DBvRows(Bill fitem) {
		ArrayList<BillDetail> flist = modelItem.getList(fitem);
		for (BillDetail item : flist) {
			// System.out.println("tai sao chay truoc");
			Vector<Object> v = new Vector<Object>();
			v.add(i++);
			ModelFood modelFood = new ModelFood();
			Food f = modelFood.getFood(item.getIdfood());
			v.add(f);
			v.add(f.getPrice());
			v.add(item.getSoluong());
			vRows.add(v);
		}
	}

	@SuppressWarnings("unchecked")
	public void addVRows(BillDetail item, int method, Food foodlayve) {
		int check = -1;
		for (int i = 0; i < vRows.size(); i++) {
			Food f = (Food) vRows.get(i).get(1);
			int soluong = 0;
			if (f.getMafood() == item.getIdfood()) {
				soluong = (int) vRows.get(i).get(3) + item.getSoluong();
				ModelKho mdkho = new ModelKho();
				int t = mdkho.checkKho(f.getMafood(), soluong);		
				if (mdkho.checkKho(f.getMafood(), soluong) == 0) {
					check = 1;
					JOptionPane.showMessageDialog(null, "Đã hết số lượng hàng trong kho");
					break;
				} else {
					check = 1;
					if (method == 2) {
						soluong = item.getSoluong();
					}
					vRows.get(i).remove(3);
					vRows.get(i).add(soluong);
					break;
				}
			}
		}
			if (check != 1) {
				Vector<Object> v = new Vector<Object>();
				v.add(i++);
				v.add(foodlayve);
				v.add(foodlayve.getPrice());
				v.add(item.getSoluong());
				vRows.add(v);
			}
	}

	public void changeVRows(BillDetail item, int method, Food foodlayve) {
		int check = -1;
		for (int i = 0; i < vRows.size(); i++) {
			Food f = (Food) vRows.get(i).get(1);
			int soluong = 0;
			if (f.getMafood() == item.getIdfood()) {
				soluong = (int) item.getSoluong();
				if (method == 2) {
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
	public int editDB(Vector<Vector> vRows, Bill fitem) {

		return modelItem.editDB(vRows, fitem);
	}

	public int updateThanhToan(Bill item) {
		return modelItem.updateThanhToan(item);
	}

}
