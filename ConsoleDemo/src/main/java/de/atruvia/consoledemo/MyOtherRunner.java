package de.atruvia.consoledemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;


@Component
@Order(1)
public class MyOtherRunner implements CommandLineRunner {

    @Autowired
    private Demo demo;


    @Override
    public void run(String... args) throws Exception {
        System.out.println(demo);
        System.out.println("Hallo Other");
    }
}
