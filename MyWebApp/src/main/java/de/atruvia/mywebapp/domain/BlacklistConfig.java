package de.atruvia.mywebapp.domain;



import de.atruvia.mywebapp.domain.inner.PersonenServiceImpl;
import de.atruvia.mywebapp.repositories.PersonenRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class BlacklistConfig {

    @Bean
    @Qualifier("antipathen")
    public List<String>  doofeLeute() {
        return List.of("Attila", "Peter","Paul","Mary");
    }

    @Bean
    @Qualifier("fruechte")
    public List<String>  fruits() {
        return List.of("Banana", "Cherry","Strawberry","Raspberry");
    }

//    @Bean
//    public PersonenService createPersonenService(PersonenRepository repo, PersonMapper mapper, @Qualifier("antipathen") List<String> antipathen) {
//        return new PersonenServiceImpl(repo, mapper, antipathen);
//    }

}
