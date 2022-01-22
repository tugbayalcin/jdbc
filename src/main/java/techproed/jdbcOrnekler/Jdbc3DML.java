package techproed.jdbcOrnekler;

import java.sql.*;

public class Jdbc3DML {

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
		
		/*=======================================================================
		  ORNEK1: Bolumler tablosuna yeni bir kayit (80, 'ARGE', 'ISTANBUL') 
		  ekleyelim 
		========================================================================*/
		
		//*int s1 = st.executeUpdate("INSERT INTO bolumler VALUES(91, 'ARGE2','CORLU')");
		//*System.out.println("Eklenen satir sayisi: " + s1);
		
		 /*=======================================================================
	      ORNEK2: Bolumler tablosuna birden fazla yeni kayıt ekleyelim.
	     ========================================================================*/ 
	    
	    // 1.YONTEM
	 	// -----------------------------------------------
	 	// Ayri ayri sorgular ile veritabanina tekrar tekrar ulasmak islemlerin 
	 	// yavas yapilmasina yol acar. 10000 tane veri kaydi yapildigi dusunuldugunde
	    // bu kotu bir yaklasimdir.
		
		//**String[] sorgular = {"INSERT INTO bolumler VALUES(95, 'YEMEKHANE', 'ISTANBUL')",
        //**   	  				 "INSERT INTO bolumler VALUES(85, 'OFIS','ANKARA')",
		//**					 "INSERT INTO bolumler VALUES(75, 'OFIS2', 'VAN')"};
		// burada birden fazla sorgu var
		// ben bunlari ayni anda st.executeUpdate() icine koyamam
		// bu sorgular string ifadeler oldugu icin bir String array olusturup, sorgularimi icine koyuyorum
		// ardindan foreach ile array'i gezerek her bir elemani executeUpdate icine koyarak calistirilmasini sagliyorum
       
		//**int count = 0; // sayac tanimladik, kac adet satir eklendigini dongu icerisinden bu sekilde ogrenebiliriz
		//**for(String each : sorgular) 
		//**{
		//**	count += st.executeUpdate(each);
		//**}
		//**System.out.println(count + " Adet Veri Eklendi. ");
		
		
		// 2.YONTEM (addBath ve excuteBatch() metotlari ile)
	 	// ----------------------------------------------------
	 	// addBatch metodu ile SQL ifadeleri gruplandirilabilir ve exucuteBatch()
	 	// metodu ile veritabanina bir kere gonderilebilir.
	 	// excuteBatch() metodu bir int [] dizi dondurur. Bu dizi her bir ifade sonucunda 
	 	// etkilenen satir sayisini gosterir. 
		
	 	String [] sorgular1 = {"INSERT INTO bolumler VALUES(81, 'YEMEKHANE2', 'MUS')",
	 		            	  "INSERT INTO bolumler VALUES(82, 'OFIS3','ORDU')",
	 		            	  "INSERT INTO bolumler VALUES(83, 'OFIS4', 'MUGLA')"};
	 	int sayac =0;
	 	
	 	for(String each : sorgular1) 
	 	{
	 		st.addBatch(each);
	 		sayac ++;// for dongusuyle array'den  bir bir gelen verileri bir araya toplar
	 	}
		st.executeBatch(); // verileri bir seferde database'e yollar 
		System.out.println(sayac + " Adet Veri Eklendi");
		
		
		// 3. YONTEM
	 	//-----------------------------------------------------
	 	// batch metoduyla birlikte PreparedStatement kullanmak en efektif yontemdir.
	 	// bir sonraki ornekte bunu gerceklestirecegiz.
		// diger class'ta anlatiliyor
		
		
		
		
		
		st.close();
		con.close();

	}

}
