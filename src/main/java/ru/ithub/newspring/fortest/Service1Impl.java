package ru.ithub.newspring.fortest;

import ru.ithub.newspring.annotations.Component;
import ru.ithub.newspring.annotations.Inject;

@Component
public class Service1Impl implements Service1{

    @Inject
    private Service2 service2;

    @Override
    public void someHello() {
        System.out.println("Service 1 hello");
        service2.hello();
    }
}
