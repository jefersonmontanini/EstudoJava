package MyProject;

import MyProject.domain.entity.Client;
import MyProject.domain.entity.Order;
import MyProject.domain.repository.Clients;
import MyProject.domain.repository.Orders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@SpringBootApplication                              //classe que deve ser inicada por spring
@RestController                                     //classe pode mandar informações para o browser
public class MainApplication {


    @Bean
    public CommandLineRunner init(
            @Autowired Clients clients,
            @Autowired Orders orders

    ) {
        return args -> {
            Client jef = clients.save(new Client( "jeferson"));
//            Client charles = clients.save(new Client("charles"));

//            jef.setName("jheff");                       //altera o valor do client
//            clients.delete(jef);
//            clients.save(jef);                          //salva no BD o valor da variavel alterado

//            Order _order = new Order();
//            _order.setClient_id(jef);
//            _order.setDate_order(LocalDate.now());              //obter data atual
//            _order.setTotal(BigDecimal.valueOf(100));

            Order or = orders.save(new Order(jef, LocalDate.now(), BigDecimal.valueOf(100)));

//            orders.save(_order);


//            Client client = clients.getClientFetchOrder(jef.getId());
//            System.out.println(client);
//            System.out.println(client.getOrders());


            List<Client> listAll = clients.findAll();
            listAll.forEach(System.out::println);

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
