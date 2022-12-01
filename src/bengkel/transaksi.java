/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bengkel;
import java.awt.event.KeyEvent;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import model.Login_m;
import model.Pelanggan_m;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

/**
 *
 * @author Puguh Laksono
 */
public class transaksi extends javax.swing.JInternalFrame {
        private Connection conn = new Koneksi().connect();
        private DefaultTableModel tabmode;
        int hrg, sub, jml, ongkos; 
        String kd_sparepart,nama_spare;
        
JasperReport jr;
JasperPrint jp;
JasperDesign jd;
HashMap param = new HashMap();   

      
    private void kosong(){
    autoNumber();
    txHarga.setText("");
    txjumlah.setText("");
    txStok.setText("");
    txTotal.setText("");
    txKembalian.setText("");
    txBayar.setText("");
    txongkos.setText("");
    cbmekanik.setSelectedItem("Pilih Mekanik");
    cbplg.setSelectedItem("Pilih Pelanggan");
    cbsparepart.setSelectedItem("Pilih Sparepart");
    txnopol.setText("");
    txkeluhan.setText("");
    }
    
    private void non_aktif(){
        txNo.setEnabled(false);
        txHarga.setEnabled(false);
        txStok.setEnabled(false);
        txongkos.setEnabled(false);
        txTotal.setEnabled(false);
//        txDateTime.setEnabled(false);
    }
    
//     private void kodeSparepart(){
//        String sql = "SELECT * FROM sparepart WHERE kd_sparepart = '"+cbkode.getSelectedItem()+"'";
//        try {
//            Statement stat = conn.createStatement();
//            ResultSet hasil = stat.executeQuery(sql);
//            while(hasil.next()){
//                txHarga.setText(hasil.getString("harga"));
//                txongkos.setText(hasil.getString("ongkos"));
//                txjumlah.requestFocus();
//                txStok.setText(hasil.getString("stok"));
//            }
//            hasil.close();
//            stat.close();
//            } catch (SQLException ex) {
//        }         
//    }
     
    /**
     * Creates new form 
     */
    public transaksi() {
        initComponents();
        ((javax.swing.plaf.basic.BasicInternalFrameUI)getUI()).setNorthPane(null);       
        non_aktif();
        tampilComboplg();
        AutoCompleteDecorator.decorate(cbplg);
        tampilCombomekanik();
        AutoCompleteDecorator.decorate(cbmekanik);
        tampilCombosp();
        AutoCompleteDecorator.decorate(cbsparepart);
        kd_p.setVisible(false);
        kd_meka.setVisible(false);
        kd_spa.setVisible(false);
        Date date = new Date();
        autoNumber();
        
        tabmode =new DefaultTableModel();
        jTable1.setModel(tabmode);
        tabmode.addColumn("Kode");
        tabmode.addColumn("Nama Sparepart");
        tabmode.addColumn("Harga");
        tabmode.addColumn("jumlah");
        tabmode.addColumn("Ongkos");
        tabmode.addColumn("Sub Total");
        
    }
    
       private void autoNumber() {
        try {
            java.util.Date tgl = new java.util.Date();  
            java.text.SimpleDateFormat kal = new java.text.SimpleDateFormat("yyyyMM");  
            java.text.SimpleDateFormat tanggal = new java.text.SimpleDateFormat("yyyyMM");  
            String sql = "SELECT MAX(no_faktur) FROM service_motor WHERE SUBSTRING(no_faktur,1,6) = '" +tanggal.format(tgl) +"'";
            PreparedStatement stat = conn.prepareStatement(sql);
            ResultSet rs = stat.executeQuery(sql);
            while(rs.next()){  
            Long a =rs.getLong(1); //mengambil nilai tertinggi  
                if(a == 0){  
                    this.txNo.setText(kal.format(tgl)+"0000"+(a+1));  
                }else{  
                    this.txNo.setText(""+(a+1));  
                }  
            }  
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();//penanganan masalah
        }
    }   
      
       private void tampilComboplg(){
        String sql = "SELECT * FROM pelanggan order by nm_pelanggan asc";
        String nama[];
        try {
            Statement stat = conn.createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            while(hasil.next()){
                cbplg.addItem(hasil.getString("nm_pelanggan"));
            }
            hasil.close();
            stat.close();
            } catch (SQLException ex) {
        }         
    }
      
