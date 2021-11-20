package ru.ithub.newspring.factories;

public interface ObjectFactory {

    <T> T createObject(Class<T> implClass);
}
