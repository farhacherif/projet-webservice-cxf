package fr.pwa.webservice.rest;

import java.util.Collection;
import java.util.HashMap;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Library")
public class Library {
	static private final Library instance = new Library();
	private HashMap<Long, Book> bookCollection = new HashMap<>();

	private Library() {
		this.addBook(new Book(1, "Toto", "1234000"));
		this.addBook(new Book(2, "Mozart", "1234040"));
		this.addBook(new Book(3, "Victor HUGO", "1668900"));
	}

	public static Library getInstace() {
		return instance;
	}

	public void addBook(Book newBook) {
		bookCollection.put(newBook.getId(), newBook);
	}

	public Book removeBook(Long bookIdToRemove) {
		return bookCollection.remove(bookIdToRemove);
	}

	public Book getBook(Long bookId) {
		return bookCollection.get(bookId);
	}

	public Collection<Book> getBooks() {
		return bookCollection.values();
	}
}
