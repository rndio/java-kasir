package DashboardKasir;

import Function.DatabaseConnection;
import java.awt.Color;
import java.sql.*;
import java.text.NumberFormat;
import java.util.Locale;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

/**
 *
 * @author MrSimamora
 */
public class Receipt extends javax.swing.JFrame {

    
    Connection cn = DatabaseConnection.getConnection();
    NumberFormat nf = NumberFormat.getNumberInstance(new Locale("in", "ID"));
    public int id = 0;

    public Receipt() {
        initComponents();
        formatJTable();
    }
    
    public void render(int id){
        this.id = id;
        this.setVisible(true);
        renderTable(this.id);
    }
    
    public void printFrame() {
        PrinterJob job = PrinterJob.getPrinterJob();
        job.setPrintable(new PrintableReceipt(this));

        if (job.printDialog()) {
            try {
                job.print();
            } catch (PrinterException e) {
                // Handle the exception
                e.printStackTrace();
            }
        }
    }
    
    private void formatJTable(){
        JTableHeader header = tableData.getTableHeader();
        header.setVisible(false);
        DefaultTableCellRenderer right = new DefaultTableCellRenderer();
        right.setHorizontalAlignment(JLabel.RIGHT);
        tableData.getColumnModel().getColumn(1).setCellRenderer(right);
        tableData.setShowGrid(false);
        tableData.setAutoResizeMode(0);
        tableData.getColumnModel().getColumn(0).setPreferredWidth(70);
        tableData.getColumnModel().getColumn(1).setPreferredWidth(70);
        tablePane.getColumnHeader().setVisible(false);
        tablePane.getViewport().setBackground(Color.WHITE);
    }
    
    public void renderTable(int id){
        System.out.println(id);
        try{
            Statement st = cn.createStatement();
            
            PreparedStatement psmt = cn.prepareStatement("SELECT tt.*,tb.kd_barang, tk.nm_kasir ,tb.nm_barang, tb.harga ,ttd.qty, tb.harga * ttd.qty AS total_harga FROM `tbl_transaksi` AS tt INNER JOIN tbl_transaksi_detail AS ttd ON tt.id_transaksi = ttd.id_transaksi INNER JOIN tbl_barang AS tb ON ttd.kd_barang = tb.kd_barang INNER JOIN tbl_kasir AS tk ON tt.id_kasir = tk.id_kasir WHERE tt.id_transaksi = ?");
            psmt.setInt(1, id);
            ResultSet rs = psmt.executeQuery();
            
            DefaultTableModel model = new DefaultTableModel();
            
            model.addColumn("Nama Barang");
            model.addColumn("QTY");
            model.addColumn("Harga Satuan");
            
            model.getDataVector().removeAllElements();
            model.fireTableDataChanged();
            model.setRowCount(0);
            double total = 0;
            while(rs.next()){
                txtKasir.setText("Kasir : " + rs.getString("nm_kasir"));
                txtDiskon.setText("Diskon : " + rs.getString("diskon") + "%");
                txtTunai.setText("Tunai : Rp" + nf.format(Integer.parseInt(rs.getString("tunai"))));
                txtKembalian.setText("Kembalian : Rp" + nf.format(Integer.parseInt(rs.getString("kembalian"))));
                total += rs.getInt("total_harga");
                Object[] data = {
                  rs.getString("nm_barang"),
                  rs.getString("qty"),
                  nf.format(Integer.parseInt(rs.getString("harga")))
                };
                model.addRow(data); 
            }
            txtTotalHarga.setText("Total Harga : Rp" + nf.format(total));
            tableData.setCellEditor(null);
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

        jPanel1 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        separatorTextArea = new javax.swing.JTextArea();
        jScrollPane4 = new javax.swing.JScrollPane();
        headerTextArea = new javax.swing.JTextArea();
        jScrollPane5 = new javax.swing.JScrollPane();
        separatorTextArea1 = new javax.swing.JTextArea();
        jScrollPane6 = new javax.swing.JScrollPane();
        separatorTextArea2 = new javax.swing.JTextArea();
        tablePane = new javax.swing.JScrollPane();
        tableData = new javax.swing.JTable();
        txtKasir = new javax.swing.JLabel();
        txtKembalian = new javax.swing.JLabel();
        txtDiskon = new javax.swing.JLabel();
        txtTunai = new javax.swing.JLabel();
        txtTotalHarga = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setResizable(false);
        setType(java.awt.Window.Type.POPUP);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane3.setBorder(null);
        jScrollPane3.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane3.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        separatorTextArea.setEditable(false);
        separatorTextArea.setBackground(new java.awt.Color(255, 255, 255));
        separatorTextArea.setColumns(20);
        separatorTextArea.setFont(new java.awt.Font("Consolas", 1, 14)); // NOI18N
        separatorTextArea.setRows(5);
        separatorTextArea.setText("  Nama Barang       QTY      Harga Satuan");
        separatorTextArea.setAutoscrolls(false);
        separatorTextArea.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        separatorTextArea.setFocusable(false);
        jScrollPane3.setViewportView(separatorTextArea);

        jPanel1.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 130, 350, 20));

