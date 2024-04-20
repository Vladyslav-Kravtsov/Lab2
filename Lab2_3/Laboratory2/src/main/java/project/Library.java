package project;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Library implements Externalizable {
    private static final long serialVersionUID = 1L;
    private String name;
    private ArrayList<BookStore> bookStores = new ArrayList<>();
    private ArrayList<BookReader> readers = new ArrayList<>();

    public Library() {
    }

    public Library(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    public void setName(String name){
        this.name=name;
    }
    public void addBookStore(BookStore bookStore) {
        bookStores.add(bookStore);
    }

    public void removeBookStore(BookStore bookStore) {
        bookStores.remove(bookStore);
    }

    public List<BookStore> getBookStores() {
        return bookStores;
    }

    public void addReader(BookReader reader) {
        readers.add(reader);
    }

    public void removeReader(BookReader reader) {
        readers.remove(reader);
    }

    public List<BookReader> getReaders() {
        return readers;
    }

    public void setBookStores(List<BookStore> bookStores) {
        this.bookStores = (ArrayList<BookStore>) bookStores;
    }

    public void setReaders(List<BookReader> readers) {
        this.readers = (ArrayList<BookReader>) readers;
    }

    public void lendBook(String titleBook, int idReader) {
        Book book = null;
        BookReader reader = null;
        for (BookStore store : bookStores) {
            book = store.searchByTitle(titleBook);
            if (book != null) {
                break;
            }
        }
        if (book == null) {
            System.out.println("Book not found.");
            return;
        }
        for (BookReader r : readers) {
            if (r.getId() == idReader) {
                reader = r;
                break;
            }
        }
        if (reader == null) {
            System.out.println("Reader not found.");
            return;
        }
        reader.addBook(book);
        for (BookStore store : bookStores) {
            Book foundBook = store.searchByTitle(book.getTitle());
            if (foundBook != null) {
                store.removeBook(foundBook);
                break;
            }
        }

    }

    @Override
    public String toString() {
        return "Name: " + name +
                "\nBook Store: " + bookStores +
                "\nReaders: " + readers;
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(name);
        out.writeInt(bookStores.size());
        for (Externalizable ext : bookStores)
            ext.writeExternal(out);
        out.writeInt(readers.size());
        for (Externalizable ext : readers)
            ext.writeExternal(out);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        name = (String) in.readObject();
        int quantity = in.readInt();
        for(int i=0; i<quantity; i++){
            BookStore bs = new BookStore();
            bs.readExternal(in);
            bookStores.add(bs);
        }
        int quantityy = in.readInt();
        for(int i=0; i<quantityy; i++){
            BookReader bs = new BookReader();
            bs.readExternal(in);
            readers.add(bs);
        }
    }
}
