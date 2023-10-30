package MyProject.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import java.util.Locale;

@Configuration
public class internacionalizationConfig {

    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();          // Fonte de menssagens dos textos CHAVE/VALOR
        messageSource.setBasename("classpath:messages");                                                            //indicar em qual caminho se encontra o arquivo que contem as informações
        messageSource.setDefaultEncoding("ISO-8859-1");                                                             //Indica qual a formatação dos textos no arquivo encontrado
        messageSource.setDefaultLocale(Locale.getDefault());                                                        //Pega o local padrão de onde roda aplicação
        return messageSource;
    }

    @Bean
    public LocalValidatorFactoryBean ValidatorFactoryBean() {                                                  //Faz a troca dos textos CHAVE/VALOR
        LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
        bean.setValidationMessageSource(messageSource());
        return bean;
    }
}
