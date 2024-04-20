package main;

import java.util.Arrays;

class Book {
    private String title;
    private Author[] authors;
    private int year;
    private int edition;

    public Book(String title, Author[] authors, int year, int edition) {
        this.title = title;
        this.authors = authors;
        this.year = year;
        this.edition = edition;
    }

    public String getTitle() {
        return title;
    }

    public Author[] getAuthors() {
        return authors;
    }

    public int getYear() {
        return year;
    }

    public int getEdition() {
        return edition;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthors(Author[] authors) {
        this.authors = authors;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setEdition(int edition) {
        this.edition = edition;
    }

    @Override
    public String toString() {
        return "Title: " + title +
                "\nAuthors: " + Arrays.toString(authors) +
                "\nYear: " + year +
                "\nEdition: " + edition;
    }
}
