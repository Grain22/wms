package designpattern.template;

public class Test extends Operators {
    @Override
    public void begin() {
        System.out.println("begin");
    }

    @Override
    public void then() {
        System.out.println("begin");

    }

    @Override
    public void end() {
        System.out.println("begin");
    }

    public static void main(String[] args) {
        Operators t = new Test();
        t.go();
    }
}
