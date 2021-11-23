package ru.ithub.newspring.fillers;

import ru.ithub.newspring.contexts.ApplicationContext;

public interface ObjectFiller {
    void fill(Object instance, ApplicationContext context);
}
