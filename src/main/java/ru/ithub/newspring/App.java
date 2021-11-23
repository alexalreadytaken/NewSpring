package ru.ithub.newspring;

import ru.ithub.newspring.contexts.ApplicationContext;
import ru.ithub.newspring.forTest.ServiceWithBigMethod;
import ru.ithub.newspring.forTest.ServiceWithConstructor;
import ru.ithub.newspring.starters.impl.JavaApplicationStarter;

public class App {

    public static void main(String[] args) {
        ApplicationContext context = new JavaApplicationStarter().start("ru.ithub.newspring.forTest");
        ServiceWithConstructor serviceWithConstructor = context.getObject(ServiceWithConstructor.class);
        ServiceWithBigMethod serviceWithBigMethod = context.getObject(ServiceWithBigMethod.class);

        serviceWithConstructor.superHello();
        serviceWithConstructor.showProperty();
        System.out.println();
        serviceWithBigMethod.businessLogic("Name",10);
    }

}