        jScrollPane4.setBorder(null);
        jScrollPane4.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane4.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        headerTextArea.setEditable(false);
        headerTextArea.setBackground(new java.awt.Color(255, 255, 255));
        headerTextArea.setColumns(20);
        headerTextArea.setFont(new java.awt.Font("Consolas", 1, 14)); // NOI18N
        headerTextArea.setRows(5);
        headerTextArea.setText("                 Jon Grosir\n          Jl. Pembangunan No. 111\n\n         https://jon-grosir.store\n               +628123456789");
        headerTextArea.setAutoscrolls(false);
        headerTextArea.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        headerTextArea.setFocusable(false);
        jScrollPane4.setViewportView(headerTextArea);

        jPanel1.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 350, 90));

        jScrollPane5.setBorder(null);
        jScrollPane5.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane5.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        separatorTextArea1.setEditable(false);
        separatorTextArea1.setBackground(new java.awt.Color(255, 255, 255));
        separatorTextArea1.setColumns(20);
        separatorTextArea1.setFont(new java.awt.Font("Consolas", 1, 14)); // NOI18N
        separatorTextArea1.setRows(5);
        separatorTextArea1.setText("--------------------------------------------");
        separatorTextArea1.setAutoscrolls(false);
        separatorTextArea1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        separatorTextArea1.setFocusable(false);
        jScrollPane5.setViewportView(separatorTextArea1);

        jPanel1.add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 110, 350, 10));

        jScrollPane6.setBorder(null);
        jScrollPane6.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane6.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        separatorTextArea2.setEditable(false);
        separatorTextArea2.setBackground(new java.awt.Color(255, 255, 255));
        separatorTextArea2.setColumns(20);
        separatorTextArea2.setFont(new java.awt.Font("Consolas", 1, 14)); // NOI18N
        separatorTextArea2.setRows(5);
        separatorTextArea2.setText("--------------------------------------------");
        separatorTextArea2.setAutoscrolls(false);
        separatorTextArea2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        separatorTextArea2.setFocusable(false);
        jScrollPane6.setViewportView(separatorTextArea2);

        jPanel1.add(jScrollPane6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 150, 350, 10));

        tablePane.setBackground(new java.awt.Color(255, 255, 255));
        tablePane.setBorder(null);
        tablePane.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        tablePane.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        tableData.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Title 1", "Title 2"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableData.setGridColor(new java.awt.Color(255, 255, 255));
        tableData.setSelectionBackground(new java.awt.Color(255, 255, 255));
        tablePane.setViewportView(tableData);

        jPanel1.add(tablePane, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 170, 330, 200));

        txtKasir.setText("Kasir : ");
        jPanel1.add(txtKasir, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 380, -1, -1));

        txtKembalian.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        txtKembalian.setText("Kembalian : ");
        jPanel1.add(txtKembalian, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 420, 200, -1));

        txtDiskon.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        txtDiskon.setText("Diskon : ");
        jPanel1.add(txtDiskon, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 400, -1, -1));

        txtTunai.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        txtTunai.setText("Tunai : ");
        jPanel1.add(txtTunai, new org.netbeans.lib.awtextra.AbsoluteConstraints(158, 400, 180, -1));

        txtTotalHarga.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        txtTotalHarga.setText("Total Harga : ");
        jPanel1.add(txtTotalHarga, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 380, 180, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 452, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(Receipt.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Receipt.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Receipt.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Receipt.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Receipt().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea headerTextArea;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JTextArea separatorTextArea;
    private javax.swing.JTextArea separatorTextArea1;
    private javax.swing.JTextArea separatorTextArea2;
    private javax.swing.JTable tableData;
    private javax.swing.JScrollPane tablePane;
    private javax.swing.JLabel txtDiskon;
    private javax.swing.JLabel txtKasir;
    private javax.swing.JLabel txtKembalian;
    private javax.swing.JLabel txtTotalHarga;
    private javax.swing.JLabel txtTunai;
    // End of variables declaration//GEN-END:variables
}
