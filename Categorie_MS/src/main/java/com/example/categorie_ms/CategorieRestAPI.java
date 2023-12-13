package com.example.categorie_ms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/Categories")
public class CategorieRestAPI {
	@Autowired
	CategorieRepository repository;
	
	@GetMapping
	public List<Categorie> getAllCategories(){
		System.out.println("Get All Categories ....");
		
		List<Categorie> categories = new ArrayList<Categorie>();
		repository.findAll().forEach(categories :: add);
		return categories;
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Categorie> getCategorieById(@PathVariable(value = "id") int categorieId)
	throws ResourceNotFoundException {
		Categorie categorie = repository.findById(categorieId)
				.orElseThrow(() -> new ResourceNotFoundException("Categorie not found for this is ::"+ categorieId));
		return ResponseEntity.ok().body(categorie);
	}
	
	@PostMapping
	public Categorie createCategorie(@RequestBody Categorie categorie){
		return repository.save(categorie);
	} 
	
	@DeleteMapping("/{id}")
	public Map<String, Boolean> deleteCategorie(@PathVariable(value = "id") int categorieId)
		throws ResourceNotFoundException{
		Categorie categorie = repository.findById(categorieId)
				.orElseThrow(() -> new ResourceNotFoundException("Artocle not fond id ::"+categorieId));
		repository.delete(categorie);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
	
	@DeleteMapping
	public ResponseEntity<String> deleteAllCategories(){
		System.out.println("Delete All Categories");
		repository.deleteAll();
		return new ResponseEntity<>("All Categories have been deleted!", HttpStatus.OK);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Categorie> updateCategorie(@PathVariable("id") int id,
			@RequestBody Categorie Categorie){
		System.out.println("update Categorie with Id =" + id + " .....");
		
		Optional<Categorie> categorieInfo = repository.findById(id);
		
		if(categorieInfo.isPresent()){
			Categorie categorie = categorieInfo.get();
			categorie.setCode(categorie.getCode());
			categorie.setLibelle(categorie.getLibelle());
		
			
			return new ResponseEntity<>(repository.save(categorie), HttpStatus.OK);	
	
	}else {
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
}
}
