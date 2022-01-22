package techproed.jdbcOrnekler;

// importlar java.sql'den olmali

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

//import com.mysql.cj.protocol.Resultset;


//import com.mysql.cj.jdbc.Driver;
//import com.mysql.cj.xdevapi.Statement;
// bu importlar otomatik geldi, ama lazim degil ve yoruma almazsan ileride casting yapman gereken durumlar ortaya cikiyor 
// o yuzden yoruma al ya da sil 

public class Jdbc1Query01 {

	public static void main(String[] args) throws ClassNotFoundException, SQLException 
	{
		// ****1) ILGILI DRIVER'I YUKLEMELIYIZ --> TV'nin fisini tak, baska alet calismasin, ne calisacagini bilsin
		
		Class.forName("com.mysql.cj.jdbc.Driver"); // kirmizi cizer cunku ya ben bu driver'i bulamazsam, ya evde yoksa exception'u bu
		// uzerine gelip otomatik cozmesini iste, class'in yanina throws ekledi 
		
		
		// ****2) BAGLANTI OLUSTURMALIYIZ --> Uydu sifrelerini girmeliyiz
		
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sys?serverTimezone=UTC", "root", "Mevlana152.");
		// burada root senin mysql'deki user name kısmin
		// sifren de bir sonraki
		
		
		// ****3) SQL KOMUTLARI ICIN BIR STATEMENT NESNESI OLUSTUR
		
		Statement st = con.createStatement();
		
		
		// ****4) SQL IFADELERI YAZABILIR VE CALISTIRABILIRIZ --> kumandada 1'e basarim trt gelir
		
		ResultSet veri = st.executeQuery("SELECT isim, maas FROM personel WHERE id=123456789");
		
		// ****5) SONUCLARI ALDIK VE ISLEDIK, HENUZ GORMEDIK, SOUT ILE GORECEGIZ
		
		while(veri.next()) 
		{
			System.out.println(veri.getString("isim") + veri.getInt("maas"));
			
			System.out.println("Personel Adi: " + veri.getString(1) + "\nMaas: " + veri.getInt(2));
			// burada 1 ve 2 diye yazdigim sey 43. satirdaki sql kodunda 1 isim, 2 maas getirilmesini istedim
			// sql deki tablo sutun sirasi onemli degil, yukariya yazdigim sira onemli
		}
		
		
		// ****6) OLUSTURULAN NESNELERİ KAPATARAK BELLEKTEN KALDIRALIM
		
		con.close();
		st.close();
		veri.close();
		

	}

}
