package com.esprit.microservices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class FournisseurRestApi {

    @Autowired
    private FournisseurService fournisseurService;

    private String title = "Hello, I'm the fournisseur service";

    @RequestMapping("/hello")
    public String sayHello() {
        System.out.println(title);
        return title;
    }

    @PostMapping(consumes = MediaType.APPLICATION_XML_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Fournisseur> createFournisseur(@RequestBody Fournisseur fournisseur) {
        return new ResponseEntity<>(fournisseurService.addFournisseur(fournisseur), HttpStatus.OK);
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Fournisseur> updateFournisseur(@PathVariable(value = "id") Long id,
                                                         @RequestBody Fournisseur fournisseur) {
        return new ResponseEntity<>(fournisseurService.updateFournisseur(id, fournisseur), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> deleteFournisseur(@PathVariable(value = "id") Long id) {
        return new ResponseEntity<>(fournisseurService.deleteFournisseur(id), HttpStatus.OK);
    }

}
