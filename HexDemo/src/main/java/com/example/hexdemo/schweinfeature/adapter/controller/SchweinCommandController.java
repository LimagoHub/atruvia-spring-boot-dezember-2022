package com.example.hexdemo.schweinfeature.adapter.controller;


import com.example.hexdemo.schweinfeature.adapter.dto.SchweinDTO;
import com.example.hexdemo.schweinfeature.adapter.mapper.SchweinDtoMapper;
import com.example.hexdemo.schweinfeature.application.commandhandler.SchweinHandler;
import com.example.hexdemo.schweinfeature.domain.schwein.model.Schwein;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SchweinCommandController {

    private final SchweinHandler handler;
    private final SchweinDtoMapper mapper;

    public void speichern(SchweinDTO dto) throws Exception{
        handler.handleSchweinErfasst(mapper.convert(dto));
    }

}
