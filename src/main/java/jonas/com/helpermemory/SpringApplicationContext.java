package jonas.com.helpermemory;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

//this class allows to access to out Context so we can access to classes like Authentication that are not a bean 
public class SpringApplicationContext implements ApplicationContextAware {

    private static ApplicationContext CONTEXT;
    
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        CONTEXT = applicationContext;
    }

    public static Object getBean(String beanName) {
        return CONTEXT.getBean(beanName);
    }
}
