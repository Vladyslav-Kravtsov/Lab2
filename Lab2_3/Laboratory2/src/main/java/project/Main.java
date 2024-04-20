package project;

import java.util.Arrays;
import java.util.Scanner;

import static java.lang.System.out;

public class Main {
    static Scanner in = new Scanner(System.in);
    public static void main(String[] args) {
        Library library = new Library("Central library");
        BookStore firstStore = new BookStore("Comedy");
        BookStore secondStore = new BookStore("Fantastic");
        Author[] firstAuthorList = {new Author("Nikolay","Gogol")};
        Author[] secondAuthorList = {new Author("Ivan","Karpovich")};
        Author[] thirdAuthorList = {new Author("Ray","Bradbury")};
        Book firstBook = new Book("Revisor",firstAuthorList,1835,123);
        Book secondBook = new Book("100 thousands",secondAuthorList,1891,456);
        Book thirdBook = new Book("451 degrees fahrenheit",thirdAuthorList,1953,789);
        firstStore.addBook(firstBook);
        secondStore.addBook(thirdBook);
        firstStore.addBook((secondBook));
        library.addBookStore(firstStore);
        library.addBookStore(secondStore);
        library.addReader(new BookReader("Vladyslav","Kravtsov",111));
        library.addReader(new BookReader("Oleg","Nekrasov",222));
        int choice;
        do{
            out.println("1. Serialization");
            out.println("2. Deserialization");
            out.println("3. Lend book");
            out.println("4. Print list");
            out.println("5. Exit");
            out.print("Your choice: ");
            choice = in.nextInt();
            switch (choice){
                case 1:{
                    LibraryDriver.serializeLibrary(library,"SerFile.dat");
                    out.println();
                    break;
                }
                case 2:{
                    LibraryDriver.deserializeLibrary("SerFile.dat");
                    out.println();
                    break;
                }
                case 3:{
                    library.lendBook("Revisor", 111);
                    library.lendBook("451 degrees fahrenheit", 222);
                    out.println("Books are successfully lent!");
                    out.println();
                    break;
                }
                case 4:{
                    printList(library);
                    out.println();
                    break;
                }
                case 5:{
                    out.println("\nThe program is over!");
                    System.exit(0);
                }
                default: out.println("\nYou have selected a non-existent option. Please select again.\n");
                    break;
            }
        }while(true);
    }
    private static void printList(Library library){
        out.println();
        out.println("Library: " + library.getName());
        for (BookStore bookStore: library.getBookStores()) {
            System.out.println("Store " + bookStore.getName() + ":");
            for (Book book : bookStore.getBooks()) {
                System.out.println("Title: "+ book.getTitle() + "\nAuthors: " + Arrays.toString(book.getAuthors()) +
                        "\nYear: " + book.getYear()+ "\nEdition: " + book.getEdition());
                out.println();
            }
        }
        for (BookReader bookReader: library.getReaders()) {
            out.println();
            System.out.println("Reader: "+ bookReader.getName() + " " + bookReader.getSurname());
            System.out.print(bookReader);
            out.println();
        }
    }
}
