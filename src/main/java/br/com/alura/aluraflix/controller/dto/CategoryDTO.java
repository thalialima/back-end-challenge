package br.com.alura.aluraflix.controller.dto;

import java.util.ArrayList;
import java.util.List;

import br.com.alura.aluraflix.model.Category;
import org.springframework.data.domain.Page;

public class CategoryDTO {

	
	private Long id;
	private String title;
	private String color;

	public CategoryDTO(Category category) {
		this.id = category.getId();
		this.title = category.getTitle();
		this.color = category.getColor();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public static List<CategoryDTO> toCategoriesDTO(List<Category> categories) {

		List<CategoryDTO> categoriesDTO = new ArrayList<CategoryDTO>();
		categories.forEach(c -> categoriesDTO.add(new CategoryDTO(c)));
		return categoriesDTO;
	}

	public static Page<CategoryDTO> toCategoriesDTOPage(Page<Category> categories) {
		return categories.map(CategoryDTO::new);
	}
}
