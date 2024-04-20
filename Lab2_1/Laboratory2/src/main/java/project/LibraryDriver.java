package project;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class LibraryDriver implements Serializable{
    public static void serializeLibrary(Library library, String fileName) {
        try {
            FileOutputStream fileOut = new FileOutputStream(fileName);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(library);
            out.close();
            fileOut.close();
            System.out.println("Serialization to file " + fileName + " completed successfully");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Library deserializeLibrary(String fileName) {
        Library library = null;
        try (ObjectInputStream ois = new ObjectInputStream(Files.newInputStream(Paths.get(fileName)))) {
            library = (Library) ois.readObject();
            System.out.println("Deserialization from file " + fileName + " completed successfully");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("An error occurred while deserializing from a file " + fileName);
        }
        return library;
    }
}
