package de.samples.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotate a method with @Greeting to print a greeting to the console.
 * The method must provide a return value that is converted to a String and placed into
 * the greeting message. It must not have any parameter.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Greeting {

    String value() default "Hello World, my name is {0}.";

}
