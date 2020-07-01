package nhanam.bean;

import nhanam.entity.Book;

public class Product {
	private Book book;
	private int soluong;
	
	public Product() {
	}

	public Product(Book book, int soluong) {
		this.book = book;
		this.soluong = soluong;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public int getSoluong() {
		return soluong;
	}

	public void setSoluong(int soluong) {
		this.soluong = soluong;
	}
	
	
	
}
