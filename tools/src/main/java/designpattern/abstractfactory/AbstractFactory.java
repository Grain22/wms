package designpattern.abstractfactory;

interface food {
}

class A implements food {
}

class B implements food {
}

interface produce {
    food get();
}

class FactoryForA implements produce {
    @Override
    public food get() {
        return new A();
    }
}

class FactoryForB implements produce {
    @Override
    public food get() {
        return new B();
    }
}

/**
 * @author laowu
 */
public class AbstractFactory {
    public void ClientCode(String name) {
        food x = new FactoryForA().get();
        x = new FactoryForB().get();
    }
}