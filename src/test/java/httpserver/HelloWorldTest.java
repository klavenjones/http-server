package httpserver;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class HelloWorldTest {
    @Test
    void shouldShowSimpleAssertion() {
        HelloWorld helloWorld = new HelloWorld("Hello World");
        String greeting = helloWorld.sayHello();
        Assertions.assertEquals("Hello World", greeting);
    }
}
