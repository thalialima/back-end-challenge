package br.com.alura.aluraflix.controller.form;

import br.com.alura.aluraflix.modelo.Video;
import br.com.alura.aluraflix.repository.VideoRepository;

public class VideoForm {

	private String name;
	private String description;
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

	public Video toVideo(VideoRepository videoRepository) {
		return new Video(this.name, this.description, this.url);
	}

}
