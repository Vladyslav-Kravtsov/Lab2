package main;

import java.io.*;
import java.util.ArrayList;

class Library implements Serializable {
    private String name;
    private ArrayList<BookStore> bookStores;
    transient private ArrayList<BookReader> readers;

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
    private void writeObject(java.io.ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
        out.writeInt(readers.size());
        for (BookReader reader : readers) {
            out.writeInt(reader.getId());
            out.writeInt(reader.getBooks().size());
            for (Book book : reader.getBooks()) {
                out.writeObject(book.getTitle());
                out.writeInt(book.getYear());
                out.writeInt(book.getEdition());
                out.writeInt(book.getAuthors().length);
                for (Author author : book.getAuthors()) {
                    out.writeObject(author.getName());
                    out.writeObject(author.getSurname());
                }
            }
            out.writeObject(reader.getName());
            out.writeObject(reader.getSurname());
        }
    }

    private void readObject(java.io.ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        int numReaders = in.readInt();
        readers = new ArrayList<>(numReaders);
        for (int i = 0; i < numReaders; i++) {
            int readerId = in.readInt();
            int numBooks = in.readInt();
            ArrayList<Book> books;
            books = new ArrayList<>(numBooks);
            for (int j = 0; j < numBooks; j++) {
                String title = (String) in.readObject();
                int publicationYear = in.readInt();
                int publicationNumber = in.readInt();
                int numAuthors = in.readInt();
                Author[] authors = new Author[numAuthors];
                for (int k = 0; k < numAuthors; k++) {
                    String firstName = (String) in.readObject();
                    String lastName = (String) in.readObject();
                    authors[j] = new Author(firstName, lastName);
                }
                Book book = new Book(title, authors, publicationYear, publicationNumber);
                books.add(book);
                String firstName = (String) in.readObject();
                String lastName = (String) in.readObject();
                BookReader reader = new BookReader(firstName, lastName, readerId);
                reader.setBooks(books);
                readers.add(reader);
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
