package fr.pwa.webservice.rest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import javax.jws.WebService;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.CrossOrigin;

@WebService()
@Path("/data/")
public class LibraryService {

	private Library library = Library.getInstace();

	/*JSONObject jo = new JSONObject();
	jo.put("name", "jon doe");
	jo.put("age", "22");
	jo.put("city", "chicago");
	return jo.toString();*/
	
	@GET
	@Path("/alldata/")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@CrossOrigin(origins = "http://localhost:4200")
	public String getAllData() {
		try{  
			Class.forName("oracle.jdbc.driver.OracleDriver");  
			
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","system","Enzo123456");  			
			Statement stmt=con.createStatement();  
			ResultSet rs=stmt.executeQuery("select * from AUDIT_RESULT");  
			JSONArray jo = new JSONArray();
			ResultSetMetaData rsmd = rs.getMetaData();
			while(rs.next()) {
			  int numColumns = rsmd.getColumnCount();
			  JSONObject obj = new JSONObject();
			  for (int i=1; i<=numColumns; i++) {
			    String column_name = rsmd.getColumnName(i);
			    obj.put(column_name, rs.getObject(column_name));
			  }
			  jo.put(obj);
			}
			con.close();  
			return jo.toString();
			}
		catch(Exception e){ 
			return e.toString();
			}
	}
	
	@GET
	@Path("/madrefreshselect/")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public String getsecondselect() {
		try{  
			Class.forName("oracle.jdbc.driver.OracleDriver");  
			
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","system","Enzo123456");  			
			Statement stmt=con.createStatement();  
			ResultSet rs=stmt.executeQuery("select * from AUDIT_RESULT where EVENT_TYPE_NAME = 'Refresh' and OBJECT_NAME not like '%do_not_use%' order by start_time desc");  
			JSONArray jo = new JSONArray();
			ResultSetMetaData rsmd = rs.getMetaData();
			while(rs.next()) {
			  int numColumns = rsmd.getColumnCount();
			  JSONObject obj = new JSONObject();
			  for (int i=1; i<=numColumns; i++) {
			    String column_name = rsmd.getColumnName(i);
			    obj.put(column_name, rs.getObject(column_name));
			  }
			  jo.put(obj);
			}
			con.close();  
			return jo.toString();
			}
		catch(Exception e){ 
			return e.toString();
			}
	}

	@GET
	@Path("/book/{id}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Book getBook(@PathParam("id") String id) {
		System.out.println("----invoking getBook, Book id is: " + id);
		long bookId = Long.parseLong(id);
		return library.getBook(bookId);
	}

	@DELETE
	@Path("/book/{id}")
	public Response deleteBook(@PathParam("id") String id) {
		System.out.println("----invoking deleteBook, Book id is: " + id);
		long bookId = Long.parseLong(id);
		library.removeBook(bookId);
		return Response.ok().build();
	}

	@PUT
	@Path("/book/")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response addBook(Book newBook) {
		System.out.println("----invoking addBook, Book id is: " + newBook.getId());
		library.addBook(newBook);
		return Response.ok(newBook).build();
	}

}
