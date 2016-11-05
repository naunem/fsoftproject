/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import bean.Ban;
import bean.Bill;
import bean.User;
import controller.ControllerHoaDon;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;
import javax.swing.JSplitPane;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import library.LibraryDimension;
import library.LibraryName;

/**
 *
 * @author HungPham
 */
public class PnHoaDon extends javax.swing.JPanel implements ActionListener {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
     * Creates new form PnViTri
     */
    ControllerHoaDon controller;
    DefaultTableModel model;
    int crow = -1;
    FrMain fr;
    User user;

    public PnHoaDon(String title, FrMain fr) {
        initComponents();
        user = fr.login;
        LibraryName lbName = new LibraryName();
        tfTitleCenter.setForeground(lbName.getColor_Title_PnCenter());
        tfTitleCenter.setFont(lbName.getFont_Title_PnCenter());
        tfTitleCenter.setText(title.toUpperCase());
        pnBillTop.setBackground(lbName.getColor_background());
        pnBillCenter.setBackground(lbName.getColor_background());
        pnBillF.setBackground(lbName.getColor_background());
        pnBillLeft.setBackground(lbName.getColor_background());
        pnHoadonTacVu.setBackground(lbName.getColor_small_panel());

        JSplitPane sp = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, pnBillLeft, pnBillCenter);
        sp.setOneTouchExpandable(true);
        //add(pnBillTop, BorderLayout.NORTH);
        add(sp, BorderLayout.CENTER);
        pnBillLeft.setMinimumSize(new Dimension(LibraryDimension.PANEL_LEFT_WIDTH, pnBillLeft.getSize().height));
        controller = new ControllerHoaDon();
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
       
