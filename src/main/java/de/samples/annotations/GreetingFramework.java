package de.samples.annotations;

import java.lang.reflect.Method;

public class GreetingFramework {

    public void greet(Object source) {
        try {
            // analyze the object's class using reflection
            for (Method method : source.getClass().getMethods()) {
                // get the annotation, if existing
                Greeting greeting = method.getAnnotation(Greeting.class);
                if (null != greeting) {
                    // invoke the method
                    Object resultOfMethod = method.invoke(source);
                    // convert to String (null --> "null")
                    String name = String.valueOf(resultOfMethod);
                    // create greeting message
                    String message = greeting.value().replace("{0}", name);
                    // output the message
                    System.out.println(message);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e); // quick & dirty!
        }
    }

}
