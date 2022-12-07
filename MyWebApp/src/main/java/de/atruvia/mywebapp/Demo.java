package de.atruvia.mywebapp;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.Duration;
import java.time.Instant;
import java.util.List;

@Component

public class Demo {
    private final List<String> slf;

    public Demo(@Qualifier("stadtLandFluss") List<String> slf) {
        this.slf = slf;
    }

    @PostConstruct
    public void go() {
        var start = Instant.now();
        System.out.println(slf);
        var end = Instant.now();
        Duration duration = Duration.between(start,end);
        System.out.printf("Duration = %s ms.\n", duration.toMillis());
    }
}
