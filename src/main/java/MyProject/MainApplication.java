package MyProject;

import MyProject.domain.entity.Client;
import MyProject.domain.repository.Clients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@SpringBootApplication                              //classe que deve ser inicada por spring
@RestController                                     //classe pode mandar informações para o browser
public class MainApplication {


    @Bean
    public CommandLineRunner inti(@Autowired Clients clients) {
        return args -> {
            Client client1 = new Client();
            client1.setName("jeff");
            clients.save(client1);

            clients.save(new Client("charles"));

            List<Client> listed = clients.list();
            listed.forEach(System.out::println);
        };
    }

//    @Autowired
//    @Qualifier("ConfiTest")
    @Value("${application.name}")
    private String configTest;


    @GetMapping("/hello")
    public String start() {
        System.out.println(configTest);
        return configTest;
    }
    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);                 //função que inicia aplicação
    }
}
