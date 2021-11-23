package ru.ithub.newspring.starters.impl;

import ru.ithub.newspring.configs.Config;
import ru.ithub.newspring.configs.impl.JavaConfig;
import ru.ithub.newspring.contexts.ApplicationContext;
import ru.ithub.newspring.contexts.impl.ApplicationContextImpl;
import ru.ithub.newspring.factories.ObjectFactory;
import ru.ithub.newspring.factories.impl.ObjectFactoryImpl;
import ru.ithub.newspring.starters.ApplicationStarter;

public class JavaApplicationStarter implements ApplicationStarter {

    @Override
    public ApplicationContext start(String packageToScan) {
        Config config = new JavaConfig(packageToScan);
        ApplicationContextImpl context = new ApplicationContextImpl(config);
        ObjectFactory factory = new ObjectFactoryImpl(context);
        context.setObjectFactory(factory);
        return context;
    }
}
