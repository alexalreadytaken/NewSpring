package ru.ithub.newspring.configurators;

import ru.ithub.newspring.contexts.ApplicationContext;

public interface ObjectFiller {
    void fill(Object instance, ApplicationContext context);
}
