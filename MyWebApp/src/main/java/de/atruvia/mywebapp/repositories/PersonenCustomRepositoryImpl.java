package de.atruvia.mywebapp.repositories;

import de.atruvia.mywebapp.repositories.entities.PersonEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

@Repository
@Transactional
public class PersonenCustomRepositoryImpl implements PersonenCustomRepository{

    @Autowired
    private EntityManager em;

    @Override
    public void peter(PersonEntity personEntity) {

        em.persist(personEntity);
    }
}
