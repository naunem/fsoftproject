/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import bean.Ban;
import bean.Bill;
import bean.KhuVuc;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import model.ModelViTri;
import view.PnViTri;

/**
 *
 * @author HungPham
 */
public class ControllerViTri {
	ModelViTri modelItem;
	PnViTri pnViTri;
	 public boolean flag = false;

	public ControllerViTri(PnViTri pn) {
		modelItem = new ModelViTri();
		this.pnViTri = pn;
	}

	public ArrayList<KhuVuc> getList() {
		return modelItem.getList();
	}

	public JPanel getPanel(int idkhuvuc) {
		// ModelViTri modelItem = new ModelViTri();
		JPanel pn = new JPanel();
		ArrayList<Ban> alBan = modelItem.getBan(idkhuvuc);
		for (Ban item : alBan) {
			JButton b = new JButton();
			b.setVerticalTextPosition(SwingConstants.BOTTOM);
			b.setHorizontalTextPosition(SwingConstants.CENTER);
			if (item.getTinhtrang() != -1) {
				b.setBackground(Color.GREEN);
				b.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent ex) {

						Bill bill = new Bill(item.getIdkhuvuc(), item.getSoban());
						// System.out.println("tinh
						// trang:"+item.getTinhtrang());
						bill.setIdbill(item.getTinhtrang());
						KhuVuc kv = modelItem.getKhuVucbyID(item.getIdkhuvuc());
						bill.setKhuvuc(kv.getKhuvuc());
						if(flag == false){
							pnViTri.LapHoaDon(bill, 2, b);
							flag =  true;
						}
						
					}
				});
			} else {
				b.setBackground(Color.BLUE);
				b.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent ex) {

						Bill bill = new Bill(item.getIdkhuvuc(), item.getSoban());
						bill.setIdbill(-1);
						KhuVuc kv = modelItem.getKhuVucbyID(item.getIdkhuvuc());
						bill.setKhuvuc(kv.getKhuvuc());
						if(flag == false){
							pnViTri.LapHoaDon(bill, 1, b);
							flag = true;
						}
						
					}
				});
			}

			b.setIcon(new ImageIcon(getClass().getResource("/images/coffee_48.png")));
			b.setFont(new Font("Tahoma", Font.BOLD, 15));
			b.setText(item.getSoban() + "");
			pn.add(b);
		}
		return pn;
	}

	public void loadTable(JTable table, DefaultTableModel model) {
		table.setModel(model);
		model.setDataVector(vRows(), vCols());
	}

	public Vector<String> vCols() {
		Vector<String> vCols = new Vector<String>();
		vCols.add("ID");
		vCols.add("Tên khu vực");
		return vCols;
	}

	@SuppressWarnings("rawtypes")
	public Vector vRows() {
		Vector<Vector<Comparable>> vRows = new Vector<Vector<Comparable>>();
		ArrayList<KhuVuc> flist = modelItem.getList();
		for (KhuVuc item : flist) {
			Vector<Comparable> v = new Vector<Comparable>();
			v.add(item.getIdkhuvuc());
			v.add(item.getKhuvuc());
			vRows.add(v);
		}
		return vRows;

	}

	public int add(KhuVuc item) {
		int result = 0;
		if (isValid(item, "add")) {
			modelItem.add(item);
			result = 1;
		}
		return result;
	}

	public int add(Ban item) {
		int result = 0;
		if (isValid(item, "add")) {

			result = modelItem.add(item);
		}
		return result;
	}

	public int edit(KhuVuc item) {
		int result = 0;
		if (isValid(item, "edit")) {
			modelItem.edit(item);
			result = 1;
		}
		return result;
	}

	public int del(KhuVuc item) {
		int result = 0;
		if (isValid(item, "del")) {
			modelItem.del(item);
			result = 1;
		}
		return result;
	}

	public int del(Ban item) {
		int result = 0;
		if (isValid(item, "del")) {
			modelItem.del(item);
			result = 1;
		}
		return result;
	}

	private boolean isValid(KhuVuc item, String method) {
		switch (method) {
		case "add":
			if ("".equals(item.getKhuvuc())) {
				JOptionPane.showMessageDialog(null, "Vui lòng nhập tên khu vực");
				return false;
			}
			break;
		case "del":
			if (item.getIdkhuvuc() == 0) {
				JOptionPane.showMessageDialog(null, "Vui lòng chọn một khu vực");
				return false;
			}
			break;
		case "edit":
			if (item.getIdkhuvuc() == 0) {
				JOptionPane.showMessageDialog(null, "Vui lòng chọn một khu vực");
				return false;
			}
			if ("".equals(item.getKhuvuc())) {
				JOptionPane.showMessageDialog(null, "Vui lòng nhập khu vực");
				return false;
			}

			break;

		}
		return true;
	}

	private boolean isValid(Ban item, String method) {
		switch (method) {
		case "add":
			if ("".equals(item.getTenkhuvuc())) {
				JOptionPane.showMessageDialog(null, "Vui lòng chọn khu vực");
				return false;
			}
			break;
		case "del":
			if (item.getIdkhuvuc() == 0) {
				JOptionPane.showMessageDialog(null, "Vui lòng chọn một khu vực");
				return false;
			}
			break;
		case "edit":
			if (item.getIdkhuvuc() == 0) {
				JOptionPane.showMessageDialog(null, "Vui lòng chọn một khu vực");
				return false;
			}
			if ("".equals(item.getSoban())) {
				JOptionPane.showMessageDialog(null, "Vui lòng nhập bàn");
				return false;
			}

			break;

		}
		return true;
	}

}
