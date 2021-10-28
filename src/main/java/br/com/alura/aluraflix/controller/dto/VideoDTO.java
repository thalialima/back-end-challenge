package br.com.alura.aluraflix.controller.dto;

import java.util.ArrayList;
import java.util.List;

import br.com.alura.aluraflix.model.Category;
import br.com.alura.aluraflix.model.Video;
import org.springframework.data.domain.Page;

public class VideoDTO {
	private Long id;
	private String name;
	private String description;
	private String url;
	private String categoryTitle;

	public VideoDTO(Video video) {
		this.id = video.getId();
		this.name = video.getName();
		this.description = video.getDescription();
		this.url = video.getUrl();
		this.categoryTitle = video.getCategoryTitle();
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

	public String getCategoryTitle() {
		return categoryTitle;
	}

	public String getDescription() {
		return description;
	}

	public String getUrl() {
		return url;
	}


	public static List<VideoDTO> toVideosDTO(List<Video> videos) {
		List<VideoDTO> videosDTO = new ArrayList<VideoDTO>();
		videos.forEach(v -> videosDTO.add(new VideoDTO(v)));
		return videosDTO;
	}

	public static Page<VideoDTO> toVideosDTOPage(Page<Video> videos) {
		return videos.map(VideoDTO::new);
	}
}
