package project;
import java.io.*;
import java.util.ArrayList;

public class BookStore implements Externalizable {
    private static final long serialVersionUID = 1L;
    private String name;
    private ArrayList<Book> books = new ArrayList<>();

    public BookStore() {
    }

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

    public void setName(String name) {
        this.name = name;
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
    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(name);
        out.writeInt(books.size());
        for (Externalizable bs : books)
            bs.writeExternal(out);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        name = (String)in.readObject();
        int quantity = in.readInt();
        for(int i=0; i<quantity; i++){
            Book b = new Book();
            b.readExternal(in);
            books.add(b);
        }
    }
}
