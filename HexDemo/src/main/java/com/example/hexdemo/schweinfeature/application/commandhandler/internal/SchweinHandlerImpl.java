package com.example.hexdemo.schweinfeature.application.commandhandler.internal;


import com.example.hexdemo.schweinfeature.adapter.dto.SchweinDTO;
import com.example.hexdemo.schweinfeature.application.commandhandler.SchweinHandler;
import com.example.hexdemo.schweinfeature.domain.schwein.SchweinService;
import com.example.hexdemo.schweinfeature.domain.schwein.SchweineServiceException;
import com.example.hexdemo.schweinfeature.domain.schwein.model.Schwein;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.service.spi.ServiceException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = SchweineServiceException.class, propagation = Propagation.REQUIRED)
@RequiredArgsConstructor
@Slf4j
public class SchweinHandlerImpl implements SchweinHandler {

    private final SchweinService service;
    @Override
    public void handleSchweinErfasst(Schwein schwein) {
        try {
            service.speichern(schwein);
            // Erfolgreich erfasst event
        } catch (SchweineServiceException e) {
            // Upps Event
            throw new RuntimeException(e);
        }

    }

    @Override
    public void handleSchweinGeandert(Schwein schwein) throws SchweineServiceException{

    }

    @Override
    public void handleSchweinGeloescht(String id) throws SchweineServiceException{

    }

    @Override
    public void handleGefuettert(String id) throws SchweineServiceException{

    }
}
