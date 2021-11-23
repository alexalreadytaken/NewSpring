package ru.ithub.newspring.forTest;

import ru.ithub.newspring.annotations.Component;

@Component
public class Service2Impl implements Service2{
    @Override
    public void hello() {
        System.out.println("Service 2 hello");
    }
}
