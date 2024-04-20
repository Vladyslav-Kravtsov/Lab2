package project;
import java.io.*;
import java.util.Arrays;

public class Book implements Externalizable {

    private String title;
    private Author[] authors;
    private int year;
    private int edition;

    public Book() {
    }

    public Book(String title, Author[] authors, int year, int edition) {
        this.title = title;
        this.authors = authors;
        this.year = year;
        this.edition = edition;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Author[] getAuthors() {
        return authors;
    }

    public void setAuthors(Author[] authors) {
        this.authors = authors;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getEdition() {
        return edition;
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

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(title);
        out.writeObject(authors);
        out.writeInt(year);
        out.writeInt(edition);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        title = (String) in.readObject();
        authors = (Author[]) in.readObject();
        year = in.readInt();
        edition = in.readInt();
    }
}
