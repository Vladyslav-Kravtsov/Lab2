package main;

import java.util.ArrayList;

class BookReader extends Human {
    private int id;
    private ArrayList<Book> books;

    public BookReader(String name, String surname, int id) {
        super(name, surname);
        this.id = id;
        this.books = new ArrayList<Book>();
    }

    public int getId() {
        return id;
    }

    public ArrayList<Book> getBooks() {
        return books;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setBooks(ArrayList<Book> books) {
        this.books = books;
    }

    public void addBook(Book book) {
        books.add(book);
    }
    @Override
    public String toString() {
        return "Id: " + id +
                " Books: " + books;
    }
}
