package MyProject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication                              //classe que deve ser inicada por spring
@RestController                                     //classe pode mandar informações para o browser
public class MainApplication {

//    @Autowired
//    @Qualifier("ConfiTest")
    @Value("${application.name}")
    private String configTest;


    @GetMapping
    public String start() {
        System.out.println(configTest);
        return configTest;
    }
    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);                 //função que inicia aplicação
    }
}
