package service;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class HelloService {


    public String sayHello(String name) {
        return String.format("Hello %s!", name);
    }


    public String sayHelloWorld() {
        return sayHello("World");
    }
}
