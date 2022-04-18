package com.example.listofbadguys.model;

public class Person {
    private String lastName;
    private String comment;

    public Person(String lastName, String comment) {
        this.lastName = lastName;
        this.comment = comment;
    }

    public String getLastName() {
        return lastName;
    }

    public String getComment() {
        return comment;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
