package de.atruvia.consoledemo;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(10)
@RequiredArgsConstructor
public class MyConsoleRunner implements CommandLineRunner {


    private final Demo demo;



    @Override
    public void run(String... args) throws Exception {
        System.out.println(demo);
        System.out.println("Hallo Runner");
    }
}
