package br.com.alura.aluraflix.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.alura.aluraflix.repository.CategoryRepository;

@Entity
@Table(name = "videos")
public class Video {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String description;
	private String url;

	@ManyToOne
	private Category category;

	public Video() {

	}

	public Video(String name, String description, String url, Category category) {
		this.name = name;
		this.description = description;
		this.url = url;
		this.category = category;
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

	public String getCategoryTitle(){
		return this.category.getTitle();
	}

	public Long getId() {
		return id;
	}

	public Category getCategory() {
		return category;
	}

}
