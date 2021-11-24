### 1.DI = весь проект
### 2.[singleton](src/main/java/ru/ithub/newspring/contexts/impl/ApplicationContextImpl.java) (components map)
### 3.[proxy maker](src/main/java/ru/ithub/newspring/fillers/impl/LogMethodProxyFiller.java) 
### 4.[factory](src/main/java/ru/ithub/newspring/factories/impl/ObjectFactoryImpl.java)
### 5,6.Пакет [fillers](src/main/java/ru/ithub/newspring/fillers) можно представить как _билдер_ + они используются последовательно решая делать ли что-то, как в цепочке обязанностей 
### 7.Bridge = классы в [forTest](src/main/java/ru/ithub/newspring/forTest) используют друг друга через интерфейсы
