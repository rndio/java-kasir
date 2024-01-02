/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package DashboardKasir;

import java.sql.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.NumberFormat;
import java.util.Locale;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.Document;

/**
 *
 * @author MrSimamora
 */
public class Transaksi extends javax.swing.JFrame {

    
    Connection cn = Function.DatabaseConnection.getConnection();
    NumberFormat nf = NumberFormat.getNumberInstance(new Locale("in", "ID"));
    
    public Transaksi() {
        initComponents();
        total();
        kalkulatorDiskon();
        kalkulatorKembalian();
        txtKodeBarang.grabFocus();
    }
    
    private void kalkulatorKembalian() {
        txtBayar.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            private void x() {
                try {
                    if (txtBayar.getText().equals("")) {
                        txtKembalian.setText(nf.format(0));
                        return;
                    }
                    int bayar = Integer.parseInt(txtBayar.getText().replace(".", ""));
                    int kembalian = bayar - Integer.parseInt(txtJumlahHarga.getText().replace(".", ""));
                    txtKembalian.setText(nf.format(kembalian));
                    
                    if(kembalian < 0){
                        btnProsesBayar.disable();
                    }else{
                        btnProsesBayar.enable();
                    }
                    
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex);
                }
            }
            
            @Override
            public void insertUpdate(DocumentEvent e) {
                // throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
                x();
            }
            
            @Override
            public void removeUpdate(DocumentEvent e) {
                // throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
                x();
            }
            
