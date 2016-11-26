/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;

import library.LibraryDimension;
import library.LibraryName;
import view.inc.PnLeft;
import bean.Ban;
import bean.Bill;
import bean.KhuVuc;
import bean.User;
import controller.ControllerViTri;

/**
 *
 * @author Bi
 */
public class PnViTri extends javax.swing.JPanel implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Creates new form PnViTri
	 */
	ControllerViTri controller;

	DefaultTableModel model;
	JTabbedPane tabbedPane;
	int crow = -1;
	PnLeft pn;
	int del = 1;
	FrMain fr;
	User login;

	public PnViTri(String title, PnLeft pn, FrMain fr) {
		this.pn = pn;
		this.fr = fr;
		login = fr.login;
		initComponents();
		LibraryName lbName = new LibraryName();
		tfTitleCenter.setForeground(lbName.getColor_Title_PnCenter());
		tfTitleCenter.setFont(lbName.getFont_Title_PnCenter());
		tfTitleCenter.setText(title);
		pnViTriTop.setBackground(lbName.getColor_background());
		pnViTriCenter.setBackground(lbName.getColor_background());
		pnViTriLeft.setBackground(lbName.getColor_background());
		JSplitPane sp = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, pnViTriLeft, pnViTriCenter);
		sp.setOneTouchExpandable(true);
		add(pnViTriTop, BorderLayout.NORTH);
		add(sp, BorderLayout.CENTER);
		pnViTriLeft.setMinimumSize(new Dimension(LibraryDimension.PANEL_LEFT_WIDTH, pnViTriLeft.getSize().height));

		tabbedPane = new JTabbedPane();
		model = new DefaultTableModel() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {
				return false;
			}

			public Class<?> getColumnClass(int col) {
				if (col == 0 || col == 1) {
					return Integer.class;
				} else {
					return super.getColumnClass(col);
				}
			}
		};
		pnViTriCenter.add(tabbedPane, java.awt.BorderLayout.CENTER);

		/* create three JPanel, which is content of tabs */
		controller = new ControllerViTri(this);
		btKhuVucThem.addActionListener(this);
		btKhuVucSua.addActionListener(this);
		btKhuVucNhapLai.addActionListener(this);
		btKhuVucXoa.addActionListener(this);
		btBanThem.addActionListener(this);
		btBanXoa.addActionListener(this);
		/* add three tab with three JPanel */
		loadTable();
		if(login.getId_chucvu() == 1){
		}else{
			pnViTriLeft.setVisible(false);
		}
		loadPanel();

	}

	public PnViTri(String title) {
		login = new User();
		initComponents();
		LibraryName lbName = new LibraryName();
		tfTitleCenter.setForeground(lbName.getColor_Title_PnCenter());
		tfTitleCenter.setFont(lbName.getFont_Title_PnCenter());
		tfTitleCenter.setText(title);
		pnViTriTop.setBackground(lbName.getColor_background());
		pnViTriCenter.setBackground(lbName.getColor_background());
		pnViTriLeft.setBackground(lbName.getColor_background());
		JSplitPane sp = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, pnViTriLeft, pnViTriCenter);
		sp.setOneTouchExpandable(true);
		add(pnViTriTop, BorderLayout.NORTH);
		add(sp, BorderLayout.CENTER);
		pnViTriLeft.setMinimumSize(new Dimension(LibraryDimension.PANEL_LEFT_WIDTH, pnViTriLeft.getSize().height));

		tabbedPane = new JTabbedPane();
		model = new DefaultTableModel() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {
				return false;
			}

			public Class<?> getColumnClass(int col) {
				if (col == 0 || col == 1) {
					return Integer.class;
				} else {
					return super.getColumnClass(col);
				}
			}
		};
		pnViTriCenter.add(tabbedPane, java.awt.BorderLayout.CENTER);

		/* create three JPanel, which is content of tabs */
		controller = new ControllerViTri(this);
		btKhuVucThem.addActionListener(this);
		btKhuVucSua.addActionListener(this);
		btKhuVucNhapLai.addActionListener(this);
		btKhuVucXoa.addActionListener(this);

		btBanThem.addActionListener(this);
		btBanXoa.addActionListener(this);
		/* add three tab with three JPanel */
		
