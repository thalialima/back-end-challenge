package br.com.alura.aluraflix.controller;

import java.net.URI;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

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
	
	@PostMapping
	@Transactional
	public ResponseEntity<VideoDTO> 
	register(@RequestBody VideoForm form, UriComponentsBuilder urBuilder) {
		Video video = form.toVideo(videoRepository);
		videoRepository.save(video);
		
		URI uri = urBuilder.path("/videos/{id}").buildAndExpand(video.getId()).toUri();
		
		return ResponseEntity.created(uri).body(new VideoDTO(video));
		
	}
	
}
