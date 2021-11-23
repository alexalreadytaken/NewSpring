package ru.ithub.newspring.forTest;

import ru.ithub.newspring.annotations.Component;
import ru.ithub.newspring.annotations.Prop;

import javax.annotation.PostConstruct;

@Component
public class ServiceWithConstructorImpl implements ServiceWithConstructor {

    @Prop(key = "some.property")
    private String propertyText;

    private Service1 service1;
    private Service2 service2;

    public ServiceWithConstructorImpl(Service1 service1, Service2 service2) {
        this.service1 = service1;
        this.service2 = service2;
    }

    public ServiceWithConstructorImpl() {
    }

    @PostConstruct
    public void init(){
        System.err.println("init service with constructor");
    }

    public void showProperty(){
        System.out.println(propertyText);
    }

    public void superHello() {
        service1.someHello();
        service2.hello();
    }
}
