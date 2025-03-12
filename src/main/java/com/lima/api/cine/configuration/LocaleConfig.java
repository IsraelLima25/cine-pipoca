package com.lima.api.cine.configuration;

import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Configuration;

import java.util.Locale;

@Configuration
public class LocaleConfig {

    @PostConstruct
    public void init() {
        Locale.setDefault(new Locale("pt", "BR")); // Define pt-BR como padrão
    }
}
