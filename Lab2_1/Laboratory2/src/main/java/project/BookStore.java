package project;

import java.io.Serializable;
import java.util.ArrayList;

class BookStore implements Serializable {
    private String name;
    private ArrayList<Book> books;

    public BookStore(String name) {
        this.name = name;
        this.books = new ArrayList<Book>();
    }

    public String getName() {
        return name;
    }

    public ArrayList<Book> getBooks() {
        return books;
    }

    public void addBook(Book book) {
        books.add(book);
    }
    public void removeBook(Book book) {
        books.remove(book);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBooks(ArrayList<Book> books) {
        this.books = books;
    }
    public Book searchByTitle(String title){
        for (Book item: books) {
            if(item.getTitle().equals(title)){
                return item;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "Name: " + name +
                "\nBooks: " + books;
    }
}
