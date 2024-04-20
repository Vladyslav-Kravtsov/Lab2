package project;

import java.io.Serializable;

class Author extends Human implements Serializable {
    public Author(String name, String surname) {
        super(name, surname);
    }
}
