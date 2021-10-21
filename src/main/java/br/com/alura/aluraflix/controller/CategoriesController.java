package br.com.alura.aluraflix.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.alura.aluraflix.controller.dto.CategoryDTO;
import br.com.alura.aluraflix.controller.form.CategoryForm;
import br.com.alura.aluraflix.model.Category;
import br.com.alura.aluraflix.repository.CategoryRepository;

@RestController
@RequestMapping("/categories")
public class CategoriesController {

	@Autowired
	private CategoryRepository categoryRepository;

	@GetMapping("/")
	public List<CategoryDTO> showCategories() {
		List<Category> categories = categoryRepository.findAll();
		return CategoryDTO.toCategoriesDTO(categories);
	}

	@GetMapping("/{id}")
	public ResponseEntity<CategoryDTO> details(@PathVariable Long id) {
		Optional<Category> optional = categoryRepository.findById(id);

		// fail fast
		if (!optional.isPresent())
			return ResponseEntity.notFound().build();

		return ResponseEntity.ok().body(new CategoryDTO(optional.get()));
	}

	@PostMapping
	@Transactional
	public ResponseEntity<CategoryDTO> register(@RequestBody @Valid CategoryForm categoryForm,
			UriComponentsBuilder uriBuilder) {

		Category category = categoryForm.toCategory();
		categoryRepository.save(category);

		URI uri = uriBuilder.path("/categories/{id}").buildAndExpand(category.getId()).toUri();

		return ResponseEntity.created(uri).body(new CategoryDTO(category));
	}

	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<CategoryDTO> update(@PathVariable Long id, @RequestBody @Valid CategoryForm categoryForm) {

		Optional<Category> optional = categoryRepository.findById(id);

		if (!optional.isPresent())
			return ResponseEntity.notFound().build();

		Category category =categoryForm.update(id, categoryRepository);
		return ResponseEntity.ok().body(new CategoryDTO(category));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id){
		Optional<Category> optional = categoryRepository.findById(id);
		
		if(!optional.isPresent())
			return ResponseEntity.notFound().build();
		
		categoryRepository.deleteById(id);
		return ResponseEntity.ok().build();
	}
	
	
	
	
	
	
	
	

}
