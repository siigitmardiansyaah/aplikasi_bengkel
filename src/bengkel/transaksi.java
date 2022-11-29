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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Puguh Laksono
 */
public class transaksi extends javax.swing.JInternalFrame {
        private Connection conn = new Koneksi().connect();
        private DefaultTableModel tabmode;
        int hrg, sub, jml, ongkos; 
        String kode_pelanggan;
        
JasperReport jr;
JasperPrint jp;
JasperDesign jd;
HashMap param = new HashMap();   

//    private void tampilCombosp(){
//        String sql = "SELECT * FROM sparepart";
//        try {
//            Statement stat = conn.createStatement();
//            ResultSet hasil = stat.executeQuery(sql);
//            while(hasil.next()){
//                cbkode.addItem(hasil.getString("kd_sparepart"));
//            }
//            hasil.close();
//            stat.close();
//            } catch (SQLException ex) {
//        }         
//    }
      
     private void kosong(){
//    cbkode.setSelectedItem("Sparepart");
    txHarga.setText("");
    txjumlah.setText("");
    txStok.setText("0");
    txTotal.setText("");
    txKembalian.setText("");
    txBayar.setText("");
    txongkos.setText("");
    }
    
    private void non_aktif(){
        txNo.setEnabled(false);
        txHarga.setEnabled(false);
        txStok.setEnabled(false);
        txplg.setEnabled(false);
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
//        tampilComboplg();
        tampilCombomekanik();
//        tampilCombosp();
        Date date = new Date();
//        jDateChooser1.setDate(date);
        autoNumber();
        dataTabel();
        
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
            java.text.SimpleDateFormat kal = new java.text.SimpleDateFormat("yyMMdd");  
            java.text.SimpleDateFormat tanggal = new java.text.SimpleDateFormat("yyyyMMdd");  
            String sql = "SELECT MAX(no_faktur) FROM service_motor WHERE tanggal ="+tanggal.format(tgl);
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
       
       public void getKode(String data) {
           System.out.println(data);
           String sql = "SELECT * FROM pelanggan WHERE kd_pelanggan = '"+data+"'";
            try {
                Statement stat = conn.createStatement();
                ResultSet hasil = stat.executeQuery(sql);
                if (hasil.next()) {
                    String kode = hasil.getString("kd_pelanggan");
                    String nama = hasil.getString("nm_pelanggan");
                               System.out.println(kode);
           System.out.println(nama);

                    txplg.setText(nama);
                    kd_pelanggan.setText(kode);
                }
            } catch (Exception e) {
            }
       }
      
//      private void tampilComboplg(){
//        String sql = "SELECT * FROM pelanggan";
//        try {
//            Statement stat = conn.createStatement();
//            ResultSet hasil = stat.executeQuery(sql);
//            while(hasil.next()){
//                cbplg.addItem(hasil.getString("nm_pelanggan"));
//            }
//            hasil.close();
//            stat.close();
//            } catch (SQLException ex) {
//        }         
//    }
      
       private void tampilCombomekanik(){
        String sql = "SELECT * FROM mekanik";
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
      
//       private void tampilPlg(){ 
//        try {
////        String sql = "SELECT kd_pelanggan FROM pelanggan WHERE nm_pelanggan='"+cbplg.getSelectedItem()+"'"; 
//        Statement stat = conn.createStatement();
//        ResultSet hasil = stat.executeQuery(sql);
//        while(hasil.next()){
//            Object[] ob = new Object[1];
//            ob[0]=  hasil.getString(1);
//            txplg.setText((String) ob[0]);
//        }
//            hasil.close(); 
//            hasil.close(); 
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }     
//  
//    }
       
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
        
              private void dataTabel(){ 
        Object[] Baris ={"Kode","Nama Sparepart","Harga","Jumlah", "Ongkos", "Sub Total"};
        tabmode = new DefaultTableModel(null, Baris);
        jTable1.setModel(tabmode);   
        try {
            String sql = "SELECT * FROM sparepart,detail_service WHERE detail_service.kd_sparepart = sparepart.kd_sparepart "
                + "AND detail_service.no_faktur='"+this.txNo.getText()+"'";
            Statement stat = conn.createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            while(hasil.next()){
                String kode = hasil.getString("kd_sparepart");
                String nama = hasil.getString("nm_sparepart");
                String harga = hasil.getString("harga");
                String jumlah = hasil.getString("jumlah");
                String ongkos = hasil.getString("ongkos");
                String subtotal = hasil.getString("subtotal");
                String[] data={kode, nama, harga, jumlah,ongkos, subtotal};
                tabmode.addRow(data);
            }
        } catch (Exception e) {
        }
    //menjumlahkan isi colom sub total
    int total = 0;
    for (int i =0; i< jTable1.getRowCount(); i++){
    int amount = Integer.parseInt((String)jTable1.getValueAt(i, 5));
    total += amount;
    }
    txTotal.setText(""+total);
    }
        
    private void hapusservice(){
//         int ok = JOptionPane.showConfirmDialog(null, "Apakah Anda Yakin ingin Menghapus Data Ini?", "Konfirmasi Dialog", JOptionPane.YES_NO_OPTION);
//        if (ok == 0) {
////            String sql = "delete from detail_service where no_faktur='"+txNo.getText() + "' and kd_sparepart='"+cbkode.getSelectedItem()+"'";
//            try {
//                PreparedStatement stat = conn.prepareStatement(sql);
//                stat.executeUpdate();
//                JOptionPane.showMessageDialog(null, "Data Berhasil Dihapus");
//                kosong();
//   
//            } catch (SQLException e) {
//                JOptionPane.showMessageDialog(null, "Data Gagal Di Hapus" + e);
//            }
//        }
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
                stat.setString(3, txplg.getText());
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
            dataTabel();
    }
         
    private void tambah_item(){
   
    hrg=Integer.parseInt(txHarga.getText());
    jml=Integer.parseInt(txjumlah.getText());
    ongkos= Integer.parseInt(txongkos.getText());
    sub = hrg*jml+ongkos;
    
    String sql = "INSERT INTO detail_service values(?,?,?,?,?,?)";
            try {
                PreparedStatement stat = conn.prepareStatement(sql);
//                stat.setString(1, (String) cbkode.getSelectedItem());
                stat.setString(2, txHarga.getText());
                stat.setString(3, txjumlah.getText());
                stat.setString(4, txNo.getText());
                stat.setString(5, txongkos.getText());
                stat.setString(6, String.valueOf(sub));
                
                stat.executeUpdate();
                kosong();
//                cbkode.requestFocus();
                tambahItem.requestFocus();
                
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Data Gagal Disimpan "+e);
                kosong();
            }
            non_aktif();       
    }
    
     private void update_stok(){
        int x, y, z;
        x = Integer.parseInt(txStok.getText());
        y = Integer.parseInt(txjumlah.getText());
        z = x-y;
        try{
        String sql ="UPDATE sparepart SET stok=? WHERE kd_sparepart=?";  
        PreparedStatement stat = conn.prepareStatement(sql);
           stat.setInt(1,z);
//           stat.setString(2, (String) cbkode.getSelectedItem());
           stat.executeUpdate();  
           stat.close();  
        }catch(SQLException e){  
        System.out.println("Terjadi Kesalahan");  
        }finally{
        JOptionPane.showMessageDialog(this,"Stock sparepart telah di update Diubah");  
        }
    }
     
    private void cariKode(){
    int i=jTable1.getSelectedRow();  
    if(i==-1)  
    { return; }  
    String ID=(String)tabmode.getValueAt(i, 0); 
//    cbkode.setSelectedItem(ID);
    }
    
     private void tampilKlik(){
//        try {
//        String sql="SELECT * FROM sparepart, detail_service WHERE "
//                + "detail_service.kd_sparepart = '"+this.cbkode.getSelectedItem()+"'";
//        PreparedStatement stat = conn.prepareStatement(sql);
//        ResultSet rs = stat.executeQuery(sql);
//        while(rs.next()){
//        this.txHarga.setText(rs.getString("harga"));
//        this.txjumlah.setText(rs.getString("jumlah"));
//        this.txongkos.setText(rs.getString("ongkos"));
////        this.cbkode.setSelectedItem(rs.getString("kd_sparepart"));
//        this.txnopol.setText(rs.getString("no_polisi"));
//        this.txkeluhan.setText(rs.getString("keluhan"));
//        this.txplg.setText(rs.getString("kd_pelanggan"));
////        this.txmekanik.setText(rs.getString("kd_mekanik"));
//        }
//        rs.close(); 
//        stat.close();}
//        catch (Exception e) {
//            System.out.println(e.getMessage());
//        }      
    }
     
     private void tampilStok(){
//        try {
//        String sql="SELECT * FROM sparepart WHERE kd_sparepart = '"+this.cbkode.getSelectedItem()+"'";
//        PreparedStatement stat = conn.prepareStatement(sql);
//        ResultSet rs = stat.executeQuery(sql);
//        while(rs.next()){
//        this.txStok.setText(rs.getString("stok"));
//        }
//        rs.close(); 
//        stat.close();}
//        catch (Exception e) {
//            System.out.println(e.getMessage());
//        }      
    }
     
        private void hapusData(){
        try {
        String sql="DELETE From detail_service WHERE no_faktur='"+txNo.getText()+"'";
        PreparedStatement stat =conn.prepareStatement(sql);
        stat.executeUpdate();
        stat.close();
        }catch(SQLException e){
        System.out.println("Terjadi Kesalahan");
        }finally{
        }  
        try {
        String sql="DELETE FROM service_motor WHERE no_faktur='"+txNo.getText()+"'";
        PreparedStatement stat =conn.prepareStatement(sql);
        stat.executeUpdate();
        stat.close();
        }catch(SQLException e){
        System.out.println("Terjadi Kesalahan");
        }finally{
        dataTabel();
        JOptionPane.showMessageDialog(null,"Sukses Hapus Data...");
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
        txjumlah = new javax.swing.JTextField();
        tambahItem = new javax.swing.JButton();
        txStok = new javax.swing.JTextField();
        hapusItem = new javax.swing.JButton();
        txongkos = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        txNo = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        txnopol = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txplg = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        cbmekanik = new javax.swing.JComboBox();
        btnCari = new javax.swing.JButton();
        txkeluhan = new javax.swing.JTextField();
        kd_pelanggan = new javax.swing.JTextField();
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
        jLabel4.setText("Kode Sparepart");

        jLabel5.setBackground(new java.awt.Color(204, 204, 204));
        jLabel5.setFont(new java.awt.Font("Serif", 1, 14)); // NOI18N
        jLabel5.setText("Harga");

        jLabel6.setBackground(new java.awt.Color(204, 204, 204));
        jLabel6.setFont(new java.awt.Font("Serif", 1, 14)); // NOI18N
        jLabel6.setText("Jumlah");

        txjumlah.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txjumlahMouseClicked(evt);
            }
        });
        txjumlah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txjumlahActionPerformed(evt);
            }
        });
        txjumlah.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txjumlahKeyPressed(evt);
            }
        });

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

        hapusItem.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        hapusItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/004-delete.png"))); // NOI18N
        hapusItem.setText("HAPUS");
        hapusItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hapusItemActionPerformed(evt);
            }
        });

        jLabel10.setBackground(new java.awt.Color(204, 204, 204));
        jLabel10.setFont(new java.awt.Font("Serif", 1, 14)); // NOI18N
        jLabel10.setText("Ongkos");

        jLabel15.setBackground(new java.awt.Color(204, 204, 204));
        jLabel15.setFont(new java.awt.Font("Serif", 1, 14)); // NOI18N
        jLabel15.setText("Stok");

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/001-search.png"))); // NOI18N
        jButton1.setText("Cari");
        jButton1.setToolTipText("");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel10)
                                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel5))
                                .addGap(65, 65, 65))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(18, 18, 18)))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txStok, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txongkos, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txjumlah, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txHarga, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 384, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton1))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(tambahItem, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(hapusItem)))
                .addContainerGap(36, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 49, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txHarga, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addGap(6, 6, 6)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txjumlah, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txongkos, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel15)
                    .addComponent(txStok, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tambahItem)
                    .addComponent(hapusItem))
                .addContainerGap())
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

        cbmekanik.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Mekanik" }));
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

        btnCari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/001-search.png"))); // NOI18N
        btnCari.setText("Cari");
        btnCari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCariActionPerformed(evt);
            }
        });

        txkeluhan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txkeluhanActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel13)
                            .addComponent(jLabel1)
                            .addComponent(jLabel11))
                        .addGap(7, 7, 7))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel18)
                        .addGap(18, 18, 18)))
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txNo)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(txplg, javax.swing.GroupLayout.DEFAULT_SIZE, 509, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(btnCari))
                    .addComponent(txnopol)
                    .addComponent(cbmekanik, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txkeluhan)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(kd_pelanggan, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
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
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txplg, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(btnCari)))
                .addGap(5, 5, 5)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(txnopol, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbmekanik, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(txkeluhan, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(kd_pelanggan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        txTotal.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        txTotal.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel2.setFont(new java.awt.Font("Serif", 1, 14)); // NOI18N
        jLabel2.setText("Total");

        jLabel8.setFont(new java.awt.Font("Serif", 1, 14)); // NOI18N
        jLabel8.setText("Bayar");

        jLabel9.setFont(new java.awt.Font("Serif", 1, 14)); // NOI18N
        jLabel9.setText("Kembalian");

        txBayar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txBayar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txBayarActionPerformed(evt);
            }
        });
        txBayar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txBayarKeyPressed(evt);
            }
        });

        txKembalian.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel9)
                                    .addComponent(jLabel8))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txTotal)
                                    .addComponent(txBayar, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
                                    .addComponent(txKembalian, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE))))
                        .addGap(147, 147, 147)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btbatal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cetak, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel8)
                                    .addComponent(txBayar))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txKembalian, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel9))
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(cetak, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btbatal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(12, 12, 12))))
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                .addContainerGap(26, Short.MAX_VALUE))
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
                .addGap(18, 18, 18)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(36, Short.MAX_VALUE))
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

    private void btnCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCariActionPerformed
        // TODO add your handling code here:
        FormPelanggan p = new FormPelanggan();
        p.setVisible(true);
    }//GEN-LAST:event_btnCariActionPerformed

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

    private void btbatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btbatalActionPerformed
        hapusservice();
        dataTabel();
    }//GEN-LAST:event_btbatalActionPerformed

    private void hapusItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hapusItemActionPerformed
        hapusData();
    }//GEN-LAST:event_hapusItemActionPerformed

    private void tambahItemKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tambahItemKeyPressed
        update_stok();
        tambah_item();
        dataTabel();
    }//GEN-LAST:event_tambahItemKeyPressed

    private void tambahItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tambahItemActionPerformed
        tambah_item();
        update_stok();
        dataTabel();
    }//GEN-LAST:event_tambahItemActionPerformed

    private void txjumlahKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txjumlahKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode()==KeyEvent.VK_ENTER) {
            int jumlah = Integer.parseInt(txjumlah.getText());
            int stok = Integer.parseInt(txStok.getText());
            if(evt.getKeyCode()==KeyEvent.VK_ENTER){
                if (stok < jumlah ){
                    JOptionPane.showMessageDialog(null,"Stok Kurang");
                    txjumlah.requestFocus();
                    txjumlah.setText("");
                }else {
                    txongkos.requestFocus();
                    tambahItem.requestFocus();
                }
            }
        }
    }//GEN-LAST:event_txjumlahKeyPressed

    private void txjumlahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txjumlahActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txjumlahActionPerformed

    private void txjumlahMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txjumlahMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_txjumlahMouseClicked

    private void txkeluhanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txkeluhanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txkeluhanActionPerformed

    private void cetakActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cetakActionPerformed
        try {
            File report = new File("src/report/cetak.jrxml");
            jd = JRXmlLoader.load(report);
            param.clear();
            //param.put("kd",Integer.parseInt(txtcari.getText()));
            jr =JasperCompileManager.compileReport(jd);
            jp = JasperFillManager.fillReport(jr, param, conn);
            JasperViewer.viewReport(jp, false);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_cetakActionPerformed

    private void txBayarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txBayarKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            int bayar=Integer.parseInt(txBayar.getText());
            int total=Integer.parseInt(txTotal.getText());
            if (bayar>=total){
                int kembali=bayar-total;
                txKembalian.setText(String.valueOf(kembali));
                simpanNota();
                kosong();
                txnopol.setText("");
                txkeluhan.setText("");
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
    private javax.swing.JButton btnCari;
    private javax.swing.JComboBox cbmekanik;
    private javax.swing.JButton cetak;
    private javax.swing.JButton hapusItem;
    private javax.swing.JButton jButton1;
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
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField kd_pelanggan;
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
    private javax.swing.JTextField txplg;
    // End of variables declaration//GEN-END:variables
}
