/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import model.ModelKho;
import bean.Bill;
import bean.BillDetail;
import bean.Food;
import controller.ControllerFood;
import controller.ControllerLapHoaDon;
import jxl.write.Formula;
import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import javax.swing.JSpinner;

/**
 *
 * @author Bi
 */
public class FrLapHoaDon extends javax.swing.JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Creates new form FrLapHoaDon
	 */

	int crowfood = -1, crowDetail = -1;

	DefaultTableModel model;
	DefaultTableModel modelFood;
	DefaultTableModel modelDetail;
	ControllerLapHoaDon controller;
	Bill item;
	PnHoaDon pnHoaDon;
	PnViTri pnViTri;
	int thanhtien;
	int method = 1;
	JButton b;

	/**
	 * @wbp.parser.constructor
	 */
	public FrLapHoaDon(Bill item, int method, PnHoaDon pnHoaDon) {
		this.setIconImage(new ImageIcon(getClass().getResource("/images/coffee_48.png")).getImage());
		this.pnHoaDon = pnHoaDon;
		this.method = method;
		initComponents();
		this.item = item;
		this.setMinimumSize(new Dimension(804, this.getHeight()));
		this.setResizable(false);
		this.setLocation(100, 0);
		JSplitPane sp = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, pnLapHoaDonLeft, PnLapHoaDonCenter);
		sp.setOneTouchExpandable(true);
		getContentPane().add(sp, BorderLayout.CENTER);
		pnLapHoaDonLeft.setMinimumSize(new Dimension(this.getWidth() / 2 - 4, pnLapHoaDonLeft.getSize().height));
		pnLapHoaDonLeftTacVu.setSize(pnLapHoaDonLeft.getSize().width, 150);
		PnLapHoaDonCenter.setMinimumSize(pnLapHoaDonLeft.getMinimumSize());
		pnLapHoaDonCenterTacVu.setSize(PnLapHoaDonCenter.getSize().width, 150);

		model = new DefaultTableModel() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {
				return false;
			}

			public Class<?> getColumnClass(int col) {
				if (col == 0 || col == 3) {
					return Integer.class;
				} else {
					return super.getColumnClass(col);
				}
			}
		};

		modelFood = new DefaultTableModel() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {
				return false;
			}

			public Class<?> getColumnClass(int col) {
				if (col == 0 || col == 3) {
					return Integer.class;
				} else {
					return super.getColumnClass(col);
				}
			}
		};
		modelDetail = new DefaultTableModel() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {
				return false;
			}

			public Class<?> getColumnClass(int col) {
				if (col == 0 || col == 2 || col == 3) {
					return Integer.class;
				} else {
					return super.getColumnClass(col);
				}
			}
		};
		controller = new ControllerLapHoaDon();
		loadTable(null);
		if (method == 2) {
			btBillAdd.setText("Sửa hóa đơn");
			btBillAdd.setIcon(new ImageIcon(getClass().getResource("/images/paper&pencil_48.png")));
		}
		if (method == 3) {
			btBillAdd.setText("Thoát");
			btBillAdd.setIcon(new ImageIcon(getClass().getResource("/images/cancel_48.png")));
			btThemFood.setVisible(false);
			btRemove.setVisible(false);
			btnThayDoi.setVisible(false);
			btThanhToan.setVisible(false);
		}
		loadTbFood();
		controller.DBvRows(item);
		thanhtien = controller.getThanhTien(item);
		tfThanhTien.setText(thanhtien + " VNĐ");
		loadTbDetail();
		lbBan.setText(item.getKhuvuc() + "-" + item.getBan());
	}

	@SuppressWarnings("serial")
	public FrLapHoaDon(Bill item, int method, PnViTri pnViTri, JButton b) {
		this.b = b;
		this.pnViTri = pnViTri;
		this.method = method;
		initComponents();
		this.item = item;
		this.setMinimumSize(new Dimension(804, this.getHeight()));
		this.setResizable(false);
		this.setLocation(100, 0);
		JSplitPane sp = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, pnLapHoaDonLeft, PnLapHoaDonCenter);
		sp.setOneTouchExpandable(true);
		getContentPane().add(sp, BorderLayout.CENTER);
		pnLapHoaDonLeft.setMinimumSize(new Dimension(this.getWidth() / 2 - 4, pnLapHoaDonLeft.getSize().height));
		pnLapHoaDonLeftTacVu.setSize(pnLapHoaDonLeft.getSize().width, 150);
		PnLapHoaDonCenter.setMinimumSize(pnLapHoaDonLeft.getMinimumSize());
		pnLapHoaDonCenterTacVu.setSize(PnLapHoaDonCenter.getSize().width, 150);

		model = new DefaultTableModel() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {
				return false;
			}

			public Class<?> getColumnClass(int col) {
				if (col == 0 || col == 3) {
					return Integer.class;
				} else {
					return super.getColumnClass(col);
				}
			}
		};

		modelFood = new DefaultTableModel() {
			public boolean isCellEditable(int row, int column) {
				return false;
			}

			public Class<?> getColumnClass(int col) {
				if (col == 0 || col == 3) {
					return Integer.class;
				} else {
					return super.getColumnClass(col);
				}
			}
		};
		modelDetail = new DefaultTableModel() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {
				return false;
			}

			public Class<?> getColumnClass(int col) {
				if (col == 0 || col == 2 || col == 3) {
					return Integer.class;
				} else {
					return super.getColumnClass(col);
				}
			}
		};
		controller = new ControllerLapHoaDon();
		if (method == 2) {
			btBillAdd.setText("Sửa hóa đơn");
			btBillAdd.setIcon(new ImageIcon(getClass().getResource("/images/paper&pencil_48.png")));
		}
		if (method == 3) {
			btBillAdd.setText("Thoát");
			btBillAdd.setIcon(new ImageIcon(getClass().getResource("/images/cancel_48.png")));
			btThemFood.setVisible(false);
			btRemove.setVisible(false);
			btThanhToan.setVisible(false);
		}
		if (method == 1) {
			btThanhToan.setVisible(false);
		}
		loadTbFood();
		controller.DBvRows(item);
		thanhtien = controller.getThanhTien(item);
		tfThanhTien.setText(thanhtien + " VNĐ");
		loadTbDetail();
		lbBan.setText(item.getKhuvuc() + "-" + item.getBan());
	}

	private void loadTbFood() {
		ControllerFood controllerFood = new ControllerFood();
		controllerFood.loadTable(tbFood, modelFood, null);
		controllerFood.setWidthHeightTable(tbFood);
	}

	private void loadTbDetail() {
		controller.loadTable(tbDetail, modelDetail, item);

	}

	private void setFormFood() {
		crowfood = tbFood.getSelectedRow();
		tfFoodName.setText(tbFood.getValueAt(crowfood, 1).toString());

		tfFoodGia.setText(tbFood.getValueAt(crowfood, 2).toString());
		spFoodSoluong.setValue(1);
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents() {

		pnLapHoaDonCenterTacVu = new javax.swing.JPanel();
		jLabel6 = new javax.swing.JLabel();
		jLabel7 = new javax.swing.JLabel();
		jTextField3 = new javax.swing.JTextField();
		jLabel8 = new javax.swing.JLabel();
		jTextField4 = new javax.swing.JTextField();
		jLabel9 = new javax.swing.JLabel();
		jTextField5 = new javax.swing.JTextField();
		jButton1 = new javax.swing.JButton();
		jButton2 = new javax.swing.JButton();
		jButton3 = new javax.swing.JButton();
		jComboBox1 = new javax.swing.JComboBox<>();
		jComboBox2 = new javax.swing.JComboBox<>();
		pnLapHoaDonTop = new javax.swing.JPanel();
		jLabel1 = new javax.swing.JLabel();
		pnLapHoaDonLeft = new javax.swing.JPanel();
		pnLapHoaDonLeftTitle = new javax.swing.JPanel();
		jLabel2 = new javax.swing.JLabel();
		pnLapHoaDonLeftTacVu = new javax.swing.JPanel();
		jScrollPane2 = new javax.swing.JScrollPane();
		tbFood = new javax.swing.JTable();
		pnLapHoaDonLeftTable = new javax.swing.JPanel();
		jLabel3 = new javax.swing.JLabel();
		jLabel4 = new javax.swing.JLabel();
		tfFoodName = new javax.swing.JTextField();
		btThemFood = new javax.swing.JButton();
		tfFoodGia = new javax.swing.JTextField();
		jLabel15 = new javax.swing.JLabel();
		spFoodSoluong = new javax.swing.JSpinner();
		PnLapHoaDonCenter = new javax.swing.JPanel();
		lbTitle = new javax.swing.JPanel();
		jLabel5 = new javax.swing.JLabel();
		jPanel2 = new javax.swing.JPanel();
		jScrollPane1 = new javax.swing.JScrollPane();
		tbDetail = new javax.swing.JTable();
		jPanel1 = new javax.swing.JPanel();
		jPanel3 = new javax.swing.JPanel();
		jLabel10 = new javax.swing.JLabel();
		tfDetailFood = new javax.swing.JTextField();
		jLabel11 = new javax.swing.JLabel();
		btBillAdd = new javax.swing.JButton();
		btRemove = new javax.swing.JButton();
		btThanhToan = new javax.swing.JButton();
		jLabel13 = new javax.swing.JLabel();
		jLabel14 = new javax.swing.JLabel();
		tfThanhTien = new javax.swing.JTextField();
		lbBan = new javax.swing.JLabel();
		pnTimKiem = new javax.swing.JPanel();
		JPanel pnTable = new javax.swing.JPanel();
		spThayDoi = new JSpinner();

		jLabel6.setText("Bàn:");

		jLabel7.setText("Thành Tiền:");

		jTextField3.setEditable(false);

		jLabel8.setText("Món:");

		jTextField4.setEditable(false);

		jLabel9.setText("Số lượng:");

		jTextField5.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jTextField5ActionPerformed(evt);
			}
		});

		jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/cancel_16.png"))); // NOI18N
		jButton1.setText("Bỏ");

		jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/accepted_48.png"))); // NOI18N
		jButton2.setText("Thanh toán");

		jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/add_48.png"))); // NOI18N
		jButton3.setText("Thêm mới hóa đơn");

		jComboBox1.setModel(
				new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

		jComboBox2.setModel(
				new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

		javax.swing.GroupLayout pnLapHoaDonCenterTacVuLayout = new javax.swing.GroupLayout(pnLapHoaDonCenterTacVu);
		pnLapHoaDonCenterTacVu.setLayout(pnLapHoaDonCenterTacVuLayout);
		pnLapHoaDonCenterTacVuLayout.setHorizontalGroup(pnLapHoaDonCenterTacVuLayout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(pnLapHoaDonCenterTacVuLayout.createSequentialGroup().addContainerGap()
						.addGroup(pnLapHoaDonCenterTacVuLayout
								.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jLabel8)
								.addComponent(jLabel9).addComponent(jLabel6).addComponent(jLabel7))
						.addGap(33, 33, 33)
						.addGroup(pnLapHoaDonCenterTacVuLayout
								.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(
										jTextField4)
								.addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
										pnLapHoaDonCenterTacVuLayout.createSequentialGroup()
												.addGroup(pnLapHoaDonCenterTacVuLayout
														.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
														.addGroup(pnLapHoaDonCenterTacVuLayout.createSequentialGroup()
																.addComponent(jTextField5,
																		javax.swing.GroupLayout.PREFERRED_SIZE, 48,
																		javax.swing.GroupLayout.PREFERRED_SIZE)
																.addPreferredGap(
																		javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
																.addComponent(jButton1))
														.addComponent(jTextField3,
																javax.swing.GroupLayout.PREFERRED_SIZE, 128,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addGroup(pnLapHoaDonCenterTacVuLayout.createSequentialGroup()
																.addComponent(jComboBox1,
																		javax.swing.GroupLayout.PREFERRED_SIZE,
																		javax.swing.GroupLayout.DEFAULT_SIZE,
																		javax.swing.GroupLayout.PREFERRED_SIZE)
																.addPreferredGap(
																		javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
																.addComponent(jComboBox2,
																		javax.swing.GroupLayout.PREFERRED_SIZE,
																		javax.swing.GroupLayout.DEFAULT_SIZE,
																		javax.swing.GroupLayout.PREFERRED_SIZE)))
												.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 85,
														Short.MAX_VALUE)
												.addGroup(pnLapHoaDonCenterTacVuLayout
														.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING,
																false)
														.addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
														.addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
												.addGap(20, 20, 20)))));
		pnLapHoaDonCenterTacVuLayout.setVerticalGroup(pnLapHoaDonCenterTacVuLayout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(pnLapHoaDonCenterTacVuLayout.createSequentialGroup().addContainerGap()
						.addGroup(pnLapHoaDonCenterTacVuLayout
								.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jLabel8)
								.addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
						.addGroup(pnLapHoaDonCenterTacVuLayout
								.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addGroup(pnLapHoaDonCenterTacVuLayout.createSequentialGroup()
										.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(pnLapHoaDonCenterTacVuLayout
												.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(jLabel9)
												.addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addComponent(jButton1))
										.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED,
												javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addGroup(pnLapHoaDonCenterTacVuLayout
												.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(jLabel6)
												.addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
										.addGroup(pnLapHoaDonCenterTacVuLayout
												.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addComponent(jLabel7))
										.addGap(51, 51, 51))
								.addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
										pnLapHoaDonCenterTacVuLayout.createSequentialGroup()
												.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19,
														Short.MAX_VALUE)
												.addComponent(jButton3)
												.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
												.addComponent(jButton2).addContainerGap()))));

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		addWindowListener(new java.awt.event.WindowAdapter() {
			public void windowClosing(java.awt.event.WindowEvent evt) {
				formWindowClosing(evt);
			}
		});

		jLabel1.setBackground(new java.awt.Color(0, 51, 204));
		jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
		jLabel1.setForeground(new java.awt.Color(0, 51, 204));
		jLabel1.setText("LẬP HÓA ĐƠN MỚI");
		pnLapHoaDonTop.add(jLabel1);

		getContentPane().add(pnLapHoaDonTop, java.awt.BorderLayout.PAGE_START);

		pnLapHoaDonLeft.setLayout(new java.awt.BorderLayout());

		pnLapHoaDonLeftTitle.setBackground(new java.awt.Color(255, 255, 204));
		pnLapHoaDonLeftTitle.setForeground(new java.awt.Color(51, 51, 255));

		jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
		jLabel2.setForeground(new java.awt.Color(0, 0, 204));
		jLabel2.setText("DANH SÁCH ĐỒ UỐNG");
		pnLapHoaDonLeftTitle.add(jLabel2);

		pnLapHoaDonLeft.add(pnLapHoaDonLeftTitle, java.awt.BorderLayout.PAGE_START);

		pnLapHoaDonLeftTacVu.setLayout(new java.awt.BorderLayout());
		pnTimKiem.setLayout(new FlowLayout());

		// search food
		jLabel6.setText("Tên thức uống");

		JLabel lbTimKiem = new JLabel();
		lbTimKiem.setText("Tên thức uống:");
		pnTimKiem.add(lbTimKiem);

		tfFoodNameFind = new JTextField(30);
		tfFoodNameFind.setText("");
		tfFoodNameFind.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				tfFoodNameFindActionPerformed(evt);
			}
		});
		pnTimKiem.add(tfFoodNameFind);

		// pnFoodInTimKiem.setBackground(new java.awt.Color(255, 255, 255));

		JButton btFoodFind = new JButton();

		btFoodFind.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/search-icon.png"))); // NOI18N
		btFoodFind.setText("Tìm kiếm");
		btFoodFind.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btFoodFindActionPerformed(evt);
			}
		});

		pnTimKiem.add(btFoodFind);

		tbFood.setModel(
				new javax.swing.table.DefaultTableModel(
						new Object[][] { { null, null, null, null }, { null, null, null, null },
								{ null, null, null, null }, { null, null, null, null } },
						new String[] { "Title 1", "Title 2", "Title 3", "Title 4" }));
		tbFood.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				tbFoodMouseClicked(evt);
			}

			public void mouseEntered(java.awt.event.MouseEvent evt) {
				tbFoodMouseEntered(evt);
			}

			public void mousePressed(java.awt.event.MouseEvent evt) {
				tbFoodMousePressed(evt);
			}

			public void mouseReleased(java.awt.event.MouseEvent evt) {
				tbFoodMouseReleased(evt);
			}
		});
		tbFood.addKeyListener(new java.awt.event.KeyAdapter() {
			public void keyReleased(java.awt.event.KeyEvent evt) {
				tbFoodKeyReleased(evt);
			}
		});

		jScrollPane2.setViewportView(tbFood);
		pnLapHoaDonLeftTacVu.add(pnTimKiem, BorderLayout.NORTH);
		pnLapHoaDonLeftTacVu.add(jScrollPane2, java.awt.BorderLayout.CENTER);

		jLabel3.setText("Số lượng:");

		jLabel4.setText("Món:");

		tfFoodName.setEditable(false);

		btThemFood.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/add-icon.gif"))); // NOI18N
		btThemFood.setText("Thêm món");
		btThemFood.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btThemFoodActionPerformed(evt);
			}
		});

		tfFoodGia.setEditable(false);

		jLabel15.setText("Giá bán:");

		javax.swing.GroupLayout pnLapHoaDonLeftTableLayout = new javax.swing.GroupLayout(pnLapHoaDonLeftTable);
		pnLapHoaDonLeftTable.setLayout(pnLapHoaDonLeftTableLayout);
		pnLapHoaDonLeftTableLayout.setHorizontalGroup(pnLapHoaDonLeftTableLayout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(pnLapHoaDonLeftTableLayout.createSequentialGroup().addGap(20, 20, 20)
						.addGroup(pnLapHoaDonLeftTableLayout
								.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jLabel4)
								.addComponent(jLabel3).addComponent(jLabel15))
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
						.addGroup(pnLapHoaDonLeftTableLayout
								.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(tfFoodName)
								.addGroup(pnLapHoaDonLeftTableLayout.createSequentialGroup()
										.addComponent(spFoodSoluong, javax.swing.GroupLayout.PREFERRED_SIZE, 59,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(40, 40, 40).addComponent(btThemFood).addGap(0, 218, Short.MAX_VALUE))
								.addComponent(tfFoodGia))
						.addContainerGap()));
		pnLapHoaDonLeftTableLayout.setVerticalGroup(pnLapHoaDonLeftTableLayout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnLapHoaDonLeftTableLayout.createSequentialGroup()
						.addGap(0, 0, 0)
						.addGroup(pnLapHoaDonLeftTableLayout
								.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(tfFoodName, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(jLabel4))
						.addGap(9, 9, 9)
						.addGroup(pnLapHoaDonLeftTableLayout
								.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(tfFoodGia, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(jLabel15))
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addGroup(pnLapHoaDonLeftTableLayout
								.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jLabel3)
								.addComponent(btThemFood).addComponent(spFoodSoluong,
										javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.PREFERRED_SIZE))
						.addContainerGap(117, Short.MAX_VALUE)));

		pnLapHoaDonLeftTacVu.add(pnLapHoaDonLeftTable, java.awt.BorderLayout.PAGE_END);

		pnLapHoaDonLeft.add(pnLapHoaDonLeftTacVu, java.awt.BorderLayout.CENTER);

		getContentPane().add(pnLapHoaDonLeft, java.awt.BorderLayout.LINE_START);

		PnLapHoaDonCenter.setLayout(new java.awt.BorderLayout());

		lbTitle.setBackground(new java.awt.Color(255, 255, 204));

		jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
		jLabel5.setForeground(new java.awt.Color(0, 0, 255));
		jLabel5.setText("HÓA ĐƠN");
		lbTitle.add(jLabel5);

		PnLapHoaDonCenter.add(lbTitle, java.awt.BorderLayout.PAGE_START);

		jPanel2.setLayout(new java.awt.BorderLayout());

		tbDetail.setModel(
				new javax.swing.table.DefaultTableModel(
						new Object[][] { { null, null, null, null }, { null, null, null, null },
								{ null, null, null, null }, { null, null, null, null } },
						new String[] { "Title 1", "Title 2", "Title 3", "Title 4" }));
		tbDetail.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				tbDetailMouseClicked(evt);
			}

			public void mouseEntered(java.awt.event.MouseEvent evt) {
				tbDetailMouseEntered(evt);
			}

			public void mousePressed(java.awt.event.MouseEvent evt) {
				tbDetailMousePressed(evt);
			}
		});
		tbDetail.addKeyListener(new java.awt.event.KeyAdapter() {
			public void keyReleased(java.awt.event.KeyEvent evt) {
				tbDetailKeyReleased(evt);
			}
		});
		jScrollPane1.setViewportView(tbDetail);

		jPanel2.add(jScrollPane1, java.awt.BorderLayout.CENTER);

		jLabel10.setText("Món");

		tfDetailFood.setEditable(false);

		jLabel11.setText("Số lượng");

		btBillAdd.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
		btBillAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/add_48.png"))); // NOI18N
		btBillAdd.setText("Thêm hóa đơn");
		btBillAdd.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btBillAddActionPerformed(evt);
			}
		});

		btRemove.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/cancel_16.png"))); // NOI18N
		btRemove.setText("Bỏ");
		btRemove.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
		btRemove.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btRemoveActionPerformed(evt);
			}
		});

		btThanhToan.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
		btThanhToan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/accepted_48.png"))); // NOI18N
		btThanhToan.setText("Thanh toán");
		btThanhToan.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btThanhToanActionPerformed(evt);

			}
		});

		jLabel13.setText("Bàn:");

		jLabel14.setText("Thành tiền:");

		tfThanhTien.setEditable(false);

		lbBan.setText("jLabel16");

		// giảm số lượng thức uống
		btnThayDoi = new JButton("Thay đổi");
		btnThayDoi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/cancel_16.png"))); // NOI18N
		btnThayDoi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnThayDoiActionPerformed(arg0);
			}
		});

		javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
		jPanel3Layout.setHorizontalGroup(jPanel3Layout.createParallelGroup(Alignment.TRAILING)
				.addGroup(jPanel3Layout.createSequentialGroup().addContainerGap()
						.addGroup(jPanel3Layout.createParallelGroup(Alignment.LEADING).addComponent(jLabel14)
								.addComponent(jLabel10).addComponent(jLabel11).addComponent(jLabel13,
										GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE))
						.addGap(8)
						.addGroup(jPanel3Layout.createParallelGroup(Alignment.LEADING)
								.addComponent(lbBan, GroupLayout.PREFERRED_SIZE, 92, GroupLayout.PREFERRED_SIZE)
								.addGroup(jPanel3Layout
										.createSequentialGroup().addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(tfDetailFood, GroupLayout.DEFAULT_SIZE, 233, Short.MAX_VALUE))
								.addGroup(jPanel3Layout.createSequentialGroup()
										.addComponent(spThayDoi, GroupLayout.PREFERRED_SIZE, 59,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.UNRELATED).addComponent(btnThayDoi)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(btRemove, GroupLayout.DEFAULT_SIZE, 65, Short.MAX_VALUE))
								.addComponent(tfThanhTien, GroupLayout.DEFAULT_SIZE, 233, Short.MAX_VALUE))
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addGroup(jPanel3Layout.createParallelGroup(Alignment.LEADING, false)
								.addComponent(btThanhToan, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE)
								.addComponent(btBillAdd, GroupLayout.DEFAULT_SIZE, 223, Short.MAX_VALUE))));
		jPanel3Layout
				.setVerticalGroup(
						jPanel3Layout.createParallelGroup(Alignment.LEADING).addGroup(jPanel3Layout
								.createSequentialGroup().addContainerGap().addGroup(jPanel3Layout
										.createParallelGroup(Alignment.LEADING).addGroup(jPanel3Layout
												.createSequentialGroup()
												.addGroup(jPanel3Layout.createParallelGroup(Alignment.BASELINE)
														.addComponent(tfDetailFood, GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
														.addComponent(jLabel10))
												.addPreferredGap(ComponentPlacement.RELATED)
												.addGroup(jPanel3Layout.createParallelGroup(Alignment.BASELINE)
														.addComponent(spThayDoi, GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
														.addComponent(jLabel11, GroupLayout.PREFERRED_SIZE, 14,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(btnThayDoi).addComponent(btRemove))
												.addPreferredGap(ComponentPlacement.RELATED)
												.addGroup(jPanel3Layout.createParallelGroup(Alignment.BASELINE)
														.addComponent(lbBan).addComponent(jLabel13)))
										.addComponent(btBillAdd, GroupLayout.PREFERRED_SIZE, 71,
												GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(jPanel3Layout.createParallelGroup(Alignment.LEADING)
										.addGroup(jPanel3Layout.createParallelGroup(Alignment.BASELINE)
												.addComponent(tfThanhTien, GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addComponent(jLabel14))
										.addComponent(btThanhToan, GroupLayout.PREFERRED_SIZE, 67,
												GroupLayout.PREFERRED_SIZE))
								.addContainerGap(20, Short.MAX_VALUE)));
		jPanel3.setLayout(jPanel3Layout);

		javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
		jPanel1.setLayout(jPanel1Layout);
		jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(jPanel1Layout.createSequentialGroup()
						.addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(0, 0, Short.MAX_VALUE)));
		jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
						jPanel1Layout.createSequentialGroup().addGap(0, 0, Short.MAX_VALUE).addComponent(jPanel3,
								javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
								javax.swing.GroupLayout.PREFERRED_SIZE)));

		jPanel2.add(jPanel1, java.awt.BorderLayout.PAGE_END);

		PnLapHoaDonCenter.add(jPanel2, java.awt.BorderLayout.CENTER);

		getContentPane().add(PnLapHoaDonCenter, java.awt.BorderLayout.CENTER);

		pack();
	}// </editor-fold>

	protected void btnThayDoiActionPerformed(ActionEvent arg0) {
		thayDoiSoLuong();
	}

	protected void tfFoodNameFindActionPerformed(ActionEvent evt) {
		// TODO Auto-generated method stub

	}

	protected void btFoodFindActionPerformed(ActionEvent evt) {
		// TODO Auto-generated method stub
		int id = 0;
		Food foodFind = new Food(id, tfFoodNameFind.getText(), 0, "");
		loadTable(foodFind);
	}

	public void loadTable(Food item) {
		controller.loadTableSearch(tbFood, model, item);
		controller.setWidthHeightTable(tbFood);
	}

	private void jTextField5ActionPerformed(java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:
	}

	private void tbFoodKeyReleased(java.awt.event.KeyEvent evt) {
		if (method != 3)
			setFormFood();
	}

	private void tbFoodMousePressed(java.awt.event.MouseEvent evt) {
		if (method != 3)
			setFormFood();
	}

	private void addFood() {
		if (crowfood != -1) {
			int id = Integer.parseInt(tbFood.getValueAt(crowfood, 0).toString());
			int sl = (Integer) spFoodSoluong.getValue();
			ModelKho mdkho = new ModelKho();
			if (mdkho.checkKho(id, sl) == 0) {
				JOptionPane.showMessageDialog(null, "Hết số lượng hàng trong kho");
			} else {
				System.out.println("vo day chua :");
				Food food = new Food(id, tbFood.getValueAt(crowfood, 1).toString());
				food.setPrice(Integer.parseInt(tbFood.getValueAt(crowfood, 2).toString()));
				Integer soluong = (Integer) spFoodSoluong.getValue();

				if (soluong <= 0) {
					System.out.println("khong duoc nhap so luong < 0");
					JOptionPane.showMessageDialog(null, "Số lượng không được < 0");
				} else {
					System.out.println("nhan ne:");
					BillDetail item = new BillDetail(1, id, soluong);
					controller.addVRows(item, 1, food);
					thanhtien += Integer.parseInt(tfFoodGia.getText()) * soluong;
					tfThanhTien.setText(thanhtien + " VNĐ");
					loadTbDetail();
				}
			}
		}
	}

	// giam so luong
	private void thayDoiSoLuong() {
		if (crowDetail != -1) {

			int soluong = 0;// gia tri moi
			try {
				soluong = Integer.parseInt(spThayDoi.getValue().toString());
			} catch (Exception e) {
				JOptionPane.showMessageDialog(this, "ban vui long dien so nguyen duong");
			}
			int t = (Integer) tbDetail.getValueAt(crowDetail, 3);// gia tri cu
			ModelKho mdkho = new ModelKho();
			Food f = (Food) tbDetail.getValueAt(crowDetail, 1);
			int ta = mdkho.checkKho(f.getMafood(), soluong);
			if (ta == 0) {
				JOptionPane.showMessageDialog(null, "Đã hết số lượng hàng trong kho");
			} else {
				if (soluong >= 0) {
					thanhtien += (int) tbDetail.getValueAt(crowDetail, 2) * (soluong - t);
					tfThanhTien.setText(thanhtien + " VNĐ");

					if (soluong >= 1) {
						Vector v = new Vector<>();
						v.add(tbDetail.getValueAt(crowDetail, 0));
						v.add(tbDetail.getValueAt(crowDetail, 1));
						v.add(tbDetail.getValueAt(crowDetail, 2));
						v.add(soluong);
						controller.getvRows().remove(crowDetail);
						controller.getvRows().add(crowDetail, v);
					} else {
						controller.getvRows().remove(crowDetail);
					}
					loadTbDetail();
				} else {
					JOptionPane.showMessageDialog(this, "Số lượng phải lớn hơn 0");
				}
			}
		}
	}

	private void btThemFoodActionPerformed(java.awt.event.ActionEvent evt) {
		addFood();
	}

	private void formWindowClosing(java.awt.event.WindowEvent evt) {

	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void btBillAddActionPerformed(java.awt.event.ActionEvent evt) {
		if (method == 2) {
			Vector<Vector> vRows = controller.getvRows();
			controller.editDB(vRows, item);
			controller.setvRows(new Vector());
			if (pnHoaDon != null) {
				pnHoaDon.loadTable();
			} else {
				pnViTri.addPanel();
			}
			this.dispose();

		} else if (method == 3) {

			this.dispose();
		} else {

			Vector<Vector> vRows = controller.getvRows();
			controller.setvRows(new Vector());
			controller.addDB(vRows, item);
			if (pnHoaDon != null) {
				pnHoaDon.loadTable();
			} else {
				// b.setBackground(Color.RED);
				pnViTri.addPanel();
			}
			this.dispose();

		}
	}

	private void setFormDetail() {
		crowDetail = tbDetail.getSelectedRow();
		tfDetailFood.setText(tbDetail.getValueAt(crowDetail, 1).toString());

		spThayDoi.setValue(tbDetail.getValueAt(crowDetail, 3));

	}

	private void tbDetailKeyReleased(java.awt.event.KeyEvent evt) {
		if (method != 3)
			setFormDetail();
	}

	private void tbDetailMousePressed(java.awt.event.MouseEvent evt) {
		if (method != 3)
			setFormDetail();
	}

	private void removeFood() {
		if (crowDetail != -1) {
			int soluong = (Integer) tbDetail.getValueAt(crowDetail, 3);
			thanhtien -= (int) tbDetail.getValueAt(crowDetail, 2) * soluong;
			tfThanhTien.setText(thanhtien + " VNĐ");
			controller.getvRows().remove(crowDetail);
			loadTbDetail();
		}
	}

	private void btRemoveActionPerformed(java.awt.event.ActionEvent evt) {
		removeFood();
	}

	private void tbFoodMouseClicked(java.awt.event.MouseEvent evt) {
		addFood();
	}

	private void tbDetailMouseClicked(java.awt.event.MouseEvent evt) {
		// removeFood();
	}

	private void tbDetailMouseEntered(java.awt.event.MouseEvent evt) {

	}

	private void btThanhToanActionPerformed(java.awt.event.ActionEvent evt) {
		@SuppressWarnings("rawtypes")
		Vector<Vector> vRows = controller.getvRows();
		controller.editDB(vRows, item);
		System.out.println("toi se noi cho em nghe:" + item.getIdbill());
		controller.updateThanhToan(item);
		ModelKho modelKho = new ModelKho();
		for (int i = 0; i < vRows.size(); i++) {
			Food food = (Food) vRows.get(i).get(1);
			System.out.println("toi khong tin");
			System.out.println("toi muon biet:" + (int) vRows.get(i).get(3));
			modelKho.updateKho(food.getMafood(), -(int) vRows.get(i).get(3));
		}
		if (pnHoaDon != null) {
			pnHoaDon.loadTable();
		} else if (pnViTri != null) {
			pnViTri.addPanel();
		}
		this.dispose();
	}

	private void tbFoodMouseEntered(java.awt.event.MouseEvent evt) {
		// TODO add your handling code here:
	}

	private void tbFoodMouseReleased(java.awt.event.MouseEvent evt) {
		// TODO add your handling code here:
	}

	/**
	 * @param args
	 *            the command line arguments
	 */

	// Variables declaration - do not modify
	private javax.swing.JPanel PnLapHoaDonCenter;
	private javax.swing.JButton btBillAdd;
	private javax.swing.JButton btRemove;
	private javax.swing.JButton btThanhToan;
	private javax.swing.JButton btThemFood;
	private javax.swing.JButton jButton1;
	private javax.swing.JButton jButton2;
	private javax.swing.JButton jButton3;
	private javax.swing.JComboBox<String> jComboBox1;
	private javax.swing.JComboBox<String> jComboBox2;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel10;
	private javax.swing.JLabel jLabel11;
	private javax.swing.JLabel jLabel13;
	private javax.swing.JLabel jLabel14;
	private javax.swing.JLabel jLabel15;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JLabel jLabel3;
	private javax.swing.JLabel jLabel4;
	private javax.swing.JLabel jLabel5;
	private javax.swing.JLabel jLabel6;
	private javax.swing.JLabel jLabel7;
	private javax.swing.JLabel jLabel8;
	private javax.swing.JLabel jLabel9;
	private javax.swing.JPanel jPanel1;
	private javax.swing.JPanel jPanel2;
	private javax.swing.JPanel jPanel3;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JScrollPane jScrollPane2;
	private javax.swing.JTextField jTextField3;
	private javax.swing.JTextField jTextField4;
	private javax.swing.JTextField jTextField5;
	private javax.swing.JLabel lbBan;
	private javax.swing.JPanel lbTitle;
	private javax.swing.JPanel pnLapHoaDonCenterTacVu;
	private javax.swing.JPanel pnLapHoaDonLeft;
	private javax.swing.JPanel pnLapHoaDonLeftTable;
	private javax.swing.JPanel pnLapHoaDonLeftTacVu;
	private javax.swing.JPanel pnLapHoaDonLeftTitle;
	private javax.swing.JPanel pnLapHoaDonTop;
	private javax.swing.JSpinner spFoodSoluong;
	private javax.swing.JTable tbDetail;
	private javax.swing.JTable tbFood;
	private javax.swing.JTextField tfDetailFood;
	private javax.swing.JTextField tfFoodGia;
	private javax.swing.JTextField tfFoodName;
	private javax.swing.JTextField tfThanhTien;
	private javax.swing.JPanel pnTimKiem;
	private javax.swing.JTextField tfFoodNameFind;
	private javax.swing.JButton btnThayDoi;
	private javax.swing.JSpinner spThayDoi;
}
