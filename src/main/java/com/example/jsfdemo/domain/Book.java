package com.example.jsfdemo.domain;

import java.util.Date;

import javax.validation.constraints.Min;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class Book {
	
	private String title = "unknown";
	private String kind = "";
	private String serial = "";
	private Date dateOfPrint = new Date();
	private double pages;
	private boolean own;
	private int quantity;
	
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	
	public String getKind() {
		return kind;
	}
	public void setKind(String kind) {
		this.kind = kind;
	}
	
	
	public String getSerial() {
		return serial;
	}
	public void setSerial(String serial) {
		this.serial = serial;
	}
	
	@Min(0)
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	@Past
	public Date getDateOfPrint() {
		return dateOfPrint;
	}
	public void setDateOfPrint(Date dateOfPrint) {
		this.dateOfPrint = dateOfPrint;
	}
	
	public double getPages() {
		return pages;
	}
	public void setPages(double pages) {
		this.pages = pages;
	}
	
	public boolean isOwn() {
		return own;
	}
	public void setOwn(boolean own) {
		this.own = own;
	}
	
}
