package pckg1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MySQLConnection {
	
	//getResutlSet class içinde tanımladım.
	//main static method erişsin diye static yazdım
	private static ResultSet rs;
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException  {
		//Register Driver
			//Establish Connection
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/studentlist", "root", "S44l44pn_");
		System.out.println("Connection created.");
		
		//Statement, after writing it, select the sql one
				Statement st = con.createStatement();
				rs = st.executeQuery("select * from students");
				
				while(rs.next()) {
					System.out.println(rs.getInt(1)+"\t\t"+rs.getString("first_name")+"\t\t"+rs.getString("last_name")+"\t\t"+rs.getString("faculty")+"\t\t"+rs.getString("department")+"\t\t"+rs.getBigDecimal("gpa"));
				}
	}
	
	public ResultSet getResultSet() {
        return rs;
    }

}
