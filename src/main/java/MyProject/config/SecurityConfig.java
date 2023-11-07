//package MyProject.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.Customizer;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//public class SecurityConfig {
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    @Bean
//    public InMemoryUserDetailsManager userDetailsService() {                                                            //autenticações do usuario e colocar usuario dentro do security
//        UserDetails user = User.builder()
//                .username("user")
//                .password(passwordEncoder().encode("123"))
//                .roles("USER", "ADMIN")
//                .build();
//        System.out.println("InMemoryUserDetailsManager");
//        System.out.println(user);
//        return new InMemoryUserDetailsManager(user);
//    }
//
//
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {                                        //PERMISSOES, verifica se usuario autenticado tem permissão para fazer ação
//        HttpSecurity user = http.authorizeHttpRequests(authorize -> authorize
////                        MatchUrl(authorize,"/client", "USER")
////                        .requestMatchers(new AntPathRequestMatcher("/client")).hasRole("")
////                        .requestMatchers(new AntPathRequestMatcher("/order")).hasAnyRole("USER", "ADMIN")
////                        .requestMatchers(new AntPathRequestMatcher("/product")).hasRole("ADMIN")
////                                .requestMatchers(new AntPathRequestMatcher("/hello")).hasRole("USER")
//                                .anyRequest().hasRole("USER")
//                )                                                                                                       //AUTHENTICATED qualquer autenticação passa
////                .formLogin(Customizer.withDefaults())                                                                 //Cria o formulario de login
//                .httpBasic(Customizer.withDefaults());
//        System.out.println("Validador");
//        System.out.println(user);
//        return http.build();
//
//    }
//
//
////    AuthorizeHttpRequestsConfigurer.AuthorizationManagerRequestMatcherRegistry extends org.springframework.security.config.annotation.web.AbstractRequestMatcherRegistry<AuthorizeHttpRequestsConfigurer.AuthorizedUrl>
//
////
////    private AuthorizeHttpRequestsConfigurer.AuthorizationManagerRequestMatcherRegistry MatchUrl(
////            AuthorizeHttpRequestsConfigurer.AuthorizationManagerRequestMatcherRegistry authorize,
////            String url,
////            String role
////    ) {
////        authorize.requestMatchers(new AntPathRequestMatcher(url))
////        return authorize;
////    }
//
//}