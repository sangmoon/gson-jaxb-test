package com.tmax.sangmoon;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import com.google.gson.annotations.SerializedName;

@XmlAccessorType(XmlAccessType.FIELD)
public class Catalog {
	
	@XmlElement(name="book")
	private List<Book> books;
	
	public Catalog() {
		this.books = new ArrayList<>();
	}
	
	public List<Book> getBooks() {
		return books;
	}
	
	public void setBooks(List<Book> books) {
		this.books = books;
	}
	
}
