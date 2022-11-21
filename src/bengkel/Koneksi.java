
package bengkel;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class Koneksi {
     private Connection koneksi;
        public Connection connect(){
            try{
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException ex){
                System.out.println("Koneksi Gagal "+ex);
            }
            String url = "jdbc:mysql://localhost:3306/bengkel";
            try{
                koneksi = DriverManager.getConnection(url, "root", "");
                
            }catch (SQLException ex){
                System.out.println("Gagal Koneksi "+ex);
            }
            return koneksi;
        }
    void getConnection() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }}
