package com.esprit.microservices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FournisseurService {

    @Autowired
    private FournisseurRepository fournisseurRepository;

    public Fournisseur addFournisseur(Fournisseur Fournisseur) {
        return fournisseurRepository.save(Fournisseur);
    }
    public Fournisseur updateFournisseur(Long id, Fournisseur newFournisseur) {
        if (fournisseurRepository.findById(id).isPresent()) {
            Fournisseur existingFournisseur = fournisseurRepository.findById(id).get();
            existingFournisseur.setNom(newFournisseur.getNom());
            existingFournisseur.setPrenom(newFournisseur.getPrenom());
            existingFournisseur.setAdresse(newFournisseur.getAdresse());
            existingFournisseur.setMail(newFournisseur.getMail());
            return fournisseurRepository.save(existingFournisseur);
        } else
            return null;
    }
    public String deleteFournisseur(Long id) {
        if (fournisseurRepository.findById(id).isPresent()) {
            fournisseurRepository.deleteById(id);
            return "Fournisseur supprimé";
        } else
            return "Fournisseur non supprimé";
    }


}

