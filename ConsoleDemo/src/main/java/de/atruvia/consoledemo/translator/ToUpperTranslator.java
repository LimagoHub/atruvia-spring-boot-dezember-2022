package de.atruvia.consoledemo.translator;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
//@Qualifier("upper")
@Profile("prod")
public class ToUpperTranslator implements Translator{
    @Override
    public String translate(String message) {
        return message.toUpperCase();
    }
}
