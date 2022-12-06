package de.atruvia.mywebapp.domain.inner;

import de.atruvia.mywebapp.domain.BlacklistService;
import de.atruvia.mywebapp.domain.model.Person;
import org.springframework.stereotype.Service;

@Service
public class BlackListServiceImpl implements BlacklistService {
    @Override
    public boolean is_blacklisted(Person p) {
        return false;
    }
}
