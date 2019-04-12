package designpattern.factorymethod;

interface food {
}

class A implements food {
}

class B implements food {
}

class C implements food {
}

/**
 * @author laowu
 */
public class StaticFactory {
    private StaticFactory() {
    }

    public static food getA() {
        return new A();
    }

    public static food getB() {
        return new B();
    }

    public static food getC() {
        return new C();
    }
}

class Client {
    /*客户端代码只需要将相应的参数传入即可得到对象*/
    /*用户不需要了解工厂类内部的逻辑。*/
    public void get(String name) {
        food x = null;
        if ("A".equals(name)) {
            x = StaticFactory.getA();
        } else if ("B".equals(name)) {
            x = StaticFactory.getB();
        } else {
            x = StaticFactory.getC();
        }
    }
}