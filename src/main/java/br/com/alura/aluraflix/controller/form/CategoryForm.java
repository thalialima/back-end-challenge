package br.com.alura.aluraflix.controller.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import br.com.alura.aluraflix.modelo.Category;

public class CategoryForm {

	@NotNull
	@NotBlank
	@NotEmpty
	@Length(max = 30)
	private String title;
	@NotNull
	@NotBlank
	@NotEmpty
	@Length(max = 30)
	private String color;

	public Category toCategory() {
		return new Category(this.title, this.color);
	}

	public String getTitle() {
		return title;
	}

	public String getColor() {
		return color;
	}
	
	
}
