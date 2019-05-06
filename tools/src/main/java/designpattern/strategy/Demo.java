package designpattern.strategy;

import designpattern.strategy.method.Add;
import designpattern.strategy.method.Sub;

public class Demo {
    public static void main(String[] args) {
        Context context = new Context(new Add());
        System.out.println(context.execute(1, 2));
        context = new Context(new Sub());
        System.out.println(context.execute(1, 2));
    }
}
