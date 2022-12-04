package bengkel;
import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


public class DataUser extends javax.swing.JInternalFrame {
    private Connection conn = new Koneksi().connect();
    private DefaultTableModel tabmode;
    
    
    public DataUser() {
        ((javax.swing.plaf.basic.BasicInternalFrameUI)getUI()).setNorthPane(null);
        initComponents();
        initComponents();
        autoNumber();
        non_aktif();
        datatable();
        batal.setEnabled(false);
    }
    @SuppressWarnings("unchecked")
    
    private void datatable() {
        Object[] Baris = {"ID Login", "Username","Password", "Email", "Role","Nama"};
        tabmode = new DefaultTableModel(null, Baris);
        tblmekanik.setModel(tabmode);
        String sql = "select * from login";
        try {
            Statement stat = conn.createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            while (hasil.next()) {
                String id = hasil.getString("id_login");
                String username = hasil.getString("username");
                String password = hasil.getString("password");
                String email = hasil.getString("email");
                String status = hasil.getString("status");
                String nama = hasil.getString("nama");
                String[] data = {id,username,password,email,status,nama};
                tabmode.addRow(data);
            }
        } catch (Exception e) {
        }
    }
    
    private void non_aktif() {
        txid.setEnabled(false);
        txuser.setEnabled(false);
        txpassword.setEnabled(false);
        txemail.setEnabled(false);
        cmbxbox.setEnabled(false);
        txnamaa.setEnabled(false);
    }
    private void aktif() {
        txpassword.setEnabled(true);
        txuser.setEnabled(true);
        txemail.setEnabled(true);
        cmbxbox.setEnabled(true);
        txnamaa.setEnabled(true);
        txid.requestFocus();
    }
    
     private void kosong() {
        txpassword.setText("");
        txuser.setText("");
        cmbxbox.setSelectedItem("Pilih Role");
        txemail.setText("");
        tambah.setEnabled(true);
        update.setEnabled(true);
        hapus.setEnabled(true);
        txnamaa.setText("");
    }
     
      private void autoNumber() {
        try {
            String sql = "SELECT * FROM login ORDER BY id_login DESC";
            PreparedStatement stat = conn.prepareCall(sql);
            ResultSet rs = stat.executeQuery(sql);
            if (rs.next()) {
                String kd = rs.getString("id_login").substring(2);
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
                txid.setText("LG" + Nol + AN);
            } else {
                txid.setText("LG0001");
            }
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel4 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txid = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txuser = new javax.swing.JTextField();
        STATUS = new javax.swing.JLabel();
        txpassword = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txemail = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        cmbxbox = new javax.swing.JComboBox();
        tambah = new javax.swing.JButton();
        update = new javax.swing.JButton();
        hapus = new javax.swing.JButton();
        keluar = new javax.swing.JButton();
        batal = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        txnamaa = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblmekanik = new javax.swing.JTable();
        txtCari = new javax.swing.JTextField();
        btnCari = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Data User");

        jPanel4.setBackground(new java.awt.Color(204, 204, 204));

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));
        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel1.setFont(new java.awt.Font("Serif", 1, 14)); // NOI18N
        jLabel1.setText("ID");

        jLabel3.setFont(new java.awt.Font("Serif", 1, 14)); // NOI18N
        jLabel3.setText("PASSWORD");

        STATUS.setFont(new java.awt.Font("Serif", 1, 14)); // NOI18N
        STATUS.setText("STATUS");

        jLabel6.setFont(new java.awt.Font("Serif", 1, 14)); // NOI18N
        jLabel6.setText("EMAIL");

        jLabel2.setFont(new java.awt.Font("Serif", 1, 14)); // NOI18N
        jLabel2.setText("USERNAME");

        cmbxbox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Pilih Role", "Admin", "Kasir", "Gudang" }));

