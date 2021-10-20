package br.com.alura.aluraflix.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.alura.aluraflix.config.validation.FormErrorDTO;
import br.com.alura.aluraflix.controller.dto.VideoDTO;
import br.com.alura.aluraflix.controller.form.VideoForm;
import br.com.alura.aluraflix.modelo.Video;
import br.com.alura.aluraflix.repository.VideoRepository;

@RestController
@RequestMapping("/videos")
public class VideosController {
	
	@Autowired
	private VideoRepository videoRepository;

	
	@GetMapping
	public List<VideoDTO> showVideos() {
		List<Video> videos = videoRepository.findAll();
		return VideoDTO.toVideosDTO(videos);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<VideoDTO> details(@PathVariable Long id){
		Optional<Video> video = videoRepository.findById(id);
		
		//fail fast
		if(!video.isPresent())
			return ResponseEntity.notFound().build();
		
		return ResponseEntity.ok(new VideoDTO(video.get()));
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<VideoDTO> 
	register(@RequestBody @Valid VideoForm form,
			UriComponentsBuilder urBuilder) {
		
		Video video = form.toVideo();
		videoRepository.save(video);
		
		URI uri = urBuilder.path("/videos/{id}").buildAndExpand(video.getId()).toUri();
		return ResponseEntity.created(uri).body(new VideoDTO(video));
		
	}

	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<VideoDTO> update(@PathVariable Long id, 
			@RequestBody @Valid VideoForm videoForm){
		
		Optional<Video> optional = videoRepository.findById(id);
		
		if(optional.isPresent()) {
			Video video = videoRepository.save(videoForm.update(id, videoRepository));
			return ResponseEntity.ok(new VideoDTO(video));
		}

		return ResponseEntity.notFound().build();
		
	}
	
	
	
	
	
}
