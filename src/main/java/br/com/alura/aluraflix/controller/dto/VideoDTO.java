package br.com.alura.aluraflix.controller.dto;

import java.util.ArrayList;
import java.util.List;

import br.com.alura.aluraflix.modelo.Video;

public class VideoDTO {
	private Long id;
	private String name;
	private String description;
	private String url;

	public VideoDTO(Video video) {
		this.id = video.getId();
		this.name = video.getName();
		this.description = video.getDescription();
		this.url = video.getUrl();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public static List<VideoDTO> toVideosDTO(List<Video> videos) {
		List<VideoDTO> videosDTO = new ArrayList<VideoDTO>();
		videos.forEach(v -> videosDTO.add(new VideoDTO(v)));
		return videosDTO;
	}
}
