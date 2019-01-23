package database;

import java.sql.*;

public class Connect{
	public Connection connexion()throws Exception{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			String user = "jirama";
			String passwd = "jirama";
			
			Connection conn = DriverManager.getConnection(url, user,
			passwd);
			
			return conn;
	}
	
	public void deconnection(ResultSet result , Statement state)throws Exception{
		result.close();
		state.close();
	}
}