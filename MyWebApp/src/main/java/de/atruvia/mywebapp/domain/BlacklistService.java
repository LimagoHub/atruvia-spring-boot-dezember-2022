package de.atruvia.mywebapp.domain;

import de.atruvia.mywebapp.domain.model.Person;

public interface BlacklistService {

    boolean is_blacklisted(Person p);
}
