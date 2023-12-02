/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package DashboardAdmin;

import Function.DatabaseConnection;
import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import java.text.SimpleDateFormat;
import java.util.Date;


/**
 *
 * @author MrSimamora
 */
public class form_barang extends javax.swing.JFrame {

    /**
     * Creates new form form_kasir
     */
    
    Connection cn = DatabaseConnection.getConnection();
    
    public form_barang() {
        initComponents();
        getJenisBarangtoComboBox();
        getDataTable();
    }
    
    private void getJenisBarangtoComboBox(){
        try{
            // Init Statement
            Statement st = cn.createStatement();
            // Query Execute
            ResultSet rs = st.executeQuery("SELECT * FROM tbl_jenisbarang");
            
            while (rs.next()) {
                id_jenis.addItem(rs.getString("nama"));
            }
        }catch (SQLException e){e.printStackTrace();}
    }
    
    private void clearForm(){
        kd_barang.setText("");
        nm_barang.setText("");
        id_jenis.setSelectedIndex(0);
        jumlah.setText("0");
        tgl_exp.setText("01-01-1970");
        harga.setText("0");
    }
    
    private void getDataTable(){
        try{
            Statement st = cn.createStatement();
            String sql = "SELECT b.*, jb.nama AS nm_jenis, DATE_FORMAT(tgl_exp, '%d-%m-%Y') AS formatted_tgl_exp FROM tbl_barang AS b INNER JOIN tbl_jenisbarang AS jb ON b.id_jenis = jb.id;";
            ResultSet rs = st.executeQuery(sql);
            
            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("Kode Barang");
            model.addColumn("Nama");
            model.addColumn("Jenis Barang");
            model.addColumn("Jumlah");
            model.addColumn("Tgl Exp.");
            model.addColumn("Harga");
            
            model.getDataVector().removeAllElements();
            model.fireTableDataChanged();
            model.setRowCount(0);
            
            while(rs.next()){
                Object[] data = {
                  rs.getString("kd_barang"),
                  rs.getString("nm_barang"),
                  rs.getString("nm_jenis"),
                  rs.getString("jumlah"),
                  rs.getString("formatted_tgl_exp"),
                  rs.getString("harga"),
                };
                model.addRow(data);   
            }
            
            tableData.setModel(model);
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        nm_barang = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        id_jenis = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        jumlah = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        tgl_exp = new javax.swing.JFormattedTextField();
        jLabel6 = new javax.swing.JLabel();
        harga = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableData = new javax.swing.JTable();
        btn_simpan = new javax.swing.JButton();
        btn_hapus = new javax.swing.JButton();
        btn_batal = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        kd_barang = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Barlow", 1, 18)); // NOI18N
        jLabel1.setText("Form Barang");

        jLabel2.setText("Nama Barang");

        nm_barang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nm_barangActionPerformed(evt);
            }
        });

        jLabel3.setText("Jenis Barang");

        id_jenis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                id_jenisActionPerformed(evt);
            }
        });

        jLabel4.setText("Jumlah");

        jumlah.setText("0");
        jumlah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jumlahActionPerformed(evt);
            }
        });

        jLabel5.setText("Tanggal Exp");

        tgl_exp.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat("dd-MM-yyyy"))));
        tgl_exp.setText("01-01-1970");
        tgl_exp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tgl_expActionPerformed(evt);
            }
        });

        jLabel6.setText("Harga");

        harga.setText("0");
        harga.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hargaActionPerformed(evt);
            }
        });

        tableData.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Kode Barang", "Nama", "Jenis", "Jumlah", "Tgl Expired", "Harga"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Boolean.class, java.lang.String.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableData.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableDataMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tableData);
        if (tableData.getColumnModel().getColumnCount() > 0) {
            tableData.getColumnModel().getColumn(0).setResizable(false);
        }

        btn_simpan.setText("Simpan");
        btn_simpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_simpanActionPerformed(evt);
            }
        });

        btn_hapus.setText("Hapus");
        btn_hapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_hapusActionPerformed(evt);
            }
        });

        btn_batal.setText("Batal");
        btn_batal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_batalActionPerformed(evt);
            }
        });

        jLabel7.setText("ID Barang");

        kd_barang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kd_barangActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel2)
                            .addGap(12, 12, 12)
                            .addComponent(nm_barang))
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel3)
                                .addComponent(jLabel4)
                                .addComponent(jLabel5)
                                .addComponent(jLabel6))
                            .addGap(18, 18, 18)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jumlah, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addComponent(btn_simpan)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(btn_hapus)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(btn_batal))
                                .addComponent(harga, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(tgl_exp, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(id_jenis, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addComponent(jLabel7))
                    .addComponent(kd_barang, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(39, 39, 39)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(16, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(kd_barang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(nm_barang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(id_jenis, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(jumlah, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(tgl_exp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(harga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btn_simpan)
                            .addComponent(btn_hapus)
                            .addComponent(btn_batal))
                        .addGap(28, 28, 28))))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void nm_barangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nm_barangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nm_barangActionPerformed

    private void id_jenisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_id_jenisActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_id_jenisActionPerformed

    private void jumlahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jumlahActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jumlahActionPerformed

    private void tgl_expActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tgl_expActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tgl_expActionPerformed

    private void hargaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hargaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_hargaActionPerformed

    private void btn_simpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_simpanActionPerformed
        try{
            Statement st = cn.createStatement();
            if(nm_barang.getText().equals("") || jumlah.getText().equals(0) || harga.getText().equals(0)){
                JOptionPane.showMessageDialog(null, "Data tidak boleh kosong!", "Validasi Data", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            
            if(btn_simpan.getText().equals("Simpan")){
                // Create Action
                String sql = "SELECT * FROM tbl_barang WHERE nm_barang = '" + nm_barang.getText() + "'";
                ResultSet rs = st.executeQuery(sql);
                if(rs.next()){
                    JOptionPane.showMessageDialog(null, "Barang sudah terdaftar!", "Validasi Data", JOptionPane.INFORMATION_MESSAGE);
                }else{
                    
                    PreparedStatement psmt = cn.prepareStatement("SELECT id FROM tbl_jenisbarang WHERE nama = ?");
                    psmt.setString(1, id_jenis.getSelectedItem().toString());
                    ResultSet hasil = psmt.executeQuery();

                    int jenis_id = 0;
                    if(hasil.next()){
                        jenis_id = hasil.getInt("id");
                    }
                    
                    String dateString = tgl_exp.getText();
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                    Date tglExp = dateFormat.parse(dateString);
                    java.sql.Date sqlTglExp = new java.sql.Date(tglExp.getTime());
                    
                    psmt = cn.prepareStatement("INSERT INTO tbl_barang VALUES (?,?,?,?,?,?)");
                    psmt.setString(1, kd_barang.getText());
                    psmt.setString(2, nm_barang.getText());
                    psmt.setInt(3, jenis_id);
                    psmt.setInt(4, Integer.parseInt(jumlah.getText()));
                    psmt.setDate(5, sqlTglExp);
                    psmt.setInt(6, Integer.parseInt(harga.getText()));
                    psmt.executeUpdate();
                    psmt.close();
                    
                    clearForm();
                    getDataTable();
                }
            }else{
                // Edit Action
                
                
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);
            
        }
    }//GEN-LAST:event_btn_simpanActionPerformed

    private void btn_hapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_hapusActionPerformed
        if(kd_barang.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Silahkan Pilih Data yg akan dihapus!");
        }else{
            int jawab = JOptionPane.showConfirmDialog(null, "Apakah anda yakin ingin menghapus data ini?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if(jawab == 0){
                try{
                PreparedStatement psmt = cn.prepareStatement("DELETE FROM tbl_barang WHERE kd_barang = ?");
                psmt.setString(1, kd_barang.getText());
                psmt.executeUpdate();
                psmt.close();
                clearForm();
                getDataTable();
                }catch(Exception e){
                    JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);
                }
                
            }
        }
    }//GEN-LAST:event_btn_hapusActionPerformed

    private void btn_batalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_batalActionPerformed
        btn_simpan.setText("Simpan");
        clearForm();
    }//GEN-LAST:event_btn_batalActionPerformed

    private void tableDataMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableDataMouseClicked
        kd_barang.setText(tableData.getValueAt(tableData.getSelectedRow(), 0).toString());
        nm_barang.setText(tableData.getValueAt(tableData.getSelectedRow(), 1).toString());
        id_jenis.setSelectedItem(tableData.getValueAt(tableData.getSelectedRow(), 2).toString());
        jumlah.setText(tableData.getValueAt(tableData.getSelectedRow(), 3).toString());
        tgl_exp.setText(tableData.getValueAt(tableData.getSelectedRow(), 4).toString());
        harga.setText(tableData.getValueAt(tableData.getSelectedRow(), 5).toString());
        btn_simpan.setText("Ubah");
    }//GEN-LAST:event_tableDataMouseClicked

    private void kd_barangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kd_barangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_kd_barangActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(form_barang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(form_barang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(form_barang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(form_barang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new form_barang().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_batal;
    private javax.swing.JButton btn_hapus;
    private javax.swing.JButton btn_simpan;
    private javax.swing.JTextField harga;
    private javax.swing.JComboBox<String> id_jenis;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jumlah;
    private javax.swing.JTextField kd_barang;
    private javax.swing.JTextField nm_barang;
    private javax.swing.JTable tableData;
    private javax.swing.JFormattedTextField tgl_exp;
    // End of variables declaration//GEN-END:variables
}