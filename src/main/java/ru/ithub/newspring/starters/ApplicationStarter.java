package ru.ithub.newspring.starters;

import ru.ithub.newspring.contexts.ApplicationContext;

public interface ApplicationStarter {

    ApplicationContext start(String packageToScan);

}
