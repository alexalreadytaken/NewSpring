package ru.ithub.newspring.fortest;

import ru.ithub.newspring.annotations.Component;

@Component
public class ServiceWithConstructorImpl implements ServiceWithConstructor {

    private Service1 service1;
    private Service2 service2;

    public ServiceWithConstructorImpl(Service1 service1, Service2 service2) {
        this.service1 = service1;
        this.service2 = service2;
    }

    public ServiceWithConstructorImpl() {
    }

    public void superHello() {
        service1.someHello();
        service2.hello();
    }
}