        btXem.addActionListener(this);
        btXem.setVisible(false);
        loadTable();

    }

    public void loadTable() {
        controller.loadTable(tbBill, model);
        int count = controller.getCount();
        int sum = controller.getSum();
        lbSoHoaDon.setText("Số hóa đơn: " + count + "");
        lbThuNhap.setText("Thu nhập: " + sum + " VNĐ");
    }

    public void loadTable(int d, int m, int y) {
        controller.loadTable(tbBill, model, d, m, y);
        int count = controller.getCount();
        int sum = controller.getSum();
        lbSoHoaDon.setText("Số hóa đơn: " + count + "");
        lbThuNhap.setText("Thu nhập: " + sum + "VNĐ");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        pnBill = new javax.swing.JPanel();
        pnBillTop = new javax.swing.JPanel();
        tfTitleCenter = new javax.swing.JLabel();
        pnBillLeft = new javax.swing.JPanel();
        pnHoadonTacVu = new javax.swing.JPanel();
        btHomNay = new javax.swing.JButton();
        btThang = new javax.swing.JButton();
        btNam = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        btTuan = new javax.swing.JButton();
        tfMonth = new javax.swing.JTextField();
        tfDay = new javax.swing.JTextField();
        tfYear = new javax.swing.JTextField();
        spinNewsDateFrom = new javax.swing.JSpinner();
        spinNewsDateTo = new javax.swing.JSpinner();
        pnHoadonTacVu1 = new javax.swing.JPanel();
        lbSoHoaDon = new javax.swing.JLabel();
        lbThuNhap = new javax.swing.JLabel();
        btExcel = new javax.swing.JButton();
        pnBillCenter = new javax.swing.JPanel();
        pnBillF = new javax.swing.JPanel();
        btXem = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbBill = new javax.swing.JTable();

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 393, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 379, Short.MAX_VALUE)
        );

        setLayout(new java.awt.BorderLayout());

        pnBill.setLayout(new java.awt.BorderLayout());

        pnBillTop.setBackground(new java.awt.Color(255, 255, 204));
        pnBillTop.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        tfTitleCenter.setForeground(new java.awt.Color(0, 0, 204));
        tfTitleCenter.setText("Quản lý vị trí");
        pnBillTop.add(tfTitleCenter);

        pnBill.add(pnBillTop, java.awt.BorderLayout.NORTH);

        pnBillLeft.setBackground(new java.awt.Color(255, 255, 153));
        pnBillLeft.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5), javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Quản lý tác vụ", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Arial", 1, 14), new java.awt.Color(51, 51, 255)))); // NOI18N
        pnBillLeft.setLayout(new java.awt.BorderLayout());

        pnHoadonTacVu.setBackground(new java.awt.Color(255, 255, 255));
        pnHoadonTacVu.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createEtchedBorder(), javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5)), "Thời gian", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 12))); // NOI18N
        pnHoadonTacVu.setForeground(new java.awt.Color(255, 255, 204));

        btHomNay.setText("Ngày");
        btHomNay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btHomNayActionPerformed(evt);
            }
        });

        btThang.setText("Tháng");
        btThang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btThangActionPerformed(evt);
            }
        });

        btNam.setText("Năm");
        btNam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btNamActionPerformed(evt);
            }
        });

        jLabel1.setText("Ngày ");

        jLabel2.setText("Đến");

        btTuan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/sumbleupon_48.png"))); // NOI18N
        btTuan.setText("Xuất");
        btTuan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btTuanActionPerformed(evt);
            }
        });

        spinNewsDateFrom.setModel(new javax.swing.SpinnerDateModel());
        spinNewsDateFrom.setEditor(new javax.swing.JSpinner.DateEditor(spinNewsDateFrom, "dd/MM/yyyy"));

        spinNewsDateTo.setModel(new javax.swing.SpinnerDateModel());
        spinNewsDateTo.setEditor(new javax.swing.JSpinner.DateEditor(spinNewsDateTo, "dd/MM/yyyy"));

        javax.swing.GroupLayout pnHoadonTacVuLayout = new javax.swing.GroupLayout(pnHoadonTacVu);
        pnHoadonTacVu.setLayout(pnHoadonTacVuLayout);
        pnHoadonTacVuLayout.setHorizontalGroup(
            pnHoadonTacVuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnHoadonTacVuLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnHoadonTacVuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnHoadonTacVuLayout.createSequentialGroup()
                        .addGroup(pnHoadonTacVuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnHoadonTacVuLayout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(spinNewsDateFrom))
                            .addGroup(pnHoadonTacVuLayout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(18, 18, 18)
                                .addComponent(spinNewsDateTo)))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnHoadonTacVuLayout.createSequentialGroup()
                        .addGap(0, 31, Short.MAX_VALUE)
                        .addGroup(pnHoadonTacVuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnHoadonTacVuLayout.createSequentialGroup()
                                .addGroup(pnHoadonTacVuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(btNam, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btThang, javax.swing.GroupLayout.DEFAULT_SIZE, 81, Short.MAX_VALUE)
                                    .addComponent(btHomNay, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(pnHoadonTacVuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(tfDay, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(tfMonth, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(tfYear, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(183, 183, 183))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnHoadonTacVuLayout.createSequentialGroup()
                                .addComponent(btTuan)
                                .addContainerGap())))))
        );
        pnHoadonTacVuLayout.setVerticalGroup(
            pnHoadonTacVuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnHoadonTacVuLayout.createSequentialGroup()
                .addGroup(pnHoadonTacVuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btHomNay)
                    .addComponent(tfDay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnHoadonTacVuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btThang)
                    .addComponent(tfMonth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnHoadonTacVuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btNam)
                    .addComponent(tfYear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnHoadonTacVuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(spinNewsDateFrom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnHoadonTacVuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(spinNewsDateTo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btTuan))
        );

        pnBillLeft.add(pnHoadonTacVu, java.awt.BorderLayout.PAGE_START);

        pnHoadonTacVu1.setBackground(new java.awt.Color(255, 255, 255));
        pnHoadonTacVu1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createEtchedBorder(), javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5)), "Thống kê", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 12))); // NOI18N
        pnHoadonTacVu1.setForeground(new java.awt.Color(255, 255, 204));

        lbSoHoaDon.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lbSoHoaDon.setForeground(new java.awt.Color(255, 0, 0));
        lbSoHoaDon.setText("Số hóa đơn:");

        lbThuNhap.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lbThuNhap.setForeground(new java.awt.Color(255, 0, 0));
        lbThuNhap.setText("Thu nhập:");

        btExcel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/table_green_48.png"))); // NOI18N
        btExcel.setText("Xuất ra Exel");
        btExcel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btExcelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnHoadonTacVu1Layout = new javax.swing.GroupLayout(pnHoadonTacVu1);
        pnHoadonTacVu1.setLayout(pnHoadonTacVu1Layout);
        pnHoadonTacVu1Layout.setHorizontalGroup(
            pnHoadonTacVu1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbSoHoaDon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lbThuNhap, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btExcel, javax.swing.GroupLayout.DEFAULT_SIZE, 372, Short.MAX_VALUE)
        );
        pnHoadonTacVu1Layout.setVerticalGroup(
            pnHoadonTacVu1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnHoadonTacVu1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbSoHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbThuNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btExcel))
        );

        pnBillLeft.add(pnHoadonTacVu1, java.awt.BorderLayout.CENTER);

        pnBill.add(pnBillLeft, java.awt.BorderLayout.WEST);

        pnBillCenter.setLayout(new java.awt.BorderLayout());

        pnBillF.setBackground(new java.awt.Color(255, 255, 153));

        btXem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/editor.png"))); // NOI18N
        btXem.setText("Xem");
        pnBillF.add(btXem);

        pnBillCenter.add(pnBillF, java.awt.BorderLayout.PAGE_END);

        tbBill.setBackground(new java.awt.Color(204, 255, 204));
        tbBill.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ) {
            /**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			boolean[] canEdit = new boolean [] {
                false, false, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbBill.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tbBillMousePressed(evt);
            }
        });
        tbBill.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tbBillKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tbBill);
        if (tbBill.getColumnModel().getColumnCount() > 0) {
            tbBill.getColumnModel().getColumn(0).setResizable(false);
            tbBill.getColumnModel().getColumn(1).setResizable(false);
            tbBill.getColumnModel().getColumn(2).setResizable(false);
            tbBill.getColumnModel().getColumn(3).setResizable(false);
        }

        pnBillCenter.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        pnBill.add(pnBillCenter, java.awt.BorderLayout.CENTER);

        add(pnBill, java.awt.BorderLayout.CENTER);
    }// </editor-fold>                        

    private void tbBillKeyReleased(java.awt.event.KeyEvent evt) {                                   
        setForm();
    }                                  

    private void tbBillMousePressed(java.awt.event.MouseEvent evt) {                                    
        setForm();
    }                                   

    private void btHomNayActionPerformed(java.awt.event.ActionEvent evt) {                                         

        int day = Integer.parseInt(tfDay.getText());
        int m = Integer.parseInt(tfMonth.getText());
        int y = Integer.parseInt(tfYear.getText());
        loadTable(day, m, y);


    }                                        

    private void btThangActionPerformed(java.awt.event.ActionEvent evt) {                                        
        int m = Integer.parseInt(tfMonth.getText());
        int y = Integer.parseInt(tfYear.getText());
        loadTable(-1, m, y);
    }                                       

    private void btNamActionPerformed(java.awt.event.ActionEvent evt) {                                      
        int y = Integer.parseInt(tfYear.getText());
        loadTable(-1, -1, y);
    }                                     

    private void btTuanActionPerformed(java.awt.event.ActionEvent evt) {                                       
        Date dateFrom = (Date) spinNewsDateFrom.getValue();
        Date dateTo = (Date) spinNewsDateTo.getValue();
        String date_giovao_search = dateFrom + "---" + dateTo;
        loadTable(date_giovao_search);
    }                                      

    private void btExcelActionPerformed(java.awt.event.ActionEvent evt) {                                        
        try {
            WritableWorkbook workbook;
            @SuppressWarnings("rawtypes")
			Vector<Vector> vRows = controller.getvR();
            Date date = new Date();
            String filename = "ThongKe"+date.getTime()+".xls";
            File f = new File(filename);
            f.createNewFile();
            workbook = Workbook.createWorkbook(f);
            WritableSheet sheet1 = workbook.createSheet("TK", 0);

            try {
                sheet1.addCell(new Label(0, 0, "DANH SÁCH HÓA ĐƠN"));
                int count = controller.getCount();
                int sum = controller.getSum();
                sheet1.addCell(new Label(0, 1, "Số hóa đơn"));
                sheet1.addCell(new Label(1, 1, count + ""));
                sheet1.addCell(new Label(0, 2, "Thu nhập"));
                sheet1.addCell(new Label(1, 2, sum + ""));
                sheet1.addCell(new Label(0, 3, "ID"));
                sheet1.addCell(new Label(3, 3, "Người lập"));
                sheet1.addCell(new Label(4, 3, "Bàn"));
                sheet1.addCell(new Label(5, 3, "Thành tiền"));
                sheet1.addCell(new Label(6, 3, "Tình trạng"));

                for (int i = 0; i < vRows.size(); i++) {
                    String id = vRows.get(i).get(0).toString();
                    String nguoilap = vRows.get(i).get(3).toString();
                    String ban = vRows.get(i).get(4).toString();
                    String thanhtien = vRows.get(i).get(5).toString();
                    String tinhtrang = vRows.get(i).get(6).toString();
                    int row = i + 4;
                    sheet1.addCell(new Label(0, row, id));
                    sheet1.addCell(new Label(3, row, nguoilap));
                    sheet1.addCell(new Label(4, row, ban));
                    sheet1.addCell(new Label(5, row, thanhtien));
                    sheet1.addCell(new Label(6, row, tinhtrang));

                }
                workbook.write();
                workbook.close();
                JOptionPane.showMessageDialog(this, "Đã xuất ra file Excel "+filename);
            } catch (WriteException ex) {
                Logger.getLogger(PnHoaDon.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(PnHoaDon.class.getName()).log(Level.SEVERE, null, ex);
            }

        } catch (IOException ex) {
            Logger.getLogger(PnHoaDon.class.getName()).log(Level.SEVERE, null, ex);
        }

    }                                       
    private void setForm() {
        crow = tbBill.getSelectedRow();
        String tinhtrang = tbBill.getValueAt(crow, 5).toString();
        System.out.println(tinhtrang);
        if ("Đã thanh toán".equals(tinhtrang)) {

            btXem.setVisible(true);
        } else {
           
            btXem.setVisible(false);
        }
    }

    // Variables declaration - do not modify                     
    private javax.swing.JButton btExcel;
    private javax.swing.JButton btHomNay;
    private javax.swing.JButton btNam;
    private javax.swing.JButton btThang;
    private javax.swing.JButton btTuan;
    private javax.swing.JButton btXem;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbSoHoaDon;
    private javax.swing.JLabel lbThuNhap;
    private javax.swing.JPanel pnBill;
    private javax.swing.JPanel pnBillCenter;
    private javax.swing.JPanel pnBillF;
    private javax.swing.JPanel pnBillLeft;
    private javax.swing.JPanel pnBillTop;
    private javax.swing.JPanel pnHoadonTacVu;
    private javax.swing.JPanel pnHoadonTacVu1;
    private javax.swing.JSpinner spinNewsDateFrom;
    private javax.swing.JSpinner spinNewsDateTo;
    private javax.swing.JTable tbBill;
    private javax.swing.JTextField tfDay;
    private javax.swing.JTextField tfMonth;
    private javax.swing.JLabel tfTitleCenter;
    private javax.swing.JTextField tfYear;
    // End of variables declaration                   

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btXem) {
            crow = tbBill.getSelectedRow();
            int id = (int) tbBill.getValueAt(crow, 0);
            Ban ban = (Ban) tbBill.getValueAt(crow, 3);
            Bill b = new Bill(id);
            b.setBan(ban.getSoban());
            b.setIdkhuvuc(ban.getIdkhuvuc());
            b.setKhuvuc(ban.getTenkhuvuc());
            LapHoaDon(b, 3);
        } 

    }

    public void LapHoaDon(Bill fitem, int method) {
        final FrLapHoaDon frame = new FrLapHoaDon(fitem, method, this);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent ev) {

                int check = JOptionPane.showConfirmDialog(null, "Hóa đơn sẽ không lưu lại, tiếp tục?", "Tắt?", JOptionPane.OK_OPTION);
                if (check == JOptionPane.YES_OPTION) {
                    frame.dispose();
                }

            }
        });
    }

    private void loadTable(String date_giovao_search) {
        controller.loadTable(tbBill, model, date_giovao_search);
        int count = controller.getCount();
        int sum = controller.getSum();
        lbSoHoaDon.setText("Số hóa đơn: " + count + "");
        lbThuNhap.setText("Thu nhập: " + sum + " VNĐ");
    }

}
