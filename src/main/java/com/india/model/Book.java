package com.india.model;

public class Book {
	private Integer id;
    private String title;
	public Integer getId() {
		return id;
	}
	public String getTitle() {
		return title;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	@Override
	public String toString() {
		return "Book [id=" + id + ", title=" + title + "]";
	}
    

}