        tambah.setFont(new java.awt.Font("Serif", 1, 11)); // NOI18N
        tambah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/002-plus.png"))); // NOI18N
        tambah.setText("TAMBAH");
        tambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tambahActionPerformed(evt);
            }
        });

        update.setFont(new java.awt.Font("Serif", 1, 11)); // NOI18N
        update.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/003-edit.png"))); // NOI18N
        update.setText("EDIT");
        update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateActionPerformed(evt);
            }
        });

        hapus.setFont(new java.awt.Font("Serif", 1, 11)); // NOI18N
        hapus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/004-delete.png"))); // NOI18N
        hapus.setText("HAPUS");
        hapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hapusActionPerformed(evt);
            }
        });

        keluar.setFont(new java.awt.Font("Serif", 1, 11)); // NOI18N
        keluar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/012-undo.png"))); // NOI18N
        keluar.setText("KEMBALI");
        keluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                keluarActionPerformed(evt);
            }
        });

        batal.setFont(new java.awt.Font("Serif", 1, 11)); // NOI18N
        batal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/005-cancel.png"))); // NOI18N
        batal.setText("BATAL");
        batal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                batalActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Serif", 1, 14)); // NOI18N
        jLabel5.setText("NAMA");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(keluar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tambah, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(batal, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(hapus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(update, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel6)
                            .addComponent(STATUS)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txnamaa, javax.swing.GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txemail)
                                .addComponent(txid)
                                .addComponent(txpassword)
                                .addComponent(txuser)
                                .addComponent(cmbxbox, 0, 210, Short.MAX_VALUE)))
                        .addGap(0, 26, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txid, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txuser, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txpassword, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txnamaa, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(11, 11, 11)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txemail, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(STATUS)
                        .addGap(18, 18, 18)
                        .addComponent(tambah)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(update)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(hapus)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(batal)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(keluar))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(cmbxbox, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jPanel2.setBackground(new java.awt.Color(204, 204, 204));
        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        tblmekanik.setModel(new javax.swing.table.DefaultTableModel(
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
        tblmekanik.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblmekanikMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblmekanik);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 957, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
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

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(txtCari)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnCari)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCari, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCari))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel3.setBackground(new java.awt.Color(204, 204, 204));

        jLabel4.setFont(new java.awt.Font("Serif", 1, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(204, 0, 0));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("DATA USER");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCariActionPerformed
        String tombol = btnCari.getText();
        String carii = txtCari.getText();
        if(carii.equals("")) {
            JOptionPane.showMessageDialog(null, "Kolom Pencarian Tidak Boleh Kosong");
        } else {
        if (tombol.equals("Cari")){
            Object[] Baris = {"id_login", "Usernama", "Password", "Email", "Status","Nama"};
            tabmode = new DefaultTableModel(null, Baris);
            tblmekanik.setModel(tabmode);
            String sql = "Select * from login where id_login like '%" + txtCari.getText() + "%'" +
            "or username like '%" + txtCari.getText() + "%' or nama like '%"+txtCari.getText()+"%'";
            try {
                Statement stat = conn.createStatement();
                ResultSet hasil = stat.executeQuery(sql);
                if (hasil.next()) {
                    String id = hasil.getString("id_login");
                    String username = hasil.getString("username");
                    String password = hasil.getString("password");
                    String email = hasil.getString("email");
                    String status = hasil.getString("status");
                    String nama = hasil.getString("nama");
                    String[] data = {id,username,password,email,status,nama};
                    tabmode.addRow(data);
                    btnCari.setText("Batal");
                    tambah.setEnabled(true);
                    update.setEnabled(true);
                    hapus.setEnabled(true);
                    batal.setEnabled(false);
                }else{
                    JOptionPane.showMessageDialog(null, "Data User Tidak Ditemukan");
                    datatable();
                    btnCari.setText("Cari");
                    txtCari.setText("");
                    tambah.setEnabled(true);
                    update.setEnabled(true);
                    hapus.setEnabled(true);
                    batal.setEnabled(false);
                }
            } catch (Exception e) {
                        datatable();
                        btnCari.setText("Cari");
                        txtCari.setText("");
                        tambah.setEnabled(true);
                        update.setEnabled(true);
                        hapus.setEnabled(true);
                        batal.setEnabled(false);
            }
        
        }else{
            datatable();
            btnCari.setText("Cari");
            txtCari.setText("");
            tambah.setEnabled(true);
            update.setEnabled(true);
            hapus.setEnabled(true);
            batal.setEnabled(false);
        }
        }
    }//GEN-LAST:event_btnCariActionPerformed

    private void tambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tambahActionPerformed

        String tombol = tambah.getText();
        if (tombol.equals("TAMBAH")) {
            aktif();
            kosong();
            autoNumber();
            tambah.setText("SIMPAN");
            update.setEnabled(false);
            hapus.setEnabled(false);
            batal.setEnabled(true);
            btnCari.setText("Cari");
            datatable();
        } else {
            if (txuser.getText() == null || txpassword.getText() == null || cmbxbox.getSelectedItem() == "Pilih Role" ) {
                JOptionPane.showMessageDialog(null, "Kolom Tidak Boleh Kosong");
            } else {
                String sql = "insert into login values(?,?,?,?,?,?)";
            try {
                PreparedStatement stat = conn.prepareStatement(sql);
                stat.setString(1, txuser.getText());
                stat.setString(2, txemail.getText());
                stat.setString(3, txpassword.getText());
                stat.setString(4, cmbxbox.getSelectedItem().toString());
                stat.setString(5, txid.getText());
                stat.setString(6, txnamaa.getText());
                stat.executeUpdate();
                JOptionPane.showMessageDialog(null, "Data user Berhasil Disimpan");
                kosong();
                autoNumber();
                txid.requestFocus();
                datatable();
            } catch (SQLException e) {
                JOptionPane.showConfirmDialog(null, "Data user gagal disimpan" + e);
            }
            tambah.setText("TAMBAH");
            update.setEnabled(true);
            hapus.setEnabled(true);
            batal.setEnabled(false);
            non_aktif();
            }
            
        }
    }//GEN-LAST:event_tambahActionPerformed

    private void updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateActionPerformed

        if (update.getText()=="EDIT") {
            aktif();
            update.setText("UPDATE");
            hapus.setEnabled(false);
            tambah.setEnabled(false);
            batal.setEnabled(true);
        } else {
            String sql = "update login set username=?, password=?, email=?, status=?,nama=? where id_login='"+txid.getText()+"'";
            try {
                PreparedStatement stat = conn.prepareStatement(sql);
                stat.setString(1, txuser.getText());
                stat.setString(2, txpassword.getText());
                stat.setString(3, txemail.getText());
                stat.setString(4, cmbxbox.getSelectedItem().toString());
                stat.setString(5, txnamaa.getText());
                stat.executeUpdate();
                JOptionPane.showMessageDialog(null, "Data User berhasil Di Update");
                
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Data User Gagal Di Update" + e);
            }
                update.setText("EDIT");
                hapus.setEnabled(true);
                tambah.setEnabled(true);
                batal.setEnabled(false);
                datatable();
                kosong();
                non_aktif();
        }
    }//GEN-LAST:event_updateActionPerformed

    private void hapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hapusActionPerformed

        int ok = JOptionPane.showConfirmDialog(null, "Apakah Anda Yakin ingin Menghapus Data Ini?", "Konfirmasi Dialog", JOptionPane.YES_NO_OPTION);
        if (ok == 0) {
            String sql = "delete from login where id_login='"+txid.getText() + "'";
            try {
                PreparedStatement stat = conn.prepareStatement(sql);
                stat.executeUpdate();
                JOptionPane.showMessageDialog(null, "Data User Berhasil Dihapus");
                kosong();
                txid.requestFocus();
                datatable();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Data Gagal Di Hapus" + e);
            }
        }
        autoNumber();
        kosong();
    }//GEN-LAST:event_hapusActionPerformed

    private void keluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_keluarActionPerformed
        dispose();
    }//GEN-LAST:event_keluarActionPerformed

    private void tblmekanikMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblmekanikMouseClicked

        non_aktif();
        int bar = tblmekanik.getSelectedRow();
        String a = tabmode.getValueAt(bar, 0).toString();
        String b = tabmode.getValueAt(bar, 1).toString();
        String c = tabmode.getValueAt(bar, 2).toString();
        String d = tabmode.getValueAt(bar, 3).toString();
        String e = tabmode.getValueAt(bar, 4).toString();
        String f = tabmode.getValueAt(bar, 5).toString();
        txid.setText(a);
        txuser.setText(b);
        txpassword.setText(c);
        txemail.setText(d);
        txnamaa.setText(f);
        cmbxbox.setSelectedItem(e);
        update.setEnabled(true);
        update.setText("EDIT");
        hapus.setEnabled(true);
        batal.setEnabled(false);
        tambah.setEnabled(true);
    }//GEN-LAST:event_tblmekanikMouseClicked

    private void batalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_batalActionPerformed
