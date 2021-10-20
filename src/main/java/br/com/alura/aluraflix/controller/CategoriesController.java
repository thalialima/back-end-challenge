package br.com.alura.aluraflix.controller;

import java.net.URI;
import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.alura.aluraflix.controller.dto.CategoryDTO;
import br.com.alura.aluraflix.controller.form.CategoryForm;
import br.com.alura.aluraflix.modelo.Category;
import br.com.alura.aluraflix.repository.CategoryRepository;

@RestController
@RequestMapping("/categories")
public class CategoriesController {

	@Autowired
	private CategoryRepository categoryRepository;
	
	
	@GetMapping("/")
	public List<CategoryDTO> showCategories(){
		List<Category> categories = categoryRepository.findAll();
		return CategoryDTO.toCategoriesDTO(categories);
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<CategoryDTO> register(@RequestBody @Valid CategoryForm categoryForm, 
			UriComponentsBuilder uriBuilder){
		
		Category category = categoryForm.toCategory();
		categoryRepository.save(category);
		
		URI uri = uriBuilder.path("/categories/{id}").buildAndExpand(category.getId()).toUri();
		
		return ResponseEntity.created(uri).body(new CategoryDTO(category));
	}
}
