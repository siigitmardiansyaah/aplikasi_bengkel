
package bengkel;
import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

public class fsparepart extends javax.swing.JInternalFrame {
        private Connection conn = new Koneksi().connect();
        private DefaultTableModel tabmode;
        
        Map<String, Object> param = new HashMap<String, Object>();
        
    public fsparepart() {
        initComponents();
         ((javax.swing.plaf.basic.BasicInternalFrameUI)getUI()).setNorthPane(null);
        autoNumber();
        non_aktif();
        datatable();
    }
    @SuppressWarnings("unchecked")
    
     private void datatable() {
        Object[] Baris = {"Kode Sparepart", "Nama Sparepart", "Harga", "Stok", "Ongkos"};
        tabmode = new DefaultTableModel(null, Baris);
        tblsparepart.setModel(tabmode);
        String sql = "select * from sparepart order by kd_sparepart ASC";
        try {
            Statement stat = conn.createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            while (hasil.next()) {
                String kode = hasil.getString("kd_sparepart");
                String nama = hasil.getString("nm_sparepart");
                String stok = hasil.getString("stok");
                String harga = hasil.getString("harga");
                String ongkos = hasil.getString("ongkos");
                String[] data = {kode,nama,harga,stok,ongkos};
                tabmode.addRow(data);
            }
        } catch (Exception e) {
        }
    }
    
    private void non_aktif() {
        txkode.setEnabled(false);
        txnama.setEnabled(false);
        txstok.setEnabled(false);
        txharga.setEnabled(false);
        txongkos.setEnabled(false);
    }
    
    private void aktif() {
        txstok.setEnabled(true);
        txnama.setEnabled(true);
        txharga.setEnabled(true);
        txongkos.setEnabled(true);
        txkode.requestFocus();
    }
    
     private void kosong() {
        txstok.setText("");
        txnama.setText("");
        txharga.setText("");
        txongkos.setText("");
        tambah.setEnabled(true);      
        update.setEnabled(false);
        hapus.setEnabled(false);
    }
     
     private void autoNumber() {
        try {
            String sql = "SELECT * FROM sparepart ORDER BY kd_sparepart DESC";
            PreparedStatement stat = conn.prepareCall(sql);
            ResultSet rs = stat.executeQuery(sql);
            if (rs.next()) {
                String kd = rs.getString("kd_sparepart").substring(2);
                String AN =  "" + (Integer.parseInt(kd) + 1);
                String Nol = "";

                if (AN.length()==1) {
                    Nol = "000";
                } else if (AN.length()==2) {
                    Nol = "00";
                } else if (AN.length()==3) {
                    Nol = "0";
                } else if (AN.length()==4) {
                    Nol = "";
                }
                txkode.setText("SP" + Nol + AN);
            } else {
                txkode.setText("SP0001");
            }
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        txnama = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txharga = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txkode = new javax.swing.JTextField();
        txstok = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txongkos = new javax.swing.JTextField();
        update = new javax.swing.JButton();
        tambah = new javax.swing.JButton();
        hapus = new javax.swing.JButton();
        batal = new javax.swing.JButton();
        batal1 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblsparepart = new javax.swing.JTable();
        txtCari = new javax.swing.JTextField();
        btnCari = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();

        setClosable(true);
        setForeground(new java.awt.Color(255, 255, 255));
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setPreferredSize(new java.awt.Dimension(1062, 551));

        jPanel3.setBackground(new java.awt.Color(204, 204, 204));

        jPanel5.setBackground(new java.awt.Color(204, 204, 204));

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));
        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel3.setFont(new java.awt.Font("Serif", 1, 14)); // NOI18N
        jLabel3.setText("HARGA");

        jLabel1.setFont(new java.awt.Font("Serif", 1, 14)); // NOI18N
        jLabel1.setText("KODE");

        jLabel2.setFont(new java.awt.Font("Serif", 1, 14)); // NOI18N
        jLabel2.setText("NAMA SPAREPART");

        txharga.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txhargaActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Serif", 1, 14)); // NOI18N
        jLabel5.setText("STOK");

        txstok.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txstokActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Serif", 1, 14)); // NOI18N
        jLabel6.setText("ONGKOS");

