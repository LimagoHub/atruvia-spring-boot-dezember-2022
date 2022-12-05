package de.atruvia.consoledemo;

import de.atruvia.consoledemo.translator.Translator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
@Scope(BeanDefinition.SCOPE_SINGLETON) // Default
@Lazy
public class Demo {

    @Autowired
    //@Qualifier("upper")
    private  Translator translator;

//    public Demo(@Qualifier("upper") Translator translator) {
//        this.translator = translator;
//    }

    public Demo() {
        System.out.println("Ctor Demo");
    }

    @PostConstruct
    public void init() {
        System.out.println(translator.translate("Init"));
    }

    @PreDestroy // funktioniert nur bei Singleton
    public void fertig() {
        System.out.println("Habe fertig");
    }


}
