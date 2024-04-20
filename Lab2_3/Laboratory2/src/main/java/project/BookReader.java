package project;
import java.io.*;
import java.util.ArrayList;

public class BookReader extends Human implements Externalizable {

    private int id;
    private ArrayList<Book> books = new ArrayList<>();

    public BookReader() {
        super();
    }

    public BookReader(String name, String surName, int id) {
        super(name, surName);
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<Book> getBooks() {
        return books;
    }

    public void setBooks(ArrayList<Book> books) {
        this.books = books;
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public void removeBook(Book book) {
        books.remove(book);
    }

    @Override
    public String toString() {
        return "Id: " + id +
                " Books: " + books;
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        super.writeExternal(out);
        out.writeInt(id);
        out.writeObject(books);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        super.readExternal(in);
        id = in.readInt();
        books = (ArrayList<Book>) in.readObject();
    }
}