       private void tampilCombomekanik(){
        String sql = "SELECT * FROM mekanik order by nm_mekanik";
        try {
            Statement stat = conn.createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            while(hasil.next()){
                  cbmekanik.addItem(hasil.getString("nm_mekanik"));
            }
            hasil.close();
            stat.close();
            } catch (SQLException ex) {
        }         
    }
       
       private void tampilCombosp(){
        String sql = "SELECT * FROM sparepart order by nm_sparepart";
        try {
            Statement stat = conn.createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            while(hasil.next()){
                  cbsparepart.addItem(hasil.getString("nm_sparepart"));
            }
            hasil.close();
            stat.close();
            } catch (SQLException ex) {
        }         
    }
       
        private void tampilMekanik(){ 
        try {
        String sql = "SELECT kd_mekanik FROM mekanik WHERE nm_mekanik='"+cbmekanik.getSelectedItem()+"'"; 
        Statement stat = conn.createStatement();
        ResultSet hasil = stat.executeQuery(sql);
        while(hasil.next()){
            Object[] ob = new Object[1];
            ob[0]=  hasil.getString(1);
//            txmekanik.setText((String) ob[0]);
        }
            hasil.close(); 
            hasil.close(); 
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }     
  
    }
                
    
    private void simpanNota(){
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); 
    Date tanggal = new Date(); 
//    tanggal = jDateChooser1.getDate(); 
    String jual_tgl = dateFormat.format(tanggal);
    
