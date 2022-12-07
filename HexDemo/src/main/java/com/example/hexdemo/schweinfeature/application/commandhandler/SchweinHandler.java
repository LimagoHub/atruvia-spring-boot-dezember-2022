package com.example.hexdemo.schweinfeature.application.commandhandler;


import com.example.hexdemo.schweinfeature.adapter.dto.SchweinDTO;
import com.example.hexdemo.schweinfeature.domain.schwein.SchweineServiceException;
import com.example.hexdemo.schweinfeature.domain.schwein.model.Schwein;

public interface SchweinHandler {
    void handleSchweinErfasst(Schwein schwein) throws SchweineServiceException;
    void handleSchweinGeandert(Schwein schwein)throws SchweineServiceException;
    void handleSchweinGeloescht(String id)throws SchweineServiceException;
    void handleGefuettert(String id)throws SchweineServiceException;

}
