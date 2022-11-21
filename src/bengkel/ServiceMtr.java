
package bengkel;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class ServiceMtr extends javax.swing.JFrame {
 private Connection conn = new Koneksi().connect();
        private DefaultTableModel tabmode;
        int hrg, sub, jml, ongkos; 
        
        
    public ServiceMtr() {
        initComponents();
        non_aktif();
        tampilComboplg();
        tampilCombomekanik();
        tampilCombosp();
        Date date = new Date();
        jDateChooser1.setDate(date);
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

    private void tampilCombosp(){
        String sql = "SELECT * FROM sparepart";
        try {
            Statement stat = conn.createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            while(hasil.next()){
                cbkode.addItem(hasil.getString("kd_sparepart"));
            }
            hasil.close();
            stat.close();
            } catch (SQLException ex) {
        }         
    }
      
     private void kosong(){
    cbkode.setSelectedItem("Sparepart");
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
        txDateTime.setEnabled(false);
    }
    
     private void kodeSparepart(){
        String sql = "SELECT * FROM sparepart WHERE kd_sparepart = '"+cbkode.getSelectedItem()+"'";
        try {
            Statement stat = conn.createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            while(hasil.next()){
                txHarga.setText(hasil.getString("harga"));
                txongkos.setText(hasil.getString("ongkos"));
                txjumlah.requestFocus();
                txStok.setText(hasil.getString("stok"));
            }
            hasil.close();
            stat.close();
            } catch (SQLException ex) {
        }         
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
      
      private void tampilComboplg(){
        String sql = "SELECT * FROM pelanggan";
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
      
       private void tampilPlg(){ 
        try {
        String sql = "SELECT kd_pelanggan FROM pelanggan WHERE nm_pelanggan='"+cbplg.getSelectedItem()+"'"; 
        Statement stat = conn.createStatement();
        ResultSet hasil = stat.executeQuery(sql);
        while(hasil.next()){
            Object[] ob = new Object[1];
            ob[0]=  hasil.getString(1);
            txplg.setText((String) ob[0]);
        }
            hasil.close(); 
            hasil.close(); 
        } catch (Exception e) {
            System.out.println(e.getMessage());
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
            txmekanik.setText((String) ob[0]);
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
         int ok = JOptionPane.showConfirmDialog(null, "Apakah Anda Yakin ingin Menghapus Data Ini?", "Konfirmasi Dialog", JOptionPane.YES_NO_OPTION);
        if (ok == 0) {
            String sql = "delete from detail_service where no_faktur='"+txNo.getText() + "' and kd_sparepart='"+cbkode.getSelectedItem()+"'";
            try {
                PreparedStatement stat = conn.prepareStatement(sql);
                stat.executeUpdate();
                JOptionPane.showMessageDialog(null, "Data Berhasil Dihapus");
                kosong();
   
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Data Gagal Di Hapus" + e);
            }
        }
    }
    private void simpanNota(){
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); 
    Date tanggal = new Date(); 
    tanggal = jDateChooser1.getDate(); 
    String jual_tgl = dateFormat.format(tanggal);
    
    String sql = "INSERT INTO service_motor VALUES(?,?,?,?,?,?)";
            try {
                PreparedStatement stat = conn.prepareStatement(sql);
                stat.setString(1, txNo.getText());
                stat.setString(2, jual_tgl);
                stat.setString(3, txplg.getText());
                stat.setString(4, txmekanik.getText());
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
                stat.setString(1, (String) cbkode.getSelectedItem());
                stat.setString(2, txHarga.getText());
                stat.setString(3, txjumlah.getText());
                stat.setString(4, txNo.getText());
                stat.setString(5, txongkos.getText());
                stat.setString(6, String.valueOf(sub));
                
                stat.executeUpdate();
                kosong();
                cbkode.requestFocus();
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
           stat.setString(2, (String) cbkode.getSelectedItem());
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
    cbkode.setSelectedItem(ID);
    }
    
     private void tampilKlik(){
        try {
        String sql="SELECT * FROM sparepart, detail_service WHERE "
                + "detail_service.kd_sparepart = '"+this.cbkode.getSelectedItem()+"'";
        PreparedStatement stat = conn.prepareStatement(sql);
        ResultSet rs = stat.executeQuery(sql);
        while(rs.next()){
        this.txHarga.setText(rs.getString("harga"));
        this.txjumlah.setText(rs.getString("jumlah"));
        this.txongkos.setText(rs.getString("ongkos"));
        }
        rs.close(); 
        stat.close();}
        catch (Exception e) {
            System.out.println(e.getMessage());
        }      
    }
     
     private void tampilStok(){
        try {
        String sql="SELECT * FROM sparepart WHERE kd_sparepart = '"+this.cbkode.getSelectedItem()+"'";
        PreparedStatement stat = conn.prepareStatement(sql);
        ResultSet rs = stat.executeQuery(sql);
        while(rs.next()){
        this.txStok.setText(rs.getString("stok"));
        }
        rs.close(); 
        stat.close();}
        catch (Exception e) {
            System.out.println(e.getMessage());
        }      
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
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel4 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        cbplg = new javax.swing.JComboBox<>();
        txplg = new javax.swing.JTextField();
        cbmekanik = new javax.swing.JComboBox();
        txmekanik = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txHarga = new javax.swing.JTextField();
        txjumlah = new javax.swing.JTextField();
        tambahItem = new javax.swing.JButton();
        txStok = new javax.swing.JTextField();
        hapusItem = new javax.swing.JButton();
        cbkode = new javax.swing.JComboBox();
        txongkos = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        txDateTime = new javax.swing.JTextField();
        batalNota = new javax.swing.JButton();
        txTotal = new javax.swing.JTextField();
        txBayar = new javax.swing.JTextField();
        txKembalian = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        txNo = new javax.swing.JTextField();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txnopol = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        txkeluhan = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        btbatal = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel4.setBackground(new java.awt.Color(169, 193, 227));

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("TRANSAKSI SERVICE");

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton1.setText("X");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jPanel5.setBackground(new java.awt.Color(204, 204, 255));

        jPanel1.setBackground(new java.awt.Color(153, 153, 255));
        jPanel1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel1.setToolTipText("");

        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Pelanggan");

        cbplg.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pelanggan" }));
        cbplg.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cbplgMouseClicked(evt);
            }
        });
        cbplg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbplgActionPerformed(evt);
            }
        });

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

        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Mekanik");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txplg)
                            .addComponent(cbplg, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txmekanik)
                            .addComponent(cbmekanik, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(83, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbplg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txplg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(cbmekanik, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txmekanik, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
        );

        jPanel2.setBackground(new java.awt.Color(153, 153, 255));
        jPanel2.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Kode Sparepart");

        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Harga");

        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
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
        tambahItem.setText("+");
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
        hapusItem.setText("-");
        hapusItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hapusItemActionPerformed(evt);
            }
        });

        cbkode.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Sparepart" }));
        cbkode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbkodeActionPerformed(evt);
            }
        });

        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Ongkos");

        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("Stok");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel10))
                        .addGap(61, 61, 61)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(tambahItem, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(hapusItem, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txongkos)
                            .addComponent(txjumlah))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txStok, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5))
                        .addGap(22, 22, 22)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cbkode, 0, 152, Short.MAX_VALUE)
                            .addComponent(txHarga))
                        .addGap(21, 21, 21))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(cbkode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txHarga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txongkos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txStok, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txjumlah, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(hapusItem)
                    .addComponent(tambahItem))
                .addGap(5, 5, 5))
        );

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
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        batalNota.setText("Batal Transaksi");
        batalNota.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                batalNotaActionPerformed(evt);
            }
        });

        txTotal.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        txTotal.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        txBayar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txBayar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txBayarKeyPressed(evt);
            }
        });

        txKembalian.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Kembalian");

        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Bayar");

        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Total");

        jPanel7.setBackground(new java.awt.Color(153, 153, 255));
        jPanel7.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("No Nota");

        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Tanggal");

        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("No Polisi");

        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Keluhan");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(jDateChooser1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(txNo, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(132, 132, 132)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addGap(18, 18, 18)
                        .addComponent(txkeluhan, javax.swing.GroupLayout.PREFERRED_SIZE, 341, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addGap(18, 18, 18)
                        .addComponent(txnopol, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(198, 198, 198)))
                .addContainerGap(149, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13)
                    .addComponent(txnopol, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2)
                    .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel14)
                        .addComponent(txkeluhan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btbatal.setText("Batal");
        btbatal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btbatalActionPerformed(evt);
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
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(42, 42, 42)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(btbatal)
                        .addGap(78, 78, 78))))
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txDateTime, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
                    .addComponent(batalNota, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(50, 50, 50)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(51, 51, 51)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addComponent(jLabel8))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txBayar, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
                    .addComponent(txKembalian))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btbatal))
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txDateTime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addComponent(batalNota))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel8)
                                    .addComponent(txBayar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel9)
                                    .addComponent(txKembalian, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(385, 385, 385))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel12)
                .addGap(272, 272, 272)
                .addComponent(jButton1)
                .addGap(34, 34, 34))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(jButton1))
                .addGap(18, 18, 18)
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
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 657, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 3, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void cbplgMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbplgMouseClicked
        // TODO add your handling code here:
        if (cbplg.getSelectedItem() != "Pelanggan"){
            txjumlah.setEnabled(true);
            tambahItem.setEnabled(true);
        }else{

        }
    }//GEN-LAST:event_cbplgMouseClicked

    private void cbplgActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbplgActionPerformed
        // TODO add your handling code here
        tampilPlg();
    }//GEN-LAST:event_cbplgActionPerformed

    private void cbmekanikMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbmekanikMouseClicked
        if (cbmekanik.getSelectedItem() != "Mekanik"){
            txjumlah.setEnabled(true);
            tambahItem.setEnabled(true);
        }else{

        }
    }//GEN-LAST:event_cbmekanikMouseClicked

    private void cbmekanikActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbmekanikActionPerformed
        tampilMekanik(); // TODO add your handling code here:
    }//GEN-LAST:event_cbmekanikActionPerformed

    private void txjumlahMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txjumlahMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_txjumlahMouseClicked

    private void txjumlahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txjumlahActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txjumlahActionPerformed

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

    private void tambahItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tambahItemActionPerformed
        tambah_item();
        update_stok();
        dataTabel();
    }//GEN-LAST:event_tambahItemActionPerformed

    private void tambahItemKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tambahItemKeyPressed
        update_stok();
        tambah_item();
        dataTabel();
    }//GEN-LAST:event_tambahItemKeyPressed

    private void hapusItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hapusItemActionPerformed
        hapusData();
    }//GEN-LAST:event_hapusItemActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        cariKode();
        tampilStok();
        tampilKlik();
    }//GEN-LAST:event_jTable1MouseClicked

    private void batalNotaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_batalNotaActionPerformed
        // TODO add your handling code here:
        kosong();
        txplg.setText("");
        cbplg.setSelectedItem("Pelanggan");
        txmekanik.setText("");
        cbmekanik.setSelectedItem("Mekanik");
    }//GEN-LAST:event_batalNotaActionPerformed

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

    private void cbkodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbkodeActionPerformed
       kodeSparepart();
    }//GEN-LAST:event_cbkodeActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
               dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btbatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btbatalActionPerformed
      hapusservice();
      dataTabel();
    }//GEN-LAST:event_btbatalActionPerformed

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
            java.util.logging.Logger.getLogger(ServiceMtr.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ServiceMtr.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ServiceMtr.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ServiceMtr.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ServiceMtr().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton batalNota;
    private javax.swing.JButton btbatal;
    private javax.swing.JComboBox cbkode;
    private javax.swing.JComboBox cbmekanik;
    private javax.swing.JComboBox<String> cbplg;
    private javax.swing.JButton hapusItem;
    private javax.swing.JButton jButton1;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JButton tambahItem;
    private javax.swing.JTextField txBayar;
    private javax.swing.JTextField txDateTime;
    private javax.swing.JTextField txHarga;
    private javax.swing.JTextField txKembalian;
    private javax.swing.JTextField txNo;
    private javax.swing.JTextField txStok;
    private javax.swing.JTextField txTotal;
    private javax.swing.JTextField txjumlah;
    private javax.swing.JTextField txkeluhan;
    private javax.swing.JTextField txmekanik;
    private javax.swing.JTextField txnopol;
    private javax.swing.JTextField txongkos;
    private javax.swing.JTextField txplg;
    // End of variables declaration//GEN-END:variables
}
