package ru.ithub.newspring;

import ru.ithub.newspring.contexts.ApplicationContext;
import ru.ithub.newspring.fortest.Service1;
import ru.ithub.newspring.fortest.Service2;
import ru.ithub.newspring.starters.impl.JavaApplicationStarter;

public class App {

    public static void main(String[] args) {
        ApplicationContext context = new JavaApplicationStarter().start("ru.ithub.newspring.fortest");
        Service2 service2 = context.getObject(Service2.class);
        Service1 service1 = context.getObject(Service1.class);

        service2.hello();
        service1.someHello();
    }
}