        update.setFont(new java.awt.Font("Serif", 1, 12)); // NOI18N
        update.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/003-edit.png"))); // NOI18N
        update.setText("EDIT");
        update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateActionPerformed(evt);
            }
        });

        tambah.setFont(new java.awt.Font("Serif", 1, 12)); // NOI18N
        tambah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/002-plus.png"))); // NOI18N
        tambah.setText("TAMBAH");
        tambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tambahActionPerformed(evt);
            }
        });

        hapus.setFont(new java.awt.Font("Serif", 1, 12)); // NOI18N
        hapus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/004-delete.png"))); // NOI18N
        hapus.setText("HAPUS");
        hapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hapusActionPerformed(evt);
            }
        });

        batal.setFont(new java.awt.Font("Serif", 1, 12)); // NOI18N
        batal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/005-cancel.png"))); // NOI18N
        batal.setText("BATAL");
        batal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                batalActionPerformed(evt);
            }
        });

        batal1.setFont(new java.awt.Font("Serif", 1, 12)); // NOI18N
        batal1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/012-undo.png"))); // NOI18N
        batal1.setText("KEMBALI");
        batal1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                batal1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(txkode, javax.swing.GroupLayout.DEFAULT_SIZE, 260, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(txnama)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(txharga)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(txstok)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(txongkos)
                        .addContainerGap())))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(update, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tambah, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(hapus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(batal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(batal1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(txkode, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(txnama, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(txharga, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(txstok, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txongkos, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(tambah, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(13, 13, 13)
                .addComponent(update, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(hapus, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(batal, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(batal1, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel2.setBackground(new java.awt.Color(204, 204, 204));
        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        tblsparepart.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblsparepart.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblsparepartMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblsparepart);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2)
                .addContainerGap())
        );

        btnCari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/001-search.png"))); // NOI18N
        btnCari.setText("Cari");
        btnCari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCariActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(txtCari, javax.swing.GroupLayout.PREFERRED_SIZE, 1233, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(16, 16, 16)
                        .addComponent(btnCari, javax.swing.GroupLayout.DEFAULT_SIZE, 92, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCari, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCari))
                .addGap(8, 8, 8)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        jPanel4.setBackground(new java.awt.Color(204, 204, 204));

        jLabel4.setBackground(new java.awt.Color(204, 204, 204));
        jLabel4.setFont(new java.awt.Font("Serif", 1, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(204, 0, 0));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("DATA SPAREPART");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        setBounds(0, 0, 1377, 646);
    }// </editor-fold>//GEN-END:initComponents

    private void updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateActionPerformed

        if (update.getText()=="EDIT") {
            aktif();
            update.setText("UPDATE");
            hapus.setEnabled(false);
            tambah.setEnabled(false);
        } else {
            String sql = "update sparepart set nm_sparepart=?, harga=?, stok=?, ongkos=? where kd_sparepart='"+txkode.getText()+"'";
            try {
                PreparedStatement stat = conn.prepareStatement(sql);

                stat.setString(1, txnama.getText());
                stat.setString(2, txharga.getText());
                stat.setString(3, txstok.getText());
                stat.setString(4, txongkos.getText());
                stat.executeUpdate();
                JOptionPane.showMessageDialog(null, "Data Sparepart berhasil Di Update");
                update.setText("EDIT");
                datatable();
                kosong();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Data Sparepart Gagal Di Update" + e);
            }
        }
    }//GEN-LAST:event_updateActionPerformed

    private void hapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hapusActionPerformed

        int ok = JOptionPane.showConfirmDialog(null, "Apakah Anda Yakin ingin Menghapus Data Ini?", "Konfirmasi Dialog", JOptionPane.YES_NO_OPTION);
        if (ok == 0) {
            String sql = "delete from sparepart where kd_sparepart='"+txkode.getText() + "'";
            try {
                PreparedStatement stat = conn.prepareStatement(sql);
                stat.executeUpdate();
                JOptionPane.showMessageDialog(null, "Data Sparepart Berhasil Dihapus");
                kosong();
                txkode.requestFocus();
                datatable();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Data Sparepart Gagal Di Hapus" + e);
            }
        }
    }//GEN-LAST:event_hapusActionPerformed

    private void batalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_batalActionPerformed
        kosong();
                        autoNumber();
        tambah.setText("TAMBAH");
        update.setEnabled(true);
        hapus.setEnabled(true);
        non_aktif();
    }//GEN-LAST:event_batalActionPerformed

    private void txstokActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txstokActionPerformed

    }//GEN-LAST:event_txstokActionPerformed

    private void tblsparepartMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblsparepartMouseClicked
        update.setEnabled(true);
        update.setText("EDIT");
        hapus.setEnabled(true);
        int bar = tblsparepart.getSelectedRow();
        String a = tabmode.getValueAt(bar, 0).toString();
        String b = tabmode.getValueAt(bar, 1).toString();
        String c = tabmode.getValueAt(bar, 2).toString();
        String d = tabmode.getValueAt(bar, 3).toString();
        String e = tabmode.getValueAt(bar, 4).toString();
        txkode.setText(a);
        txnama.setText(b);
        txharga.setText(c);
        txstok.setText(d);
        txongkos.setText(e);
    }//GEN-LAST:event_tblsparepartMouseClicked

    private void tambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tambahActionPerformed
        String tombol = tambah.getText();
        if (tombol.equals("TAMBAH")) {
            aktif();
            kosong();
            tambah.setText("SIMPAN");
            update.setEnabled(false);
            hapus.setEnabled(false);
        } else {
            if(txnama.getText() == null || txharga.getText() == null || txstok.getText() == null || txongkos.getText() == null) {
            JOptionPane.showMessageDialog(null, "Kolom Tidak Boleh Kosong");
            } else if(txharga.getText().matches("\\d+\\.\\d+") || txstok.getText().matches("\\d+\\.\\d+") ||txongkos.getText().matches("\\d+\\.\\d+") ){
            JOptionPane.showMessageDialog(null, "Kolom Harga / Stok / Ongkos Tidak Boleh Menggunakan Huruf");
            } else {
            String sql = "insert into sparepart values(?,?,?,?,?)";
            try {
                PreparedStatement stat = conn.prepareStatement(sql);
                stat.setString(1, txkode.getText());
                stat.setString(2, txnama.getText());
                stat.setString(3, txharga.getText());
                stat.setString(4, txstok.getText());
                stat.setString(5, txongkos.getText());
                stat.executeUpdate();
                JOptionPane.showMessageDialog(null, "Data sparepart Berhasil Disimpan");
                kosong();
                autoNumber();
                txkode.requestFocus();
                datatable();
            } catch (SQLException e) {
                JOptionPane.showConfirmDialog(null, "Data sparepart gagal disimpan" + e);
            }
            tambah.setText("TAMBAH");
            non_aktif();
            kosong();
            autoNumber();
            update.setEnabled(true);
            hapus.setEnabled(true);
            batal.setEnabled(true);
            }

        }
    }//GEN-LAST:event_tambahActionPerformed

    private void txhargaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txhargaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txhargaActionPerformed

    private void btnCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCariActionPerformed
       String tombol = btnCari.getText();
        if (tombol.equals("Cari")){
         Object[] Baris = {"Kode Sparepart", "Nama Sparepart", "Harga", "Stok", "Ongkos"};
        tabmode = new DefaultTableModel(null, Baris);
        tblsparepart.setModel(tabmode);
        String sql = "Select * from sparepart where kd_sparepart like '%" + txtCari.getText() + "%'" +
            "or nm_sparepart like '%" + txtCari.getText() + "%'";
        try {
            Statement stat = conn.createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            while (hasil.next()) {
                String kode = hasil.getString("kd_sparepart");
                String nama = hasil.getString("nm_sparepart");
                String stok = hasil.getString("stok");
                String harga = hasil.getString("harga");
                String ongkos = hasil.getString("ongkos");
                String[] data = {kode,nama,harga,stok, ongkos};
                tabmode.addRow(data);
            }
        } catch (Exception e) {
        }
        btnCari.setText("Batal");
    }else{
            datatable();
            btnCari.setText("Cari");
            txtCari.setText("");
            tambah.setEnabled(true);
            update.setEnabled(false);
            hapus.setEnabled(false);
            batal.setEnabled(false);
        }
    }//GEN-LAST:event_btnCariActionPerformed

    private void batal1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_batal1ActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_batal1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton batal;
    private javax.swing.JButton batal1;
    private javax.swing.JButton btnCari;
    private javax.swing.JButton hapus;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton tambah;
    private javax.swing.JTable tblsparepart;
    private javax.swing.JTextField txharga;
    private javax.swing.JTextField txkode;
    private javax.swing.JTextField txnama;
    private javax.swing.JTextField txongkos;
    private javax.swing.JTextField txstok;
    private javax.swing.JTextField txtCari;
    private javax.swing.JButton update;
    // End of variables declaration//GEN-END:variables
}
