package com.example.clientms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/clients")
public class ClientRestAPI {
    @Autowired
    ClientRepository repository;

    @GetMapping
    public List<Client> getAllClients(){
        System.out.println("Get All Clients ....");

        List<Client> Clients = new ArrayList<Client>();
        repository.findAll().forEach(Clients :: add);
        return Clients;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Client> getClientById(@PathVariable(value = "id") int ClientId)
            throws ResourceNotFoundException {
        Client Client = repository.findById(ClientId)
                .orElseThrow(() -> new ResourceNotFoundException("Client not found"));
        return ResponseEntity.ok().body(Client);
    }

    @PostMapping
    public Client createClient( @RequestBody Client Client){
        return repository.save(Client);
    }

    @DeleteMapping("/{id}")
    public Map<String, Boolean> deleteClient(@PathVariable(value = "id") int ClientId)
            throws ResourceNotFoundException{
        Client Client = repository.findById(ClientId)
                .orElseThrow(() -> new ResourceNotFoundException("Artocle not delete"));
        repository.delete(Client);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

    @DeleteMapping
    public ResponseEntity<String> deleteAllClients(){
        System.out.println("Delete All Clients");
        repository.deleteAll();
        return new ResponseEntity<>("All clients have been deleted!", HttpStatus.OK);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Client> updateClient(@PathVariable("id") int id,
                                               @RequestBody Client Client) {
        System.out.println("update Client with Id =" + id + " .....");

        Optional<Client> ClientInfo = repository.findById(id);

        if (ClientInfo.isPresent()) {
            Client client = ClientInfo.get();
            client.setCode(Client.getCode());
            client.setLibelle(Client.getLibelle());
            client.setAdresse(Client.getAdresse());
            client.setContact(Client.getContact());
            client.setEmail(Client.getEmail());
            client.setLogin(Client.getLogin());
            client.setMatfisc(Client.getMatfisc());
            client.setPwd(Client.getPwd());
            client.setTimbre(Client.getTimbre());
            client.setTel(Client.getTel());
            client.setSoldeinit(Client.getSoldeinit());
            client.setSolde(Client.getSolde());

            return new ResponseEntity<>(repository.save(Client), HttpStatus.OK);

        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
