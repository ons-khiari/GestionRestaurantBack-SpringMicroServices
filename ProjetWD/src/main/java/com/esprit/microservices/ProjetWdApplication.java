package com.esprit.microservices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class ProjetWdApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProjetWdApplication.class, args);
    }

    @Autowired
    private FournisseurRepository fournisseurRepository;


   @Bean
    ApplicationRunner init(){
       return (args ) ->{
         fournisseurRepository.save(new Fournisseur("ben aissa","ons","Rades","bla@gmail.com")) ;

       fournisseurRepository.findAll().forEach(System.out::println);
       };
   }
}
