package me.ddongman.reactive.section9.chap3;

public class Book {
    private String isbn;
    private String bookName;
    private String author;

    public Book(String isbn, String bookName, String author) {
        this.isbn = isbn;
        this.bookName = bookName;
        this.author = author;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getBookName() {
        return bookName;
    }

    public String getAuthor() {
        return author;
    }
}
