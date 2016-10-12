package com.bam.GESTIBANKBAM.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.bam.GESTIBANKBAM.model.Client;
import com.bam.GESTIBANKBAM.model.Personne;
import com.bam.GESTIBANKBAM.service.ClientService;
import com.bam.GESTIBANKBAM.service.EmployeService;
import com.bam.GESTIBANKBAM.service.PersonneService;

@RestController
public class GestiBankBAMRestControler {
 
    @Autowired
    ClientService clientService;  //Service which will do all data retrieval/manipulation work
 
    @Autowired
    PersonneService personneService;  //Service which will do all data retrieval/manipulation work
    
    //@Autowired
    //ConseillerService conseillerService;  //Service which will do all data retrieval/manipulation work

    //@Autowired
    //AdminService adminService;  //Service which will do all data retrieval/manipulation work

    @Autowired
    EmployeService employeService;  //Service which will do all data retrieval/manipulation work

    //-------------------Retrieve All Clients--------------------------------------------------------
     
    @RequestMapping(value = "/client/", method = RequestMethod.GET)
    public ResponseEntity<List<Client>> listAllClients() {
        List<Client> clients = clientService.findAllClients();
        if(clients.isEmpty()){
            return new ResponseEntity<List<Client>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        
        return new ResponseEntity<List<Client>>(clients, HttpStatus.OK);
    }

//    //-------------------Retrieve All New Clients--------------------------------------------------------
//    
//    @RequestMapping(value = "/client/news/", method = RequestMethod.GET)
//    public ResponseEntity<List<Client>> listAllNewClients() {
//        List<Client> clients = clientService.findAllNewClients();
//        if(clients.isEmpty()){
//            return new ResponseEntity<List<Client>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
//        }
//        
//        return new ResponseEntity<List<Client>>(clients, HttpStatus.OK);
//    }
 
    
    //-------------------Retrieve Single Client--------------------------------------------------------
     
    @RequestMapping(value = "/client/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Client> getClient(@PathVariable("id") String id) {
        System.out.println("Fetching User with id " + id);
        Client client = clientService.findById(id);
        if (client == null) {
            System.out.println("Client with id " + id + " not found");
            return new ResponseEntity<Client>(HttpStatus.NOT_FOUND);
        }
        System.out.println("Client founded :"+client.toString());
        return new ResponseEntity<Client>(client, HttpStatus.OK);
    }
 
     
     
    //-------------------Create a Client--------------------------------------------------------
     
    @RequestMapping(value = "/client/", method = RequestMethod.POST)
    public ResponseEntity<Void> createClient(@RequestBody Client client,    UriComponentsBuilder ucBuilder) {
        System.out.println("Creating Client " + client.getNom() + " - " + client.getPrenom());
 
        if (clientService.isClientExist(client)) {
            System.out.println("A Client with name " + client.getNom() + " " + client.getPrenom() + " already exist");
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
 
        clientService.saveClient(client);
 
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/client/{id}").buildAndExpand(client.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }
 
    
     
    //------------------- Update a Client --------------------------------------------------------
     
    @RequestMapping(value = "/client/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Client> updateClient(@PathVariable("id") String id, @RequestBody Client client) {
        System.out.println("Updating User " + id);
         
        Client currentClient = clientService.findById(id);
         
        if (currentClient==null) {
            System.out.println("Client with id " + id + " not found");
            return new ResponseEntity<Client>(HttpStatus.NOT_FOUND);
        }
 
        currentClient.setCivilite(client.getCivilite());
        currentClient.setNom(client.getNom());
        currentClient.setPrenom(client.getPrenom());
        currentClient.setDdn(client.getDdn());
        currentClient.setHashMdp(client.getHashMdp());
        currentClient.setAdresse(client.getAdresse());
         
        clientService.updateClient(currentClient);
        return new ResponseEntity<Client>(currentClient, HttpStatus.OK);
    }
 
    
    
    //------------------- Delete a Client --------------------------------------------------------
     
    @RequestMapping(value = "/client/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Client> deleteClient(@PathVariable("id") String id) {
        System.out.println("Fetching & Deleting Client with id " + id);
 
        Client client = clientService.findById(id);
        if (client == null) {
            System.out.println("Unable to delete. Client with id " + id + " not found");
            return new ResponseEntity<Client>(HttpStatus.NOT_FOUND);
        }
 
        clientService.deleteClientById(id);
        return new ResponseEntity<Client>(HttpStatus.NO_CONTENT);
    }
 
     
    
    //------------------- Delete All Clients --------------------------------------------------------
     
    @RequestMapping(value = "/client/", method = RequestMethod.DELETE)
    public ResponseEntity<Client> deleteAllClients() {
        System.out.println("Deleting All Clients");
 
        clientService.deleteAllClients();
        return new ResponseEntity<Client>(HttpStatus.NO_CONTENT);
    }
  //-------------------Retrieve All Personnes--------------------------------------------------------
    
    @RequestMapping(value = "/personne/", method = RequestMethod.GET)
    public ResponseEntity<List<Personne>> listAllPersonnes() {
        List<Personne> personnes = personneService.findAllPersonnes();
        if(personnes.isEmpty()){
            return new ResponseEntity<List<Personne>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<Personne>>(personnes, HttpStatus.OK);
    }
 //----------------------------------Authenticate Personne---------------------------------------
    
    @RequestMapping(value = "/personne/id-{id}-mdp-{mdp}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
      public ResponseEntity<Personne> getPersonne(@PathVariable("id") String id, @PathVariable("mdp") String mdp) {
        System.out.println("Fetching personne with id"+" "+ id);
        Personne pers = personneService.findById(id);
        if (pers == null) {
            System.out.println("Client with id " + id + " not found");
            return new ResponseEntity<Personne>(HttpStatus.NOT_FOUND);
        }
        else {
        	System.out.println("Bienvenue chez BamBank");
        }
        if (pers.getHashMdp().equals(mdp)){
        	return new ResponseEntity<Personne>(pers, HttpStatus.OK);        	
        }
        else {
        	System.out.println("The mdp " + mdp +" "+ "is invalid");
        	return new ResponseEntity<Personne>(HttpStatus.NOT_FOUND);
        }
    }
     
 
}