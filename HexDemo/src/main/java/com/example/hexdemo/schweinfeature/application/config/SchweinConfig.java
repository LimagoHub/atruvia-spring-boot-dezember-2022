package com.example.hexdemo.schweinfeature.application.config;


import com.example.hexdemo.schweinfeature.domain.schwein.SchweinService;
import com.example.hexdemo.schweinfeature.domain.schwein.inner.SchweinServiceImpl;
import com.example.hexdemo.schweinfeature.domain.schwein.repository.SchweinRepo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SchweinConfig {

    @Bean
    public SchweinService createKreditantragService(SchweinRepo repo) {
        return new SchweinServiceImpl(repo);
    }
}