//        // TODO add your handling code here:
        
       String tambah1 = tambah.getText().toString();
       String update1 = update.getText().toString();
        
        if (tambah1.equals("SIMPAN")) {
            tambah.setText("TAMBAH");
            tambah.setEnabled(true);
            txid.requestFocus();
            kosong();
            non_aktif();
            autoNumber();
            update.setEnabled(true);
            hapus.setEnabled(true);
            batal.setEnabled(false);
        }else if(update1.equals("UPDATE") ) {
            update.setText("EDIT");
            update.setEnabled(true);
            txid.requestFocus();
            kosong();
            non_aktif();
            autoNumber();
            tambah.setEnabled(true);
            hapus.setEnabled(true);
            batal.setEnabled(false);
        }
        
    }//GEN-LAST:event_batalActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel STATUS;
    private javax.swing.JButton batal;
    private javax.swing.JButton btnCari;
    private javax.swing.JComboBox cmbxbox;
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
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton keluar;
    private javax.swing.JButton tambah;
    private javax.swing.JTable tblmekanik;
    private javax.swing.JTextField txemail;
    private javax.swing.JTextField txid;
    private javax.swing.JTextField txnamaa;
    private javax.swing.JTextField txpassword;
    private javax.swing.JTextField txtCari;
    private javax.swing.JTextField txuser;
    private javax.swing.JButton update;
    // End of variables declaration//GEN-END:variables
}
