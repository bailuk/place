package service;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class HelloWorldService {

    public String sayHelloWorld() {
        return String.format("Hello World!");
    }
}
