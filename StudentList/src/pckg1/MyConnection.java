package pckg1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyConnection {
	
	public static final String URL = "jdbc:mysql://localhost:3306/studentlist";
	public static final String USER = "root";
	public static final String PASSWORD = "password";
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
		}
	}

}
