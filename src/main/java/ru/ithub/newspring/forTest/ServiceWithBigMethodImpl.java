package ru.ithub.newspring.forTest;

import ru.ithub.newspring.annotations.Component;
import ru.ithub.newspring.annotations.LogMethods;

@Component
@LogMethods
public class ServiceWithBigMethodImpl implements ServiceWithBigMethod {

    public String businessLogic(String name, Integer age) {
        return name.repeat(age);
    }
}
