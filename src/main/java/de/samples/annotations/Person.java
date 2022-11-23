package de.samples.annotations;

public class Person {

    private final String firstName;
    private final String lastName;

    public Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    @Greeting
    public String getGreetingName() {
        return getFirstName() + " " + getLastName();
    }

    @Greeting("Hi, I'm {0}, and I proudly present annotations with Java!")
    public String getGreetingNameWithCustomMessage() {
        return getFirstName() + " " + getLastName();
    }

}
