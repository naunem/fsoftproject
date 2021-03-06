/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import bean.Food;
import controller.ControllerFood;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Timestamp;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;

import javax.swing.table.DefaultTableModel;
import library.LibraryDimension;
import library.LibraryName;

/**
 *
 * @author HungPham
 */
public class PnFood extends javax.swing.JPanel implements ActionListener {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
     * Creates new form PnViTri
     */
    int crow = -1;
    JTable table;
    DefaultTableModel model;
    
    ControllerFood controller;

    public JButton getBtFoodNhapLai() {
        return btFoodNhapLai;
    }

    public void setBtFoodNhapLai(JButton btFoodNhapLai) {
        this.btFoodNhapLai = btFoodNhapLai;
    }

    public JButton getBtFoodSua() {
        return btFoodSua;
    }

    public void setBtFoodSua(JButton btFoodSua) {
        this.btFoodSua = btFoodSua;
    }

    public PnFood(String title) {
        initComponents();
        LibraryName lbName = new LibraryName();
        tfTitleCenter.setForeground(lbName.getColor_Title_PnCenter());
        tfTitleCenter.setFont(lbName.getFont_Title_PnCenter());
        tfTitleCenter.setText(title);
        pnFoodInTacVu.setBackground(lbName.getColor_small_panel());
        pnFoodTacVu.setBackground(lbName.getColor_small_panel());
        pnFoodTimKiem.setBackground(lbName.getColor_small_panel());
        pnFoodInTimKiem.setBackground(lbName.getColor_small_panel());
        JSplitPane sp = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, pnViTriLeft, pnViTriCenter);
        sp.setOneTouchExpandable(true);
        //add(pnViTriTop, BorderLayout.NORTH);
        add(sp, BorderLayout.CENTER);
        pnViTriLeft.setMinimumSize(new Dimension(LibraryDimension.PANEL_LEFT_WIDTH, pnViTriLeft.getSize().height));

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
        controller = new ControllerFood();
        loadTable(null);

