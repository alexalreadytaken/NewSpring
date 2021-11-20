package ru.ithub.newspring.configurators;

import ru.ithub.newspring.contexts.ApplicationContext;

public interface ObjectConfigurator {
    void configure(Object instance, ApplicationContext context);
}
