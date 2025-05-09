package pckg1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseWorkShop {

	public static void main(String[] args) throws SQLException {
		
		//connection yazdıktan sonra crtl + space -> select Connection java.sql
		Connection con = DriverManager
				.getConnection("jdbc:mysql://localhost:3306/university", "root", "S44l44pn_");

		//statement yazdıktan sonra ctrl + space -> select statement java.sql
		Statement st = con.createStatement();
		//Resultset yazdıktan sonra ctrl + space -> select resultset java.sql
		ResultSet rs = st.executeQuery("select * from student");
		
		//next func. returns a value -> true or false(checks the first line, if there is info, it will return 1, if no info, 0
		while(rs.next()) {
			System.out.println(rs.getInt(1) + " " + rs.getString(2) + " " + rs.getString(3) + " " + rs.getString(4));
		}
		
		//CRUD
		//CREATE****************************************************************************************************************************
		
		/*
		Student stu = new Student();
		stu.setStudentId(4);
		stu.setName("Marry");
		stu.setSurname("Hall");
		stu.setDepartment("Computing");
		
		//soru işaretleri yerine bu da olur tabi
		//"insert into student values('" + stu.getStudentId() + "','" + stu.getName() + "','" + stu.getSurname() + "','" + stu.getDepartment() + ");";
		
		String insertQuery = "insert into student values(?,?,?,?)";
		//PreparedStatement yazdıktan sonra ctrl + space -> select PreparedStatement java.sql
		PreparedStatement ps = con.prepareStatement(insertQuery);
		//soru işaretlerini tanımlıyorum
		//(question mark indexes, value)
		ps.setInt(1, stu.getStudentId());
		ps.setString(2, stu.getName());
		ps.setString(3, stu.getSurname());
		ps.setString(4, stu.getDepartment());
		ps.executeUpdate();
		//**********UPDATED**********************************************UPDATED DATABASE
		//MySQL database de 3 kişi vardı, tabloyu yeniledim, 4. kişi de eklendi
		//konsola da yazdırması için tekrar select yapmamız lazım ve create kısmını yorum yapmamız gerekir çünkü zaten database e eklemiştik
		//JFrame de böyle bir sorun olmaz sadece bu işlemleri SAVE button içinde yaparız.
		
		
		*/
		
		//Create kısnmını yoruma aldım /**/ ile
		//Şimdi tekrar çalıştıracağım ki konsola yazması için yoruma aldıktan sonra sistemi tekrar çalıştıracağım. -> 
		
		
		
		
		
		
		
		
		
		//DELETE************************************************************************************************************************************************
		/*
		String deleteQuery = "delete from student where studentId =?";
		PreparedStatement ps = con.prepareStatement(deleteQuery);
		//(question mark index, student id)
		ps.setInt(1, 4);
		ps.executeUpdate();
		*/
		
		
		
		
		
		
		
		//UPDATE***************************************************************************************************************************************************
		/*
		String updateQuery = "update student set department=? where studentId=?";
		PreparedStatement ps = con.prepareStatement(updateQuery);
		//(question mark indexes, value)
		ps.setString(1, "Computing");
		ps.setInt(2, 3);
		ps.executeUpdate();
		*/
		
		//__________________________________________________________________________________
		//Resultset in içinde öenmli bilgiler var (ekstra)
		//mesela bir tablodaki column sayısı gibi(önemli bilgidir)
		//column isimleri, table ın ismi gibi
		System.out.println(rs.getMetaData().getColumnCount());
		//database name
		System.out.println(rs.getMetaData().getCatalogName(1));
		//2. column un name i
		System.out.println(rs.getMetaData().getColumnName(2));
		
		//bütün column ların isimleri
		for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
			System.out.print(rs.getMetaData().getColumnName(i) + " ");
		}
		
		// column 2 nin type ı(int, string etc.)
		System.out.println("\n" + rs.getMetaData().getColumnTypeName(2));
		
		System.out.println(rs.getMetaData().getTableName(1));
		
		
		
	}

}
