package fr.pwa.webservice.rest;

import java.util.Collection;

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

import org.json.JSONObject;

@WebService()
@Path("/library/")
public class LibraryService {

	private Library library = Library.getInstace();

	@GET
	@Path("/book/{f}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public String getBook() {
		//database logic
		/*	name | lastname | age
		 * 
		 * rs = stmt.executeQuery("SELCT COLUMNS NAMES OF A TABLE");
		 * ArrayList tableKeys = new ArrayList<String>();
		 * while(rs.next){
		 * 		tableKeys.add(rs.getString(0));
		 * }
		 * 
		 * rs2 = stmt.executeQuery("select .... where dateofbirth= " +gg + "gg...");
		 * ArrayList tableValues = new ArrayList<String>();
		 * while(rs.next){
		 *	
		 * 		tableValues.add(rs2.getString(0));
		 * 		tableValues.add(rs2.getString(1));
		 * 		tableValues.add(rs2.getString(2));
		 * 		tableValues.add(rs2.getString(3));
		 *
		 * }
		 * 
		 * for(i, i<lengthKeys){
		 * 		jo.put(tableKeys[i], tableValues[i]);
		 * }
		 * 
		 * */
		//returning results
		JSONObject jo = new JSONObject();
		jo.put("name", "jon doe");
		jo.put("age", "22");
		jo.put("city", "chicago");
		return jo.toString();
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
