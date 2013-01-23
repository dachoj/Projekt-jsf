package com.example.jsfdemo.web;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIForm;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;
import javax.faces.model.ListDataModel;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;
import javax.inject.Named;

import com.example.jsfdemo.domain.Book;
import com.example.jsfdemo.service.BookManager;

@SessionScoped
@Named("bookBean")
public class BookFormBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Book book = new Book();

	private ListDataModel<Book> books = new ListDataModel<Book>();

	@Inject
	private BookManager pm;

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public ListDataModel<Book> getAllBooks() {
		books.setWrappedData(pm.getAllBooks());
		return books;
	}

	// Actions
	public String addBook() {
		pm.addBook(book);
		return "showBooks";
		//return null;
	}

	public String deleteBook() {
		Book bookToDelete = books.getRowData();
		pm.deleteBook(bookToDelete);
		return null;
	}

	// Validators

	// Business logic validation
	public void uniqueSerial(FacesContext context, UIComponent component,
			Object value) {

		String serial = (String) value;

		for (Book book : pm.getAllBooks()) {
			if (book.getSerial().equalsIgnoreCase(serial)) {
				FacesMessage message = new FacesMessage(
						"Book with this serial number already exists in database");
				message.setSeverity(FacesMessage.SEVERITY_ERROR);
				throw new ValidatorException(message);
			}
		}
	}

	// Multi field validation with <f:event>
	// Rule: first two digits of PIN must match last two digits of the year of
	// birth
	public void validateSerialDop(ComponentSystemEvent event) {

		UIForm form = (UIForm) event.getComponent();
		UIInput serial = (UIInput) form.findComponent("serial");
		UIInput dop = (UIInput) form.findComponent("dop");

		if (serial.getValue() != null && dop.getValue() != null
				&& serial.getValue().toString().length() >= 2) {
			String twoDigitsOfSerial = serial.getValue().toString().substring(0, 2);
			Calendar cal = Calendar.getInstance();
			cal.setTime(((Date) dop.getValue()));

			String lastDigitsOfDop = ((Integer) cal.get(Calendar.YEAR))
					.toString().substring(2);

			if (!twoDigitsOfSerial.equals(lastDigitsOfDop)) {
				FacesContext context = FacesContext.getCurrentInstance();
				context.addMessage(form.getClientId(), new FacesMessage(
						"Serial doesn't match date of print"));
				context.renderResponse();
			}
		}
	}
}
