package br.com.alura.aluraflix.controller.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import br.com.alura.aluraflix.modelo.Video;
import br.com.alura.aluraflix.repository.VideoRepository;

public class VideoForm {

	@NotNull @NotBlank @NotEmpty
	@Length(max = 50)
	private String name;
	
	@NotNull @NotBlank @NotEmpty
	@Length(max = 120)
	private String description;
	
	@NotNull @NotBlank @NotEmpty
	@Length(min = 10) @URL
	private String url;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Video toVideo() {
		return new Video(this.name, this.description, this.url);
	}
	
	@SuppressWarnings("deprecation")
	public Video update(Long id, VideoRepository videoRepository) {
		Video video = videoRepository.getOne(id);
		video.setName(this.name);
		video.setDescription(this.description);
		video.setUrl(this.url);
		return video;
	}

}
