package project;

import java.io.Serializable;
import java.util.ArrayList;

class Library implements Serializable {
    private String name;
    private ArrayList<BookStore> bookStores;
    private ArrayList<BookReader> readers;

    public Library(String name) {
        this.name = name;
        this.bookStores = new ArrayList<BookStore>();
        this.readers = new ArrayList<BookReader>();
    }

    public String getName() {
        return name;
    }

    public ArrayList<BookStore> getBookStores() {
        return bookStores;
    }

    public ArrayList<BookReader> getReaders() {
        return readers;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBookStores(ArrayList<BookStore> bookStores) {
        this.bookStores = bookStores;
    }

    public void setReaders(ArrayList<BookReader> readers) {
        this.readers = readers;
    }

    public void addBookStore(BookStore bookStore) {
        bookStores.add(bookStore);
    }

    public void addReader(BookReader reader) {
        readers.add(reader);
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
}
