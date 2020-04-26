package designpattern.strategy.method;

import designpattern.strategy.Strategy;

/**
 * @author laowu
 * @version 5/6/2019 2:20 PM
 */
public class Sub implements Strategy {
    @Override
    public int run(int a, int b) {
        return a - b;
    }
}
