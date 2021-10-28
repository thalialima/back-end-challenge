package br.com.alura.aluraflix.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import br.com.alura.aluraflix.controller.dto.VideoDTO;
import br.com.alura.aluraflix.model.Video;
import br.com.alura.aluraflix.repository.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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

	@Autowired
	private VideoRepository videoRepository;

	@GetMapping("/")
	public Page<CategoryDTO> showCategories(@RequestParam int page) {
		Pageable pageable = PageRequest.of(page, 5);
		Page<Category> categories = categoryRepository.findAll(pageable);
		return CategoryDTO.toCategoriesDTOPage(categories);
	}

	@GetMapping("/{id}")
	public ResponseEntity<CategoryDTO> details(@PathVariable Long id) {
		Optional<Category> optional = categoryRepository.findById(id);

		// fail fast
		if (!optional.isPresent())
			return ResponseEntity.notFound().build();

		return ResponseEntity.ok().body(new CategoryDTO(optional.get()));
	}

	//requisição GET para exibir vídeos por categoria
	//i have no idea how to do this.
	@GetMapping("/{id}/videos/")
	public ResponseEntity<List<VideoDTO>> detailsByCategory(@PathVariable("id") Long id) {
		Optional<Category> optional = categoryRepository.findById(id);

		// fail fast
		if (!optional.isPresent()) {
			return ResponseEntity.notFound().build();
		}

		List<Video> videosByCategory = videoRepository.findByCategoryId(id);
		List<VideoDTO> videos = VideoDTO.toVideosDTO(videosByCategory);
		return ResponseEntity.ok().body(videos);
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

		Category category = categoryForm.update(id, categoryRepository);
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