    String sql = "INSERT INTO service_motor VALUES(?,?,?,?,?,?)";
            try {
                PreparedStatement stat = conn.prepareStatement(sql);
                stat.setString(1, txNo.getText());
                stat.setString(2, jual_tgl);
//                stat.setString(4, txmekanik.getText());
                stat.setString(5, txnopol.getText());
                stat.setString(6, txkeluhan.getText());
                stat.executeUpdate();
                JOptionPane.showMessageDialog(null, "Data Berhasil Disimpan");
                
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Data Gagal Disimpan "+e);
                kosong();
            }
            autoNumber();
    }
         
    private void tambah_item(){
   
    nama_spare = cbsparepart.getSelectedItem().toString();
    hrg=Integer.parseInt(txHarga.getText());
    jml=Integer.parseInt(txjumlah.getText());
    ongkos= Integer.parseInt(txongkos.getText());
    int stok = Integer.parseInt(txStok.getText());
    sub = hrg*jml+ongkos;
    kd_sparepart = kd_spa.getText();
    if(jml > stok) {
        JOptionPane.showMessageDialog(this,"Stok Tidak Cukup"); 
        cbsparepart.setSelectedItem("Pilih Sparepart");
        txHarga.setText("");
        txjumlah.setText("");
        txongkos.setText("");
        txStok.setText("");
    } else if(nama_spare.equals("Pilih Sparepart")){
        JOptionPane.showMessageDialog(this,"Pilih Terlebih Dahulu Sparepartnya"); 
        cbsparepart.setSelectedItem("Pilih Sparepart");
        txHarga.setText("");
        txjumlah.setText("");
        txongkos.setText("");
        txStok.setText("");
    } else if(jml == 0){
        JOptionPane.showMessageDialog(this,"Jumlah Tidak Boleh 0"); 
        txjumlah.setText("");
    }else{
        Object[] data = {kd_sparepart,nama_spare, hrg, jml, ongkos,sub };
        tabmode = (DefaultTableModel)jTable1.getModel();
        tabmode.addRow(data);
        cbsparepart.setSelectedItem("Pilih Sparepart");
        txHarga.setText("");
        txjumlah.setText("");
        txongkos.setText("");
        txStok.setText("");
        int total = 0;
        for (int i =0; i< jTable1.getRowCount(); i++){
               total = total + Integer.parseInt(jTable1.getValueAt(i, 5).toString());
        }
        txTotal.setText(Integer.toString(total));
        JOptionPane.showMessageDialog(null, "Sparepart Berhasil Di Tambah");

    }
    
    }
    
    private void clean_table() {
       tabmode = (DefaultTableModel) jTable1.getModel();
       tabmode.setRowCount(0);
    }
    
        private void hapusData(){
        int row = jTable1.getSelectedRow();
        if(row >=0 ){
        int ok = JOptionPane.showConfirmDialog(null, "Yakin Mau Hapus?","Konfirmasi",JOptionPane.YES_NO_OPTION);
        if(ok == 0){
        tabmode.removeRow(row);
        }     
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

        jPanel4 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txHarga = new javax.swing.JTextField();
        tambahItem = new javax.swing.JButton();
        txStok = new javax.swing.JTextField();
        hapusItem = new javax.swing.JButton();
        txongkos = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        cbsparepart = new javax.swing.JComboBox();
        kd_spa = new javax.swing.JTextField();
        txjumlah = new javax.swing.JTextField();
        jPanel7 = new javax.swing.JPanel();
        txNo = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        txnopol = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        cbmekanik = new javax.swing.JComboBox();
        txkeluhan = new javax.swing.JTextField();
        kd_p = new javax.swing.JTextField();
        cbplg = new javax.swing.JComboBox();
        kd_meka = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        txTotal = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txBayar = new javax.swing.JTextField();
        txKembalian = new javax.swing.JTextField();
        cetak = new javax.swing.JButton();
        btbatal = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setBackground(new java.awt.Color(204, 204, 204));

        jPanel4.setBackground(new java.awt.Color(204, 204, 204));

        jLabel12.setFont(new java.awt.Font("Serif", 1, 24)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(204, 0, 0));
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("TRANSAKSI SERVICE");

        jPanel5.setBackground(new java.awt.Color(204, 204, 204));

        jPanel2.setBackground(new java.awt.Color(204, 204, 204));
        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel4.setBackground(new java.awt.Color(204, 204, 204));
        jLabel4.setFont(new java.awt.Font("Serif", 1, 14)); // NOI18N
        jLabel4.setText("Nama Sparepart");

        jLabel5.setBackground(new java.awt.Color(204, 204, 204));
        jLabel5.setFont(new java.awt.Font("Serif", 1, 14)); // NOI18N
        jLabel5.setText("Harga");

        jLabel6.setBackground(new java.awt.Color(204, 204, 204));
        jLabel6.setFont(new java.awt.Font("Serif", 1, 14)); // NOI18N
        jLabel6.setText("Jumlah");

        txHarga.setEditable(false);

        tambahItem.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        tambahItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/002-plus.png"))); // NOI18N
        tambahItem.setText("TAMBAH");
        tambahItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tambahItemActionPerformed(evt);
            }
        });
        tambahItem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tambahItemKeyPressed(evt);
            }
        });

        txStok.setEditable(false);

        hapusItem.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        hapusItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/004-delete.png"))); // NOI18N
        hapusItem.setText("HAPUS");
        hapusItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hapusItemActionPerformed(evt);
            }
        });

        txongkos.setEditable(false);

        jLabel10.setBackground(new java.awt.Color(204, 204, 204));
        jLabel10.setFont(new java.awt.Font("Serif", 1, 14)); // NOI18N
        jLabel10.setText("Ongkos");

        jLabel15.setBackground(new java.awt.Color(204, 204, 204));
        jLabel15.setFont(new java.awt.Font("Serif", 1, 14)); // NOI18N
        jLabel15.setText("Stok");

        cbsparepart.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Pilih Sparepart" }));
        cbsparepart.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbsparepartItemStateChanged(evt);
            }
        });

        txjumlah.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txjumlahKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(kd_spa, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tambahItem, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(hapusItem))
                    .addComponent(txongkos)
                    .addComponent(txjumlah)
                    .addComponent(txHarga)
                    .addComponent(cbsparepart, 0, 470, Short.MAX_VALUE)
                    .addComponent(txStok))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(cbsparepart, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txHarga, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txjumlah, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txongkos, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txStok, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(kd_spa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(50, 50, 50))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tambahItem)
                            .addComponent(hapusItem))
                        .addContainerGap())))
        );

        jPanel7.setBackground(new java.awt.Color(204, 204, 204));
        jPanel7.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel1.setFont(new java.awt.Font("Serif", 1, 14)); // NOI18N
        jLabel1.setText("No Nota");

        jLabel13.setFont(new java.awt.Font("Serif", 1, 14)); // NOI18N
        jLabel13.setText("No Polisi");

        jLabel3.setFont(new java.awt.Font("Serif", 1, 14)); // NOI18N
        jLabel3.setText("Pelanggan");

        jLabel18.setFont(new java.awt.Font("Serif", 1, 14)); // NOI18N
        jLabel18.setText("Keluhan");

        jLabel11.setFont(new java.awt.Font("Serif", 1, 14)); // NOI18N
        jLabel11.setText("Mekanik");

        cbmekanik.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Pilih Mekanik" }));
        cbmekanik.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbmekanikItemStateChanged(evt);
            }
        });
        cbmekanik.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cbmekanikMouseClicked(evt);
            }
        });
        cbmekanik.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbmekanikActionPerformed(evt);
            }
        });

        txkeluhan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txkeluhanActionPerformed(evt);
            }
        });

        cbplg.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Pilih Pelanggan" }));
        cbplg.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbplgItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel13)
                    .addComponent(jLabel1)
                    .addComponent(jLabel11)
                    .addComponent(jLabel18))
                .addGap(69, 69, 69)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txNo)
                    .addComponent(cbmekanik, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txkeluhan)
                    .addComponent(cbplg, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txnopol))
                .addContainerGap())
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(139, 139, 139)
                .addComponent(kd_p, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(kd_meka, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(9, 9, 9))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                        .addComponent(txNo, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbplg, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txnopol, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbmekanik, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txkeluhan, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(kd_p, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(kd_meka, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8))
        );

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));
        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Kode Sparepart", "Nama Sparepart", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        txTotal.setEditable(false);
        txTotal.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        txTotal.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel2.setFont(new java.awt.Font("Serif", 1, 18)); // NOI18N
        jLabel2.setText("Total");

        jLabel8.setFont(new java.awt.Font("Serif", 1, 18)); // NOI18N
        jLabel8.setText("Bayar");

        jLabel9.setFont(new java.awt.Font("Serif", 1, 18)); // NOI18N
        jLabel9.setText("Kembalian");

        txBayar.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        txBayar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txBayarActionPerformed(evt);
            }
        });
        txBayar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txBayarKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txBayarKeyTyped(evt);
            }
        });

        txKembalian.setEditable(false);
        txKembalian.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N

        cetak.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        cetak.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/006-printer.png"))); // NOI18N
        cetak.setText("CETAK NOTA");
        cetak.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cetakActionPerformed(evt);
            }
        });

        btbatal.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btbatal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/005-cancel.png"))); // NOI18N
        btbatal.setText("BATAL TRANSAKSI");
        btbatal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btbatalActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/012-undo.png"))); // NOI18N
        jButton2.setText("KEMBALI");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(330, 330, 330)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(jLabel2)
                            .addComponent(jLabel8))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 84, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txBayar, javax.swing.GroupLayout.PREFERRED_SIZE, 353, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 353, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txKembalian, javax.swing.GroupLayout.PREFERRED_SIZE, 353, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 66, Short.MAX_VALUE)
                        .addComponent(cetak, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btbatal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addGap(12, 12, 12)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txBayar, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9))
                        .addGap(11, 11, 11)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txKembalian, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btbatal, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(cetak, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cbmekanikActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbmekanikActionPerformed
        tampilMekanik(); // TODO add your handling code here:
    }//GEN-LAST:event_cbmekanikActionPerformed

    private void cbmekanikMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbmekanikMouseClicked
