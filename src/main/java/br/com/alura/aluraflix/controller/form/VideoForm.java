package br.com.alura.aluraflix.controller.form;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import br.com.alura.aluraflix.model.Video;
import br.com.alura.aluraflix.model.Category;
import br.com.alura.aluraflix.repository.CategoryRepository;
import br.com.alura.aluraflix.repository.VideoRepository;

public class VideoForm {

    @NotNull
    @NotBlank
    @NotEmpty
    @Length(max = 50)
    private String name;

    @NotNull
    @NotBlank
    @NotEmpty
    @Length(max = 120)
    private String description;

    @NotNull
    @NotBlank
    @NotEmpty
    @Length(min = 10)
    @URL
    private String url;

    private String categoryTitle;

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCategoryTitle() {
        return categoryTitle;
    }

    public Video toVideo(CategoryRepository categoryRepository) {
        String title = verifyCategory(categoryTitle);
        Category category = categoryRepository.findByTitle(title);
		return new Video(this.name, this.description, this.url, category);
	}

    private String verifyCategory(String categoryTitle){
        if (categoryTitle.isBlank() || categoryTitle == null)
            return "LIVRE";

        return categoryTitle;
    }
	
	@SuppressWarnings("deprecation")
	public Video update(Long id, VideoRepository videoRepository, CategoryRepository categoryRepository) {
		Video video = videoRepository.getOne(id);
        Category category = categoryRepository.findByTitle(this.categoryTitle);
		video.setName(this.name);
		video.setDescription(this.description);
		video.setUrl(this.url);
        video.setCategory(category);
		return video;
	}

}