            @Override
            public void changedUpdate(DocumentEvent e) {
                // throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
                // x();
            }
        });
    }
    
    private void kalkulatorDiskon() {
        txtDiskon.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            private void x() {
                int diskon,subtotal;
                double total;
                
                if(txtDiskon.getText().equals("")){
                    txtDiskon.setText("0");
                    diskon = 0;
                }else{
                    diskon = Integer.parseInt(txtDiskon.getText());
                }
                
                if(Integer.parseInt(txtDiskon.getText()) < 0 || Integer.parseInt(txtDiskon.getText()) > 100){
                    JOptionPane.showMessageDialog(null, "Nominal Diskon tidak valid");
                    txtDiskon.setText("0");
                    diskon = 0;
                }
               
                subtotal = Integer.parseInt(txtSubTotal.getText().replace(".", ""));
                total = subtotal - ((subtotal * diskon) / 100);
                txtJumlahHarga.setText(nf.format(total));
                txtTotalHargaBesar.setText("Rp"+nf.format(total));
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
                x();
            }
            
            @Override
            public void removeUpdate(DocumentEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
                x();
            }
            
            @Override
            public void changedUpdate(DocumentEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
                x();
            }
        });
    }
    
    private void total() {
        txtQTY.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            
            private void x() {
                try {
                    int hasil = 0;
                    if (txtQTY.getText().equals("")) {
                        hasil = Integer.parseInt(txtHargaBarang.getText().replace(".", "")) * 0;
                    } else {
                        hasil = Integer.parseInt(txtHargaBarang.getText().replace(".", ""))
                                * Integer.parseInt(txtQTY.getText());
                    }
                    txtTotalHarga.setText(nf.format(hasil));
                } catch (Exception ex) {
                    
                }
            }
            
            @Override
            public void insertUpdate(DocumentEvent e) {
                // throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
                x();
            }
            
            @Override
            public void removeUpdate(DocumentEvent e) {
                // throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
                x();
            }
            
            @Override
            public void changedUpdate(DocumentEvent e) {
                // throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }
        });
    }
    
    private void jmlTotalHarga() {
        int sub_total = 0;
        for (int i = 0; i < tblList.getRowCount(); i++) {
            sub_total += Integer.parseInt((String) tblList.getValueAt(i, 5).toString().replace(".", ""));            
        }
        txtSubTotal.setText(nf.format(sub_total));
        txtTotalHargaBesar.setText(("Rp" + nf.format(sub_total)));
    }
    
    private void clearForm() {
        txtQTY.setText("");
        txtTotalHarga.setText("");
        txtNamaBarang.setText("");
        txtHargaBarang.setText("");
        txtKodeBarang.setText("");
        txtKodeBarang.grabFocus();
    }
    
    private void clearTable(){
        DefaultTableModel model = (DefaultTableModel) tblList.getModel();
        model.setRowCount(0);
    }
    
    private void clearCalculator(){
        txtTotalHargaBesar.setText("Rp0");
        txtSubTotal.setText("");
        txtDiskon.setText("");
        txtJumlahHarga.setText("");
        txtBayar.setText("");
        txtKembalian.setText("");
    }
    
    private void cariBarang() {
        try {
            String kodebarang = txtKodeBarang.getText();
            Connection cn = Function.DatabaseConnection.getConnection();
            String sql = "SELECT * FROM tbl_barang WHERE kd_barang = ? LIMIT 1";
            PreparedStatement myStmt = cn.prepareStatement(sql);
            myStmt.setString(1, kodebarang);
            ResultSet rs = myStmt.executeQuery();
            
            if (rs.next()) {
                do {
                    String namaBarang = rs.getString("nm_barang");
                    String hargaBarang = nf.format(rs.getInt("harga"));
                    txtNamaBarang.setText(namaBarang);
                    txtHargaBarang.setText(hargaBarang);
                    txtQTY.setText("1");
                    txtTotalHarga.setText(hargaBarang);
                } while (rs.next());
                txtQTY.grabFocus();
            } else {
                JOptionPane.showMessageDialog(null, "Barang tidak ditemukan di Database!");
            }
            cn.close();
        } catch (SQLException e) {
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

        txtTotalHargaBesar = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        txtKodeBarang = new javax.swing.JTextField();
        txtNamaBarang = new javax.swing.JTextField();
        txtHargaBarang = new javax.swing.JTextField();
        txtQTY = new javax.swing.JTextField();
        txtTotalHarga = new javax.swing.JTextField();
        btnCariBarang = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblList = new javax.swing.JTable();
        txtSubTotal = new javax.swing.JTextField();
        ssasdasd = new javax.swing.JLabel();
        btnProsesBayar = new javax.swing.JButton();
        ssasdasd1 = new javax.swing.JLabel();
        txtDiskon = new javax.swing.JTextField();
        txtJumlahHarga = new javax.swing.JTextField();
        ssasdasd2 = new javax.swing.JLabel();
        txtBayar = new javax.swing.JTextField();
        ssasdasd3 = new javax.swing.JLabel();
        txtKembalian = new javax.swing.JTextField();
        ssasdasd4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        txtTotalHargaBesar.setFont(new java.awt.Font("Consolas", 1, 36)); // NOI18N
        txtTotalHargaBesar.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        txtTotalHargaBesar.setText("Rp0");

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel3.setText("Kode Barang");

        txtKodeBarang.setText("B");
        txtKodeBarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtKodeBarangActionPerformed(evt);
            }
        });

        txtNamaBarang.setEditable(false);

        txtHargaBarang.setEditable(false);
        txtHargaBarang.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        txtQTY.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtQTY.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtQTYActionPerformed(evt);
            }
        });

        txtTotalHarga.setEditable(false);
        txtTotalHarga.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        btnCariBarang.setText("...");
        btnCariBarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCariBarangActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtKodeBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCariBarang)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNamaBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtHargaBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtQTY, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTotalHarga, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(66, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtKodeBarang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNamaBarang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtHargaBarang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtQTY, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTotalHarga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCariBarang))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tblList.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No", "Kode Barang", "Nama Barang", "Harga", "QTY", "Total Harga"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblList);
        if (tblList.getColumnModel().getColumnCount() > 0) {
            tblList.getColumnModel().getColumn(0).setResizable(false);
            tblList.getColumnModel().getColumn(1).setResizable(false);
            tblList.getColumnModel().getColumn(2).setResizable(false);
            tblList.getColumnModel().getColumn(3).setResizable(false);
            tblList.getColumnModel().getColumn(4).setResizable(false);
            tblList.getColumnModel().getColumn(5).setResizable(false);
        }

        txtSubTotal.setEditable(false);

        ssasdasd.setText("Sub Total");

        btnProsesBayar.setText("Proses Pembayaran");
        btnProsesBayar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProsesBayarActionPerformed(evt);
            }
        });

        ssasdasd1.setText("Diskon (%)");

        txtJumlahHarga.setEditable(false);

        ssasdasd2.setText("Total");

        ssasdasd3.setText("Bayar");

        txtKembalian.setEditable(false);

        ssasdasd4.setText("Kembalian");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jScrollPane1)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(ssasdasd2)
                                        .addGap(12, 12, 12)
                                        .addComponent(txtJumlahHarga, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(ssasdasd1)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtDiskon, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(ssasdasd)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtSubTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(ssasdasd4)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(txtKembalian, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                            .addComponent(ssasdasd3)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(txtBayar, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addComponent(btnProsesBayar, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(txtTotalHargaBesar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 504, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(txtTotalHargaBesar, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtBayar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(ssasdasd3))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtSubTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(ssasdasd)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtKembalian, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(ssasdasd4))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtDiskon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(ssasdasd1)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtJumlahHarga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(ssasdasd2))
                    .addComponent(btnProsesBayar))
                .addGap(28, 28, 28))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txtQTYActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtQTYActionPerformed
        if (txtQTY.getText().equals("")) {
            return;
        } else {
            DefaultTableModel model = (DefaultTableModel) tblList.getModel();
            
            Object obj[] = new Object[6];
            obj[1] = txtKodeBarang.getText();
            obj[2] = txtNamaBarang.getText();
            obj[3] = txtHargaBarang.getText();
            obj[4] = txtQTY.getText();
            obj[5] = txtTotalHarga.getText();
            
            model.addRow(obj);
            int baris = model.getRowCount();
            for (int i = 0; i < baris; i++) {
                String no = String.valueOf(i + 1);
                model.setValueAt(no + ".", i, 0);
            }
            tblList.setRowHeight(30);
            jmlTotalHarga();
            clearForm();
        }
    }//GEN-LAST:event_txtQTYActionPerformed

    private void txtKodeBarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtKodeBarangActionPerformed
        cariBarang();
    }//GEN-LAST:event_txtKodeBarangActionPerformed

    private void btnProsesBayarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProsesBayarActionPerformed
        try(PreparedStatement psmt = cn.prepareStatement("INSERT INTO tbl_transaksi (id_kasir,tunai,kembalian,diskon) VALUES (1,?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS)) {
        psmt.setInt(1, Integer.parseInt(txtBayar.getText().replace(".","")));
        psmt.setInt(2, Integer.parseInt(txtKembalian.getText().replace(".","")));
        psmt.setInt(3, Integer.parseInt(txtDiskon.getText().replace(".","")));
        int affectedRows = psmt.executeUpdate();
        
        if(affectedRows > 0){
            // Retrieve the generated keys
            try (ResultSet generatedKeys = psmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int lastInsertedId = generatedKeys.getInt(1);
                    for (int i = 0; i < tblList.getRowCount(); i++) {
                        PreparedStatement psmt2 = cn.prepareStatement("INSERT INTO tbl_transaksi_detail (id_transaksi,kd_barang,qty) VALUES (?,?,?)");
                        psmt2.setInt(1, lastInsertedId);
                        psmt2.setString(2, tblList.getValueAt(i, 1).toString());
                        psmt2.setInt(3, Integer.parseInt(tblList.getValueAt(i, 4).toString()));
                        psmt2.executeUpdate();
                    }
                    JOptionPane.showMessageDialog(null, "Transaksi Berhasil!");
                    Receipt x = new Receipt();
                    x.render(lastInsertedId);
                    x.printFrame();
                    clearTable();
                    clearForm();
                }
            }
        } else {
            JOptionPane.showMessageDialog(null,"No rows affected.");
        }
            
        }catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_btnProsesBayarActionPerformed

    private void btnCariBarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCariBarangActionPerformed
        cariBarang();
    }//GEN-LAST:event_btnCariBarangActionPerformed

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
            java.util.logging.Logger.getLogger(Transaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Transaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Transaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Transaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Transaksi().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCariBarang;
    private javax.swing.JButton btnProsesBayar;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel ssasdasd;
    private javax.swing.JLabel ssasdasd1;
    private javax.swing.JLabel ssasdasd2;
    private javax.swing.JLabel ssasdasd3;
    private javax.swing.JLabel ssasdasd4;
    private javax.swing.JTable tblList;
    private javax.swing.JTextField txtBayar;
    private javax.swing.JTextField txtDiskon;
    private javax.swing.JTextField txtHargaBarang;
    private javax.swing.JTextField txtJumlahHarga;
    private javax.swing.JTextField txtKembalian;
    private javax.swing.JTextField txtKodeBarang;
    private javax.swing.JTextField txtNamaBarang;
    private javax.swing.JTextField txtQTY;
    private javax.swing.JTextField txtSubTotal;
    private javax.swing.JTextField txtTotalHarga;
    private javax.swing.JLabel txtTotalHargaBesar;
    // End of variables declaration//GEN-END:variables
}
