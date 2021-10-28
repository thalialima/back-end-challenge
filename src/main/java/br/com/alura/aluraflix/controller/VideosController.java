package br.com.alura.aluraflix.controller;

import br.com.alura.aluraflix.controller.dto.VideoDTO;
import br.com.alura.aluraflix.controller.form.VideoForm;
import br.com.alura.aluraflix.model.Video;
import br.com.alura.aluraflix.repository.CategoryRepository;
import br.com.alura.aluraflix.repository.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/videos")
public class VideosController {

	@Autowired
	private VideoRepository videoRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;

	@GetMapping
	public Page<VideoDTO> showVideos(@RequestParam int page) {

		Pageable pageable = PageRequest.of(page, 5);

		Page<Video> videos = videoRepository.findAll(pageable);
		return VideoDTO.toVideosDTOPage(videos);
	}

	@GetMapping("/{id}")
	public ResponseEntity<VideoDTO> details(@PathVariable Long id) {
		Optional<Video> optional = videoRepository.findById(id);

		// fail fast
		if (!optional.isPresent())
			return ResponseEntity.notFound().build();

		return ResponseEntity.ok(new VideoDTO(optional.get()));
	}

	@GetMapping("/search")
	public ResponseEntity<VideoDTO> detailsByName(String name) {
		Optional<Video> optional = videoRepository.findByName(name);

		// fail fast
		if (!optional.isPresent())
			return ResponseEntity.notFound().build();

		return ResponseEntity.ok(new VideoDTO(optional.get()));
	}

	@PostMapping
	@Transactional
	public ResponseEntity<VideoDTO> register(@RequestBody @Valid VideoForm form, UriComponentsBuilder urBuilder) {
		Video video = form.toVideo(categoryRepository);
		videoRepository.save(video);

		URI uri = urBuilder.path("/videos/{id}").buildAndExpand(video.getId()).toUri();
		return ResponseEntity.created(uri).body(new VideoDTO(video));

	}

	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<VideoDTO> update(@PathVariable Long id, @RequestBody @Valid VideoForm videoForm) {

		Optional<Video> optional = videoRepository.findById(id);

		if (optional.isPresent()) {
			Video video = videoForm.update(id, videoRepository, categoryRepository);
			return ResponseEntity.ok(new VideoDTO(video));
		}

		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> delete(@PathVariable Long id) {

		Optional<Video> optional = videoRepository.findById(id);
		if (optional.isPresent()) {
			videoRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}

		return ResponseEntity.notFound().build();
	}
	
	

}