        btFoodThem.addActionListener(this);
        btFoodSua.addActionListener(this);
        btFoodNhapLai.addActionListener(this);
        btFoodXoa.addActionListener(this);
        btFoodFind.addActionListener(this);
        
    }

    public JButton getBtFoodThem() {
        return btFoodThem;
    }

    public void setBtFoodThem(JButton btFoodThem) {
        this.btFoodThem = btFoodThem;
    }

    public JButton getBtFoodXoa() {
        return btFoodXoa;
    }

    public void setBtFoodXoa(JButton btFoodXoa) {
        this.btFoodXoa = btFoodXoa;
    }

    public JTable getTbFood() {
        return tbFood;
    }

    public void setTbFood(JTable tbFood) {
        this.tbFood = tbFood;
    }

    public void loadTable(Food item) {
        controller.loadTable(tbFood, model, item);
        controller.setWidthHeightTable(tbFood);
    }

    public void setForm() {
        crow = tbFood.getSelectedRow();
        tfFoodId.setText(tbFood.getValueAt(crow, 0).toString());
        tfFoodName.setText(tbFood.getValueAt(crow, 1).toString());
        //lbFoodHinhAnh.setText(tbFood.getValueAt(crow, 2).toString());
        tfFoodPrice.setText(tbFood.getValueAt(crow, 2).toString());
    }

    private void resetForm() {
        tfFoodId.setText("");
        tfFoodName.setText("");
        tfFoodPrice.setText("");
        //lbFoodHinhAnh.setText("");
        crow = -1;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        pnViTri = new javax.swing.JPanel();
        pnViTriTop = new javax.swing.JPanel();
        tfTitleCenter = new javax.swing.JLabel();
        pnViTriCenter = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbFood = new javax.swing.JTable();
        pnViTriLeft = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        pnFoodTacVu = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        tfFoodId = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        tfFoodName = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        tfFoodPrice = new javax.swing.JTextField();
        pnFoodInTacVu = new javax.swing.JPanel();
        btFoodThem = new javax.swing.JButton();
        btFoodSua = new javax.swing.JButton();
        btFoodNhapLai = new javax.swing.JButton();
        btFoodXoa = new javax.swing.JButton();
        pnFoodTimKiem = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        tfFoodIdFind = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        tfFoodNameFind = new javax.swing.JTextField();
        pnFoodInTimKiem = new javax.swing.JPanel();
        btFoodFind = new javax.swing.JButton();

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setLayout(new java.awt.BorderLayout());

        pnViTri.setLayout(new java.awt.BorderLayout());

        pnViTriTop.setBackground(new java.awt.Color(255, 255, 153));
        pnViTriTop.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        tfTitleCenter.setForeground(new java.awt.Color(0, 0, 204));
        tfTitleCenter.setText("Quản lý vị trí");
        pnViTriTop.add(tfTitleCenter);

        pnViTri.add(pnViTriTop, java.awt.BorderLayout.NORTH);

        pnViTriCenter.setLayout(new java.awt.BorderLayout());

        tbFood.setAutoCreateRowSorter(true);
//        tbFood.setBackground(new java.awt.Color(204, 255, 204));
        tbFood.setModel(new javax.swing.table.DefaultTableModel(
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
                false, false, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbFood.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tbFoodMousePressed(evt);
            }
        });
        tbFood.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tbFoodKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tbFood);
        tbFood.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        if (tbFood.getColumnModel().getColumnCount() > 0) {
            tbFood.getColumnModel().getColumn(0).setResizable(false);
            tbFood.getColumnModel().getColumn(0).setHeaderValue("Title 1");
            tbFood.getColumnModel().getColumn(1).setResizable(false);
            tbFood.getColumnModel().getColumn(1).setHeaderValue("Title 2");
            tbFood.getColumnModel().getColumn(2).setResizable(false);
            tbFood.getColumnModel().getColumn(2).setHeaderValue("Title 3");
            tbFood.getColumnModel().getColumn(3).setResizable(false);
            tbFood.getColumnModel().getColumn(3).setHeaderValue("Title 4");
        }

        pnViTriCenter.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        pnViTriLeft.setBackground(new java.awt.Color(255, 255, 153));
        pnViTriLeft.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5), javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Quản lý tác vụ", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Arial", 1, 14), new java.awt.Color(51, 51, 255)))); // NOI18N
        pnViTriLeft.setLayout(new java.awt.BorderLayout());

        jPanel2.setLayout(new java.awt.BorderLayout());
        pnViTriLeft.add(jPanel2, java.awt.BorderLayout.PAGE_END);

        pnFoodTacVu.setBackground(new java.awt.Color(255, 255, 255));
        pnFoodTacVu.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setText("ID thức uống");

        tfFoodId.setEditable(false);
        tfFoodId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfFoodIdActionPerformed(evt);
            }
        });

        jLabel2.setText("Tên thức uống");

        tfFoodName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfFoodNameActionPerformed(evt);
            }
        });

        jLabel3.setText("Giá bán");

        pnFoodInTacVu.setBackground(new java.awt.Color(255, 255, 255));

        btFoodThem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/add_16.png"))); // NOI18N
        btFoodThem.setText("Thêm");
        pnFoodInTacVu.add(btFoodThem);

        btFoodSua.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/edit-icon.gif"))); // NOI18N
        btFoodSua.setText("Sửa");
        pnFoodInTacVu.add(btFoodSua);

        btFoodNhapLai.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/edit.gif"))); // NOI18N
        btFoodNhapLai.setText("Nhập lại");
        pnFoodInTacVu.add(btFoodNhapLai);

        btFoodXoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/del.gif"))); // NOI18N
        btFoodXoa.setText("Xóa");
        pnFoodInTacVu.add(btFoodXoa);

        javax.swing.GroupLayout pnFoodTacVuLayout = new javax.swing.GroupLayout(pnFoodTacVu);
        pnFoodTacVu.setLayout(pnFoodTacVuLayout);
        pnFoodTacVuLayout.setHorizontalGroup(
            pnFoodTacVuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnFoodTacVuLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnFoodTacVuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnFoodInTacVu, javax.swing.GroupLayout.DEFAULT_SIZE, 426, Short.MAX_VALUE)
                    .addGroup(pnFoodTacVuLayout.createSequentialGroup()
                        .addGroup(pnFoodTacVuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 85, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(pnFoodTacVuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tfFoodName)
                            .addGroup(pnFoodTacVuLayout.createSequentialGroup()
                                .addComponent(tfFoodId, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(tfFoodPrice))))
                .addContainerGap())
        );
        pnFoodTacVuLayout.setVerticalGroup(
            pnFoodTacVuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnFoodTacVuLayout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(pnFoodTacVuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(tfFoodId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnFoodTacVuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(tfFoodName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnFoodTacVuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(tfFoodPrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
                .addComponent(pnFoodInTacVu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25))
        );

        pnViTriLeft.add(pnFoodTacVu, java.awt.BorderLayout.PAGE_START);

        pnFoodTimKiem.setBackground(new java.awt.Color(255, 255, 255));
        pnFoodTimKiem.setBorder(javax.swing.BorderFactory.createCompoundBorder(null, javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Tìm kiếm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 18), new java.awt.Color(0, 0, 204)), javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5)))); // NOI18N

        jLabel5.setText("ID thức uống");

        tfFoodIdFind.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfFoodIdFindActionPerformed(evt);
            }
        });

        jLabel6.setText("Tên thức uống");

        tfFoodNameFind.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfFoodNameFindActionPerformed(evt);
            }
        });

        pnFoodInTimKiem.setBackground(new java.awt.Color(255, 255, 255));

        btFoodFind.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/search-icon.png"))); // NOI18N
        btFoodFind.setText("Tìm kiếm");
        pnFoodInTimKiem.add(btFoodFind);

        javax.swing.GroupLayout pnFoodTimKiemLayout = new javax.swing.GroupLayout(pnFoodTimKiem);
        pnFoodTimKiem.setLayout(pnFoodTimKiemLayout);
        pnFoodTimKiemLayout.setHorizontalGroup(
            pnFoodTimKiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnFoodTimKiemLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnFoodTimKiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnFoodInTimKiem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnFoodTimKiemLayout.createSequentialGroup()
                        .addGroup(pnFoodTimKiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 74, Short.MAX_VALUE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnFoodTimKiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnFoodTimKiemLayout.createSequentialGroup()
                                .addComponent(tfFoodIdFind, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(262, Short.MAX_VALUE))
                            .addComponent(tfFoodNameFind)))))
        );
        pnFoodTimKiemLayout.setVerticalGroup(
            pnFoodTimKiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnFoodTimKiemLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnFoodTimKiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(tfFoodIdFind, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnFoodTimKiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfFoodNameFind, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(pnFoodInTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pnViTriLeft.add(pnFoodTimKiem, java.awt.BorderLayout.PAGE_END);

        pnViTriCenter.add(pnViTriLeft, java.awt.BorderLayout.LINE_START);

        pnViTri.add(pnViTriCenter, java.awt.BorderLayout.CENTER);

        add(pnViTri, java.awt.BorderLayout.CENTER);
    }// </editor-fold>                        

    private void tbFoodKeyReleased(java.awt.event.KeyEvent evt) {                                   
        setForm();
    }                                  

    private void tbFoodMousePressed(java.awt.event.MouseEvent evt) {                                    
        setForm();
    }                                   

    private void tfFoodIdActionPerformed(java.awt.event.ActionEvent evt) {                                         
        // TODO add your handling code here:
    }                                        

    private void tfFoodNameActionPerformed(java.awt.event.ActionEvent evt) {                                           
        // TODO add your handling code here:
    }                                          

    private void btPriceFoodActionPerformed(java.awt.event.ActionEvent evt) {                                            
        crow = tbFood.getSelectedRow();
        int id = 0;
        try {
            id = Integer.parseInt(tfFoodId.getText());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn một thức uống");
            return;
        }
       if (id != 0){
            btFoodSua.setEnabled(false);
            btFoodSua.setEnabled(false);
            btFoodThem.setEnabled(false);
            btFoodXoa.setEnabled(false);
            btFoodNhapLai.setEnabled(false);
            tbFood.setRowSelectionAllowed(false);
        } else {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn một thức uống");
            return;
        }
    }                                           

    private void tfFoodIdFindActionPerformed(java.awt.event.ActionEvent evt) {                                             
        // TODO add your handling code here:
    }                                            

    private void tfFoodNameFindActionPerformed(java.awt.event.ActionEvent evt) {                                               
        // TODO add your handling code here:
    }                                              


    // Variables declaration - do not modify                     
    private javax.swing.JButton btFoodFind;
    private javax.swing.JButton btFoodNhapLai;
    private javax.swing.JButton btFoodSua;
    private javax.swing.JButton btFoodThem;
    private javax.swing.JButton btFoodXoa;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel pnFoodInTacVu;
    private javax.swing.JPanel pnFoodInTimKiem;
    private javax.swing.JPanel pnFoodTacVu;
    private javax.swing.JPanel pnFoodTimKiem;
    private javax.swing.JPanel pnViTri;
    private javax.swing.JPanel pnViTriCenter;
    private javax.swing.JPanel pnViTriLeft;
    private javax.swing.JPanel pnViTriTop;
    private javax.swing.JTable tbFood;
    private javax.swing.JTextField tfFoodId;
    private javax.swing.JTextField tfFoodIdFind;
    private javax.swing.JTextField tfFoodName;
    private javax.swing.JTextField tfFoodNameFind;
    private javax.swing.JTextField tfFoodPrice;
    private javax.swing.JLabel tfTitleCenter;
    // End of variables declaration                   

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btFoodThem) {
            try {
                Date date_create = new Date();
                Timestamp dateupdateprice = new Timestamp(date_create.getTime());
                int price = Integer.parseInt(tfFoodPrice.getText());
                String picture = "";
                Food item = new Food(0, tfFoodName.getText(), dateupdateprice, price, picture);
                if (controller.add(item) > 0) {
                    loadTable(null);
                    JOptionPane.showMessageDialog(null, "Thêm thành công");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Vui lòng điền đầy đủ thông tin đúng định dạng");
                tfFoodPrice.setText("");
                tfFoodPrice.requestFocus();
                return;
            }

        } else if (e.getSource() == btFoodSua) {
            crow = tbFood.getSelectedRow();
            int price_current = 0;
            int id = 0;

            try {
                id = Integer.parseInt(tfFoodId.getText());
                price_current = Integer.parseInt(tbFood.getValueAt(crow,2).toString());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Vui lòng chọn một thức uống");

            }
            String name = tfFoodName.getText();
            Date date_create = new Date();
            Timestamp dateupdateprice = new Timestamp(date_create.getTime());
            int price = Integer.parseInt(tfFoodPrice.getText());
            String picture = "";
            Food item = new Food(id, name, dateupdateprice, price, picture);
            if (controller.edit(item, price_current) > 0) {
                loadTable(null);
                JOptionPane.showMessageDialog(null, "Sửa thành công");
                tbFood.setRowSelectionInterval(crow, crow);

                
            }
            
        } else if (e.getSource() == btFoodXoa) {
            int id = 0;
            try {
                id = Integer.parseInt(tfFoodId.getText());
            } catch (NumberFormatException ex) {
                System.out.println("Vui lòng chọn một thức uống");
            }
            Food item = new Food(id, "", 0, "");
            if (controller.del(item) > 0) {
                loadTable(null);
                resetForm();
                JOptionPane.showMessageDialog(null, "Xóa thành công");
            }

        } else if (e.getSource() == btFoodNhapLai) {
            resetForm();
            tbFood.setRowSelectionInterval(crow, crow);
        } else if (e.getSource() == btFoodFind){
            int id = 0;
            try{
                id = Integer.parseInt(tfFoodIdFind.getText());
            }
            catch (NumberFormatException ex){
                System.out.println("Phải nhập số vào ID");
            }
            Food foodFind = new Food(id, tfFoodNameFind.getText(),0, "");
            loadTable(foodFind);
        }
    }
}
