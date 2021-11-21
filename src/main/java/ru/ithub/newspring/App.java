package ru.ithub.newspring;

import ru.ithub.newspring.contexts.ApplicationContext;
import ru.ithub.newspring.fortest.ServiceWithConstructor;
import ru.ithub.newspring.starters.impl.JavaApplicationStarter;

public class App {

    public static void main(String[] args) {
        ApplicationContext context = new JavaApplicationStarter().start("ru.ithub.newspring.fortest");
        ServiceWithConstructor serviceWithConstructor = context.getObject(ServiceWithConstructor.class);

        serviceWithConstructor.superHello();
    }
}
