package com.netcracker.ui.swing.main;

import java.util.Objects;

public class Book {
    private String title;
    private Author author;
    private int year;
    private int qty;

    public Book(String title, Author author, int year) {
        this.title = title;
        this.author = author;
        this.year = year;
        this.qty = 1;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public int getFieldsCount() {
        int count = this.getClass().getDeclaredFields().length;
        return count;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return year == book.year &&
                qty == book.qty &&
                title.equals(book.title) &&
                author.equals(book.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, author, year, qty);
    }
}
