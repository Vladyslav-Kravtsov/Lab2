package project;

import java.io.Serializable;

abstract class Human implements Serializable {
    protected String name;
    protected String surname;

    public Human(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }
    @Override
    public String toString() {
        return "Name: " + name +
                " Surname: " + surname;
    }
}