//		if(login.getId_chucvu() != 1){
////			pnViTriLeft.setVisible(false);
//			pnViTriCenter.setVisible(false);
//		}
		loadTable();
		loadPanel();
		
	}

	public void loadKhuVuc() {

	}

	public void loadTable() {
		controller.loadTable(tbKhuVuc, model);

	}

	public void loadPanel() {
		ArrayList<KhuVuc> alPanel = new ArrayList<KhuVuc>();
		alPanel = controller.getList();
		tabbedPane = new JTabbedPane();
		pnViTriCenter.add(tabbedPane, java.awt.BorderLayout.CENTER);
		for (KhuVuc item : alPanel) {
			JPanel moi = controller.getPanel(item.getIdkhuvuc());
			item.setPnKhuVuc(moi);
			tabbedPane.addTab(item.getKhuvuc(), null, item.getPnKhuVuc(),
					"Nhấn vào để quản lý bàn ở " + item.getKhuvuc());
		}
		pnViTriCenter.updateUI();
	}

	public void addPanel() {
		pn.addPanel(new PnViTri("Quản lý vị trí", pn, fr));
	}

	public void setForm() {
		crow = tbKhuVuc.getSelectedRow();
		tfKhuVucID.setText(tbKhuVuc.getValueAt(crow, 0).toString());
		tfKhuVucName.setText(tbKhuVuc.getValueAt(crow, 1).toString());
		tfKhuVucBan.setText(tbKhuVuc.getValueAt(crow, 1).toString());
	}

	public void LapHoaDon(Bill fitem, int method, JButton b) {
		fitem.setUsername(fr.login.getUsername());
		final FrLapHoaDon frame = new FrLapHoaDon(fitem, method, this, b);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent ev) {
				int check = JOptionPane.showConfirmDialog(null, "Hóa đơn sẽ không lưu lại, tiếp tục?", "Tắt?",
						JOptionPane.OK_OPTION);
				if (check == JOptionPane.YES_OPTION) {
					frame.dispose();
					controller.flag = false;
				}

			}
		});
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents() {

		pnViTriCenter = new javax.swing.JPanel();
		pnViTriLeft = new javax.swing.JPanel();
		pnFoodTacVu1 = new javax.swing.JPanel();
		jLabel4 = new javax.swing.JLabel();
		tfKhuVucID = new javax.swing.JTextField();
		jLabel5 = new javax.swing.JLabel();
		tfKhuVucName = new javax.swing.JTextField();
		pnFoodInTacVu1 = new javax.swing.JPanel();
		btKhuVucThem = new javax.swing.JButton();
		btKhuVucSua = new javax.swing.JButton();
		btKhuVucNhapLai = new javax.swing.JButton();
		btKhuVucXoa = new javax.swing.JButton();
		jPanel1 = new javax.swing.JPanel();
		pnFoodTacVu = new javax.swing.JPanel();
		jLabel1 = new javax.swing.JLabel();
		jLabel2 = new javax.swing.JLabel();
		pnFoodInTacVu = new javax.swing.JPanel();
		btBanThem = new javax.swing.JButton();
		btBanNhapLai = new javax.swing.JButton();
		btBanXoa = new javax.swing.JButton();
		tfKhuVucBan = new javax.swing.JTextField();
		tfSoBan = new javax.swing.JTextField();
		jScrollPane1 = new javax.swing.JScrollPane();
		tbKhuVuc = new javax.swing.JTable();
		pnViTri = new javax.swing.JPanel();
		pnViTriTop = new javax.swing.JPanel();
		tfTitleCenter = new javax.swing.JLabel();

		setLayout(new java.awt.BorderLayout());

		pnViTriCenter.setLayout(new java.awt.BorderLayout());

		pnViTriLeft.setBackground(new java.awt.Color(255, 255, 153));
		pnViTriLeft.setBorder(javax.swing.BorderFactory.createCompoundBorder(
				javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5),
				javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(),
						"Quản lý tác vụ", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP,
						new java.awt.Font("Arial", 1, 14), new java.awt.Color(51, 51, 255)))); // NOI18N
		pnViTriLeft.setLayout(new java.awt.BorderLayout());

		pnFoodTacVu1.setBackground(new java.awt.Color(255, 255, 255));
		pnFoodTacVu1.setBorder(javax.swing.BorderFactory.createCompoundBorder(
				javax.swing.BorderFactory.createTitledBorder(null, "Khu vực",
						javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
						javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 14),
						new java.awt.Color(0, 51, 153)),
				javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5))); // NOI18N

		jLabel4.setText("ID khu vực");

		tfKhuVucID.setEditable(false);
		tfKhuVucID.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				tfKhuVucIDActionPerformed(evt);
			}
		});

		jLabel5.setText("Tên khu vực");

		tfKhuVucName.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				tfKhuVucNameActionPerformed(evt);
			}
		});

		pnFoodInTacVu1.setBackground(new java.awt.Color(255, 255, 255));

		btKhuVucThem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/add_16.png"))); // NOI18N
		btKhuVucThem.setText("Thêm");
		pnFoodInTacVu1.add(btKhuVucThem);

		btKhuVucSua.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/edit-icon.gif"))); // NOI18N
		btKhuVucSua.setText("Sửa");
		pnFoodInTacVu1.add(btKhuVucSua);

		btKhuVucNhapLai.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/edit.gif"))); // NOI18N
		btKhuVucNhapLai.setText("Nhập lại");
		pnFoodInTacVu1.add(btKhuVucNhapLai);

		btKhuVucXoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/del.gif"))); // NOI18N
		btKhuVucXoa.setText("Xóa");
		pnFoodInTacVu1.add(btKhuVucXoa);

		javax.swing.GroupLayout pnFoodTacVu1Layout = new javax.swing.GroupLayout(pnFoodTacVu1);
		pnFoodTacVu1.setLayout(pnFoodTacVu1Layout);
		pnFoodTacVu1Layout.setHorizontalGroup(pnFoodTacVu1Layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(pnFoodTacVu1Layout.createSequentialGroup().addContainerGap().addGroup(pnFoodTacVu1Layout
						.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addComponent(pnFoodInTacVu1, javax.swing.GroupLayout.DEFAULT_SIZE, 778, Short.MAX_VALUE)
						.addGroup(pnFoodTacVu1Layout.createSequentialGroup().addGap(5, 5, 5)
								.addGroup(pnFoodTacVu1Layout
										.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
										.addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
								.addGap(11, 11, 11)
								.addGroup(pnFoodTacVu1Layout
										.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
										.addComponent(tfKhuVucName)
										.addGroup(pnFoodTacVu1Layout.createSequentialGroup()
												.addComponent(tfKhuVucID, javax.swing.GroupLayout.PREFERRED_SIZE, 78,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addGap(0, 625, Short.MAX_VALUE)))))
						.addContainerGap()));
		pnFoodTacVu1Layout.setVerticalGroup(pnFoodTacVu1Layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(pnFoodTacVu1Layout.createSequentialGroup().addContainerGap()
						.addGroup(pnFoodTacVu1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(jLabel4).addComponent(tfKhuVucID, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addGroup(pnFoodTacVu1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(jLabel5).addComponent(tfKhuVucName,
										javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(pnFoodInTacVu1, javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

		pnViTriLeft.add(pnFoodTacVu1, java.awt.BorderLayout.NORTH);

		pnFoodTacVu.setBackground(new java.awt.Color(255, 255, 255));
		pnFoodTacVu.setBorder(javax.swing.BorderFactory.createCompoundBorder(
				javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Bàn",
						javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
						javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 14),
						new java.awt.Color(0, 51, 204)),
				javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5))); // NOI18N

		jLabel1.setText("Khu Vực");

		jLabel2.setText("Số bàn");

		pnFoodInTacVu.setBackground(new java.awt.Color(255, 255, 255));

		btBanThem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/add_16.png"))); // NOI18N
		btBanThem.setText("Thêm");
		btBanThem.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btBanThemActionPerformed(evt);
			}
		});
		pnFoodInTacVu.add(btBanThem);

		btBanNhapLai.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/edit.gif"))); // NOI18N
		btBanNhapLai.setText("Nhập lại");
		pnFoodInTacVu.add(btBanNhapLai);

		btBanXoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/del.gif"))); // NOI18N
		btBanXoa.setText("Xóa");
		btBanXoa.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btBanXoaActionPerformed(evt);
			}
		});
		pnFoodInTacVu.add(btBanXoa);

		tfKhuVucBan.setEditable(false);

		tfSoBan.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				tfSoBanActionPerformed(evt);
			}
		});

		javax.swing.GroupLayout pnFoodTacVuLayout = new javax.swing.GroupLayout(pnFoodTacVu);
		pnFoodTacVu.setLayout(pnFoodTacVuLayout);
		pnFoodTacVuLayout.setHorizontalGroup(pnFoodTacVuLayout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(pnFoodTacVuLayout.createSequentialGroup().addContainerGap().addGroup(pnFoodTacVuLayout
						.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addComponent(pnFoodInTacVu, javax.swing.GroupLayout.DEFAULT_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE,
								Short.MAX_VALUE)
						.addGroup(pnFoodTacVuLayout.createSequentialGroup().addGap(5, 5, 5)
								.addGroup(pnFoodTacVuLayout
										.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
										.addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
								.addGroup(pnFoodTacVuLayout
										.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
										.addGroup(pnFoodTacVuLayout.createSequentialGroup()
												.addComponent(tfKhuVucBan, javax.swing.GroupLayout.PREFERRED_SIZE, 86,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addGap(0, 0, Short.MAX_VALUE))
										.addComponent(tfSoBan))))
						.addContainerGap()));
		pnFoodTacVuLayout.setVerticalGroup(pnFoodTacVuLayout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(pnFoodTacVuLayout.createSequentialGroup().addContainerGap()
						.addGroup(pnFoodTacVuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(jLabel1).addComponent(tfKhuVucBan, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addGroup(pnFoodTacVuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(jLabel2).addComponent(tfSoBan, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED,
								javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(pnFoodInTacVu, javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addContainerGap()));

		tbKhuVuc.setAutoCreateRowSorter(true);
		tbKhuVuc.setModel(
				new javax.swing.table.DefaultTableModel(
						new Object[][] { { null, null, null, null }, { null, null, null, null },
								{ null, null, null, null }, { null, null, null, null } },
						new String[] { "Title 1", "Title 2", "Title 3", "Title 4" }));
		tbKhuVuc.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mousePressed(java.awt.event.MouseEvent evt) {
				tbKhuVucMousePressed(evt);
			}
		});
		tbKhuVuc.addKeyListener(new java.awt.event.KeyAdapter() {
			public void keyReleased(java.awt.event.KeyEvent evt) {
				tbKhuVucKeyReleased(evt);
			}
		});
		jScrollPane1.setViewportView(tbKhuVuc);

		javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
		jPanel1.setLayout(jPanel1Layout);
		jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 820, Short.MAX_VALUE)
				.addComponent(pnFoodTacVu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
						Short.MAX_VALUE));
		jPanel1Layout
				.setVerticalGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
								jPanel1Layout.createSequentialGroup()
										.addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 160,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(pnFoodTacVu, javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addContainerGap()));

		pnViTriLeft.add(jPanel1, java.awt.BorderLayout.CENTER);

		pnViTriCenter.add(pnViTriLeft, java.awt.BorderLayout.WEST);

		add(pnViTriCenter, java.awt.BorderLayout.CENTER);

		pnViTri.setLayout(new java.awt.BorderLayout());

		pnViTriTop.setBorder(javax.swing.BorderFactory.createEtchedBorder());

		tfTitleCenter.setForeground(new java.awt.Color(0, 0, 204));
		tfTitleCenter.setText("Quản lý vị trí");
		pnViTriTop.add(tfTitleCenter);

		pnViTri.add(pnViTriTop, java.awt.BorderLayout.NORTH);

		add(pnViTri, java.awt.BorderLayout.PAGE_START);
	}// </editor-fold>

	private void tfSoBanActionPerformed(java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:
	}

	private void tfKhuVucIDActionPerformed(java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:
	}

	private void tfKhuVucNameActionPerformed(java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:
	}

	private void tbKhuVucKeyReleased(java.awt.event.KeyEvent evt) {
		setForm();
	}

	private void tbKhuVucMousePressed(java.awt.event.MouseEvent evt) {
		setForm();
	}

	private void btBanThemActionPerformed(java.awt.event.ActionEvent evt) {
		if (tfSoBan.getText().contains("-") == false) {
			int id = 0;
			try {
				id = Integer.parseInt(tfSoBan.getText());
			} catch (NumberFormatException ex) {
				System.out.println("Vui lòng nhập một bàn");
			}
			Ban item = new Ban(id, Integer.parseInt(tfKhuVucID.getText()));
			if (controller.add(item) > 0) {

				addPanel();
				JOptionPane.showMessageDialog(null, "Thêm thành công");
			}
		} else {
			int check = -1;
			String[] soban = tfSoBan.getText().split("-");
			int sodau = Integer.parseInt(soban[0]);
			int socuoi = Integer.parseInt(soban[1]);
			for (int i = sodau; i <= socuoi; i++) {
				Ban item = new Ban(i, Integer.parseInt(tfKhuVucID.getText()));
				if (controller.add(item) > 0) {

					addPanel();
					check = 1;
				} else {
					check = -1;
				}
			}
			if (check == 1) {
				JOptionPane.showMessageDialog(null, "Thêm thành công");

			}
		}
	}

	private void btBanXoaActionPerformed(java.awt.event.ActionEvent evt) {

		int id = 0;
		try {
			id = Integer.parseInt(tfSoBan.getText());
		} catch (NumberFormatException ex) {
			System.out.println("Vui lòng nhập một bàn");
		}
		Ban item = new Ban(id, Integer.parseInt(tfKhuVucID.getText()));
		if (controller.del(item) > 0) {

			addPanel();
			JOptionPane.showMessageDialog(null, "Xóa thành công");

		}

	}

	// Variables declaration - do not modify
	private javax.swing.JButton btBanNhapLai;
	private javax.swing.JButton btBanThem;
	private javax.swing.JButton btBanXoa;
	private javax.swing.JButton btKhuVucNhapLai;
	private javax.swing.JButton btKhuVucSua;
	private javax.swing.JButton btKhuVucThem;
	private javax.swing.JButton btKhuVucXoa;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JLabel jLabel4;
	private javax.swing.JLabel jLabel5;
	private javax.swing.JPanel jPanel1;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JPanel pnFoodInTacVu;
	private javax.swing.JPanel pnFoodInTacVu1;
	private javax.swing.JPanel pnFoodTacVu;
	private javax.swing.JPanel pnFoodTacVu1;
	private javax.swing.JPanel pnViTri;
	private javax.swing.JPanel pnViTriCenter;
	private javax.swing.JPanel pnViTriLeft;
	private javax.swing.JPanel pnViTriTop;
	private javax.swing.JTable tbKhuVuc;
	private javax.swing.JTextField tfKhuVucBan;
	private javax.swing.JTextField tfKhuVucID;
	private javax.swing.JTextField tfKhuVucName;
	private javax.swing.JTextField tfSoBan;
	private javax.swing.JLabel tfTitleCenter;
	// End of variables declaration

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btKhuVucThem) {
			KhuVuc item = new KhuVuc(0, tfKhuVucName.getText());
			if (controller.add(item) > 0) {

				addPanel();
				JOptionPane.showMessageDialog(null, "Thêm thành công");
			}
		} else if (e.getSource() == btKhuVucSua) {
			int id = 0;
			try {
				id = Integer.parseInt(tfKhuVucID.getText());
			} catch (NumberFormatException ex) {
				System.out.println("Vui lòng chọn một khu vực");
			}
			KhuVuc item = new KhuVuc(id, tfKhuVucName.getText());
			crow = tbKhuVuc.getSelectedRow();
			if (controller.edit(item) > 0) {
				addPanel();
				JOptionPane.showMessageDialog(null, "Sửa thành công");
				tbKhuVuc.setRowSelectionInterval(crow, crow);
			}
		} else if (e.getSource() == btKhuVucNhapLai) {
			resetForm();
		} else if (e.getSource() == btKhuVucXoa) {
			int id = 0;
			try {
				id = Integer.parseInt(tfKhuVucID.getText());
			} catch (NumberFormatException ex) {
				System.out.println("Vui lòng chọn một khu vực");
			}
			KhuVuc item = new KhuVuc(id, tfKhuVucName.getText());
			if (controller.del(item) > 0) {
				addPanel();
				JOptionPane.showMessageDialog(null, "Xóa thành công");
			}

		}
	}

	private void resetForm() {
		tfKhuVucID.setText("");
		tfKhuVucName.setText("");
	}

}
