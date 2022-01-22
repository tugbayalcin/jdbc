package techproed.jdbcOrnekler;

//import java.awt.motif.*;

//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.SQLException;
//import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

public class Jdbc4CRUD {

	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		
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
		
		// 3. YONTEM
				//-----------------------------------------------------
				// batch metoduyla birlikte PreparedStatement kullanmak en efektif yontemdir.
				// bir sonraki ornekte bunu gerceklestirecegiz.
		
		/*=======================================================================
		  ORNEK1: urunler adinda bir tablo olusturalim id int, 
		  isim VARCHAR(10) fiyat int 
		========================================================================*/ 
		/*
		st.execute("CREATE TABLE urunler("
			       + " id int,"
			       + " isim VARCHAR(10),"
			       + " fiyat double)");
		System.out.println("tablo olusturuldu");
		*/
		
		/*=======================================================================
		  ORNEK2: urunler tablosuna asagidaki kayitlari toplu bir sekilde ekleyelim
		========================================================================*/ 
		// Cok miktarda kayit eklemek icin PreparedStatement metodu kullanilabilir. 
		// PreparedStatement hem hizli hem de daha guvenli (SQL injection saldirilari icin) bir yontemdir. 
		// Bunun icin; 
		// 	1) Veri girisine uygun bir POJO(Plain Old Java Object) sinifi olusturulur.
		// 	2) POJO Class nesnelerini saklayacak bir collection olusturulur
		// 	3) bir dongu ile kayitlar eklenir. 
		/*
		List<Urun> kayitlar = new ArrayList<>();
		
		kayitlar.add(new Urun(101,"laptop", 6500));
		kayitlar.add(new Urun(102,"PC", 4500));
		kayitlar.add(new Urun(103,"Telefon", 4500));
		kayitlar.add(new Urun(104,"Anakart", 1500));
		kayitlar.add(new Urun(105,"Klavye", 200));
		kayitlar.add(new Urun(106,"Fare", 100));
		
		// st kullanarak veri, ekleyince verilerim mysql im saldiriya acik oluyordu 
		// ben guvenli ekleme yapmak icin st yi degil dogrudan sql ile baglantiyi yaptigim con u kullanacagim
		
		PreparedStatement veri = con.prepareStatement("INSERT INTO urunler VALUES(?,?,?)"); // 3'lu gizli veri girisi
		// 3 girislik guvenli bir yer ac ve actigin o alana daha sonra  3 tane veri girisi yapilacak
		
		for(Urun each : kayitlar) 
		{
			veri.setInt(1, each.getId()); // 1. soru isaretine each'den gelen id yi getir
			veri.setString(2, each.getIsim()); // 2. soru isaretine each'den gelen isim i getir
			veri.setDouble(3, each.getFiyat()); // 3. soru isaretine each'den gelen fiyat i getir
			
			veri.addBatch(); // verileri top etme
			
		}
		veri.executeBatch(); // topu sql'e yollama 
		System.out.println("Kayitlar Eklendi");
		
		*/
		/*=======================================================================
		  ORNEK3: urunler tablosundaki PC'nin fiyatini %10 zam yapiniz
		========================================================================*/
		
//		int s1 = st.executeUpdate("UPDATE urunler SET fiyat = fiyat*1.1 WHERE isim = 'PC' ");
//		System.out.println(s1 + " Adet Satir Guncellendi..");
		
		/*=======================================================================
		  ORNEK4: urunler tablosuna Marka adinda ve Default degeri ASUS olan yeni 
		  bir sutun ekleyiniz.
		========================================================================*/
		
//		st.executeUpdate("ALTER TABLE urunler ADD marka VARCHAR(10) DEFAULT 'ASUS' ");
//		System.out.println("Yeni Sutun Eklendi");
		
		/*=======================================================================
		  ORNEK5: urunler tablosunu siliniz.
		========================================================================*/ 
		
		st.executeUpdate("DROP TABLE urunler");
		System.out.println("Tablo Silindi..");
		
		st.close();
		con.close();
		

	}

}
