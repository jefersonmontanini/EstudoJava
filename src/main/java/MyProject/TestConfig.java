package MyProject;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestConfig {

    @Bean(name = "ConfiTest")
    public String ConfiTest() {                                             //Bean serve para criar configurações personalizadas----- dependencia STARTER contem as configs defaults
        return "Testando configurações";
    }
}
