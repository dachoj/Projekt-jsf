package com.example.jsfdemo.service;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;

import com.example.jsfdemo.domain.Book;

@ApplicationScoped
public class BookManager {
	private List<Book> db = new ArrayList<Book>();

	public void addBook(Book book) {
		Book newBook = new Book();

		newBook.setTitle(book.getTitle());
		newBook.setKind(book.getKind());
		newBook.setSerial(book.getSerial());
		newBook.setDateOfPrint(book.getDateOfPrint());
		newBook.setOwn(book.isOwn());
		newBook.setPages(book.getPages());
		newBook.setQuantity(book.getQuantity());

		db.add(newBook);
	}

	// Removes the person with given PIN
	public void deleteBook(Book book) {
		Book bookToRemove = null;
		for (Book p : db) {
			if (book.getSerial().equals(p.getSerial())) {
				bookToRemove = p;
				break;
			}
		}
		if (bookToRemove != null)
			db.remove(bookToRemove);
	}

	public List<Book> getAllBooks() {
		return db;
	}
}
