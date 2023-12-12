package com.esprit.microservices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/fournisseur")
public class FournisseurRestApi {

    @Autowired
    private FournisseurService fournisseurService;

    private String title = "Hello, I'm the fournisseur service";

    @RequestMapping("/hello")
    public String sayHello() {
        System.out.println(title);
        return title;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Fournisseur> createFournisseur(@RequestBody Fournisseur fournisseur) {
        return new ResponseEntity<>(fournisseurService.addFournisseur(fournisseur), HttpStatus.OK);
    }

    @PutMapping( "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Fournisseur> updateFournisseur(@PathVariable(value = "id") Long id,
                                                         @RequestBody Fournisseur fournisseur) {
        return new ResponseEntity<>(fournisseurService.updateFournisseur(id, fournisseur), HttpStatus.OK);
    }

    @DeleteMapping( "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> deleteFournisseur(@PathVariable(value = "id") Long id) {
        return new ResponseEntity<>(fournisseurService.deleteFournisseur(id), HttpStatus.OK);
    }

}
