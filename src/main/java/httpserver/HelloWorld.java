package httpserver;

public class HelloWorld {
    String greeting;

    public HelloWorld(String test) {
        greeting = test;
    }

    public String sayHello() {
        return greeting;
    }

    public static void main(String[] args) {
        HelloWorld hello = new HelloWorld("What's up");
        System.out.println(hello.sayHello());
    }
}
