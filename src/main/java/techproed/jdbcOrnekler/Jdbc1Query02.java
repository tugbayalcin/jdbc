package techproed.jdbcOrnekler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.*;

// import com.mysql.cj.protocol.Resultset;

public class Jdbc1Query02 {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
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
		
		/*=======================================================================
		 ORNEK1: Bolumler tablosundaki tum kayitlari listeleyen bir sorgu yaziniz.
		========================================================================*/ 
		
		// select * from bolumler
		
		ResultSet tablo = st.executeQuery("SELECT * FROM bolumler"); // bolumler tablosunu getirdi ve biz bunu bir degiskene atadik
		// burasi hata verirse gereksiz import vardir, duzelt
		// hatta en kolayi teee en basta import java.sql.*; yazarak import yap ki bir daha ugrasma
		
		// 3 tane methodumuz var 
		// 1. executeQuery  --> su an bunu ogreniyoruz, select sorgularinda kullanilir
		// 2. execute  --> select disindaki her seyi bu kodla ve asagidakiyle yapabiliriz ama aralarindan kucuk bir fark var, DDL islemlerini yazdirmaya kalktiginda console'da false goruruz DML islemlerinde true, cunku bize sonuc dondurmuyor
		// 3. executeUpdate --> Update'lerde kullanilir yazdirmaya kalktigimizda kac satir DML'den etkilenmisse o sayiyi console'da yazdirir DDL de 0 yazdirir
		// daha detayli bilgi icin Jdbc2DDL class'ina bak
		
		while(tablo.next()) // gelen tabloyu yazdiriyoruz 
		{
			System.out.println(tablo.getInt(1) + " " + tablo.getString("bolum_isim") + " " + tablo.getString(3));
			// istersek 1,2,3 diye sirayla cagiririz istersek de adini yazariz
			
		}
		System.out.println("=====================================");
		

		/*=======================================================================
		 ORNEK2: SATIS ve MUHASEBE bolumlerinde calisan personelin isimlerini ve 
 		 maaslarini, maas ters sirali olarak listeleyiniz
		 =======================================================================*/ 
		String sorgu = "SELECT isim, maas "
				+ "FROM personel "
				+ "WHERE bolum_id IN (10,30) "
				+ "ORDER BY maas DESC"; // sorgular arasi "" lar sonunda ya da basinda bosluk olmasi onemli
		ResultSet tablo2 = st.executeQuery(sorgu);
		while(tablo2.next()) 
		{
			System.out.println(tablo2.getString(1) + " " + tablo2.getInt(2));
		}
		System.out.println("=====================================");
		
		
		/*=======================================================================
		  ORNEK3: Tüm bolumlerde calisan personelin isimlerini, bolum isimlerini 
		  ve maaslarini, bolum ve maas sirali listeleyiniz. NOT: calisani olmasa 
		  bile bolum ismi gosterilmelidir.
	  	 =======================================================================*/ 
				
		String sorgu1 = "SELECT b.bolum_isim, p.isim, p.maas "
				+ "FROM bolumler as b "
				+ "LEFT JOIN personel as p " // left join yaptik cunku soru soldaki yani bolumler tablosundaki seylerin agirlikta olarak yazilmasini istiyor
				+ "ON p.bolum_id = b.bolum_id "
				+ "ORDER BY b.bolum_isim, p.maas ";
		ResultSet tablo3 = st.executeQuery(sorgu1);
		while(tablo3.next()) 
		{
			System.out.println(tablo3.getString(1) + " " + tablo3.getString(2) + " " + tablo3.getInt(3));
		}
		System.out.println("=====================================");
		
		/*=======================================================================
		  ORNEK4: Maasi en yuksek 10 kisinin bolumunu,adini ve maasini listeyiniz
		========================================================================*/ 
		String sorgu2 = "SELECT b.bolum_isim, p.isim, p.maas "
				+ " FROM personel as p "
				+ " LEFT JOIN bolumler as b "
				+ " ON p.bolum_id = b.bolum_id "
				+ " ORDER BY maas DESC "
				+ " LIMIT 10 ";
		ResultSet tablo4 = st.executeQuery(sorgu2);
		while(tablo4.next()) 
		{
			System.out.println(tablo4.getString(1) + " " + tablo4.getString(2) + " " + tablo4.getInt(3));
		}
		System.out.println("=====================================");
		
		
		con.close();
		st.close();
		tablo.close();
		tablo2.close();
		tablo3.close();
		tablo4.close();

	}

}
