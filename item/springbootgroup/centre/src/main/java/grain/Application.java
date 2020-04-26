package grain;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationBeanNameGenerator;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.util.Assert;

/**
 * @author wulifu
 */
@SpringBootApplication
@ComponentScan(nameGenerator = FullNameGenerator.class)
public class Application {
    public static void run(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}

class FullNameGenerator extends AnnotationBeanNameGenerator {
    @Override
    protected String buildDefaultBeanName(BeanDefinition definition) {
        String beanClassName = definition.getBeanClassName();
        Assert.state(beanClassName != null, "No bean class name set");
        return beanClassName;
    }
}
