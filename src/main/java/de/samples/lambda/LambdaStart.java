package de.samples.lambda;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.DoubleSupplier;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class LambdaStart {

    static double add1(double d1, double d2) {
        return d1 + d2;
    }

    static double add2() {
        double result = 0;
        while (result <= 10) {
            result += Math.random();
        }
        return result;
    }

    @FunctionalInterface // the compiler ensures that this interface has exactly one abstract method
    interface NumberProvider {
        double provide();
    }

    static double add3(NumberProvider provider) {
        double result = 0;
        while (result <= 10) {
            result += provider.provide();
        }
        return result;
    }

    static double add8(DoubleSupplier provider) {
        double result = 0;
        while (result <= 10) {
            result += provider.getAsDouble();
        }
        return result;
    }

    public static void main(String[] args) {
        /*
         * We'll derive the usage of lambda expressions step-by-step.
         * So let's go!
         */

        // (1) We need a method to add two random numbers
        System.out.println(add1(Math.random(), Math.random()));

        // (2) We need a method that summarizes random double numbers until they reach a value greater than 10
        System.out.println(add2());

        // (3) The decision, that the numbers are determined by Math.random, should be here.
        // Therefore, we need to provide a custom type as parameter and invoke the method using an anonymous class.
        // This type is an interface that has a single abstract method. We call this "Functional Interface".
        System.out.println(add3(new NumberProvider() {
            @Override
            public double provide() {
                return Math.random();
            }
        }));
        // In comparison to (1), we now have a kind of deferred evaluation, because Math.random is invoked after
        // entering the add3 method. add3 has the control, when/if/how often/with which parameters (if ex.)
        // the provide() method is invoked.
        // That's the point, where Lambda expressions join the game!

        // (4) Let's comment out the parts of the code that the compiler could find out by itself (type inference).
        // To match the compiler's requirements, we not need to use a "->" additionally
        System.out.println(add3(/*new NumberProvider() {
            @Override
            public double provide*/() -> {
                return Math.random();
            }
          /*}*/));

        // (5) Let's remove this code
        System.out.println(add3(() -> {
              return Math.random();
          }
        ));
        // Voilà! That's a lambda expression. But we could write it shorter

        // (6) If there's only one statement in the implementation, we can remove the block:
        System.out.println(add3(() -> Math.random()));
        // (7) If the parameters match ("() -> ()"), we can use the Method Reference Operator:
        System.out.println(add3(Math::random));
        // Now, this looks like a reference to a method, that is known from functional programming.
        // Be aware that the method add3 is implemented the object-oriented way. It's only
        // the method invoker that can write it shorter.
        // The Java compiler can now optimize bytecode. While anonymous classes are compiled as separate
        // bytecode, and "new" leaves no leeway - an object must be created at runtime -
        // the lambda expression is compiled to a simple private static funtion - no need to load
        // bytecode and create objects at runtime.

        /*
         * There are functional interfaces declared by Java itself, see the package java.util.function.
         * The 4 most important are: (declaration and usage)
         */

        Supplier<Long> s1 = () -> System.currentTimeMillis();
        s1 = System::currentTimeMillis;
        Long s1Result = s1.get();

        Consumer<String> c1 = text -> System.out.println(text);
        c1 = System.out::println;
        c1.accept("Hello World");

        Function<String, Integer> f1 = text -> Integer.valueOf(text);
        f1 = Integer::valueOf;
        Integer f1Result = f1.apply("10");

        Predicate<String> p1 = text -> text.length() > 3;
        boolean p1Result = p1.test("Hello World");

        // We can use it esp. with Streams (https://www.baeldung.com/java-streams)
        // The following blocks read out short names from a collection and transform it to uppercase.
        Collection<String> names = List.of("Tom", "Julia", "Maximilian", "Jennifer");
        {
            Collection<String> result = new LinkedList<>();
            for(String name : names) {
                if(name.length()<=5) {
                    result.add(name.toUpperCase());
                }
            }
            System.out.println(result);
        }
        {
            Collection<String> result = names.stream() // imagine a stream as a factory band
              .filter(name -> name.length()<=5) // a machine to only let short names through
              .map(String::toUpperCase) // convert the strings to upper case
              .collect(Collectors.toList()); // collect the results at the end
            // Streams are lazy, so filtering and conversion is only done when objects are demanded at the end
            // Lambda expressions are necessary because of deferred evaluation
            System.out.println(result);
        }

        // (8) So let's use a built-in interface for our add method
        System.out.println(add8(Math::random));
        // No difference to add3 for the invoker!

    }

}
