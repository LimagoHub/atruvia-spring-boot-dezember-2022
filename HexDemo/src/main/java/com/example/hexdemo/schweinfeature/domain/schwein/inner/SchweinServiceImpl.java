package com.example.hexdemo.schweinfeature.domain.schwein.inner;



import com.example.hexdemo.schweinfeature.domain.schwein.SchweinService;
import com.example.hexdemo.schweinfeature.domain.schwein.SchweineServiceException;
import com.example.hexdemo.schweinfeature.domain.schwein.model.Schwein;
import com.example.hexdemo.schweinfeature.domain.schwein.repository.SchweinRepo;
import lombok.RequiredArgsConstructor;



import java.util.Optional;

@RequiredArgsConstructor
public class SchweinServiceImpl implements SchweinService {

    private final SchweinRepo repo;
    @Override
    public void speichern(Schwein schwein) throws SchweineServiceException {
        try {
            speichernImpl(schwein);
        } catch (RuntimeException e) {
            throw new SchweineServiceException("Fehler im service", e);
        }

    }

    private void speichernImpl(Schwein schwein) throws SchweineServiceException {

        if(repo.existsById(schwein.getId()))
            throw new SchweineServiceException("Schwein existiert bereits");
        repo.save(schwein);
    }


    @Override
    public void aendern(Schwein schwein) throws SchweineServiceException {

        if(! repo.existsById(schwein.getId()))
            throw new SchweineServiceException("Schwein existiert noch nicht");
        repo.save(schwein);
    }

    @Override
    public boolean loeschen(String id) throws SchweineServiceException {
        try {
            if(repo.existsById(id)) {
                repo.deleteById(id);
                return true;
            }
            return false;
        } catch (RuntimeException e) {
            throw new SchweineServiceException("Fehler im service", e);
        }
    }

    @Override

    public Optional<Schwein> findeNachId(String id) throws SchweineServiceException {
        try {
            return repo.findById(id);
        } catch (RuntimeException e) {
            throw new SchweineServiceException("Fehler im service", e);
        }

    }

    @Override
    public Iterable<Schwein> findeAlle() throws SchweineServiceException {
        try {
            return repo.findAll();
        } catch (RuntimeException e) {
            throw new SchweineServiceException("Fehler im service", e);
        }
    }

    @Override
    public boolean fuettern(String id) throws SchweineServiceException {
        try {
            var schweinOptional = findeNachId(id);
            if( schweinOptional.isEmpty()) return false;
            var schwein = schweinOptional.get();
            schwein.fuettern();
            aendern(schwein);
            return true;
        } catch (RuntimeException e) {
            throw new SchweineServiceException("Fehler im service", e);
        }
    }
}
