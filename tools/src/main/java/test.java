import lombok.Data;
import lombok.extern.log4j.Log4j2;

import java.util.Arrays;

/**
 * @author laowu
 * @version 5/10/2019 12:06 PM
 */
@Log4j2
public class test {

    public static void main(String[] args) {

    }



    //作用域问题
    public static void test0001(String[] args) {
        tem a = new tem();
        temm b = new temm();
        System.out.println(a.b);
        System.out.println(b.b);
        a.change(b.b);
        System.out.println(a.b);
        System.out.println(b.b);
    }
}

@Data
class tem {
    public int b = 0;

    public void change(int a) {
        b = a;
    }
}

class temm extends tem {
    public int b = 20;

    public void change(tem tem) {
        b = 30;
    }
}
