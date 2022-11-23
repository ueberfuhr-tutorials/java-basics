package de.samples.annotations;

public class GreetingStart {

    public static void main(String[] args) {
        GreetingFramework framework = new GreetingFramework();
        Person john = new Person("John", "Doe");
        Person julia = new Person("Julia", "Acme");
        framework.greet(john);
        framework.greet(julia);
    }

}
