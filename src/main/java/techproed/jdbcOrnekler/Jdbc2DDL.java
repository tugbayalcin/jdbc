package techproed.jdbcOrnekler;

import java.sql.*;


public class Jdbc2DDL {

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
		
		// 3 tane methodumuz var 
		// 1. executeQuery  --> su an bunu ogreniyoruz, select sorgularinda kullanilir
		// 2. execute  --> select disindaki her seyi bu kodla ve asagidakiyle yapabiliriz ama aralarindan kucuk bir fark var 
		// 3. executeUpdate --> Update'lerde kullanilir
		// aradaki fark icin asagidaki nota bakalim 
		
		/*
	 	A) CREATE TABLE, DROP TABLE, ALTER TABLE gibi DDL ifadeleri icin sonuc kümesi (ResultSet) 
	 	   dondurmeyen metotlar kullanilmalidir. Bunun icin JDBC'de 2 alternatif bulunmaktadir.  
	 	    1) execute() metodu 
	 	    2) excuteUpdate() metodu.  
	 	    
	 	B) - execute() metodu hertur SQL ifadesiyle kullanibilen genel bir komuttur. 
	 	   - execute(), Boolean bir deger dondurur. DDL islemlerinde false dondururken, 
	 	     DML islemlerinde true deger dondurur. 
	 	   - Ozellikle, hangi tip SQL ifadesinin kullanilmasinin gerektiginin belli olmadigi 
	 	     durumlarda tercih edilmektedir.  
	 	     
	 	C) - executeUpdate() metodu ise INSERT, Update gibi DML islemlerinde yaygin kullanilir.
	 	   - bu islemlerde islemden etkilenen satir sayisini dondurur.
	 	   - Ayrıca, DDL islemlerinde de kullanilabilir ve bu islemlerde 0 dondurur.
	 */
		
		 /*=======================================================================
		  ORNEK1:isciler adinda bir tablo olusturunuz id int, 
		  birim VARCHAR(10), maas int
//		  ========================================================================*/ 
	   /* 
		String sorgu = "CREATE TABLE isciler"
				 + " (id int primary key,"
		         + " birim VARCHAR(10),"
		         + " maas int)";
		         */
		         
	    
	    
	    //1.yöntem 
	    
	    // boolean s1=st.execute(sorgu);
	    
	    // System.out.println("isciler tablosu oluşturuldu" +s1);
		// execute() metodu DDL komutlarinda hep false deger dondurdugu icin 
		// donus degerine bakmak gerekli degildir. Zaten komutun calismasi ile 
		// ilgili bir sorun var ise SQL EXception olusacaktir
	    
		//2.yöntem
	    // * st.execute(sorgu);
	    // * System.out.println("isciler tablosu oluşturuldu"); /// bir ust satirdaki kodun calistigini dogrulamak ve anlamak adina boyle bir sey yazdiriyoruz
	    
	  
	    /*======================================================================
		  ORNEK2:isciler tablosunu siliniz 		
		 ======================================================================*/
		// bu kodu calistirmadan once yukaridaki create ile ilgili kisimlari yorumlara aliyoruz
		// yoksa sql kafayi yer
			    
		
	    //   st.execute("drop table isciler");
	    //   System.out.println("Isciler tablosu silindi");
		
		// calistirdiktan sonra burayi da yoruma aliyoruz 
	    // eger calistiktan sonra hemen consolda bir sey gormuyorsan kirmiziya bas ve durdur, o aslinda sildi ama sana gostermiyor
	    // bunu anlamak icin de yeniden silmeyi dene, hata verdigini goreceksin
	    // cunku olmayan bir seyi silemezsin
		
		/* =======================================================================
		   ORNEK3:isciler tablosuna yeni bir sutun {isim Varchar(20)} ekleyiniz.   
		   =======================================================================*/
		
		// create kismini yorumdan cikaralim
	    // *st.execute("ALTER table isciler ADD isim VARCHAR(20)");
	    // *System.out.println("Sutun ekleme islemi basarili.");
	    
	    /*=======================================================================
		  ORNEK4:isciler tablosuna soyisim VARCHAR(20) ve sehir VARCHAR(10)) 
		  adinda 2 yeni sutun ekleyiniz.  
		 ========================================================================*/
		// yine yukaridaki her seyi yoruma aldik
		// st.execute("ALTER table isciler ADD (soyisim VARCHAR(20), sehir VARCHAR(20))");
		// System.out.println("Islem tamamlandi");
		
		/*=======================================================================
		  ORNEK5:isciler tablosundaki birim sutunu siliniz.
		========================================================================*/ 
		//String alterQuery3 = "ALTER TABLE isciler DROP COLUMN birim";
		//st.execute(alterQuery3);
		
		//System.out.println("isciler tablosundan birim sutunu silindi..");
	
		/*=======================================================================
		  ORNEK6:isciler tablosunun adini calisanlar olarak degistiriniz.  
		========================================================================*/
//		String alterQuery4 = "ALTER TABLE isciler RENAME TO calisanlar5";
//		st.execute(alterQuery4);
//		System.out.println("isciler tablosunun adi calisanlar olarak degismistir..");
		
		/*=======================================================================
		  ORNEK7:calisanlar tablosunu siliniz.  
		========================================================================*/
		String dropQuery2 = "DROP TABLE calisanlar5 ";
		st.execute(dropQuery2); 
	
		System.out.println("calisanlar tablosu silindi..");
	    
	    st.close();
	    con.close();

	}

}
