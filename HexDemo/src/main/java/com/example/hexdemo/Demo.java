package com.example.hexdemo;


import com.example.hexdemo.schweinfeature.application.commandhandler.SchweinHandler;

import javax.inject.Inject;
import javax.inject.Named;

@Named
public class Demo {

    @Inject
    private SchweinHandler handler;

    public Demo() {
        System.out.println("Ctor Demo");
    }
}
