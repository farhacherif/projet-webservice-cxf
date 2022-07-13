package fr.pwa.webservice.rest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;  


public class OracleConn {

	public static void main(String[] args) {

		try{  
			Class.forName("oracle.jdbc.driver.OracleDriver");  
			
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","system","Enzo123456");  
			
			//here sonoo is database name, root is username and password  
			
			Statement stmt=con.createStatement();  
			
			ResultSet rs=stmt.executeQuery("select * from AUDIT_RESULT");  
			
			while(rs.next()) {
				
			System.out.println(rs.getString(2));  
			
			}
			
			con.close();  
			}
		
		catch(Exception e){ 
			System.out.println(e);
			}
	}

}