//        if (cbmekanik.getSelectedItem() != "Mekanik"){
//            txjumlah.setEnabled(true);
//            tambahItem.setEnabled(true);
//        }else{
//
//        }
    }//GEN-LAST:event_cbmekanikMouseClicked

    private void hapusItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hapusItemActionPerformed
        hapusData();
    }//GEN-LAST:event_hapusItemActionPerformed

    private void tambahItemKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tambahItemKeyPressed
        tambah_item();
    }//GEN-LAST:event_tambahItemKeyPressed

    private void tambahItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tambahItemActionPerformed
        tambah_item();
    }//GEN-LAST:event_tambahItemActionPerformed

    private void txkeluhanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txkeluhanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txkeluhanActionPerformed

    private void cbplgItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbplgItemStateChanged
        // TODO add your handling code here:
        String nm_pelanggan = cbplg.getSelectedItem().toString();
        String sql = "SELECT * FROM pelanggan where nm_pelanggan = '" + nm_pelanggan + "'";
        try {
            Statement stat = conn.createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            while(hasil.next()){
                  kd_p.setText(hasil.getString("kd_pelanggan"));
            }
            hasil.close();
            stat.close();
            } catch (SQLException ex) {
        }         
    }//GEN-LAST:event_cbplgItemStateChanged

    private void cbmekanikItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbmekanikItemStateChanged
        // TODO add your handling code here:
        String nm_pelanggan = cbmekanik.getSelectedItem().toString();
        String sql = "SELECT * FROM mekanik where nm_mekanik = '" + nm_pelanggan + "'";
        try {
            Statement stat = conn.createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            while(hasil.next()){
                  kd_meka.setText(hasil.getString("kd_mekanik"));
            }
            hasil.close();
            stat.close();
            } catch (SQLException ex) {
        }         
    }//GEN-LAST:event_cbmekanikItemStateChanged

    private void cbsparepartItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbsparepartItemStateChanged
        // TODO add your handling code here:
        String nm_pelanggan = cbsparepart.getSelectedItem().toString();
        String sql = "SELECT * FROM sparepart where nm_sparepart = '" + nm_pelanggan + "'";
        try {
            Statement stat = conn.createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            while(hasil.next()){
                  txHarga.setText(hasil.getString("harga"));
                  txStok.setText(hasil.getString("stok"));
                  txongkos.setText(hasil.getString("ongkos"));
                  kd_spa.setText(hasil.getString("kd_sparepart"));
            }
            hasil.close();
            stat.close();
            } catch (SQLException ex) {
        }
    }//GEN-LAST:event_cbsparepartItemStateChanged

    private void txjumlahKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txjumlahKeyTyped
        // TODO add your handling code here:
               char enter = evt.getKeyChar();
        if(!(Character.isDigit(enter))){
            evt.consume();
        }
    }//GEN-LAST:event_txjumlahKeyTyped

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void btbatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btbatalActionPerformed
        kosong();
        clean_table();
    }//GEN-LAST:event_btbatalActionPerformed

    private void cetakActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cetakActionPerformed
        
        
       // DATA UNTUK VALIDASI
            double total = Double.parseDouble(txTotal.getText());
            double bayar = Double.parseDouble(txBayar.getText());        
       // DATA UNTUK VALIDASI
       // DATA UNTUK HEADER
            String no_faktur = txNo.getText();
            String kd_pelanggan = kd_p.getText();
            String noPolisi = txnopol.getText();
            String kd_mekanik = kd_meka.getText();
            String keluhan = txkeluhan.getText();
            String kd_user = Login_m.getId_login();
            Date date= new Date();
            long time = date.getTime();
            Timestamp strDate = new Timestamp(time);
       // DATA UNTUK HEADER
            
       // MULAI INSERT K DATABASE
            if(bayar >= total) {
                tabmode = (DefaultTableModel)jTable1.getModel();
                if(tabmode.getRowCount() == 0) {
                   JOptionPane.showMessageDialog(null, "Data Sparepart Kosong");
                }else{
                    // INSERT HEADER DULU
                       String sql = "INSERT INTO service_motor (no_faktur,kd_pelanggan,kd_mekanik,no_polisi,keluhan,kd_user,total) VALUES (?,?,?,?,?,?,?)";
                        try {
                            PreparedStatement stat = conn.prepareStatement(sql);
                            stat.setString(1, no_faktur);
                            stat.setString(2, kd_pelanggan);
                            stat.setString(3, kd_mekanik);
                            stat.setString(4, noPolisi);
                            stat.setString(5, keluhan);
                            stat.setString(6, kd_user);
                            stat.setString(7, txTotal.getText());
                            stat.executeUpdate();
                        } catch (SQLException e) {
                            JOptionPane.showMessageDialog(null, "Data Gagal Disimpan "+e);
                        }
                    // INSERT HEADER DULU
                        
                    // INSERT DETAILNYA
                       for(int i = 0; i < tabmode.getRowCount(); i++){
                          String a = tabmode.getValueAt(i, 0).toString(); // KODE SPAREPART
                          String b = tabmode.getValueAt(i, 2).toString(); // HARGA
                          String c = tabmode.getValueAt(i, 3).toString(); // JUMLAH
                          String d = tabmode.getValueAt(i, 4).toString(); // ONGKOS
                          String e = tabmode.getValueAt(i, 5).toString(); // SUB TOTAL
                          
                          // MULAIN INSERT DETAIL
                            String sql1 = "INSERT INTO detail_service (kd_sparepart, harga, jumlah, no_faktur, ongkos, subtotal) VALUES(?,?,?,?,?,?)";
                              try {
                                  PreparedStatement stat1 = conn.prepareStatement(sql1);
                                  stat1.setString(1, a);
                                  stat1.setString(2, b);
                                  stat1.setString(3, c);
                                  stat1.setString(4, no_faktur);
                                  stat1.setString(5, d);
                                  stat1.setString(6, e);
                                  stat1.executeUpdate();
                              } catch (SQLException f) {
                                  JOptionPane.showMessageDialog(null, "Data Gagal Disimpan "+f);
                              }
                          // MULAI INSERT DETAIL
                              
                        // UPDATE STOK
                            String sql2 = "INSERT INTO stok (kd_sparepart,qty,jenis,created_by,keterangan) VALUES (?,?,?,?,?)";
                                try {
                                    PreparedStatement stat2 = conn.prepareStatement(sql2);
                                    stat2.setString(1, a);
                                    stat2.setString(2, c);
                                    stat2.setString(3, "OUT");
                                    stat2.setString(4, kd_user);
                                    stat2.setString(5, "Terjual");
                                    stat2.executeUpdate();
                                } catch (SQLException g) {
                                    JOptionPane.showMessageDialog(null, "Data Gagal Disimpan "+g);
                                }
                          // UPDATE STOK
                                
                          // CETAK STRUK
                                try {
                                        File report = new File("C:\\Users\\mardi\\Documents\\NetBeansProjects\\aplikasi_bengkel\\src\\report\\cetak.jrxml");
                                        jd = JRXmlLoader.load(report);
//                                        param.clear();
                                        param.put("no_faktur",txNo.getText());
                                        jr = JasperCompileManager.compileReport(jd);
                                        jp = JasperFillManager.fillReport(jr, param, conn);
                                        JasperViewer.viewReport(jp, false);
                                    } catch (JRException h) {
                                        JOptionPane.showMessageDialog(null, h);
                                    }
                          // CETAK STRUK
                          
                          
                       }
//                    
                    // INSERT DETAILNYA
                       
                               kosong();
                               clean_table();
                               JOptionPane.showMessageDialog(null, "Data Berhasil Di Simpan");
                }
            } else if(txBayar.getText() == null || txKembalian.getText() == null) {
             JOptionPane.showMessageDialog(null, "Kolom Bayar Atau Kolom Kembalian Tidak Boleh Kosong");
            }else{
                JOptionPane.showMessageDialog(null, "Uang Anda Kurang");
                txBayar.setText("");
            }
        // MULAI INSERT K DATABASE

            
       // DATA UNTUK DETAIL
            
       // DATA UNTUK DETAIL
    }//GEN-LAST:event_cetakActionPerformed

    private void txBayarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txBayarKeyTyped
        // TODO add your handling code here:
        char enter = evt.getKeyChar();
        if(!(Character.isDigit(enter))){
            evt.consume();
        }
        
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            int bayar=Integer.parseInt(txBayar.getText());
            int total=Integer.parseInt(txTotal.getText());
            if (bayar>=total){
                int kembali=bayar-total;
                txKembalian.setText(String.valueOf(kembali));
            }else{
                JOptionPane.showMessageDialog(null, "Uang Anda Kurang");
                txBayar.requestFocus();
                txBayar.setText("");
            }
        }
        
    }//GEN-LAST:event_txBayarKeyTyped

    private void txBayarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txBayarKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            int bayar=Integer.parseInt(txBayar.getText());
            int total=Integer.parseInt(txTotal.getText());
            if (bayar>=total){
                int kembali=bayar-total;
                txKembalian.setText(String.valueOf(kembali));
            }else{
                JOptionPane.showMessageDialog(null, "Uang Anda Kurang");
                txBayar.requestFocus();
                txBayar.setText("");
            }

        }
    }//GEN-LAST:event_txBayarKeyPressed

    private void txBayarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txBayarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txBayarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btbatal;
    private javax.swing.JComboBox cbmekanik;
    private javax.swing.JComboBox cbplg;
    private javax.swing.JComboBox cbsparepart;
    private javax.swing.JButton cetak;
    private javax.swing.JButton hapusItem;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField kd_meka;
    private javax.swing.JTextField kd_p;
    private javax.swing.JTextField kd_spa;
    private javax.swing.JButton tambahItem;
    private javax.swing.JTextField txBayar;
    private javax.swing.JTextField txHarga;
    private javax.swing.JTextField txKembalian;
    private javax.swing.JTextField txNo;
    private javax.swing.JTextField txStok;
    private javax.swing.JTextField txTotal;
    private javax.swing.JTextField txjumlah;
    private javax.swing.JTextField txkeluhan;
    private javax.swing.JTextField txnopol;
    private javax.swing.JTextField txongkos;
    // End of variables declaration//GEN-END:variables
}
