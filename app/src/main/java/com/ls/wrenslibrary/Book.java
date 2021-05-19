package com.ls.wrenslibrary;

public class Book {

    private int id;
    private String title;
    private String author;
    private String genre;
    private String isbn;
    private String datePublished;
    private String dateCreated;
    private String cover;

    public Book(int id, String title, String author, String genre, String isbn, String datePublished, String dateCreated, String cover) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.isbn = isbn;
        this.datePublished = datePublished;
        this.dateCreated = dateCreated;
        this.cover = cover;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getGenre() {
        return genre;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getDatePublished() {
        return datePublished;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public String getCover() {
        return cover;
    }
}
