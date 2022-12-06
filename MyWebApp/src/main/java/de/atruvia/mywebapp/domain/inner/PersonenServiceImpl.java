package de.atruvia.mywebapp.domain.inner;


import de.atruvia.mywebapp.domain.BlacklistService;
import de.atruvia.mywebapp.domain.PersonenService;
import de.atruvia.mywebapp.domain.PersonenServiceException;
import de.atruvia.mywebapp.domain.mapper.PersonMapper;
import de.atruvia.mywebapp.domain.model.Person;
import de.atruvia.mywebapp.repositories.PersonenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = PersonenServiceException.class, isolation = Isolation.READ_COMMITTED)
public class PersonenServiceImpl implements PersonenService {

    private final PersonenRepository repo;
    private final PersonMapper mapper;

    //private final BlacklistService blacklistService;

    private final List<String> antipathen;

    public PersonenServiceImpl(PersonenRepository repo, PersonMapper mapper, @Qualifier("antipathen") List<String> antipathen) {
        this.repo = repo;
        this.mapper = mapper;
        this.antipathen = antipathen;
    }

    /*
                person null -> PSE
                vorname null vorname kleiner 2 -> PSE
                nachname null nachname kleiner 2 -> PSE

                Attila -> PSE

                technische Exception -> PSE

                happy Day -> person to repo
         */
    @Override
    public void speichern(Person person) throws PersonenServiceException {
        try {
            speichernImpl(person);
        } catch (RuntimeException e) {
            throw new PersonenServiceException("Fehler im service", e);
        }

    }

    private void speichernImpl(Person person) throws PersonenServiceException {
        checkPerson(person);
        if(repo.existsById(person.getId()))
            throw new PersonenServiceException("Person existiert bereits");
        repo.save(mapper.convert(person));
    }

    private void checkPerson(Person person) throws PersonenServiceException {
        validate(person);
        businessCheck(person);
    }

    private void businessCheck(Person person) throws PersonenServiceException {
        if(antipathen.contains(person.getVorname()))
            throw new PersonenServiceException("Antipath");

    }

    private static void validate(Person person) throws PersonenServiceException {
        if(person == null)
            throw new PersonenServiceException("Parameter darf nicht null sein");

        if(person.getVorname() == null || person.getVorname().length() < 2)
            throw new PersonenServiceException("Vorname zu kurz");

        if(person.getNachname() == null || person.getNachname().length() < 2)
            throw new PersonenServiceException("Nachname zu kurz");
    }

    @Override
    public void aendern(Person person) throws PersonenServiceException {
        checkPerson(person);
        if(! repo.existsById(person.getId()))
            throw new PersonenServiceException("Person existiert noch nicht");
        repo.save(mapper.convert(person));
    }

    @Override
    public boolean loeschen(String id) throws PersonenServiceException {
        try {
            if(repo.existsById(id)) {
                repo.deleteById(id);
                return true;
            }
            return false;
        } catch (RuntimeException e) {
            throw new PersonenServiceException("Fehler im service", e);
        }
    }

    @Override
    @Transactional( isolation = Isolation.READ_UNCOMMITTED)
    public Optional<Person> findeNachId(String id) throws PersonenServiceException {
        try {
            return repo.findById(id).map(mapper::convert);
        } catch (RuntimeException e) {
            throw new PersonenServiceException("Fehler im service", e);
        }

    }

    @Override
    public Iterable<Person> findeAlle() throws PersonenServiceException {
        try {
            return mapper.convert(repo.findAll());
        } catch (RuntimeException e) {
            throw new PersonenServiceException("Fehler im service", e);
        }
    }
}
