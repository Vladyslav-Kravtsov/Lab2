package main;

import java.io.*;
import java.util.ArrayList;

class BookStore implements Serializable {
    private String name;
    transient private ArrayList<Book> books;

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
    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
        out.writeInt(books.size());
        for (Book book : books) {
            out.writeObject(book.getTitle());
            out.writeInt(book.getYear());
            out.writeInt(book.getEdition());
            out.writeInt(book.getAuthors().length);
            for (Author author : book.getAuthors()) {
                out.writeObject(author.getName());
                out.writeObject(author.getSurname());
            }
        }
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        int numBooks = in.readInt();
        books = new ArrayList<>(numBooks);
        for (int i = 0; i < numBooks; i++) {
            String title = (String) in.readObject();
            int publicationYear = in.readInt();
            int publicationNumber = in.readInt();
            int numAuthors = in.readInt();
            Author[] authors = new Author[numAuthors];
            for (int j = 0; j < numAuthors; j++) {
                String firstName = (String) in.readObject();
                String lastName = (String) in.readObject();
                authors[j] = new Author(firstName, lastName);
            }
            Book book = new Book(title, authors, publicationYear, publicationNumber);
            books.add(book);
        }
    }

    @Override
    public String toString() {
        return "Name: " + name +
                "\nBooks: " + books;
    }
}
